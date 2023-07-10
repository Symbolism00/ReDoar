package eco.solutions.model;

public enum UserRole {

    ADMIN(0, "Administrator"),
    PERSON(1, "Person"),
    ORGANIZATION(2, "Organization");

    private final int id;
    private final String designation;

    UserRole(int id, String designation){
        this.id = id;
        this.designation = designation;
    }
}
