package eco.solutions.model;

public enum ReliabilityLevel {

    VERY_RELIABLE(0, "Very Reliable"),
    RELIABLE(1, "Reliable"),
    NEUTRAL(2, "Neutral"),
    UNRELIABLE(3, "Unreliable"),
    VERY_UNRELIABLE(4, "Very Unreliable");

    private final int id;
    private final String designation;

    ReliabilityLevel(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    public String getDesignation() {
        return designation;
    }

    /**
     * Method that gets the reliability level by its id
     * @param id the id of the reliability level
     * @return the reliability level
     */
    public static ReliabilityLevel getById(Integer id){
        ReliabilityLevel[] reliabilityLevels = ReliabilityLevel.values();
        return id != null && id >= 0 && reliabilityLevels.length > id ? reliabilityLevels[id] : null;
    }
}
