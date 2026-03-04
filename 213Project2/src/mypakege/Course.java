package mypakege;

/**
 * Enum representing the predefined courses in the registration system.
 * Each course has a course code, credit hours, a minimum standing prerequisite,
 * and an optional "major only" restriction.
 *
 * @author HIrotaku Hayashi hh635
 * @author junjie Xia jx309
 */
public enum Course {
    CS100("CS100", 4, Standing.FRESHMAN, null),
    CS200("CS200", 4, Standing.SOPHOMORE, null),
    CS300("CS300", 4, Standing.JUNIOR, Major.CS),
    CS400("CS400", 4, Standing.JUNIOR, Major.CS),
    CS442("CS442", 3, Standing.JUNIOR, null),
    PHY100("PHY100", 5, Standing.FRESHMAN, null),
    PHY200("PHY200", 5, Standing.SOPHOMORE, null),
    ECE300("ECE300", 4, Standing.JUNIOR, Major.ECE),
    ECE400("ECE400", 4, Standing.SENIOR, Major.ECE),
    CCD("CCD", 4, Standing.FRESHMAN, null),
    HST("HST", 3, Standing.FRESHMAN, null);

    private final String code;
    private final int credits;
    private final Standing minStanding;
    private final Major majorOnly;

    /**
     * Constructs a Course enum value with its course information.
     *
     * @param code        the course code (e.g., "CS100")
     * @param credits     the number of credits for the course
     * @param minStanding the minimum standing required to enroll
     * @param majorOnly   the required major if the course is restricted; null if no major restriction
     */
    Course(String code, int credits, Standing minStanding, Major majorOnly) {
        this.code = code;
        this.credits = credits;
        this.minStanding = minStanding;
        this.majorOnly = majorOnly;
    }

    /**
     * Returns the course code (e.g., "CS100").
     *
     * @return the course code
     */
    public String getCode() {
        return code;
    }

    /**
     * Returns the number of credits for this course.
     *
     * @return the credit hours
     */
    public int getCredits() {
        return credits;
    }

    /**
     * Returns the minimum standing required to enroll in this course.
     *
     * @return the minimum required standing
     */
    public Standing getMinStanding() {
        return minStanding;
    }

    /**
     * Returns the major restriction for this course.
     *
     * @return the required major if restricted; null if not restricted to a major
     */
    public Major getMajorOnly() {
        return majorOnly;
    }

    /**
     * Converts a user input token into a matching Course enum value.
     * The lookup is case-insensitive (e.g., "cs100" matches CS100).
     *
     * @param token the input token representing a course code
     * @return the corresponding Course if found; otherwise null
     */
    public static Course from(String token) {
        if (token == null) return null;
        String s = token.toUpperCase();
        for (Course c : values()) {
            if (c.code.equals(s)) return c;
        }
        return null;
    }
}
