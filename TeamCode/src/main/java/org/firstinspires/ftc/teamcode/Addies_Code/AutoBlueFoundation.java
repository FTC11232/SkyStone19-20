package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous(name = "AutoBlueFoundation") //AutoMouse

public class AutoBlueFoundation extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    AddieHardwareFile newHwFile = new AddieHardwareFile();

    @Override
    public void runOpMode() throws InterruptedException {

        newHwFile.mapHardware(hardwareMap);

        waitForStart();

        boolean firstCall = true;

        while (opModeIsActive() && firstCall) {
            // CLIP TIME VALUES BASED ON TRIAL AND ERROR


            timedCommand("FORWARD", 1, 1);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LEFT", 1, 2.5);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LIFTUP", 1, 0.3);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("FORWARD", 1, 0.2);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LIFTDOWN", 1, 0.3);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("BACKWARD", 1, 1.2);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LIFTUP", 1,0.2);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("RIGHT", 1, 2.5);
            timedCommand("WAIT", 0, 0.5);


            firstCall = false;

        }

    }

    public void timedCommand(String CommandName, double speed, double time) {

        while (runtime.seconds() <= time && opModeIsActive()) {
            newHwFile.runCommand(CommandName, speed);
        }
        /*
            if Boolean firstcall
        }  */

    }

}