package frc.team4373.robot.commands.intake;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Intake;

/**
 * A Javadoc template. TODO: Update IntakeCommand Javadoc.
 */
public class IntakeCommand extends Command {
    private Intake intake;

    public IntakeCommand() {
        requires(this.intake = Intake.getInstance());
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        if (OI.getInstance().getOperatorJoystick().getRawAxis(RobotMap.OPER_INTAKE_BUTTON) > 0.09) {
            this.intake.run();
        } else if (OI.getInstance().getOperatorJoystick().getRawButton(
                RobotMap.OPER_INTAKE_DISLODGE_BUTTON)) {
            this.intake.dislodge();
        } else {
            this.intake.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }
}
