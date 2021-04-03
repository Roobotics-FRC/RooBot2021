package frc.team4373.robot.commands.shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Shooter;

/**
 * A Javadoc template. TODO: Update ShooterCommand Javadoc.
 */
public class ShooterCommand extends Command {
    private Shooter shooter;

    public ShooterCommand() {
        requires(this.shooter = Shooter.getInstance());
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        shooter.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        shooter.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
