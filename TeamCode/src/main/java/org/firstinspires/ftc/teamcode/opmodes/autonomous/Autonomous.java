package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import org.firstinspires.ftc.teamcode.game.Match;
import org.firstinspires.ftc.teamcode.robot.RobotConfig;
import org.firstinspires.ftc.teamcode.robot.components.drivetrain.DriveTrain;
import org.firstinspires.ftc.teamcode.robot.operations.ArmOperation;
import org.firstinspires.ftc.teamcode.robot.operations.BearingOperation;
import org.firstinspires.ftc.teamcode.robot.operations.DriveForDistanceOperation;
import org.firstinspires.ftc.teamcode.robot.operations.FollowTrajectory;
import org.firstinspires.ftc.teamcode.robot.operations.State;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeLeftForDistanceOperation;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeLeftForDistanceWithHeadingOperation;
import org.firstinspires.ftc.teamcode.robot.operations.StrafeRightForDistanceOperation;
import org.firstinspires.ftc.teamcode.robot.operations.TurnAntiClockwiseOperation;
import org.firstinspires.ftc.teamcode.robot.operations.WaitOperation;

public abstract class Autonomous extends AutonomousHelper {

    @Override
    public void start() {
        super.start();
        State state = new State("Deliver Purple Pixel");
        state.addPrimaryOperation(new WaitOperation(1000, "Wait a sec"));
        //Spike Mark 1
        state.addPrimaryOperation(new StrafeLeftForDistanceOperation(12, 50, "Line up with Spike Mark 1"));
        state.addPrimaryOperation(new DriveForDistanceOperation(44- RobotConfig.ROBOT_LENGTH, 50, "Deliver to Spike Mark 1"));
        state.addPrimaryOperation(new DriveForDistanceOperation(8, -50, "Back away"));
        state.addPrimaryOperation(new BearingOperation());
        state.addPrimaryOperation(new DriveForDistanceOperation(12, 50, "Go to Interim Position"));

        //Spike Mark 2
        state.addPrimaryOperation(new DriveForDistanceOperation(47- RobotConfig.ROBOT_LENGTH, 50, "Deliver to Spike Mark 2"));
        state.addPrimaryOperation(new DriveForDistanceOperation(12, -50, "Back away"));
        state.addPrimaryOperation(new BearingOperation());
        state.addPrimaryOperation(new DriveForDistanceOperation(24, 50, "Go to Interim Position"));

        //Spike Mark 3
        state.addPrimaryOperation(new StrafeRightForDistanceOperation(12, 50, "Line up with Spike Mark 3"));
        state.addPrimaryOperation(new DriveForDistanceOperation(44- RobotConfig.ROBOT_LENGTH, 50, "Deliver to Spike Mark 3"));
        state.addPrimaryOperation(new DriveForDistanceOperation(8, -50, "Back away"));
        state.addPrimaryOperation(new BearingOperation());
        state.addPrimaryOperation(new DriveForDistanceOperation(36, 50, "Go to Interim Position"));
        states.add(state);

        state = new State("Approach Backdrop");
        //raise cone to high level
        state.addPrimaryOperation(new FollowTrajectory(
                field.getTurnaroundTrajectory(),
                "Slide over"
        ));
        state.addPrimaryOperation(new FollowTrajectory(
                field.getDeliverLoadedConeTrajectory(),
                "Get to delivery point of loaded cone"
        ));
        states.add(state);

        state = new State("Deliver loaded cone");
        states.add(state);

        state = new State("Reach stack");
        state.addPrimaryOperation(new FollowTrajectory(
                field.getRetractFromLoadedConeDeliveryTrajectory(),
                "Retract from loaded cone delivery"
        ));
        state.addPrimaryOperation(new FollowTrajectory(
                field.getPickupConeTrajectory(),
                "Reach pickup area"
        ));
        //state.addPrimaryOperation(new WinchOperation(robot.getWinch(), robot.getFourBar(), WinchOperation.Type.Ground, "Reach stack level"));
        states.add(state);

        state = new State("Grab second cone");
        //state.addPrimaryOperation(new ClawOperation(robot.getClaw(), ClawOperation.Type.Close, "Close Claw"));
        //state.addSecondaryOperation(new WinchOperation(robot.getWinch(), robot.getFourBar(), WinchOperation.Type.High, "Level High"));
        state.addPrimaryOperation(new FollowTrajectory(
                field.getRetractFromStackTrajectory(),
                "Retract from stack"
        ));

        state.addPrimaryOperation(new FollowTrajectory(
                field.getDeliverSecondConeTrajectory(),
                "Deliver second cone"
        ));
        //state.addPrimaryOperation(new ClawOperation(robot.getClaw(), ClawOperation.Type.Open, "Open Claw"));

        states.add(state);

        state = new State("Navigate");


        state.addPrimaryOperation(new FollowTrajectory(
                field.getRetractFromSecondConeDeliveryTrajectory(),
                "Retract from second cone"
        ));

        state.addPrimaryOperation(new FollowTrajectory(
                field.getNavigationTrajectory(match.getSignalNumber()),
                "Reach right tile to navigate"
        ));
        //state.addPrimaryOperation(new WinchOperation(robot.getWinch(), robot.getFourBar(), WinchOperation.Type.Ground, "Lower"));
        states.add(state);

        Match.log("Created and added state");
    }
}
