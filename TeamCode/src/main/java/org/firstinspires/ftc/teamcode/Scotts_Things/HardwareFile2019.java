package org.firstinspires.ftc.teamcode.Scotts_Things;

import org.firstinspires.ftc.teamcode.Scotts_Things.direction;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
//Import Statements

//Class Constructor
public class HardwareFile2019 {


    //Null Future Variables and power modifiers declared
    HardwareMap hwMap = null;

    //Motors
    public static DcMotor frontLeft = null;
    public static DcMotor frontRight = null;
    public static DcMotor backLeft = null;
    public static DcMotor backRight = null;
    public static DcMotor CascadeLift = null;
    public static DcMotor LinearActuator = null;
    public static double maxOutput = 1;

    public static int xIndex = 0;
    public static int yIndex = 1;
    public static int zIndex = 2;

    public static int forwardIndex = 0;
    public static int leftIndex = 1;
    public static int rightIndex = 2;
    public static int backwardsIndex = 3;
    public static int rotationLeftIndex = 4;
    public static int rotationRightIndex = 5;

    //Motor ID's
    public static int iFL = 0;
    public static int iFR = 1;
    public static int iBL = 2;
    public static int iBR = 3;

    public direction forwardD = new direction("forward");
    public direction leftD = new direction("left");
    public direction rightD = new direction("right");
    public direction backwardsD = new direction("backwards");
    public direction rotationLeft = new direction("rotationLeft");
    public direction rotationRight = new direction("rotationRight");

    public int[][] coordinates = new int[6][3];

    mechanism active = new mechanism();
    mechanism lift = new mechanism("Lift");
    mechanism actuator = new mechanism("Actuator");

    public void setCoordinates() {
        for (int i = forwardIndex; i <= rotationRightIndex; i++) {
            for (int h = xIndex; i <= zIndex; i++) {
                coordinates[i][h] = 0;
            }
        }//Zero all the coordinates;
        coordinates[forwardIndex][yIndex] = 1;
        coordinates[leftIndex][xIndex] = -1;
        coordinates[rightIndex][xIndex] = 1;
        coordinates[backwardsIndex][yIndex] = -1;
        coordinates[rotationLeftIndex][zIndex] = -1;
        coordinates[rotationRightIndex][zIndex] = 1;
    }

    //Function to bind variables to locations in phone hardware map
    //Accepts a hardware map in calling
    public void mapHardware(HardwareMap ahwMap) {
        // Save reference to Hardware map
        hwMap = ahwMap;
        frontLeft = hwMap.get(DcMotor.class, "mfl");
        backLeft = hwMap.get(DcMotor.class, "mbl");
        frontRight = hwMap.get(DcMotor.class, "mfr");
        backRight = hwMap.get(DcMotor.class, "mbr");
        CascadeLift = hwMap.get(DcMotor.class, "liftMotor");
        LinearActuator = hwMap.get(DcMotor.class, "clamp");
        //Detail Configuration for Motors
        resetEncoders();
        resetEncoders(LinearActuator);
        directionConfig();

        //Ensure set up function does not cause unintended movement during init
        wheelStop();

        //Fills arrays with movement and corresponding
        //coordinates.
        setCoordinates();
    }

    public double ratioScaling(double oMin, double oMax, double oValue, double sMin, double sMax) {
        double output;
        //Take the relative position of a number within a range and apply it to a different range
        output = sMin + (oValue - oMin) * (sMax - sMin) / (oMax - oMin);

        return output;
    }

    //Being used, leave alone in terms of direction
    public void directionConfig() {

        frontRight.setDirection(DcMotorSimple.Direction.FORWARD);
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        backRight.setDirection(DcMotorSimple.Direction.FORWARD);
        backLeft.setDirection(DcMotorSimple.Direction.FORWARD);

        CascadeLift.setDirection(DcMotorSimple.Direction.REVERSE);

        CascadeLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        LinearActuator.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

    }

    public double liftModification(double liftPower) {
        if (liftPower > 0) {
            liftPower = liftPower * 0.5;
        }
        return liftPower;
    }

    public void wheelStop() {
        //function to end everything
        if (frontLeft != null) {
            frontLeft.setPower(0);
        }
        if (frontRight != null) {
            frontRight.setPower(0);
        }
        if (backLeft != null) {
            backLeft.setPower(0);
        }
        if (backRight != null) {
            backRight.setPower(0);
        }
        if (CascadeLift != null) {
            CascadeLift.setPower(0);
        }
        if (LinearActuator != null) {
            LinearActuator.setPower(0);
        }
    }


    public void resetEncoders() {
        //encoder config so if encoders are used it functions
        //Resets encoders
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        runWithEncoders();
    }

    public void resetEncoders(DcMotor actuator){
        actuator.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        runWithEncoders(actuator);

    }

    public void runWithEncoders() {
        //Renables encoders
        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }

    public void runWithEncoders(DcMotor actuator){
        actuator.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public double deadZone(double value, double zone) {

            //Accepts a gamepad value and nullifies it should it be beneath a certain value
            //Allows prevention of accidental controller inputs
            if (Math.abs(value) < zone) {
                value = 0;
            }

        return value;
    }

    public double zManipulation(double zL, double zR) {
        double Z;
        /*
            Takes in the value of the two triggers, returning the greatest trigger
            and if the left trigger is greater,
            returns it negative so counterclockwise rotations are possible
            The one flaw is if they're equal, by some miracle, the code will always
            rotate clockwise.  I did this because other wise
        */
        if (zR > zL) {
            Z = zR;
        } else if (zL > zR) {
            Z = -1 * zL;
        } else {
            Z = zR;
        }

        return Z;
    }

    //NOTE: THIS INT IS STRICTLY EXPERIMENTAL AND SHOULD NOT BE USED.
    //WHILE IT IS WELL COMMENTED AND DOCUMENTED, THE LOGIC IS STILL UNCERTAIN
    //DIRECTIONAL CONFIGURATION'S AFFECT STILL NEEDS TO BE TESTED
    //ONCE WHEEL TESTING HAS OCCURRED ASSUMING ALL FORWARD, CONVERSION NEEDS TO BE MADE
    //BECAUSE EQUATIONS ASSUME INVERTED RIGHT

    //STAGES OF RIGHT MOTOR
    //In + Out -   HARDWARE DEFAULT
    //In + Out +   SET DIRECTION REVERSE

    //IN + OUT +   HARDWARE CUSTOM
    //IN + OUT -   SET DIRECTION REVERSE

    public int motorInversion(DcMotor motor) {
        int inversion = 0;
        //For clarification, the Right Side is hardware inverted and thus does not need to be in code
        //The left side is also hardware inverted, excepting the backLeft, meaning to get "normal"
        //output on the left side, code inversion is required.  Because the backLeft is different,
        //it does not need code inversion.

        //Here's an example if the motors were standard:
        //frontRight means -1, backRight means -1
        //frontLeft means 1, backLeft means 1

        //Example if constant hardware inversion had been applied:
        //frontRight means 1, backRight means 1
        //frontLeft means -1, backLeft means -1

        //The drivetrain is constant hardware inversion, except backLeft
        //And thus, the code below.
        if (motor == frontRight) {
            inversion = 1;
        } else if (motor == backRight) {
            inversion = 1;
        } else if (motor == frontLeft) {
            inversion = -1;
        } else if (motor == backLeft) {
            inversion = 1;
        }

        return inversion;
    }

    public double polarEval(double first, double second) {
        //Takes the distance of two numbers from zero and returns the greatest one.
        //Primary use case is joystick coord conversion to magnitude for polar mecanum driving
        //If they're equal, returns the second one.
        //In our use case, this is fine because if X & Y are equal, we obviously want to go
        //at that magnitude, and thus it is fine to return either since they're equal.
        double firstT = Math.abs(first);
        double secondT = Math.abs(second);
        if (firstT > secondT) {
            return first;
        } else {
            return second;
        }
    }

    public void movement(int x, int y, int z) {
        frontLeft.setPower(x + y - z);
        frontRight.setPower(x - y - z);
        backLeft.setPower(x - y + z);
        backRight.setPower(x + y + z);
    }

    public void movement(direction heading) {
        if (heading == forwardD) {
            movement(forwardIndex);
        } else if (heading == leftD) {
            movement(leftIndex);
        } else if (heading == rightD) {
            movement(rightIndex);
        } else if (heading == backwardsD) {
            movement(rotationLeftIndex);
        } else if (heading == rotationLeft) {
            movement(rotationRightIndex);
        } else if (heading == rotationRight) ;
    }

    public void movement(int direction) {
        movement(coordinates[direction][xIndex], coordinates[direction][yIndex],
                coordinates[direction][zIndex]);
    }

    public void timedMove(direction heading, ElapsedTime e, double wait) {
        while (e.seconds() < wait) {
            movement(heading);
        }
        wheelStop();
        e.reset();
    }

    public void operation(mechanism m, ElapsedTime e, double wait, double power) {
        if (m == lift) {
            while (e.seconds() < wait) {
                CascadeLift.setPower(power);
            }
        }
        if (m == actuator){
            while (e.seconds() < wait){
                LinearActuator.setPower(power);
            }
        }

        wheelStop();
    }

    public void waitToEnd(ElapsedTime e){
        while(e.seconds() <= 30){
            int i = 1;
            i++;
        }
    }

}






