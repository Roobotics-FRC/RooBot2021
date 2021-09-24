package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.climber.ClimberCommand;

/**
 * A Javadoc template. TODO: Update Climber Javadoc.
 */
public class Climber extends Subsystem {
    private static volatile Climber instance;

    /**
     * The getter for the Climber class.
     * @return the singleton Climber object.
     */
    public static Climber getInstance() {
        if (instance == null) {
            synchronized (Climber.class) {
                if (instance == null) {
                    instance = new Climber();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX arm;
    private WPI_VictorSPX leftWinch;
    private WPI_VictorSPX rightWinch;
    private DigitalInput retractedLimitSwitch;
    private DigitalInput deployedLimitSwitch;

    private Climber() {
        this.arm = new WPI_TalonSRX(RobotMap.CLIMB_ARM_CONFIG.id);
        this.leftWinch = new WPI_VictorSPX(RobotMap.CLIMB_LEFT_WINCH_CONFIG.id);
        this.rightWinch = new WPI_VictorSPX(RobotMap.CLIMB_RIGHT_WINCH_CONFIG.id);
        this.retractedLimitSwitch = new DigitalInput(RobotMap.CLIMBER_ARM_RETRACTED_LIMIT_SWITCH);
        this.deployedLimitSwitch = new DigitalInput(RobotMap.CLIMBER_ARM_EXTENDED_LIMIT_SWITCH);

        this.arm.setInverted(RobotMap.CLIMB_ARM_CONFIG.inverted);
        this.leftWinch.setInverted(RobotMap.CLIMB_LEFT_WINCH_CONFIG.inverted);
        this.rightWinch.setInverted(RobotMap.CLIMB_RIGHT_WINCH_CONFIG.inverted);

        this.arm.setNeutralMode(RobotMap.CLIMB_ARM_CONFIG.neutralMode);
        this.leftWinch.setNeutralMode(RobotMap.CLIMB_LEFT_WINCH_CONFIG.neutralMode);
        this.rightWinch.setNeutralMode(RobotMap.CLIMB_RIGHT_WINCH_CONFIG.neutralMode);
    }

    public void raiseArm() {
        this.arm.set(ControlMode.PercentOutput, RobotMap.CLIMB_ARM_MOVE_SPEED);
    }

    public void lowerArm() {
        this.arm.set(ControlMode.PercentOutput, -RobotMap.CLIMB_ARM_MOVE_SPEED);
    }

    public void stopArm() {
        this.arm.stopMotor();
    }

    /**
     * Sets winch 1 at the specified percent output (or stops it, if power = 0).
     * @param power the percent of full output at which to raise, [0, 1].
     */
    public void setLeftWinch(double power) {
        power = constrainWinchOutput(power);
        this.leftWinch.set(ControlMode.PercentOutput, power);
    }

    /**
     * Sets winch 2 at the specified percent output (or stops it, if power = 0).
     * @param power the percent of full output at which to raise, [0, 1].
     */
    public void setRightWinch(double power) {
        power = constrainWinchOutput(power);
        this.rightWinch.set(ControlMode.PercentOutput, power);
    }

    /**
     * Gets the state of the bottom limit switch.
     * @return true if the switch is activated, false otherwise.
     */
    public boolean armIsRetracted() {
        return this.retractedLimitSwitch.get();
    }

    /**
     * Gets the state of the top limit switch.
     * @return true if the switch is activated, false otherwise.
     */
    public boolean armIsDeployed() {
        return this.deployedLimitSwitch.get();
    }

    /**
     * Safety-checks the power to be fed to a winch by clamping it to [-100%, 100%] of safe range
     * and then ensuring that it is positive—winches can only be raised, not lowered.
     * @param power the raw, unchecked percent-output value.
     * @return the power, constrained to safe bounds for the winch motors.
     */
    private double constrainWinchOutput(double power) {
        if (power > 1) power = 1;
        if (power < -1) power = -1;
        power *= RobotMap.CLIMB_WINCH_MAX_SPEED;
        return power;
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ClimberCommand());
    }

    public void logToSmartDashboard() {
        SmartDashboard.putBoolean("climber/arm_retracted", armIsRetracted());
        SmartDashboard.putBoolean("climber/arm_deployed", armIsDeployed());
    }
}
