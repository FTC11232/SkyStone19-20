package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019;

import static org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019.*;


@TeleOp (name = "TeleOp 2019 - Mecanum (Encoder)")
public class AddieRedo extends LinearOpMode {

    double backR, backL, frontR, frontL,lift, clamp;
    double gamepadX, gamepadY, gamepadZ, gamepadLift, gamepadClamp;

    HardwareFile2019 robot  = new HardwareFile2019();

    @Override
    public void runOpMode() {

        robot.mapHardware(hardwareMap);

        telemetry.addData("Welcome Drivers", "Hardware Mapping Complete");
        telemetry.update();

        waitForStart();
        while (opModeIsActive()){

            gamepadX = -robot.deadZone(gamepad1.left_stick_x, 0);     //gamepad inputs
            gamepadY = robot.deadZone(gamepad1.left_stick_y, 0);
            gamepadZ = robot.deadZone(robot.zManipulation(gamepad1.left_trigger, gamepad1.right_trigger), 0.2);
            gamepadLift = robot.deadZone(gamepad2.left_stick_y, 0);
            gamepadClamp = robot.deadZone(gamepad2.right_stick_x, 0);
            frontL = (gamepadX + gamepadY - gamepadZ);
            frontR = (gamepadX - gamepadY - gamepadZ);
            backL = (gamepadX - gamepadY + gamepadZ);
            backR = (gamepadX + gamepadY + gamepadZ);
            lift = robot.liftModification(gamepadLift);
            clamp = gamepadClamp;
            frontLeft.setPower(frontL * maxOutput);
            frontRight.setPower(frontR * maxOutput);
            backLeft.setPower(backL * maxOutput);
            backRight.setPower(backR * maxOutput);
            CascadeLift.setPower(lift * maxOutput);
            LinearActuator.setPower(clamp * maxOutput);
            telemetry.addData("Lift Values", "Lift" + gamepadLift + "||" + "Clamp" + gamepadClamp);
            telemetry.addData("GamePad Values", "Left Stick X" + gamepadX + "||" + "Left Stick X" + gamepadY + "||" + "Rotation Z" + gamepadZ);
            telemetry.addData("Linear Actuator Encoder", LinearActuator.getCurrentPosition());
            telemetry.update();
        }
    }

}
