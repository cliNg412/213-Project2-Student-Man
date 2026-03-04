package mypakege;

import util.Date;

/**
 * A Profile uniquely identifies a student by last name, first name, and date of birth.
 * It supports comparisons by last name, then first name, then date of birth.
 *
 * @author HIrotaku Hayashi hh635
 * @author junjie Xia jx309
 */
public class Profile implements Comparable<Profile> {
    private String fname;
    private String lname;
    private Date dob;

    /**
     * Constructs a Profile with first name, last name, and date of birth.
     *
     * @param fname the first name
     * @param lname the last name
     * @param dob   the date of birth
     */
    public Profile(String fname, String lname, Date dob) {
        this.fname = fname;
        this.lname = lname;
        this.dob = dob;
    }

    /**
     * Returns the first name.
     *
     * @return the first name
     */
    public String getFname() {
        return fname;
    }

    /**
     * Returns the last name.
     *
     * @return the last name
     */
    public String getLname() {
        return lname;
    }

    /**
     * Returns the date of birth.
     *
     * @return the date of birth
     */
    public Date getDob() {
        return dob;
    }

    /**
     * Compares this Profile with another Profile for ordering.
     * Ordering is by last name (case-insensitive), then first name (case-insensitive),
     * then date of birth (chronological).
     *
     * @param o the other Profile to compare with
     * @return a negative integer if this is less than o,
     *         zero if equal,
     *         or a positive integer if greater than o
     */
    @Override
    public int compareTo(Profile o) {
        int last = this.lname.compareToIgnoreCase(o.lname);
        if (last != 0) return last;
        int first = this.fname.compareToIgnoreCase(o.fname);
        if (first != 0) return first;
        return this.dob.compareTo(o.dob);
    }

    /**
     * Checks whether this Profile is equal to another object.
     * Two profiles are equal if their first name and last name match case-insensitively
     * and their dates of birth are equal.
     *
     * @param obj the object to compare
     * @return true if obj is a Profile with the same identifying fields; false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Profile)) return false;
        Profile p = (Profile) obj;
        return this.fname.equalsIgnoreCase(p.fname)
                && this.lname.equalsIgnoreCase(p.lname)
                && this.dob.equals(p.dob);
    }

    /**
     * Returns the string representation of the profile in the format:
     * [First Last mm/dd/yyyy]
     *
     * @return the formatted string representation
     */
    @Override
    public String toString() {
        return "[" + fname + " " + lname + " " + dob + "]";
    }

    /**
     * Testbed main() for Profile.compareTo().
     * Required by the project rubric:
     *  - 3 test cases return -1 (or negative)
     *  - 3 test cases return  1 (or positive)
     *  - 1 test case returns  0
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Base profile
        Profile base = new Profile("John", "Doe", new Date("1/2/2007"));

        // ---- 1 case that returns 0 ----
        Profile eq = new Profile("john", "doe", new Date("1/2/2007"));
        System.out.println("0:  " + base.compareTo(eq)); // expect 0

        // ---- 3 cases that return negative (base < other) ----
        Profile neg1 = new Profile("John", "Zoo", new Date("1/2/2007"));     // lname Doe < Zoo
        Profile neg2 = new Profile("Zoe", "Doe", new Date("1/2/2007"));      // fname John < Zoe
        Profile neg3 = new Profile("John", "Doe", new Date("1/3/2007"));     // dob 1/2 < 1/3
        System.out.println("-:  " + base.compareTo(neg1)); // expect < 0
        System.out.println("-:  " + base.compareTo(neg2)); // expect < 0
        System.out.println("-:  " + base.compareTo(neg3)); // expect < 0

        // ---- 3 cases that return positive (base > other) ----
        Profile pos1 = new Profile("John", "Able", new Date("1/2/2007"));    // lname Doe > Able
        Profile pos2 = new Profile("Adam", "Doe", new Date("1/2/2007"));     // fname John > Adam
        Profile pos3 = new Profile("John", "Doe", new Date("12/31/2006"));   // dob 1/2/2007 > 12/31/2006
        System.out.println("+:  " + base.compareTo(pos1)); // expect > 0
        System.out.println("+:  " + base.compareTo(pos2)); // expect > 0
        System.out.println("+:  " + base.compareTo(pos3)); // expect > 0
    }
}
