package org.firstinspires.ftc.teamcode.Scotts_Things;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import static org.firstinspires.ftc.teamcode.Scotts_Things.HardwareFile2019.*;


@TeleOp(name = "Wheel Test")
public class WheelTestingLinOp extends LinearOpMode {

    HardwareFile2019 robot = new HardwareFile2019();

    boolean run = false; //whether or not to run something

    boolean[] schedulingManager = {false, false, false, false}; //Booleans for running motors
    //Position corresponds to position in motorArray.

    boolean readyToRun;
    int selectedID;
    boolean selectionMade;
    boolean selectionConfirmed;
    boolean redoSelection;


    @Override
    public void runOpMode() throws InterruptedException {
        int temp; //Start below array so counting function works.
        try {robot.mapHardware(hardwareMap);}
        catch (IllegalArgumentException e){
            errorTelemetry(e);
        }

        sleep(1000);
        waitForStart();

        while (opModeIsActive()) {
            readyToRun = false;
            selectionMade = false;
            selectionConfirmed = false;
            selectedID = -1;
            redoSelection = false;      //Reset listeners.
            while (!selectionMade) {
                addToSchedule(); //Listen for buttons scheduling motors.
                sleep(1);
            }

            while (!selectionConfirmed && !redoSelection) {
                displaySelection();
            }
            if (selectionConfirmed && !redoSelection) { //Display the Selection

                sleep(5000);
            }
            while (!run && !redoSelection) { //Wait for a command to run.
                setRun();
            }
            if (run && !redoSelection){  //Ensure no duplicates and run.
                triggerMotor();
            }
            while (run && !redoSelection){ //Wait to be told to stop.
                stopDetection();
            }

        }


    }

    public void errorTelemetry(IllegalArgumentException e) { //Method to report errors
        String eMessage = e.getMessage().toString();
        telemetry.addData("Something Happened:", "Error Mapping Hardware");
        telemetry.addData("The Error Message", eMessage);
        telemetry.update();
    }

    public void addToSchedule() {
        selectedID = -1;
        while (!selectionMade) {
            if (gamepad1.a) { //A schedules FrontLeft

                telemetry.update();
                selectionMade = true;
                selectedID = iFR;
                break;
            }
            if (gamepad1.b) { //B Schedules FrontRight
;
                telemetry.update();
                selectionMade = true;
                selectedID = iFL;
                break;
            }
            if (gamepad1.x) { //X Schedules BackLeft

                telemetry.update();
                selectionMade = true;
                selectedID = iBL;
                break;
            }
            if (gamepad1.y) { //Y Schedules BackRight

                telemetry.update();
                selectionMade = true;
                selectedID = iBR;
                break;
            }
        }
    }

    public void displaySelection() {

        while (!selectionConfirmed) {
            if (gamepad1.right_stick_button) {
                selectionConfirmed = true;
                break;
            }
            if (gamepad1.left_stick_button) {
                selectionConfirmed = true;
                redoSelection = true; //Goes to top of loop.
                break;
            }
        }
    }

    public void setRun() {

        while (!run && !redoSelection) {
            if (gamepad1.start) {
                run = true;
                schedulingManager[selectedID] = true;
                break;
            }
            if (gamepad1.left_stick_button){
                run = false;
                redoSelection = true;
                break;
            }
        }
        return;
    }

    public void elimMultiples() {
        int scheduledCounter = 0;  //Keep track of how many things are scheduled
        int secondTickInt = 0;  //Keep where the second schedule run is.
        boolean secondTick = false;   //Keep whether or not there is a second tick.
        while (!secondTick) {  //Unless there's a second tick, do this
            for (int i = 0; i <= schedulingManager.length - 1; i++) { //Go through the schedule.
                if (schedulingManager[i]) {
                    scheduledCounter++;  //If something is scheduled, increase the total scheduled
                    if (scheduledCounter > 1) { //If the total schedule is greater than 1, there's a second tick.
                        secondTick = true;
                        secondTickInt = i;
                        break;  //Only get passed this if there is not a second program scheduled.
                    }
                }
            }
            //Have checked entire schedule by this line.  Only here if there was only one tick.
            //Because of the while loop, we'll be here forever unless we break.
            //So, if there's no second schedule, break time.  Don't need to check the schedule again.
            if (!secondTick) {
                break;
            }
        }

        if (secondTick) {
            //If there is a second scheduled program, go to it, disable it, and everything else, just in case.
            for (int i = secondTickInt; i <= schedulingManager.length - 1; i++) {
                schedulingManager[i] = false;
            }
        }
        return;

    }

    public void triggerMotor() {

        if (schedulingManager[iFL]) {
           frontLeft.setPower(1);

        }
        if (schedulingManager[iFR]) {
            frontRight.setPower(1);

        }
        if (schedulingManager[iBL]) {
           backLeft.setPower(1);

        }
        if (schedulingManager[iBR]) {
            backRight.setPower(1);

        }
        return;

    }

    public void stopDetection() {
        while (run){
            if (gamepad1.right_stick_button){
                run = false;
                if (schedulingManager[iFL]) {
                    frontLeft.setPower(0);

                }
                if (schedulingManager[iFR]) {
                    frontRight.setPower(0);

                }
                if (schedulingManager[iBL]) {
                    backLeft.setPower(0);

                }
                if (schedulingManager[iBR]) {
                    backRight.setPower(0);

                }
                break;
            }
        }
        return;
    }

}
