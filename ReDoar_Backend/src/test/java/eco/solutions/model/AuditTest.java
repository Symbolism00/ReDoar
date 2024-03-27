package eco.solutions.model;

import eco.solutions.exceptions.AuditException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AuditTest {

    @Test
    public void shouldCreateBasicAuditInsertTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(1L);
        assertEquals(1L, audit.getInsUserId());
        assertTrue(audit.isActive());
        assertEquals(1L, audit.getVersion());
        assertNotNull(audit.getInsDate());
    }

    @Test
    public void shouldCreateToggleableStateAuditInsertTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(1L, false);
        assertFalse(audit.isActive());
    }

    @Test
    public void shouldDoBasicAuditUpdateTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(1L);

        audit.update(2L);
        assertEquals(2L, audit.getAltUserId());
        assertTrue(audit.isActive());
        assertEquals(2L, audit.getVersion());
        assertNotNull(audit.getAltDate());
    }

    @Test
    public void shouldDoToggleableAuditUpdateTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(1L);

        audit.update(2L, false);
        assertFalse(audit.isActive());
    }

    @Test
    public void shouldDoSomeAuditChangesTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(1L, false);
        assertFalse(audit.isActive());
        assertEquals(1L, audit.getVersion());

        audit.update(2L, true);
        assertTrue(audit.isActive());
        assertEquals(2L, audit.getVersion());

        audit.update(2L, false);
        assertFalse(audit.isActive());
        assertEquals(3L, audit.getVersion());
    }

    @Test
    public void shouldFailUpdateAuditNotPreviouslyInsertedTest() {
        Audit audit = new Audit();
        assertThrows(AuditException.class, () -> audit.update(1L));
    }

    @Test
    public void shouldFailInsertAuditPreviouslyInsertedTest() throws AuditException {
        Audit audit = new Audit();
        audit.insert(1L);
        assertThrows(AuditException.class, () -> audit.insert(2L));
    }

}