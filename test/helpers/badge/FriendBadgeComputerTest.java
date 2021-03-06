package helpers.badge;

import java.util.EnumSet;
import java.util.List;
import java.util.Set;

import models.*;
import org.junit.Test;

/**
 * Unit tests for {@link FriendBadgeComputer}
 * @author Sryl <cyril.lacote@gmail.com>
 */
public class FriendBadgeComputerTest extends AbstractBadgeComputerTest {

    public FriendBadgeComputerTest() {
        super(new FriendBadgeComputer());
    }

    @Test
    public void grantedStaffBestFriend() {
        // Member links all Staff Members
        final List<Staff> staff = Staff.findAll();
        for (Staff s : staff) {
            member.addLink(s);
        }
        final Set<Badge> actualBadges = computer.compute(member, new BadgeComputationContext());
        assertEquals(EnumSet.of(Badge.StaffBestFriend), actualBadges);
    }
    
    @Test
    public void grantedSpeakerFan() {
        // Member links all speaker Members
        final List<Member> speakers = Talk.findAllSpeakers();
        for (Member s : speakers) {
            member.addLink(s);
        }
        final Set<Badge> actualBadges = computer.compute(member, new BadgeComputationContext());
        assertEquals(EnumSet.of(Badge.SpeakerFan), actualBadges);
    }
    
    @Test
    public void grantedSponsorFriendly() {
        // Member links all sponsor Members
        final List<Sponsor> sponsors = Sponsor.findOn(ConferenceEvent.CURRENT);
        for (Sponsor s : sponsors) {
            member.addLink(s);
        }
        final Set<Badge> actualBadges = computer.compute(member, new BadgeComputationContext());
        assertEquals(EnumSet.of(Badge.SponsorFriendly), actualBadges);
    }
}
