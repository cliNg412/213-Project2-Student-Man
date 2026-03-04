package util;

import java.util.Calendar;

/**
 * A simple Date class in mm/dd/yyyy format used by the registration system.
 * Provides validation, comparison, equality, and string representation.
 *
 * @author HIrotaku Hayashi hh635
 * @author junjie Xia jx309
 */
public class Date implements Comparable<Date> {
    private int year;
    private int month;
    private int day;

    /**
     * Constructs a Date object from a string in "mm/dd/yyyy" format.
     *
     * @param mmddyyyy the date string in "mm/dd/yyyy" format
     */
    public Date(String mmddyyyy) {
        String[] parts = mmddyyyy.split("/");
        this.month = Integer.parseInt(parts[0]);
        this.day = Integer.parseInt(parts[1]);
        this.year = Integer.parseInt(parts[2]);
    }

    /**
     * Returns the year value.
     *
     * @return the year
     */
    public int getYear() { return year; }

    /**
     * Returns the month value.
     *
     * @return the month (1-12)
     */
    public int getMonth() { return month; }

    /**
     * Returns the day value.
     *
     * @return the day of month
     */
    public int getDay() { return day; }

    /**
     * Checks whether this date is a valid calendar date.
     * This method verifies month range, day range, and leap-year rules for February.
     *
     * @return true if the date is a valid calendar date; otherwise false
     */
    public boolean isValid() {
        if (year < 1 || month < 1 || month > 12 || day < 1) return false;

        int[] days = {0,31,28,31,30,31,30,31,31,30,31,30,31};
        int max = days[month];
        if (month == 2 && isLeapYear(year)) max = 29;
        return day <= max;
    }

    /**
     * Determines whether the given year is a leap year.
     *
     * @param y the year to check
     * @return true if y is a leap year; otherwise false
     */
    private boolean isLeapYear(int y) {
        if (y % 400 == 0) return true;
        if (y % 100 == 0) return false;
        return y % 4 == 0;
    }

    /**
     * Checks whether this date is today or a future date.
     * Used to enforce the rule that DOB cannot be today or in the future.
     *
     * @return true if this date is today or later; otherwise false
     */
    public boolean isTodayOrFuture() {
        Calendar cal = Calendar.getInstance();
        int cy = cal.get(Calendar.YEAR);
        int cm = cal.get(Calendar.MONTH) + 1;
        int cd = cal.get(Calendar.DAY_OF_MONTH);

        Date today = new Date(cm + "/" + cd + "/" + cy);
        return this.compareTo(today) >= 0;
    }

    /**
     * Compares this date with another date chronologically.
     *
     * @param o the other Date to compare with
     * @return a negative integer if this date is earlier than o;
     *         zero if equal;
     *         a positive integer if this date is later than o
     */
    @Override
    public int compareTo(Date o) {
        if (this.year != o.year) return this.year - o.year;
        if (this.month != o.month) return this.month - o.month;
        return this.day - o.day;
    }

    /**
     * Checks equality between this Date and another object.
     * Two dates are equal if they have the same year, month, and day.
     *
     * @param obj the object to compare
     * @return true if obj is a Date with the same y/m/d; otherwise false
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Date)) return false;
        Date d = (Date) obj;
        return year == d.year && month == d.month && day == d.day;
    }

    /**
     * Returns the string representation of this date in "m/d/yyyy" format.
     *
     * @return the formatted date string
     */
    @Override
    public String toString() {
        return month + "/" + day + "/" + year;
    }

    /**
     * Testbed main() for unit testing Date.isValid() as required by the project.
     * Includes 4 invalid and 2 valid test cases.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Put your 4 invalid + 2 valid tests here as required.
        Date[] tests = {
                new Date("2/29/2003"),
                new Date("4/31/2003"),
                new Date("13/31/2003"),
                new Date("-1/31/2003"),
                new Date("2/29/2004"),
                new Date("12/31/2003")
        };
        for (Date d : tests) {
            System.out.println(d + " -> " + d.isValid());
        }
    }
}
