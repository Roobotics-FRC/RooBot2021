package frc.team4373.robot.commands.hopper;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Hopper;
import frc.team4373.robot.subsystems.Shooter;

/**
 * A Javadoc template. TODO: Update HopperCommand Javadoc.
 */
public class HopperCommand extends Command {
    private Hopper hopper;

    public HopperCommand() {
        requires(this.hopper = Hopper.getInstance());
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        if (OI.getInstance().getOperatorJoystick().getRawAxis(RobotMap.OPER_HOPPER_BUTTON) > 0.09) {
            if (Shooter.getInstance().isRunning()) {
                hopper.run();
            } else {
                hopper.stop();
            }
        } else if (OI.getInstance().getOperatorJoystick()
                .getRawButton(RobotMap.OPER_HOPPER_DISLODGE_BUTTON)) {
            hopper.dislodge();
        } else {
            hopper.stop();
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        hopper.stop();
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}
