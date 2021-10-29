package frc.team4373.robot.commands.auton;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.command.WaitCommand;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.drivetrain.TimedDriveAuton;
import frc.team4373.robot.commands.hopper.HopperAutonCommand;
import frc.team4373.robot.commands.intake.DeployIntakeCommand;
import frc.team4373.robot.commands.intake.IntakeAutonCommand;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;

public class ShootThenDriveAuton extends CommandGroup {
    public ShootThenDriveAuton() {
        addSequential(new DeployIntakeCommand());
        addSequential(new IntakeAutonCommand(), 1);
        addParallel(new ShooterShootCommand(RobotMap.AUTON_LINE_SHOOT_SPEED),
                RobotMap.AUTON_SHOOT_TIME_SEC);
        addSequential(new WaitCommand(RobotMap.SHOOTER_TIME_TO_SPIN_UP_SEC));
        addParallel(new HopperAutonCommand(), 1);
        addSequential(new WaitCommand(RobotMap.SHOOTER_TIME_TO_SPIN_UP_SEC));
        addSequential(new TimedDriveAuton(4, 0.1, -90));
    }
}