package eco.solutions.model;

import eco.solutions.exceptions.BusinessRuleException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class MeetingTest {

    private static Donation donation;
    private static Address address;
    private static Date datetime;

    @BeforeAll
    public static void setUp(){
        donation = new Donation();
        address = new Address();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, 1);
        datetime = calendar.getTime();
    }

    @Test
    public void shouldCreateMeetingTest() throws BusinessRuleException {
        Meeting meeting = new Meeting(datetime, donation, address);
        assertEquals(datetime, meeting.getMeetingDatetime());
        assertEquals(donation, meeting.getDonation());
        assertEquals(address, meeting.getAddress());
        assertFalse(meeting.isHasOccured());
    }

    @Test
    public void shouldFailMeetingMeetingDatetimeBeforeCurrentDateTest() {
        assertThrows(BusinessRuleException.class, () -> new Meeting(new Date(), donation, address));
    }

    @Test
    public void shouldFailMeetingDonationIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new Meeting(datetime, null, address));
    }

    @Test
    public void shouldFailMeetingAddressIsNullTest() {
        assertThrows(BusinessRuleException.class, () -> new Meeting(datetime, donation, null));
    }

}