package frc.team4373.robot.commands.climber;

import edu.wpi.first.wpilibj.command.Command;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.input.OI;
import frc.team4373.robot.subsystems.Climber;

public class ClimberCommand extends Command {
    private Climber climber;

    public ClimberCommand() {
        requires(this.climber = Climber.getInstance());
    }

    @Override
    protected void execute() {
        switch (OI.getInstance().getOperatorJoystick().getPOV()) {
            case 0:
            case 45:
            case 315:
                if (!climber.armIsDeployed()) {
                    climber.raiseArm();
                } else {
                    climber.stopArm();
                }
                break;
            case 135:
            case 180:
            case 225:
                if (!climber.armIsRetracted()) {
                    climber.lowerArm();
                } else {
                    climber.stopArm();
                }
                break;
            default:
                climber.stopArm();
                break;
        }
        this.climber.raiseLeftWinch(OI.getInstance().getOperatorJoystick()
                .getAxis(RobotMap.OPER_WINCH_AXIS));
        this.climber.raiseRightWinch(OI.getInstance().getOperatorJoystick()
                .getAxis(RobotMap.OPER_WINCH_AXIS));
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.climber.stopArm();
        this.climber.raiseLeftWinch(0);
        this.climber.raiseRightWinch(0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}