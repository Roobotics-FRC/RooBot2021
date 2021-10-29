package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Intake;

public class IntakeAutonCommand extends Command {
    private Intake intake;

    public IntakeAutonCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        this.intake.run();
    }

    @Override
    protected void end() {
        intake.stop();
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}