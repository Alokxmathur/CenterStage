package org.firstinspires.ftc.teamcode.opmodes.autonomous;

import org.firstinspires.ftc.teamcode.game.Match;
import org.firstinspires.ftc.teamcode.robot.RobotConfig;
import org.firstinspires.ftc.teamcode.robot.components.Arm;
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
import org.firstinspires.ftc.teamcode.robot.components.vision.detector.ObjectDetector;
import org.firstinspires.ftc.teamcode.robot.components.vision.detector.ObjectDetectorWebcam;

public abstract class Autonomous extends AutonomousHelper {

    @Override
    public void start() {
        super.start();
        State state = new State("Deliver Purple Pixel");
        state.addPrimaryOperation(new ArmOperation(robot.getArm(), ArmOperation.Type.Deposit, "Raise Arm"));
        //Spike Mark 1
        /*if () {
            state.addPrimaryOperation(new DriveForDistanceOperation(44 - RobotConfig.ROBOT_LENGTH, 50, "Deliver to Spike Mark 1"));
            state.addPrimaryOperation(new StrafeLeftForDistanceOperation(12, 50, "Line up with Spike Mark 1"));
            state.addPrimaryOperation(new DriveForDistanceOperation(8, -50, "Back away"));
            state.addPrimaryOperation(new BearingOperation(0, "Turn Toward Backdrop"));
            state.addPrimaryOperation(new DriveForDistanceOperation(12, 50, "Go to Interim Position"));
        }*/
        //Spike Mark 2
        //else if () {
            state.addPrimaryOperation(new DriveForDistanceOperation(47 - RobotConfig.ROBOT_LENGTH, 50, "Deliver to Spike Mark 2"));
            state.addPrimaryOperation(new DriveForDistanceOperation(12, -50, "Back away"));
            state.addPrimaryOperation(new BearingOperation(0, "Turn Toward Backdrop"));
            state.addPrimaryOperation(new DriveForDistanceOperation(24, 50, "Go to Interim Position"));
        //}
        /*else if () {
            //Spike Mark 3
            state.addPrimaryOperation(new DriveForDistanceOperation(44 - RobotConfig.ROBOT_LENGTH, 50, "Deliver to Spike Mark 3"));
            state.addPrimaryOperation(new StrafeRightForDistanceOperation(12, 50, "Line up with Spike Mark 3"));
            state.addPrimaryOperation(new DriveForDistanceOperation(8, -50, "Back away"));
            state.addPrimaryOperation(new BearingOperation(0, "Turn Toward Backdrop"));
            state.addPrimaryOperation(new DriveForDistanceOperation(36, 50, "Go to Interim Position"));
        }*/
        states.add(state);

        //position should be x = 36, y = 36

        state = new State("Approach Backdrop");
        //Spike Mark 1
        /*if () {
            state.addPrimaryOperation(new StrafeLeftForDistanceOperation(8, 10, "Slide Left"));
            state.addPrimaryOperation(new DriveForDistanceOperation(15, 10, "Approach Claw to BackDrop"));
        }
        //Spike Mark 2
        else if () {*/
            state.addPrimaryOperation(new DriveForDistanceOperation(15, 10, "Approach Claw to BackDrop"));
        //}
        //Spike Mark 3
        /*else if () {
            state.addPrimaryOperation(new StrafeRightForDistanceOperation(8, 10, "Slide Right"));
            state.addPrimaryOperation(new DriveForDistanceOperation(15, 10, "Approach Claw to BackDrop"));
        }*/
        state.addPrimaryOperation(new ArmOperation(robot.getArm(), ArmOperation.Type.Open, "Drop Yellow Pixel"));
        states.add(state);

        state = new State("Navigate to Scoring Area");
        state.addPrimaryOperation(new DriveForDistanceOperation(-3, 50, "Back Away"));
        state.addPrimaryOperation(new ArmOperation(robot.getArm(), ArmOperation.Type.Pickup, "Lower Arm"));

        //Spike Mark 1
        /*if () {
            state.addPrimaryOperation(new StrafeRightForDistanceOperation(32, 50, "Navigate"));
        }*/
        //Spike Mark 2
        //else if () {
            state.addPrimaryOperation(new StrafeRightForDistanceOperation(24, 50, "Navigate"));
        //}
        //Spike Mark 3
        /*else if () {
            state.addPrimaryOperation(new StrafeRightForDistanceOperation(16, 50, "Navigate"));
        }*/
        states.add(state);

        /*state = new State("Grab second cone");
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
*/
        Match.log("Created and added state");
    }
}
