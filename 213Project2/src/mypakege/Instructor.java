package mypakege;

/**
 *@author HIrotaku Hayashi hh635
 *@author junjie Xia jx309
 */
public enum Instructor {
    PATEL, LIM, ZIMNES, HARPER, KAUR, TAYLOR, RAMESH, CERAVOLO, BROWN;

    /**
     * Convert an input token to an Instructor enum value.
     * The matching is case-insensitive. If the token does not match any instructor,
     * this method returns null.
     *
     * @param token the instructor name token from user input (e.g., "harper")
     * @return the corresponding Instructor value, or null if token is null/invalid
     */
    public static Instructor from(String token) {
        if (token == null) return null;
        String s = token.toUpperCase();
        for (Instructor i : values()) {
            if (i.name().equals(s)) return i;
        }
        return null;
    }
}
