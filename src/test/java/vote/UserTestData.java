package vote;

import org.springframework.test.web.servlet.ResultMatcher;
import vote.model.Role;
import vote.model.User;
import vote.web.json.JsonUtil;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static vote.model.BaseEntity.START_SEQ;

public class UserTestData {
    public static final int USER_ID = START_SEQ + 3;
    public static final int ADMIN_ID = START_SEQ + 4;

    public static final User USER = new User(USER_ID, "User", "user@yandex.ru", "password", 100000, Role.ROLE_USER);
    public static final User ADMIN = new User(ADMIN_ID, "Admin", "admin@gmail.com", "admin", 100001, Role.ROLE_ADMIN);

    public static void assertMatch(User actual, User expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "password");
    }

    public static void assertMatch(Iterable<User> actual, User... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<User> actual, Iterable<User> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("password").isEqualTo(expected);
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
