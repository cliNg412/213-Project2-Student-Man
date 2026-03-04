package mypakege;

/**
 * Represents a student's academic standing, determined by completed credits.
 *
 * Standing rules:
 * <ul>
 *   <li>Credits &lt; 30: Freshman</li>
 *   <li>Credits &lt; 60: Sophomore</li>
 *   <li>Credits &lt; 90: Junior</li>
 *   <li>Credits &lt; 90: Senior</li>
 * </ul>
 *
 * @author HIrotaku Hayashi hh635
 * @author junjie Xia jx309
 */
public enum Standing {
    FRESHMAN("Freshman"),
    SOPHOMORE("Sophomore"),
    JUNIOR("Junior"),
    SENIOR("Senior");

    private final String label;

    /**
     * Constructs a Standing enum constant with a display label.
     *
     * @param label the display label for this standing
     */
    Standing(String label) {
        this.label = label;
    }

    /**
     * Returns the display label of this standing.
     *
     * @return the label string
     */
    @Override
    public String toString() {
        return label;
    }

    /**
     * Determines the standing based on completed credits.
     *
     * @param credits the number of completed credits
     * @return the corresponding standing
     */
    public static Standing fromCredits(int credits) {
        if (credits < 30) return FRESHMAN;
        if (credits < 60) return SOPHOMORE;
        if (credits < 90) return JUNIOR;
        return SENIOR;
    }

    /**
     * Checks whether this standing meets (is at least) a required standing.
     * For example, SENIOR meets JUNIOR, but FRESHMAN does not meet SOPHOMORE.
     *
     * @param req the required minimum standing
     * @return true if this standing is >= required standing; false otherwise
     */
    public boolean meets(Standing req) {
        return this.ordinal() >= req.ordinal();
    }
}
