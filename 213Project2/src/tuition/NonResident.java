package tuition;

import mypakege.Major;
import mypakege.Profile;

public class NonResident extends Student {

    // constants (from PDF)
    protected static final double FULL_TIME_TUITION = 35758;
    protected static final double PART_TIME_PER_CREDIT = 1162;
    protected static final double UNIVERSITY_FEE_FULL = 3891;

    public NonResident(Profile profile, Major major, int creditsCompleted) {
        super(profile, major, creditsCompleted);
    }

    @Override
    public double tuition(int creditsEnrolled) {
        boolean fullTime = creditsEnrolled >= 12;
        double universityFee = fullTime ? UNIVERSITY_FEE_FULL : UNIVERSITY_FEE_FULL * 0.5;

        // tuition remission: only full-time
        if (fullTime && creditsCompleted >= 120) {
            return universityFee;
        }

        double tuition;
        if (fullTime) {
            tuition = FULL_TIME_TUITION;
            if (creditsEnrolled > 16) {
                tuition += (creditsEnrolled - 16) * PART_TIME_PER_CREDIT;
            }
        } else {
            tuition = creditsEnrolled * PART_TIME_PER_CREDIT;
        }

        return tuition + universityFee;
    }

    @Override
    protected String residencyTag() {
        return "[non-resident]";
    }
}