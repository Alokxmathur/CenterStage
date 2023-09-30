package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.game.Match;
import org.firstinspires.ftc.teamcode.robot.RobotConfig;
import org.firstinspires.ftc.teamcode.robot.operations.ArmOperation;

import java.util.Locale;

public class Arm {
    DcMotor shoulder, elbow;
    int lastShoulderPosition, lastElbowPosition, lastWristPosition;

    public Arm(HardwareMap hardwareMap) {
        //initialize our shoulder motor
        this.shoulder = hardwareMap.get(DcMotor.class, RobotConfig.SHOULDER);
        this.shoulder.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        //initialize our elbow motor
        this.elbow = hardwareMap.get(DcMotor.class, RobotConfig.ELBOW);
        this.elbow.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        ensureMotorDirections();
        assumeInitialPosition();
    }

    public void ensureMotorDirections() {
        this.elbow.setDirection(DcMotorSimple.Direction.FORWARD);
        this.shoulder.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void assumeInitialPosition() {
        this.shoulder.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.elbow.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        setElbowPosition(0);
        setShoulderPosition(0);
    }

    public void raiseShoulderIncrementally() {
        setShoulderPosition(shoulder.getTargetPosition() + RobotConfig.SHOULDER_INCREMENT);
    }
    public void lowerShoulderIncrementally() {
        setShoulderPosition(shoulder.getTargetPosition() - RobotConfig.SHOULDER_INCREMENT);
    }
    public void raiseElbowIncrementally() {
        setElbowPosition(elbow.getTargetPosition() + RobotConfig.ELBOW_INCREMENT);
    }
    public void lowerElbowIncrementally() {
        setElbowPosition(elbow.getTargetPosition() - RobotConfig.ELBOW_INCREMENT);
    }

    public void stop() {
    }

    public void setPositions(ArmOperation.Type type) {
        switch (type) {
            case Pickup: {
                setPositions(RobotConfig.ARM_PICKUP_POSITION);
                break;
            }
            case High: {
                setPositions(RobotConfig.ARM_HIGH_JUNCTION_POSITION);
                break;
            }
            default : {
                Match.log("Nothing done for arm operation of type: " + type);
            }
        }
    }

    private void setPositions(ArmPosition armPosition) {
        setShoulderPosition(armPosition.getShoulder());
        setElbowPosition(armPosition.getElbow());
    }

    /**
     * Set the shoulder motor position
     * @param position
     */
    public void setShoulderPosition(int position) {
        this.shoulder.setTargetPosition(position);
        this.shoulder.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.shoulder.setPower(RobotConfig.MAX_SHOULDER_POWER);
        lastShoulderPosition = position;
    }

    /**
     * Retain shoulder in its current position
     */
    public void retainShoulder() {
        setShoulderPosition(lastShoulderPosition);
    }

    /**
     * Set the shoulder power
     * @param power
     */
    public void setShoulderPower(double power) {
        this.shoulder.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.shoulder.setPower(power);
        lastShoulderPosition = shoulder.getCurrentPosition();
    }

    /**
     * Set the elbow position
     * @param position
     */
    public void setElbowPosition(int position) {
        this.elbow.setTargetPosition(position);
        this.elbow.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        this.elbow.setPower(RobotConfig.MAX_ELBOW_POWER);
        lastElbowPosition = position;
    }

    /**
     * Retain elbow in its current position
     */
    public void retainElbow() {
        setElbowPosition(lastElbowPosition);
    }

    /**
     * Set the elbow power
     * @param power
     */
    public void setElbowPower(double power) {
        this.elbow.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        this.elbow.setPower(power);
        lastElbowPosition = elbow.getCurrentPosition();
    }

    public boolean isWithinRange() {
        Match.log(getStatus());
        return shoulderIsWithinRange() && elbowIsWithinRange();
    }

    private boolean shoulderIsWithinRange() {
        return Math.abs(shoulder.getTargetPosition() - shoulder.getCurrentPosition()) <= RobotConfig.ACCEPTABLE_SHOULDER_ERROR;
    }

    private boolean elbowIsWithinRange() {
        return Math.abs(elbow.getTargetPosition() - elbow.getCurrentPosition()) <= RobotConfig.ACCEPTABLE_ELBOW_ERROR;
    }

    /**
     * Returns the status of the arm
     * Reports the current position, target position and power of the shoulder,
     * current position, target position and power of the elbow,
     * the position of the wrist and the position of the claw
     * @return
     */
    public String getStatus() {
        return String.format(Locale.getDefault(), "S:%d->%d@%.2f, E:%d->%d@%.2f",
                shoulder.getCurrentPosition(), shoulder.getTargetPosition(), shoulder.getPower(),
                elbow.getCurrentPosition(), elbow.getTargetPosition(), elbow.getPower());
    }
}
