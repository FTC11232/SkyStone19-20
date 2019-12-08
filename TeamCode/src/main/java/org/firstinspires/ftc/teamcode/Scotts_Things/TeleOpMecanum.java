package org.firstinspires.ftc.teamcode.Scotts_Things;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import static org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019.*;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@TeleOp(name = "Mecanum Enc")
public class TeleOpMecanum extends LinearOpMode {

    double frontR, frontL, backR, backL, lift, clamp;
    double magnitude, direction, radians, rotation;
    double testAngle;
    double maxLinear = -1300;
    double minLinear = 425;

    double scaledTelemLin;

    double gamepadX, gamepadY, gamepadZ, gamepadLift, gamepadClamp;
    HardwareFile2019 robot = new HardwareFile2019();

    String mecanumSys = "Cartesian";

    @Override
    public void runOpMode() throws InterruptedException {

        try {
            robot.mapHardware(hardwareMap);
        } catch (IllegalArgumentException e) {
            errorTelemetry(e);
        }

        telemetry.clearAll();
        telemetry.addData("Welcome Driver(s)", "Hardware Mapping Complete");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            telemetry.clearAll();
            String tempMecanum = mecanumSys;
            mecanumSys = mecanumDriveSystem((gamepad1.a && gamepad1.b), (gamepad1.x && gamepad1.y), tempMecanum);

            getXYZLiftClamp();

            switch (mecanumSys) {
                case "Cartesian":
                    frontL = (gamepadX + gamepadY - gamepadZ);
                    frontR = (gamepadX - gamepadY - gamepadZ);
                    backL = (gamepadX - gamepadY + gamepadZ);
                    backR = (gamepadX + gamepadY + gamepadZ);


                    //Correct for standard Cartesian, assuming inverted Right Side.
                    //Have to correct for a funky right and left side, and then a funky left wheel.

                    lift = gamepadLift;
                    clamp = gamepadClamp;
                    break;
                case "Polar":
                    testAngle = Math.atan2(gamepad1.left_stick_x, gamepad1.left_stick_y);
                    direction = robot.ratioScaling(0, 360, testAngle, -180, 180);
                    magnitude = robot.polarEval(gamepadX, gamepadY);
                    radians = (direction + 45.0) * Math.PI / 180.0;
                    double cosD = Math.cos(radians);
                    double sinD = Math.sin(radians);
                    rotation = gamepadZ;

                    frontL = (sinD * magnitude + rotation);
                    frontR = (cosD * magnitude - rotation);
                    backL = (cosD * magnitude + rotation);
                    backR = (sinD * magnitude - rotation);
                    break;
            }
            setMotorPowers();
            if (mecanumSys == "Polar") {
                telemetry.addData("Test Angle:", testAngle);
                telemetry.addData("Direction:", direction);
                telemetry.update();
            } else {
                telemetry.clearAll();
                Telemetry.Item X = telemetry.addData("X Value:", gamepadX);
                Telemetry.Item Y = telemetry.addData("Y Value:", gamepadY);
                Telemetry.Item Z = telemetry.addData("Rotation:", gamepadZ);
                telemetry.addData("Unscaled Encoder", LinearActuator.getCurrentPosition());
                telemetry.addData("Scaled Encoder", scaledTelemLin);
                telemetry.update();
            }
        }
    }

    public void errorTelemetry(IllegalArgumentException e) { //Method to report errors
        String eMessage = e.getMessage().toString();
        telemetry.addData("Something Happened:", "Error Mapping Hardware");
        telemetry.addData("The Error Message", eMessage);
        telemetry.update();
    }

    public void getXYZLiftClamp() {                                        //Apply modifications to
        gamepadX = -robot.deadZone(gamepad1.left_stick_x, 0.2);     //gamepad inputs
        gamepadY = robot.deadZone(gamepad1.left_stick_y, 0.2);
        gamepadZ = robot.deadZone(robot.zManipulation(gamepad1.left_trigger, gamepad1.right_trigger),
                0.2);
        gamepadLift = robot.deadZone(gamepad2.left_stick_y, 0.2);
        gamepadClamp = robot.deadZone(gamepad2.right_stick_x, 0.2);
    }

    public void setMotorPowers() {               //Method to send motors powers * the output
        frontLeft.setPower(frontL * maxOutput);  //Assumes modifications have been completed.
        frontRight.setPower(frontR * maxOutput);
        backLeft.setPower(backL * maxOutput);
        backRight.setPower(backR * maxOutput);
        CascadeLift.setPower(lift * maxOutput);
        LinearActuator.setPower(clamp * maxOutput);
    }

    public String mecanumDriveSystem(boolean cartBool, boolean polBool, String currentSys) { //Method to switch drive systems.
        String mecDriveSys;  //The Drive system that is already being used.

        if (currentSys == "Cartesian" && polBool) { //If we establish a polar input, make sure we're
            //currently cartesian before changing
            mecDriveSys = "Polar";
            return mecDriveSys;
        } else if (currentSys == "Polar" && cartBool) { //If we establish a cartesian input, make sure
            // we're currently polar before changing.
            mecDriveSys = "Cartesian";
            return mecDriveSys;
        } else {
            mecDriveSys = currentSys;                //Otherwise, don't change the system.
            return mecDriveSys;
        }
    }

}
