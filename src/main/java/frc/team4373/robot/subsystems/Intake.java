package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.intake.IntakeCommand;

/**
 * A Javadoc template. TODO: Update Intake Javadoc.
 */
public class Intake extends Subsystem {
    private static volatile Intake instance;

    /**
     * The getter for the Intake class.
     * @return the singleton Intake object.
     */
    public static Intake getInstance() {
        if (instance == null) {
            synchronized (Intake.class) {
                if (instance == null) {
                    instance = new Intake();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX deployMotor;
    private WPI_TalonSRX intakeMotor;
    private DigitalInput deployedLimitSwitch;
    private DigitalInput retractedLimitSwitch;

    private Intake() {
        deployMotor = new WPI_TalonSRX(RobotMap.INTAKE_DEPLOY_MOTOR_CONFIG.id);
        deployMotor.setInverted(RobotMap.INTAKE_DEPLOY_MOTOR_CONFIG.inverted);
        deployMotor.setNeutralMode(RobotMap.INTAKE_DEPLOY_MOTOR_CONFIG.neutralMode);

        intakeMotor = new WPI_TalonSRX(RobotMap.INTAKE_DRAW_MOTOR_CONFIG.id);
        intakeMotor.setInverted(RobotMap.INTAKE_DRAW_MOTOR_CONFIG.inverted);
        intakeMotor.setNeutralMode(RobotMap.INTAKE_DRAW_MOTOR_CONFIG.neutralMode);

        this.deployedLimitSwitch = new DigitalInput(RobotMap.INTAKE_DEPLOYED_LIMIT_SWITCH);
        this.retractedLimitSwitch = new DigitalInput(RobotMap.INTAKE_RETRACTED_LIMIT_SWITCH);
    }

    public void run() {
        intakeMotor.set(ControlMode.PercentOutput, RobotMap.INTAKE_DRAW_SPEED);
    }

    public void stop() {
        intakeMotor.stopMotor();
    }

    public void dislodge() {
        intakeMotor.set(ControlMode.PercentOutput, -RobotMap.INTAKE_DISLODGE_SPEED);
    }

    /**
     * Deploys the intake. This will only run the deploy motor if the intake is retracted.
     */
    public void deploy() {
        if (deployedLimitSwitch.get()) {
            deployMotor.stopMotor();
        } else {
            deployMotor.set(ControlMode.PercentOutput, RobotMap.INTAKE_DEPLOY_SPEED);
        }
    }

    public void retract() {
        if (deployedLimitSwitch.get()) {
            deployMotor.set(ControlMode.PercentOutput, RobotMap.INTAKE_RETRACT_SPEED);
        } else {
            deployMotor.stopMotor();
        }
    }

    public boolean isDeployed() {
        return deployedLimitSwitch.get();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new IntakeCommand());
    }

    public void logToSmartDashboard() {
        SmartDashboard.putBoolean("intake/deployed", isDeployed());
        SmartDashboard.putBoolean("intake/retracted", retractedLimitSwitch.get());
        SmartDashboard.putString("intake/command", this.getCurrentCommandName());
    }
}
