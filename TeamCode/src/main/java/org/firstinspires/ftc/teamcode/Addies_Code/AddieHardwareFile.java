package org.firstinspires.ftc.teamcode.Addies_Code;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.robocol.Command;


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
        frontLeft.setPower(frontL);
        frontRight.setPower(frontR);
        backLeft.setPower(backL);
        backRight.setPower(backR);
    }

    public void forwardMovement() {

        movement(0, 1, 0);
    }

    public void backwardMovement() {
        movement(0, -1, 0);
    }

    public void strafeRight() {
        movement(1, 0, 0);
    }

    public void strafeLeft() {
        movement(-1, 0, 0);
    }

    public void slideUp() {
        CascadeLift.setPower(1);
    }

    public void slideDrop() {
        CascadeLift.setPower(-1);
    }

    public void rotateLeft() {
        movement(0, 0, -1);
    }

    public void rotateRight() {
        movement(0, 0, 1);
    }

    public void addSequential(String commandName, double speed){

        String CommandName = commandName;

        if(CommandName == "Back"){movement(0, -speed, 0);}
        if(CommandName == "Forward"){movement(0, speed, 0);}
        if(CommandName == "Left"){movement(-speed, 0, 0);}
        if(CommandName == "Right"){movement(speed, 0, 0);}
        if(CommandName == "ForwardLeft"){movement(-speed, speed, 0);}
        if(CommandName == "ForwardRight"){movement(speed, speed, 0);}
        if(CommandName == "BackLeft"){movement(-speed, -speed, 0);}
        if(CommandName == "BackRight"){movement(speed, -speed, 0);}
        if(CommandName == "LiftUp"){CascadeLift.setPower(speed);}
        if(CommandName == "LiftDown"){CascadeLift.setPower(-speed);}
        if(CommandName == "SlideOpen"){LinearActuator.setPower(speed);}
        if(CommandName == "SlideClose"){LinearActuator.setPower(-speed);}
        if(CommandName == "RotateRight"){movement(0,0,speed);}
        if(CommandName == "RotateLeft"){movement(0,0,-speed);}

    }
}


