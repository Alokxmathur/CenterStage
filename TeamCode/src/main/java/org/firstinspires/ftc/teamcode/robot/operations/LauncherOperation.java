package org.firstinspires.ftc.teamcode.robot.operations;

import org.firstinspires.ftc.teamcode.robot.components.DroneLauncher;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.robot.RobotConfig;

import java.util.Date;
import java.util.Locale;

public class LauncherOperation extends Operation{

    public enum Type {
        Release, Unrelease
    }

    DroneLauncher droneLauncher;

    Type type;

    public LauncherOperation(DroneLauncher droneLauncher, Type type, String title) {
        this.droneLauncher = droneLauncher;
        this.type = type;
        this.title = title;
    }

    public String toString() {
        return String.format(Locale.getDefault(), "Trigger: --%s",
                this.title);
    }

    @Override
    public boolean isComplete() {
        if (type == Type.Release || type == Type.Unrelease) {
            return (new Date().getTime() - this.getStartTime().getTime() > RobotConfig.SERVO_REQUIRED_TIME);
        }
        return false;
    }

    @Override
    public void startOperation() {
        switch (this.type) {
            case Release:
            {
                droneLauncher.releaseTrigger();
                break;
            }
            case Unrelease:
            {
                droneLauncher.unreleaseTrigger();
                break;
            }
        }
    }

    @Override
    public void abortOperation() {
        droneLauncher.stop();
    }
}
