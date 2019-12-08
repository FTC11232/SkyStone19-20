package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoMecanum Final Blue - Reworked") //AutoMouse
//@Disabled
public class AutonomousBlue2019Rework extends LinearOpMode {

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
                while(runtime.seconds() >= 0 && runtime.seconds() < 20 && opModeIsActive()){newHwFile.motorStop();}
                while(runtime.seconds() >= 20 && runtime.seconds() < 23 && opModeIsActive()){newHwFile.addSequential("Right", 1);}
                //while(runtime.seconds() >= 3.42 & runtime.seconds() < 4.92){newHwFile.addSequential("Right",1);}



                /*
                while(runtime.seconds() >= 3.42 & runtime.seconds() < 6.67 & opModeIsActive()){newHwFile.addSequential("Left",1);}
                while(runtime.seconds() >= 4 & runtime.seconds() < 5 & opModeIsActive()){newHwFile.addSequential("LiftUp",0.5);}
                while(runtime.seconds() >= 5 & runtime.seconds() < 6 & opModeIsActive()){newHwFile.addSequential("LiftDown",0.5);}
                while(runtime.seconds() >= 6 & runtime.seconds() < 9 & opModeIsActive()){newHwFile.addSequential("Back",1);}
                while(runtime.seconds() >= 9 & runtime.seconds() < 10 & opModeIsActive()){newHwFile.addSequential("LiftUp",0.5);}
                while(runtime.seconds() >= 10 & runtime.seconds() < 4 & opModeIsActive()){newHwFile.addSequential("Right",1);}
*/
                newHwFile.motorStop();
                runtime.reset();

            }

                firstCall = false;

            }

        }

    }








