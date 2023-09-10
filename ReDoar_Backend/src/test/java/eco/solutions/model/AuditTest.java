package eco.solutions.model;

import eco.solutions.exceptions.AuditException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditTest {
    private static User userA;
    private static User userB;

    @BeforeAll
    public static void setUp(){
        userA = new User();
        userB = new User();
    }

    @Test
    public void shouldCreateBasicAuditInsertTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(userA);
        assertEquals(userA, audit.getInsUser());
        assertTrue(audit.isActive());
        assertEquals(1L, audit.getVersion());
        assertNotNull(audit.getInsDate());
    }

    @Test
    public void shouldCreateToggleableStateAuditInsertTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(userA, false);
        assertFalse(audit.isActive());
    }

    @Test
    public void shouldDoBasicAuditUpdateTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(userA);

        audit.update(userB);
        assertEquals(userB, audit.getAltUser());
        assertTrue(audit.isActive());
        assertEquals(2L, audit.getVersion());
        assertNotNull(audit.getAltDate());
    }

    @Test
    public void shouldDoToggleableAuditUpdateTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(userA);

        audit.update(userB, false);
        assertFalse(audit.isActive());
    }

    @Test
    public void shouldDoSomeAuditChangesTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(userA, false);
        assertFalse(audit.isActive());
        assertEquals(1L, audit.getVersion());

        audit.update(userB, true);
        assertTrue(audit.isActive());
        assertEquals(2L, audit.getVersion());

        audit.update(userB, false);
        assertFalse(audit.isActive());
        assertEquals(3L, audit.getVersion());
    }

    @Test
    public void shouldFailUpdateAuditNotPreviouslyInsertedTest() {
        Audit audit = new Audit();
        assertThrows(AuditException.class, () -> audit.update(userA));
    }

    @Test
    public void shouldFailInsertAuditPreviouslyInsertedTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(userA);
        assertThrows(AuditException.class, () -> audit.insert(userB));
    }

}