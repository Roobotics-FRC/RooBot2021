package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.hopper.HopperCommand;

/**
 * A Javadoc template. TODO: Update Hopper Javadoc.
 */
public class Hopper extends Subsystem {
    private static volatile Hopper instance;

    /**
     * The getter for the Hopper class.
     * @return the singleton Hopper object.
     */
    public static Hopper getInstance() {
        if (instance == null) {
            synchronized (Hopper.class) {
                if (instance == null) {
                    instance = new Hopper();
                }
            }
        }
        return instance;
    }

    private WPI_VictorSPX motor;

    private Hopper() {
        motor = new WPI_VictorSPX(RobotMap.INTAKE_HOPPER_MOTOR_CONFIG.id);
        motor.setInverted(RobotMap.INTAKE_HOPPER_MOTOR_CONFIG.inverted);
        motor.setNeutralMode(RobotMap.INTAKE_HOPPER_MOTOR_CONFIG.neutralMode);
    }

    public void run() {
        motor.set(ControlMode.PercentOutput, RobotMap.HOPPER_SPEED);
    }

    public void dislodge() {
        motor.set(ControlMode.PercentOutput, -RobotMap.HOPPER_DISLODGE_SPEED);
    }

    public void stop() {
        motor.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new HopperCommand());
    }

    public void logToSmartDashboard() {

    }
}
