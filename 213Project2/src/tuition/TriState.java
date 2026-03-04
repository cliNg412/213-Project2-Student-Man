package tuition;

import mypakege.Major;
import mypakege.Profile;

public class TriState extends NonResident {
    private String state;
    private static final double DISCOUNT_NY = 4000;
    private static final double DISCOUNT_CT = 5000;

    public TriState(Profile profile, Major major, int creditsCompleted, String state) {
        super(profile, major, creditsCompleted);
        this.state = state == null ? "" : state.toUpperCase();
    }

    public String getState() {
        return state;
    }

    private double discount() {
        if ("NY".equals(state)) return DISCOUNT_NY;
        if ("CT".equals(state)) return DISCOUNT_CT;
        return 0;
    }

    @Override
    public double tuition(int creditsEnrolled) {
        boolean fullTime = creditsEnrolled >= 12;
        double universityFee = fullTime ? UNIVERSITY_FEE_FULL : UNIVERSITY_FEE_FULL * 0.5;
        if (fullTime && creditsCompleted >= 120) return universityFee;
        double tuition;
        if (fullTime) {
            tuition = FULL_TIME_TUITION - discount();
            if (tuition < 0) tuition = 0;
            if (creditsEnrolled > 16) tuition += (creditsEnrolled - 16) * PART_TIME_PER_CREDIT;
        } else {
            tuition = creditsEnrolled * PART_TIME_PER_CREDIT;
        }
        return tuition + universityFee;
    }

    @Override
    public String toString() {
        return getProfile() + " [" + getMajor().name() + "," + getMajor().getSchool() + "] credits earned: "
                + getCreditsCompleted() + " [" + getStanding() + "][tri-state:" + state + "]";
    }
}