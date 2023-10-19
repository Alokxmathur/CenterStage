package org.firstinspires.ftc.teamcode.robot.components;

public class ArmPosition {
    public int getShoulder() {
        return shoulder;
    }

    public int getElbow() {
        return elbow;
    }

    int shoulder, elbow, rotator;

    public ArmPosition(int shoulder, int elbow) {
        this.shoulder = shoulder;
        this.elbow = elbow;
    }

    public double getRotator() {
        return rotator;
    }
}
