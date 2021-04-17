package frc.team4373.robot.input;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.team4373.robot.RobotMap;
import frc.team4373.robot.commands.drivetrain.ResetNorthCommand;
import frc.team4373.robot.commands.shooter.ShooterFallbackShootCommand;
import frc.team4373.robot.commands.shooter.ShooterShootCommand;
import frc.team4373.robot.input.filters.*;

/**
 * OI provides access to operator interface devices.
 */
public final class OI {
    private static volatile OI oi = null;
    private RooJoystick driveJoystick;
    private RooJoystick operatorJoystick;

    private Button shootButton;
    private Button fallbackShootButton;
    private Button resetNorthButton;

    private OI() {
        //FIXME: These filters need to be tested.
        /*
        FineGrainedPiecewiseFilter: https://www.desmos.com/calculator/3rhniwotk2
        XboxAxisFilter: https://www.desmos.com/calculator/r6t3rzmh2x

        Template for new filters: https://www.desmos.com/calculator/jbb9fc5zwh
         */

        this.driveJoystick = new RooJoystick(RobotMap.DRIVE_JOYSTICK_PORT,
                new LogitechFilter(), RobotMap.JOYSTICK_DEFAULT_DEADZONE);
        driveJoystick.configureAxis(driveJoystick.getZChannel(),
                new SwerveTwistFilter(), 0.05);
        driveJoystick.configureAxis(driveJoystick.getThrottleChannel(),
                new LogitechSliderAxisFilter(), 0.01);
        this.operatorJoystick = new RooJoystick(RobotMap.OPERATOR_JOYSTICK_PORT,
                new XboxFilter(), RobotMap.JOYSTICK_DEFAULT_DEADZONE);
        operatorJoystick.configureAxis(RobotMap.OPER_ADJUST_SHOOT_SPEED_AXIS,
                new XboxAdjustShootSpeedFilter(), RobotMap.JOYSTICK_DEFAULT_DEADZONE);
        operatorJoystick.configureAxis(RobotMap.OPER_WINCH_AXIS,
                new XboxWinchFilter(), RobotMap.JOYSTICK_DEFAULT_DEADZONE);

        this.resetNorthButton = new JoystickButton(this.driveJoystick,
                RobotMap.DRIVE_RESET_NORTH_BUTTON);
        this.resetNorthButton.whenPressed(new ResetNorthCommand());

        this.shootButton = new JoystickButton(this.operatorJoystick,
                RobotMap.OPER_SHOOT_BUTTON);
         this.shootButton.whileHeld(new ShooterShootCommand());

        this.fallbackShootButton = new JoystickButton(this.operatorJoystick,
                RobotMap.OPER_FALLBACK_SHOOT_BUTTON);
        this.fallbackShootButton.whileHeld(new ShooterFallbackShootCommand());
    }

    /**
     * The getter for the OI singleton.
     *
     * @return The static OI singleton object.
     */
    public static OI getInstance() {
        if (oi == null) {
            synchronized (OI.class) {
                if (oi == null) {
                    oi = new OI();
                }
            }
        }
        return oi;
    }

    /**
     * Gets the drive joystick controlling the robot.
     * @return The drive joystick controlling the robot.
     */
    public RooJoystick getDriveJoystick() {
        return this.driveJoystick;
    }

    /**
     * Gets the operator joystick controlling the robot.
     * @return The operator joystick controlling the robot.
     */
    public RooJoystick getOperatorJoystick() {
        return this.operatorJoystick;
    }
}
