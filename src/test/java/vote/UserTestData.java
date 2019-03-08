package vote;

import org.springframework.test.web.servlet.ResultMatcher;
import vote.model.Role;
import vote.model.User;
import vote.model.VoteHistory;
import vote.web.json.JsonUtil;

import java.time.LocalDate;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static vote.model.BaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ + 4;
    public static final int ADMIN_ID = START_SEQ + 5;
    public static final int VOTED_ID = START_SEQ + 15;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", 100000, Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", 100000, Role.ROLE_ADMIN);
    public static final User VotedUser = new User(VOTED_ID, "VotedUser", "voted@yandex.ru", "voted", 100001, true, Role.ROLE_USER);

    public static final VoteHistory userVoteHistory1 = new VoteHistory(USER_ID, LocalDate.now(), "BeefSteak", 1200, "BeefHouse");
    public static final VoteHistory userVoteHistory2 = new VoteHistory(USER_ID, LocalDate.now(), "PorkSteak", 1000, "BeefHouse");
    public static final VoteHistory userVoteHistory3 = new VoteHistory(USER_ID, LocalDate.now(), "GrilledChicken", 800, "BeefHouse");

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password").isEqualTo(expected);
    }

    public static void voteHistoryAssertMatch(Iterable<VoteHistory> actual, Iterable<VoteHistory> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static ResultMatcher getUserMatcher(User... expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, User.class), List.of(expected));
    }

    public static ResultMatcher getUserMatcher(User expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, User.class), expected);
    }

    public static String jsonWithPassword(User user, String passw) {
        return JsonUtil.writeAdditionProps(user, "password", passw);
    }
}
