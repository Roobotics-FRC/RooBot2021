package frc.team4373.robot;

/**
 * A Javadoc template. TODO: Update Utils Javadoc.
 */
public class Utils {
    private Utils() {}

    /**
     * Checks whether a given double is equal to zero within an equality threshold.
     * @param n the double to check.
     * @return true if it is zero, or false otherwise.
     */
    public static boolean isZero(double n) {
        return Math.abs(n) < RobotMap.FP_EQUALITY_THRESHOLD;
    }
}
