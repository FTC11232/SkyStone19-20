package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Addies_Code.AddieHardwareFile.*;

@Autonomous(name = "AutoMecanum Final Blue") //AutoMouse
//@Disabled
public class AutonomousBlue2019 extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    AddieHardwareFile newHwFile = new AddieHardwareFile();

    @Override
    public void runOpMode() throws InterruptedException {

        newHwFile.mapHardware(hardwareMap);

        waitForStart();

        boolean firstCall = true;

        while (opModeIsActive()) {
            // CLIP TIME VALUES BASED ON TRIAL AND ERROR
            if (firstCall) {

                while (runtime.seconds() < 3.67 && opModeIsActive()) { //Go Forward
                    newHwFile.forwardMovement();
                }

                newHwFile.motorStop();
                runtime.reset();

               // while (runtime.seconds() < 1 && opModeIsActive()) { //Lift Up
                 //   newHwFile.slideUp();
                //}
                //newHwFile.motorStop();
                //runtime.reset();

                while (runtime.seconds() < 3 && opModeIsActive()){ //Strafe Left
                    newHwFile.strafeLeft();
                }

                /*runtime.reset();
                newHwFile.motorStop();

                while (runtime.seconds() < 1 && opModeIsActive()){ //Lift Down
                    newHwFile.slideDrop();
                }*/

                runtime.reset();
                newHwFile.motorStop();

                while (runtime.seconds() < 1.5 && opModeIsActive()){ //Go Backwards
                    newHwFile.backwardMovement();
                }

                runtime.reset();
                newHwFile.motorStop();

                //while (runtime.seconds() < 0.5 && opModeIsActive()){ //Lift Up
                    //newHwFile.slideUp();
                //}

                //newHwFile.motorStop();
//                runtime.reset();

                while (runtime.seconds() < 1.5 && opModeIsActive()){ //Strafe Right
                    newHwFile.strafeRight();
                }

                newHwFile.motorStop();
                runtime.reset();

                //while (runtime.seconds() < 0.5 && opModeIsActive()){ //Lift Down
                  //  newHwFile.slideDrop();
                //}

                //newHwFile.motorStop();
                //runtime.reset();

                while (runtime.seconds() < 2.0 && opModeIsActive()){ //Strafe Right
                    newHwFile.strafeRight();
                }

                newHwFile.motorStop();
                runtime.reset();

            }

                firstCall = false;

            }

        }

    }








