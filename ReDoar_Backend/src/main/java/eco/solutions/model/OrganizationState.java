package eco.solutions.model;

import lombok.Getter;

public enum OrganizationState {

    PENDING(0, "Pending", true, false, false),
    ACCEPTED(1, "Accepted", false, true, false),
    REJECTED(2, "Rejected", false, false, true);

    private final int id;
    @Getter
    private final String designation;
    @Getter
    private final boolean isPending;
    @Getter
    private final boolean isAccepted;
    @Getter
    private final boolean isRejected;

    OrganizationState(int id, String designation, boolean isPending, boolean isAccepted, boolean isRejected){
        this.id = id;
        this.designation = designation;
        this.isPending = isPending;
        this.isAccepted = isAccepted;
        this.isRejected = isRejected;
    }

    /**
     * Method that gets the organization state by its id
     * @param id the id of the organization state
     * @return the organization state
     */
    public static OrganizationState getById(Integer id){
        OrganizationState[] organizationStates = OrganizationState.values();
        return id != null && id >= 0 && organizationStates.length > id ? organizationStates[id] : null;
    }
}
