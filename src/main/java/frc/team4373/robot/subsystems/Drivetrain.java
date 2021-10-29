package frc.team4373.robot.subsystems;

import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.drivetrain.DriveStraightCommand;
import frc.team4373.robot.input.OI;
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
//        setBrakeMode(BrakeMode.OCTAGON);
//        setMaxWheelAcceleration(0.1);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new DriveStraightCommand());
    }

    public void logToSmartDashboard() {

    }
}
