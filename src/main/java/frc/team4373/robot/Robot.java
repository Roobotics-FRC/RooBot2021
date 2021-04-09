package frc.team4373.robot;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.commands.intake.DeployIntakeCommand;
import frc.team4373.robot.subsystems.*;
import frc.team4373.swerve.SwerveDrivetrain;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
    /**
     * Constructor for the Robot class. Variable initialization occurs here;
     * WPILib-related setup should occur in {@link #robotInit}.
     */
    public Robot() {
    }

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     *
     * <p>All SmartDashboard fields should be initially added here.</p>
     */
    @Override
    public void robotInit() {
        SendableChooser<SwerveDrivetrain.WheelID> chooser = new SendableChooser<>();
        chooser.addOption("Right 1", SwerveDrivetrain.WheelID.RIGHT_1);
        chooser.addOption("Right 2", SwerveDrivetrain.WheelID.RIGHT_2);
        chooser.addOption("Left 1", SwerveDrivetrain.WheelID.LEFT_1);
        chooser.addOption("Left 2", SwerveDrivetrain.WheelID.LEFT_2);
        SmartDashboard.putData("swerve/wheel", chooser);
        SmartDashboard.putNumber("swerve/drive/p", 0.1);
        SmartDashboard.putNumber("swerve/drive/i", 0);
        SmartDashboard.putNumber("swerve/drive/d", 0);
        SmartDashboard.putNumber("swerve/rotate/p", 0.1);
        SmartDashboard.putNumber("swerve/rotate/i", 0);
        SmartDashboard.putNumber("swerve/rotate/d", 0);
        SmartDashboard.putBoolean("swerve/set", false);

        Climber.getInstance().logToSmartDashboard();
        Drivetrain.getInstance().logToSmartDashboard();
        Hopper.getInstance().logToSmartDashboard();
        Intake.getInstance().logToSmartDashboard();
        Shooter.getInstance().logToSmartDashboard();
    }

    /**
     * This function is called every robot packet, no matter the mode. Use
     * this for items like diagnostics that you want run during disabled,
     * autonomous, teleoperated, and test.
     *
     * <p>This runs after the mode-specific periodic functions but before
     * LiveWindow and SmartDashboard integrated updating.
     */
    @Override
    public void robotPeriodic() {
        Scheduler.getInstance().run();

        Climber.getInstance().logToSmartDashboard();
        Drivetrain.getInstance().logToSmartDashboard();
        Hopper.getInstance().logToSmartDashboard();
        Intake.getInstance().logToSmartDashboard();
        Shooter.getInstance().logToSmartDashboard();
    }

    /**
     * This function is called once when Sandstorm mode starts.
     */
    @Override
    public void autonomousInit() {
    }

    /**
     * This function is called once when teleoperated mode starts.
     */
    @Override
    public void teleopInit() {
        Scheduler.getInstance().add(new DeployIntakeCommand());
    }

    /**
     * This function is called periodically during Sandstorm mode.
     */
    @Override
    public void autonomousPeriodic() {
    }

    /**
     * This function is called periodically during operator control.
     */
    @Override
    public void teleopPeriodic() {
    }

    /**
     * This function is called periodically during test mode.
     */
    @Override
    public void testPeriodic() {
    }

    /**
     * This function runs periodically in disabled mode.
     * It is used to verify that the selected auton configuration is legal.
     * <br>
     * <b>UNDER NO CIRCUMSTANCES SHOULD SUBSYSTEMS BE ACCESSED OR ENGAGED IN THIS METHOD.</b>
     */
    @Override
    public void disabledPeriodic() {
    }
}
