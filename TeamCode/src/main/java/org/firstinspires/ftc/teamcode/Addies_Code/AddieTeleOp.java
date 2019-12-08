package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019;
/*This important statement replaces all the static ones you had previously
Adding a .* imports everything it can from inside the file, meaning you only need
to import the file, and then everything inside of it once.
*/
import static org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019.*;

//If you can, I'd recommend starting your own hardware file now
//Your TeleOp is fairly well developed, and it could use the individualization
//Of your own hardware file now.  For testing, just add your new code to
//TeleOpMecanum, I trust you to edit it.  It's enabled so it will show up on the phone.
//Definitely start on your hardware file now, and do things differently and uniquely
//For instance, the encoder functions I use really aren't necessary yet.
//Look into what drive mode you should set the motors two and create your own function.

//If dogs fly, why am I not flying? -Victor 2019 #Victor4Pres

@TeleOp(name = "Addie's OpTele")
public class AddieTeleOp extends LinearOpMode {

    double frontR, frontL, backR, backL, lift, clamp;
    double gamepadX, gamepadY, gamepadZ, gamepadLift, gamepadClamp;

    boolean running = false;

    HardwareFile2019 robot = new HardwareFile2019();

    @Override
    public void runOpMode() throws InterruptedException {

        robot.mapHardware(hardwareMap);
        telemetry.addData("Welcome Drivers,", "Hardware Mapping Complete");
        telemetry.update();

        waitForStart();

        while (!running){

            if(opModeIsActive()){running = true;}

        }

        while (running){
            while (opModeIsActive()) ;
            {

                telemetry.clearAll();

                gamepadX = robot.deadZone(gamepad1.left_stick_y, 0.2);
                gamepadY = robot.deadZone(gamepad1.left_stick_x, 0.2);
                gamepadZ = robot.deadZone(robot.zManipulation(gamepad1.left_trigger, gamepad1.right_trigger),
                        0.2);
                gamepadLift = robot.deadZone(gamepad2.left_stick_y, 0.2);
                gamepadClamp = robot.deadZone(gamepad2.right_stick_x, 0.2);


                frontL = -(-gamepadX + gamepadY + gamepadZ);
                frontR = -(gamepadX + gamepadY + gamepadZ);
                backL = -(gamepadX + gamepadY - gamepadZ); //these are all the correct drive equations
                backR = -(-gamepadX + gamepadY - gamepadZ);
                lift = gamepadLift;
                clamp = gamepadClamp;

                telemetry.addData("Lift Values", "Lift" + gamepadLift + "||" + "Clamp" + gamepadClamp);
                telemetry.addData("GamePad Values", "Left Stick X" + gamepadX + "||" + "Left Stick X" + gamepadY + "||" + "Rotation Z" + gamepadZ);
                telemetry.update();

                frontLeft.setPower(frontL * maxOutput);
                frontRight.setPower(frontR * maxOutput);
                backLeft.setPower(backL * maxOutput);
                backRight.setPower(backR * maxOutput);
                CascadeLift.setPower(lift * maxOutput);
                LinearActuator.setPower(clamp * maxOutput);

            }
        }
    }

    public void getXYZLiftClamp() {
        gamepadX = robot.deadZone(gamepad1.left_stick_y, 0.2);
        gamepadY = robot.deadZone(gamepad1.left_stick_x, 0.2);
        gamepadZ = robot.deadZone(robot.zManipulation(gamepad1.left_trigger, gamepad1.right_trigger),
                0.2);
        gamepadLift = robot.deadZone(gamepad2.left_stick_y, 0.2);
        gamepadClamp = robot.deadZone(gamepad2.right_stick_x, 0.2);
    }
}


