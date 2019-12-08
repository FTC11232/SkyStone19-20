package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Addies_Code.AddieHardwareFile.*;

@Autonomous(name = "Auto Wait Red") //AutoMouse


public class
autoWaitRed extends LinearOpMode{
    private ElapsedTime runtime = new ElapsedTime();
    AddieHardwareFile newHwFile = new AddieHardwareFile();


    @Override
    public void runOpMode() throws InterruptedException {

        newHwFile.mapHardware(hardwareMap);

        waitForStart();

        boolean firstCall = true;


        while (opModeIsActive()){
            if (firstCall){

                while(runtime.seconds()  < 25){
                    sleep(1);
                }

                runtime.reset();

                while ((runtime.seconds() < 3)){
                    newHwFile.strafeRight();
                }

                newHwFile.motorStop();

                firstCall = false;
            }
        }

    }


}
