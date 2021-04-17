package frc.team4373.robot.commands.shooter;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.Utils;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Shooter;

/**
 * Shoots the balls from the shooter.
 */
public class ShooterShootCommand extends Command {
    protected Shooter shooter;

    private double velocity;
    private boolean vision;

    /**
     * Shoots the balls from the shooter at the specified velocity.
     * @param velocity the velocity at which to shoot the balls.
     */
    public ShooterShootCommand(double velocity) {
        requires(this.shooter = Shooter.getInstance());
        this.velocity = velocity;
        this.vision = false;
    }

    /**
     * Shoots using the camera's distance computation to determine speed.
     */
    public ShooterShootCommand() {
        requires(this.shooter = Shooter.getInstance());
        this.vision = true;
    }

    @Override
    protected void initialize() {
        if (this.vision) {
            double distance = NetworkTableInstance.getDefault()
                    .getTable(RobotMap.VISION_TABLE_NAME).getEntry(RobotMap.VISION_DIST_FIELD)
                    .getDouble(-1);
            if (distance < 0) {
                this.velocity = 1;
                DriverStation.reportError("Illegal distance " + distance
                        + " read by shoot command; shooting at full speed", false);
            } else {
                this.velocity = percentVelocityForDistance(distance);
            }
        }
    }

    @Override
    protected void execute() {
//        double adjustedVelocity = velocity
//                + OI.getInstance().getOperatorJoystick().getAxis(
//                        RobotMap.OPER_ADJUST_SHOOT_SPEED_AXIS) / 10d;
        this.shooter.setVelocity(velocity);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.shooter.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }

    /**
     * Returns the velocity at which to shoot given a distance based on our model.
     * @param distance the distance from the target, in inches.
     * @return the percent of full velocity at which to shoot.
     */
    private double percentVelocityForDistance(double distance) {
        double raw = -0.000000229 * Math.pow(distance, 3)
                + 0.000096 * Math.pow(distance, 2)
                - 0.0104 * distance
                + 1.08;
        SmartDashboard.putNumber("shooterDistanceDist", distance);
        SmartDashboard.putNumber("shooterDistanceOut", raw);
        return constrainPercentOutput(raw);
    }
    private double constrainPercentOutput(double output) {
        if (output > 1) {
            return 1;
        }
        if (output < -1) {
            return -1;
        }
        return output;
    }
}
