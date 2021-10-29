package frc.team4373.robot.commands.hopper;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Hopper;
import frc.team4373.robot.subsystems.Shooter;

/**
 * A Javadoc template. TODO: Update HopperCommand Javadoc.
 */
public class HopperAutonCommand extends Command {
    private Hopper hopper;

    public HopperAutonCommand() {
        requires(this.hopper = Hopper.getInstance());
    }

    @Override
    protected void initialize() {
        super.initialize();
    }

    @Override
    protected void execute() {
        hopper.run();
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
