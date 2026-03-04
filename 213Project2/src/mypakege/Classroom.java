package mypakege;

/**
 * Enum representing predefined classroom locations (room/building/campus).
 *@author HIrotaku Hayashi hh635
 *@author junjie Xia jx309
 */
public enum Classroom {
    HIL114("HIL114", "Hill Center", "Busch"),
    ARC103("ARC103", "Allison Road Classroom", "Busch"),
    BEAUD("BEAUD", "Beck Hall", "Livingston"),
    TIL232("TIL232", "Tillett Hall", "Livingston"),
    AB2225("AB2225", "Academic Building", "College Avenue"),
    MU302("MU302", "Murray Hall", "College Avenue");

    private final String room;
    private final String building;
    private final String campus;

    /**
     * Constructs a Classroom enum value with its room number, building name, and campus name.
     *
     * @param room     the classroom room number (e.g., "HIL114")
     * @param building the building name (e.g., "Hill Center")
     * @param campus   the campus name (e.g., "Busch")
     */
    Classroom(String room, String building, String campus) {
        this.room = room;
        this.building = building;
        this.campus = campus;
    }

    /**
     * Returns the building name of this classroom.
     *
     * @return the building name
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Returns the campus name of this classroom.
     *
     * @return the campus name
     */
    public String getCampus() {
        return campus;
    }

    public String getRoom() {
        return room;
    }

    /**
     * Converts a user input token into a matching Classroom enum value.
     * The lookup is case-insensitive (e.g., "hil114" and "HIL114" both match HIL114).
     *
     * @param token the input token representing a room number
     * @return the corresponding Classroom enum value if found; otherwise null
     */
    public static Classroom from(String token) {
        if (token == null) return null;
        String s = token.toUpperCase();
        for (Classroom c : values()) {
            if (c.room.equals(s)) return c;
        }
        return null;
    }

    /**
     * Returns the formatted string representation required by the project output.
     *
     * @return a string in the form "[ROOM, Building, Campus]"
     */
    @Override
    public String toString() {
        return "[" + room + ", " + building + ", " + campus + "]";
    }
}
