package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;


public class AddieHardwareFile {


    HardwareMap hwMap = null;


    public static DcMotor frontLeft = null;
    public static DcMotor frontRight = null;
    public static DcMotor backLeft = null;
    public static DcMotor backRight = null;
    public static DcMotor CascadeLift = null;
    public static DcMotor LinearActuator = null;
    public static double maxOutput = 1;


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
        directionConfig();

        //Ensure set up function does not cause unintended movement during init
        motorStop();

    }

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

    public void motorStop() {
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

    public void movement(double x, double y, double z) {
        double gamepadX = -x;
        double gamepadY = -y;
        double gamepadZ = z;
        double frontL = (gamepadX + gamepadY - gamepadZ);
        double frontR = (gamepadX - gamepadY - gamepadZ);
        double backL = (gamepadX - gamepadY + gamepadZ);
        double backR = (gamepadX + gamepadY + gamepadZ);
        frontLeft.setPower(frontL * maxOutput);
        frontRight.setPower(frontR * maxOutput);
        backLeft.setPower(backL * maxOutput);
        backRight.setPower(backR * maxOutput);
    }

    public void runCommand(String CommandName, double speed) {

        CommandName = CommandName.toUpperCase();

        if (CommandName == "BACK") {
            movement(0, -speed, 0);
        }
        if (CommandName == "FORWARD") {
            movement(0, speed, 0);
        }
        if (CommandName == "LEFT") {
            movement(-speed, 0, 0);
        }
        if (CommandName == "RIGHT") {
            movement(speed, 0, 0);
        }
        if (CommandName == "FORWARDLEFT") {
            movement(-speed, speed, 0);
        }
        if (CommandName == "FORWARDRIGHT") {
            movement(speed, speed, 0);
        }
        if (CommandName == "BACKLEFT") {
            movement(-speed, -speed, 0);
        }
        if (CommandName == "BACKRIGHT") {
            movement(speed, -speed, 0);
        }
        if (CommandName == "LIFTUP") {
            CascadeLift.setPower(speed);
        }
        if (CommandName == "LIFTDOWN") {
            CascadeLift.setPower(-speed);
        }
        if (CommandName == "SLIDEOPEN") {
            LinearActuator.setPower(speed);
        }
        if (CommandName == "SLIDECLOSE") {
            LinearActuator.setPower(-speed);
        }
        if (CommandName == "ROTATERIGHT") {
            movement(0, 0, speed);
        }
        if (CommandName == "ROTATELEFT") {
            movement(0, 0, -speed);
        }
        if (CommandName == "WAIT") {
            movement(0, 0, 0);
        }

    }
}


