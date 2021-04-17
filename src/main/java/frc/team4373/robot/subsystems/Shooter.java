package frc.team4373.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.shooter.ShooterCommand;

/**
 * A Javadoc template. TODO: Update Shooter Javadoc.
 */
public class Shooter extends Subsystem {
    private static volatile Shooter instance;

    /**
     * Gets the shooter instance.
     * @return the singular {@link Shooter} instance.
     */
    public static Shooter getInstance() {
        if (instance == null) {
            synchronized (Shooter.class) {
                if (instance == null) {
                    instance = new Shooter();
                }
            }
        }
        return instance;
    }

    private WPI_TalonSRX shooterMotor1;
    private WPI_TalonSRX shooterMotor2;

    private Shooter() {
        RobotMap.MotorConfig shooterMotor1Config = RobotMap.SHOOTER_MOTOR_1_CONFIG;
        RobotMap.MotorConfig shooterMotor2Config = RobotMap.SHOOTER_MOTOR_2_CONFIG;
        shooterMotor1 = new WPI_TalonSRX(shooterMotor1Config.id);
        shooterMotor2 = new WPI_TalonSRX(shooterMotor2Config.id);
        this.shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder);

        shooterMotor1.setInverted(shooterMotor1Config.inverted);
        shooterMotor2.setInverted(shooterMotor2Config.inverted);

        this.shooterMotor1.setNeutralMode(shooterMotor1Config.neutralMode);
        this.shooterMotor2.setNeutralMode(shooterMotor2Config.neutralMode);

        this.shooterMotor1.setSensorPhase(shooterMotor1Config.encoderPhase);

        this.shooterMotor1.config_kF(RobotMap.PID_IDX, shooterMotor1Config.gains.kF);
        this.shooterMotor1.config_kP(RobotMap.PID_IDX, shooterMotor1Config.gains.kP);
        this.shooterMotor1.config_kI(RobotMap.PID_IDX, shooterMotor1Config.gains.kI);
        this.shooterMotor1.config_kD(RobotMap.PID_IDX, shooterMotor1Config.gains.kD);

        this.shooterMotor2.follow(this.shooterMotor1);
    }

    /**
     * Sets the velocity of the shooter as a fraction of max speed.
     * @param speed the velocity of the shooter, [0, 1].
     */
    public void setVelocity(double speed) {
        this.setVelocityRaw(speed * RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS);
    }
    /**
     * Sets the velocity of the shooter in native units.
     * @param speed the velocity of the shooter, [0, MAX_SPEED].
     */
    public void setVelocityRaw(double speed) {
        SmartDashboard.putNumber("velocityRawPercent", speed);
        speed = Math.max(0, Math.min(speed, RobotMap.SHOOTER_MAX_SPEED_NATIVE_UNITS));
        shooterMotor1.set(ControlMode.Velocity, speed);
    }

    /**
     * Stops the shooter.
     */
    public void stop() {
        shooterMotor1.stopMotor();
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new ShooterCommand());
    }

    public boolean isRunning() {
        return Math.abs(shooterMotor1.getSelectedSensorVelocity()) > 0;
    }

    public void logToSmartDashboard() {
        SmartDashboard.putNumber("shooter/speed", shooterMotor1.getSelectedSensorVelocity());
        SmartDashboard.putBoolean("shooter/running", isRunning());
    }
}
