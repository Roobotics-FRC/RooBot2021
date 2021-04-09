package frc.team4373.robot.subsystems;

import edu.wpi.first.wpilibj.Sendable;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.drivetrain.DriveStraightCommand;
import frc.team4373.robot.input.OI;
import frc.team4373.swerve.SwerveConfig;
import frc.team4373.swerve.SwerveDrivetrain;
import frc.team4373.swerve.commands.SwerveDriveWithJoystick;

/**
 * A Javadoc template. TODO: Update Drivetrain Javadoc.
 */
public class Drivetrain extends SwerveDrivetrain {
    private static volatile Drivetrain instance;

    /**
     * The getter for the Drivetrain class.
     * @return the singleton Drivetrain object.
     */
    public static Drivetrain getInstance() {
        if (instance == null) {
            synchronized (Drivetrain.class) {
                if (instance == null) {
                    instance = new Drivetrain();
                }
            }
        }
        return instance;
    }

    private Drivetrain() {
        super(RobotMap.getSwerveConfig());
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveStraightCommand());
    }

    public void logToSmartDashboard() {
        WheelID wheel = (WheelID) ((SendableChooser) SmartDashboard.getData("swerve/wheel")).getSelected();
        double driveP = SmartDashboard.getNumber("swerve/drive/p", 0.1);
        double driveI = SmartDashboard.getNumber("swerve/drive/i", 0);
        double driveD = SmartDashboard.getNumber("swerve/drive/d", 0);
        double rotateP = SmartDashboard.getNumber("swerve/rotate/p", 0.1);
        double rotateI = SmartDashboard.getNumber("swerve/rotate/i", 0);
        double rotateD = SmartDashboard.getNumber("swerve/rotate/d", 0);
        if (SmartDashboard.getBoolean("swerve/set", false)) {
            this.setPID(wheel,
                    new SwerveConfig.PID(0, driveP, driveI, driveD),
                    new SwerveConfig.PID(0, rotateP, rotateI, rotateD));
            SmartDashboard.putBoolean("swerve/set", false);
        }
    }
}
