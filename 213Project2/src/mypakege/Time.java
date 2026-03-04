package mypakege;

/**
 * Represents the six class periods in a day and their start times.
 * Each enum constant stores a period number and the corresponding start time.
 *
 * @author HIrotaku Hayashi hh635
 * @author junjie Xia jx309
 */
public enum Time {
    P1(1, "8:30"),
    P2(2, "10:20"),
    P3(3, "12:10"),
    P4(4, "14:00"),
    P5(5, "15:50"),
    P6(6, "17:40");

    private final int period;
    private final String time;

    /**
     * Constructs a Time enum value with its period number and start time.
     *
     * @param period the period number (1-6)
     * @param time the start time string (e.g., "8:30")
     */
    Time(int period, String time) {
        this.period = period;
        this.time = time;
    }

    /**
     * Returns the numeric period for this Time value.
     *
     * @return the period number (1-6)
     */
    public int getPeriod() {
        return period;
    }

    /**
     * Returns the start time string for this Time value.
     *
     * @return the start time (e.g., "8:30")
     */
    public String getTime() {
        return time;
    }

    /**
     * Checks whether the given integer is a valid period number.
     *
     * @param p the period number to validate
     * @return true if p is between 1 and 6 (inclusive); otherwise false
     */
    public static boolean isValidPeriod(int p) {
        return p >= 1 && p <= 6;
    }

    /**
     * Returns the start time string for the given period number.
     * If the period number is invalid, returns an empty string.
     *
     * @param p the period number (1-6)
     * @return the start time string for period p, or "" if not found
     */
    public static String timeOf(int p) {
        for (Time t : values()) {
            if (t.period == p) return t.time;
        }
        return "";
    }
}
