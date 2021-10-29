package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.subsystems.Intake;

public class RetractIntakeCommand extends Command {
    private Intake intake;

    public RetractIntakeCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void initialize() {
        intake.stop();
    }

    @Override
    protected void execute() {
        intake.retract();
    }

    @Override
    protected boolean isFinished() {
        return !intake.isDeployed();
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        this.end();
    }
}