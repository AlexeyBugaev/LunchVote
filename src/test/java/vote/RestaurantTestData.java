package vote;

import org.springframework.test.web.servlet.ResultMatcher;
import vote.model.Restaurant;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static vote.model.BaseEntity.START_SEQ;
import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantTestData {
    public static final int RESTAURANT_ID = START_SEQ;

    public static final Restaurant RESTAURANT1 = new Restaurant(RESTAURANT_ID, "Kolizey", new AtomicInteger(1));
    public static final Restaurant RESTAURANT2 = new Restaurant(RESTAURANT_ID+1, "BeefHouse",new AtomicInteger(2) );
    public static final Restaurant RESTAURANT3 = new Restaurant(RESTAURANT_ID+2, "Ribai", new AtomicInteger(0));


    public static final List<Restaurant> RESTAURANTS = List.of(RESTAURANT1, RESTAURANT2, RESTAURANT3);

    public static Restaurant getCreated() {
        return new Restaurant(null, "newRestaurant", new AtomicInteger(3));
    }

    public static Restaurant getUpdated() {
        return new Restaurant(RESTAURANT_ID, "updatedRestaurant", new AtomicInteger(2));
    }

    public static void assertMatch(Restaurant actual, Restaurant expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "dishes");
    }

    public static void assertMatch(Iterable<Restaurant> actual, Restaurant... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Restaurant> actual, Iterable<Restaurant> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("dishes", "votes").isEqualTo(expected);
    }

    public static ResultMatcher getRestaurantMatcher(Restaurant expected) {
        return result -> assertMatch(TestUtil.readFromJsonMvcResult(result, Restaurant.class), expected);
    }

    public static ResultMatcher getMatcher(Restaurant...expected) {
        return result -> assertMatch(TestUtil.readListFromJsonMvcResult(result, Restaurant.class), List.of(expected));
    }
}
