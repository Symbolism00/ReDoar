package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MeetingTest {
    private static Date datetime;

    @BeforeAll
    public static void setUp(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        datetime = calendar.getTime();
    }

    @Test
    public void shouldCreateMeetingTest() throws BusinessRuleException {
        Meeting meeting = new Meeting(datetime, 1L, 1L);
        assertEquals(datetime, meeting.getMeetingDatetime());
        assertEquals(1L, meeting.getDonationId());
        assertEquals(1L, meeting.getAddressId());
        assertFalse(meeting.isHasOccurred());
    }

    @Test
    public void shouldFailMeetingMeetingDatetimeBeforeCurrentDateTest() {
        assertThrows(BusinessRuleException.class, () -> new Meeting(new Date(), 1L, 1L));
    }

    @Test
    public void shouldFailMeetingDonationIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new Meeting(datetime, null, 1L));
    }

    @Test
    public void shouldFailMeetingAddressIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new Meeting(datetime, 1L, null));
    }

}