package mypakege;

/**
 * Represents the supported student majors and the school each major belongs to.
 *
 * @author HIrotaku Hayashi hh635
 * @author junjie Xia jx309
 */
public enum Major {
    CS("School of Arts & Sciences"),
    ECE("School of Engineering"),
    MATH("School of Arts & Sciences"),
    ITI("School of Communication and Information"),
    BAIT("Rutgers Business School");

    private final String school;

    /**
     * Constructs a Major enum constant with its school name.
     *
     * @param school the school/college that this major belongs to
     */
    Major(String school) {
        this.school = school;
    }

    /**
     * Returns the school/college name associated with this major.
     *
     * @return the school name string
     */
    public String getSchool() {
        return school;
    }

    /**
     * Converts an input token (case-insensitive) into a Major enum value.
     * For example, "cs" or "CS" will return {@link #CS}.
     *
     * @param token the input major token (e.g., "cs", "BAIT")
     * @return the matching Major, or null if no match exists
     */
    public static Major from(String token) {
        if (token == null) return null;
        String s = token.toUpperCase();
        for (Major m : values()) {
            if (m.name().equals(s)) return m;
        }
        return null;
    }

    /**
     * Required output format: "MAJOR,School Name" (no surrounding brackets).
     */
    @Override
    public String toString() {
        return name() + "," + school;
    }
}
