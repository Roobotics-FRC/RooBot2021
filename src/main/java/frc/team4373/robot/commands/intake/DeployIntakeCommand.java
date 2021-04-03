package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Intake;

/**
 * A Javadoc template. TODO: Update DeployIntakeCommand Javadoc.
 */
public class DeployIntakeCommand extends Command {
    private Intake intake;

    public DeployIntakeCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void initialize() {
        intake.stop();
    }

    @Override
    protected void execute() {
        intake.deploy();
    }

    @Override
    protected boolean isFinished() {
        return intake.isDeployed();
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
