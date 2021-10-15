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

        double x = OI.getInstance().getOperatorJoystick()
                .getAxis(RobotMap.OPER_WINCH_AXIS_X);
        double y = OI.getInstance().getOperatorJoystick()
                .getAxis(RobotMap.OPER_WINCH_AXIS_Y);
        if (x > 0) {
            this.climber.setLeftWinch(y - x);
            this.climber.setRightWinch(y);
        } else {
            this.climber.setLeftWinch(y);
            this.climber.setRightWinch(y + x);
        }
    }

    @Override
    protected boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        this.climber.stopArm();
        this.climber.setLeftWinch(0);
        this.climber.setRightWinch(0);
    }

    @Override
    protected void interrupted() {
        this.end();
    }
}