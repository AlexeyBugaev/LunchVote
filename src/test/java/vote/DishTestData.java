package vote;

import org.springframework.test.web.servlet.ResultMatcher;
import vote.model.Dish;

import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static java.time.LocalDateTime.of;
import static org.assertj.core.api.Assertions.assertThat;
import static vote.TestUtil.readListFromJsonMvcResult;
import static vote.model.BaseEntity.START_SEQ;

public class DishTestData {
    public static final int DISH_ID = START_SEQ + 6;

    public static final Dish DISH1 = new Dish(DISH_ID, "BeefSteak", 1200);
    public static final Dish DISH2 = new Dish(DISH_ID+1, "PorkSteak", 1000);
    public static final Dish DISH3 = new Dish(DISH_ID+2, "GrilledChicken", 800);
    public static final Dish DISH4 = new Dish(DISH_ID+3, "Borsh", 400);
    public static final Dish DISH5 = new Dish(DISH_ID+4, "RoastWithPotatoes", 800);
    public static final Dish DISH6 = new Dish(DISH_ID+5, "FishPanCakes", 600);
    public static final Dish DISH7 = new Dish(DISH_ID+6, "Pasta", 500);
    public static final Dish DISH8 = new Dish(DISH_ID+7, "Rizotto", 800);
    public static final Dish DISH9 = new Dish(DISH_ID+8, "Lazaniya", 900);


    public static Dish getCreated() {
        return new Dish(null, "newDish", 800);
    }

    public static Dish getUpdated() {
        return new Dish(null, "updatedDish", 800);
    }

    public static void assertMatch(Dish actual, Dish expected) {
        assertThat(actual).isEqualToIgnoringGivenFields(expected, "restaurant");
    }

    public static void assertMatch(Iterable<Dish> actual, Dish... expected) {
        assertMatch(actual, List.of(expected));
    }

    public static void assertMatch(Iterable<Dish> actual, Iterable<Dish> expected) {
        assertThat(actual).usingElementComparatorIgnoringFields("restaurant").isEqualTo(expected);
    }

    public static ResultMatcher getDishMatcher(Dish... expected) {
        return getMatcher(List.of(expected));
    }

    public static ResultMatcher getMatcher(Iterable<Dish> expected) {
        return result -> assertThat(readListFromJsonMvcResult(result, Dish.class)).isEqualTo(expected);
    }

    public static List<Dish> getDishesExcept(Dish exceptDish){
        List<Dish> dishes = List.of(DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, DISH7, DISH8, DISH9);
        List<Dish> sorted = new ArrayList<>();
        dishes.forEach(dish -> {
            if(!dish.equals(exceptDish)) sorted.add(dish);
        });
        return sorted;
    }
}
