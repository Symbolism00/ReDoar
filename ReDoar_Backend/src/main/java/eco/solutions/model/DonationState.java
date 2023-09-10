package eco.solutions.model;

import lombok.Getter;

public enum DonationState {

    CREATED(0, "Created"),
    REQUESTED(1, "Requested"),
    ACCEPTED(2, "Accepted"),
    REJECTED(3, "Rejected"),
    ONGOING(4, "Ongoing"),
    FINISHED(5, "Finished"),
    CANCELED(6, "Canceled"),
    EXPIRED(7, "Expired");


    private final int id;

    @Getter
    private final String designation;

    DonationState(int id, String designation) {
        this.id = id;
        this.designation = designation;
    }

    /**
     * Method that gets the donation state by its id
     * @param id the id of the donation state
     * @return the donation state
     */
    public static DonationState getById(Integer id){
        DonationState[] donationStates = DonationState.values();
        return id != null && id >= 0 && donationStates.length > id ? donationStates[id] : null;
    }
}
