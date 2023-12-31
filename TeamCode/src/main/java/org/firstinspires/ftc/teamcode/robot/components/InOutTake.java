package org.firstinspires.ftc.teamcode.robot.components;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.RobotConfig;

import java.util.Locale;

public class InOutTake {
    DcMotor motor;
    public InOutTake(HardwareMap hardwareMap) {
        motor = hardwareMap.get(DcMotor.class, RobotConfig.INOUT_TAKE_MOTOR);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void setSpeed(double speed) {
        this.motor.setPower(Math.max((Math.min(speed, RobotConfig.MAX_INOUT_SPEED)), -RobotConfig.MAX_INOUT_SPEED));
    }

    public void stop() {
        this.motor.setPower(0);
    }

    public String getStatus() {
        return String.format(Locale.getDefault(),"C:%d->%d@%.2f",
                this.motor.getCurrentPosition(),
                this.motor.getTargetPosition(),
                this.motor.getPower());
    }
}