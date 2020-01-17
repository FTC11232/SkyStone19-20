package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

@Autonomous(name = "PAY ATTENTION") //AutoMouse

public class AutoRedFoundation extends LinearOpMode {

    private ElapsedTime runtime = new ElapsedTime();
    AddieHardwareFile newHwFile = new AddieHardwareFile();

    @Override
    public void runOpMode() throws InterruptedException {

        newHwFile.mapHardware(hardwareMap);

        waitForStart();

        boolean firstCall = true;

        while (opModeIsActive() && firstCall) {
            // CLIP TIME VALUES BASED ON TRIAL AND ERROR


            timedCommand("FORWARD", 1, 3.9);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LIFTUP", 0.5, 0.3);
            timedCommand("RIGHT", 1, 2.7);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("FORWARD", 1, 0.7);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LIFTDOWN", 0.1, 0.3);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("BACKWARD", 1, 3);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LIFTUP", .67,0.2);
            timedCommand("WAIT", 0, 0.5);
            timedCommand("LEFT", 1, 2.5);
            timedCommand("WAIT", 0, 0.5);

            firstCall = false;


        }

    }

    public void timedCommand(String CommandName, double speed, double time) {

        while (runtime.seconds() <= time && opModeIsActive()) {
            newHwFile.runCommand(CommandName, speed);
        }
        runtime.reset();
        /*
            if Boolean firstcall
        }  */

    }

}