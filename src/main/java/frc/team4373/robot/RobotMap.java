package frc.team4373.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import frc.team4373.swerve.SwerveConfig;

/**
 * Holds various mappings and constants.
 */

public class RobotMap {

    /**
     * Gets the config for the swerve drivetrain.
     * @return the config for the drivetrain.
     */
    public static SwerveConfig getSwerveConfig() {
        SwerveConfig.RobotDimensions dimensions = new SwerveConfig.RobotDimensions(30, 30);
        SwerveConfig.PID drivePID = new SwerveConfig.PID(0, 0.2, 0.002, 0.75);
        SwerveConfig.PID altDrivePID = new SwerveConfig.PID(0, 0.075, 0.002, 0.95);
        SwerveConfig.PID rotatePID = new SwerveConfig.PID(0, 1, 0, 0.05);
        SwerveConfig.MotorConfig right1Drive = new SwerveConfig.MotorConfig(15,
                true, NeutralMode.Brake, true, drivePID);
        SwerveConfig.MotorConfig right1Rotate = new SwerveConfig.MotorConfig(16,
                true, NeutralMode.Brake, false, rotatePID);
        SwerveConfig.MotorConfig right2Drive = new SwerveConfig.MotorConfig(11,
                false, NeutralMode.Brake, true, drivePID);
        SwerveConfig.MotorConfig right2Rotate = new SwerveConfig.MotorConfig(12,
                true, NeutralMode.Brake, false, rotatePID);
        SwerveConfig.MotorConfig left1Drive = new SwerveConfig.MotorConfig(13,
                true, NeutralMode.Brake, true, altDrivePID);
        SwerveConfig.MotorConfig left1Rotate = new SwerveConfig.MotorConfig(14,
                true, NeutralMode.Brake, false, rotatePID);
        SwerveConfig.MotorConfig left2Drive = new SwerveConfig.MotorConfig(17,
                false, NeutralMode.Brake, true, altDrivePID);
        SwerveConfig.MotorConfig left2Rotate = new SwerveConfig.MotorConfig(18,
                true, NeutralMode.Brake, false, rotatePID);
        double maxWheelSpeed = 7000;
        SwerveConfig.CurrentLimitConfig currentLimitConfig = SwerveConfig.CurrentLimitConfig.NONE;
        SwerveConfig.WheelsConfig wheelsConfig = new SwerveConfig.WheelsConfig(
                right1Drive, right1Rotate, right2Drive, right2Rotate,
                left1Drive, left1Rotate, left2Drive, left2Rotate,
                maxWheelSpeed, currentLimitConfig
        );
        int pigeonID = 19;

        return new SwerveConfig(dimensions, wheelsConfig, pigeonID);
    }

    // OI devices
    public static final int DRIVE_JOYSTICK_PORT = 0;
    public static final int OPERATOR_JOYSTICK_PORT = 1;
    public static final double JOYSTICK_DEFAULT_DEADZONE = 0.09;

    // Buttons and axes

    public static final int DRIVE_RESET_NORTH_BUTTON = 7;
    /*
    public static final int DRIVE_NORTH_UP_BUTTON = 10;
    public static final int DRIVE_OWN_SHIP_UP_BUTTON = 12;
    public static final int DRIVE_SLOWER_SPEED_BUTTON = 2;
    public static final int DRIVE_DISABLE_ASSIST_BUTTON = 5;
    public static final int DRIVE_CLEAR_COMMANDS_BUTTON = 11;
    public static final int DRIVE_VISION_ALIGN_BUTTON = 6;
    public static final int DRIVE_DISABLE_BRAKE_BUTTON = 1; // drive trigger
    public static final int OPER_BALL_RELEASE_BUTTON = 5; // left bumper
    public static final int OPER_INTAKE_BUTTON = 6; // right bumper
    public static final int OPER_RAISE_L_WINCH_AXIS = 2; // L trigger
    public static final int OPER_RAISE_R_WINCH_AXIS = 3; // R trigger
    public static final int OPER_SHOOT_BUTTON = 4; // Y button
    public static final int OPER_FALLBACK_SHOOT_BUTTON = 2; // B button
    public static final int OPER_TOGGLE_SPINNER_BUTTON = 10; // right stick click
    public static final int OPER_SPINNER_REVS_BUTTON = 7; // back button
    public static final int OPER_SPINNER_COLOR_BUTTON = 8; // start button
    public static final int OPER_REVERSE_INTAKE_BUTTON = 1; // A button
    public static final int OPER_ADJUST_SHOOT_SPEED_AXIS = 1; // left stick Y
    public static final int OPER_SHOOT_FROM_WALL_BUTTON = 3; // X button
    */

    // SET TO REAL BUTTON!
    public static final int DRIVE_DISABLE_BRAKE_BUTTON = 1; // drive trigger
    public static final int DRIVE_SLOWER_SPEED_BUTTON = 2; //thumb button
    public static final int OPER_INTAKE_BUTTON = 3; // R trigger
    public static final int OPER_INTAKE_DISLODGE_BUTTON = 6; // R bumper
    public static final int OPER_INTAKE_RETRACT_BUTTON = 4;
    public static final int OPER_INTAKE_DEPLOY_BUTTON = 2;
    public static final int OPER_HOPPER_AXIS = 2; // L trigger
    public static final int OPER_HOPPER_DISLODGE_BUTTON = 5; // L bumper
    public static final int OPER_SHOOT_BUTTON = 1; // A button
    public static final int OPER_FALLBACK_SHOOT_BUTTON = 3; // X button
    public static final int OPER_WINCH_AXIS_X = 4;
    public static final int OPER_WINCH_AXIS_Y = 5; // R stick Y
    public static final int OPER_ADJUST_SHOOT_SPEED_AXIS = 1; // left stick Y


    public static final double OPER_ROTATE_VIB_INTENSITY = 0.5;

    // Speed constants
    public static final double CLIMB_ARM_MOVE_SPEED = 0.25;
    public static final double CLIMB_WINCH_MAX_SPEED = 0.8;
    public static final double SHOOTER_MAX_SPEED_NATIVE_UNITS = 32500;
    public static final double DRIVE_SLOWER_SPEED_FACTOR = 4;
    public static final double AUTON_TURN_SPEED = 0.25;
    public static final double AUTON_LINE_SHOOT_SPEED = 0.75;
    public static final double AUTON_DRIVE_SPEED = 0.2;
    public static final double GROUND_INTAKE_SPEED = 1;
    public static final double UPTAKE_INTAKE_SPEED = 0.45;
    public static final double DRIVE_ASSIST_MAX_TURN_SPEED = 0.2;
    public static final double SHOOT_FROM_WALL_SPEED = 0.63;
    public static final double INTAKE_DEPLOY_SPEED = 0.2;
    public static final double INTAKE_RETRACT_SPEED = -0.4;
    public static final double INTAKE_DRAW_SPEED = 0.5;
    public static final double INTAKE_DISLODGE_SPEED = 0.15;
    public static final double HOPPER_SPEED = 0.5;
    public static final double HOPPER_DISLODGE_SPEED = 0.3;

    // Non-motor devices
    public static final int CLIMBER_ARM_RETRACTED_LIMIT_SWITCH = 1;
    public static final int CLIMBER_ARM_EXTENDED_LIMIT_SWITCH = 0;
    public static final int INTAKE_DEPLOYED_LIMIT_SWITCH = 2;
    public static final int INTAKE_RETRACTED_LIMIT_SWITCH = 3;

    // Physical state constants
    public static final double DRIVE_WHEEL_DIAMETER_IN = 4;
    public static final double DRIVE_GEARBOX_RATIO = 20 / 3d;
    public static final double SHOOTER_TIME_TO_SPIN_UP_SEC = 1d;
    // the below offset necessitates a CLOCKWISE (positive) rotation
    public static final double INTER_CAMERA_SHOOTER_DIST_IN = 4.25;

    // Talon constants
    public static final int PID_IDX = 0;
    public static final double DRIVE_ENCODER_COUNTS_PER_REV = 4096;

    // Conversion factors
    public static final double ENCODER_UNITS_TO_INCHES = DRIVE_WHEEL_DIAMETER_IN * Math.PI
            / DRIVE_ENCODER_COUNTS_PER_REV / DRIVE_GEARBOX_RATIO;

    // Vision
    public static final double VISION_SAMPLE_COUNT = 10;
    public static final String VISION_TABLE_NAME = "Vision";
    public static final String VISION_ANG_OFFSET_FIELD = "degree_offset";
    public static final String VISION_DIST_FIELD = "current_distance";
    public static final double VISION_ALIGN_ALLOWABLE_OFFSET_DEG = 1;
    public static final double MAX_TURN_AUTON_TIME_SEC = 3;
    public static final double INTER_QUERY_DELAY_SEC = 0.45;
    public static final int MAX_ALLOWABLE_VISION_ITERATIONS = 4;

    // Motor configurations
    public static final MotorConfig SHOOTER_MOTOR_1_CONFIG =
            new MotorConfig(22, true, NeutralMode.Brake, false,
                    new PID(0, 0.05, 0,0.05));
    public static final MotorConfig SHOOTER_MOTOR_2_CONFIG =
            new MotorConfig(23, true, NeutralMode.Brake);
    public static final MotorConfig CLIMB_ARM_CONFIG =
            new MotorConfig(21, false, NeutralMode.Brake);
    public static final MotorConfig CLIMB_LEFT_WINCH_CONFIG =
            new MotorConfig(31, false, NeutralMode.Brake);
    public static final MotorConfig CLIMB_RIGHT_WINCH_CONFIG =
            new MotorConfig(32, true, NeutralMode.Brake);
    public static final MotorConfig INTAKE_DEPLOY_MOTOR_CONFIG =
            new MotorConfig(36, false, NeutralMode.Brake);
    public static final MotorConfig INTAKE_DRAW_MOTOR_CONFIG =
            new MotorConfig(24, true, NeutralMode.Brake);
    public static final MotorConfig INTAKE_HOPPER_MOTOR_CONFIG =
            new MotorConfig(35, true, NeutralMode.Brake);

    // Timeouts
    public static final double AUTON_SHOOT_TIME_SEC = 2;
    // time to wait before engaging drive-straight assist (ms)
    public static final double DRIVE_STRAIGHT_COOLDOWN_MS = 500;

    // Programmatic resources
    public static final double FP_EQUALITY_THRESHOLD = 1e-5;

    // PID
    public static final PID DRIVE_STRAIGHT_ROTATE_GAINS = new RobotMap.PID(0, 0.1, 0, 0);

    // Utility classes
    public static final class MotorConfig {
        public final int id;
        public final boolean inverted;
        public final NeutralMode neutralMode;
        public final boolean encoderPhase;
        public final PID gains;

        /**
         * Constructs a new MotorConfig for a motor using closed-loop control.
         * @param id the CAN ID of the motor.
         * @param inverted whether to invert motor output values.
         * @param neutralMode the motor's neutral mode.
         * @param encoderPhase whether the motor is out of phase with its sensor.
         * @param gains the PID gains for this motor's closed-loop control.
         */
        public MotorConfig(int id, boolean inverted,
                           NeutralMode neutralMode, boolean encoderPhase, PID gains) {
            this.id = id;
            this.inverted = inverted;
            this.neutralMode = neutralMode;
            this.encoderPhase = encoderPhase;
            this.gains = gains;
        }

        /**
         * Constructs a new MotorConfig for a motor not under closed-loop control.
         * @param id the CAN ID of the motor.
         * @param inverted whether to invert motor output values.
         * @param neutralMode the motor's neutral mode.
         */
        public MotorConfig(int id, boolean inverted, NeutralMode neutralMode) {
            this.id = id;
            this.inverted = inverted;
            this.neutralMode = neutralMode;

            this.encoderPhase = false;
            this.gains = new PID(0, 0, 0,0);
        }

    }

    // PID- and sensor-related constants
    public static final class PID {
        public final double kF;
        public final double kP;
        public final double kI;
        public final double kD;

        /**
         * Constructs a new PID parameters object.
         * @param kF feed-forward gain.
         * @param kP proportional gain.
         * @param kI integral gain.
         * @param kD derivative gain.
         */
        public PID(double kF, double kP, double kI, double kD) {
            this.kF = kF;
            this.kP = kP;
            this.kI = kI;
            this.kD = kD;
        }
    }
}