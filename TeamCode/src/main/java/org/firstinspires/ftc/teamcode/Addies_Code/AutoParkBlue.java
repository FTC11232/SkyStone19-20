package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoParkBlue") //AutoMouse
//@Disabled
public class AutoParkBlue extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    AddieHardwareFile newHwFile = new AddieHardwareFile();

    @Override
    public void runOpMode() throws InterruptedException {

        newHwFile.mapHardware(hardwareMap);

        waitForStart();

        boolean firstCall = true;

        while (opModeIsActive() && firstCall) {
            // CLIP TIME VALUES BASED ON TRIAL AND ERROR



            timedCommand("WAIT", 0, 25);
            timedCommand("FORWARD", 0,0.5);
            timedCommand("RIGHT", 0, 2);
            timedCommand("WAIT", 0, 5);


            firstCall = false;

        }

    }

    public void timedCommand(String CommandName, double speed, double time) {

        while (runtime.seconds() <= time && opModeIsActive()) {
            newHwFile.runCommand(CommandName, speed);
        }
        /*
            if Boolean fristcall
        }  */

    }

}

