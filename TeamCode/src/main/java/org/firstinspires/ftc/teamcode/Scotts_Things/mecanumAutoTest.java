package org.firstinspires.ftc.teamcode.Scotts_Things;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import static org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019.*;

@Autonomous(name = "Mecanum Autonomous Test")
@Disabled
public class mecanumAutoTest extends LinearOpMode{

    private ElapsedTime runtime = new ElapsedTime();

    HardwareFile2019 robot = new HardwareFile2019();



    @Override
    public void runOpMode() throws InterruptedException {

        robot.mapHardware(hardwareMap);

        waitForStart();

        boolean firstCall = true;

        while (opModeIsActive()) {
            if (firstCall) {
                robot.timedMove(robot.forwardD, runtime, 10);
                robot.operation(robot.lift, runtime, 1, 1);

                firstCall = false;
            }


        }

    }

}
