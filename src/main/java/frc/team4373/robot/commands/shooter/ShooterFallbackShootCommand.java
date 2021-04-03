package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Shooter;

/**
 * A Javadoc template. TODO: Update ShooterCommand Javadoc.
 */
public class ShooterFallbackShootCommand extends Command {
    private Shooter shooter;

    public ShooterFallbackShootCommand() {
        requires(shooter = Shooter.getInstance());
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        double sliderVal = OI.getInstance().getDriveJoystick().rooGetThrottle();
        shooter.setVelocity(sliderVal);
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        shooter.stop();
    }
}
