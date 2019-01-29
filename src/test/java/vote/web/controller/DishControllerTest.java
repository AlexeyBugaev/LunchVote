package vote.web.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;
import vote.RestaurantTestData;
import vote.model.Dish;
import vote.web.json.JsonUtil;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vote.DishTestData.*;
import static vote.RestaurantTestData.RESTAURANT1;
import static vote.RestaurantTestData.RESTAURANT_ID;
import static vote.TestUtil.readFromJsonResultActions;
import static vote.TestUtil.userHttpBasic;
import static vote.UserTestData.ADMIN;


@Sql(scripts = "classpath:db/populateDB.sql")
class DishControllerTest extends AbstractControllerTest{

    private static final String REST_URL = DishController.REST_URL + '/';

    @Test
    void getDish() throws Exception {
        mockMvc.perform(get(REST_URL + DISH_ID)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getDishMatcher(DISH1));
    }

    @Test
    void deleteDish() throws Exception {
        mockMvc.perform(delete(REST_URL + DISH_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(dishService.getAll(), getDishesExcept(DISH1));
    }



    @Test
    void getAllDishesByRestaurantId() throws Exception {
        DISH1.setRestaurant(restaurantService.get(100001));
        DISH5.setRestaurant(restaurantService.get(100001));
        mockMvc.perform(get(REST_URL + "?restaurantId=" + 100001)
                .with(userHttpBasic(ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getDishMatcher(DISH1,DISH5));
    }

    @Test
    void updateDish() throws Exception {
        Dish updated = new Dish(DISH1);
        updated.setName("updatedDish");
        updated.setPrice(1000);
        mockMvc.perform(put(REST_URL + DISH_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isNoContent());
        assertMatch(dishService.get(DISH_ID), updated);
    }

    @Test
    void createDish() throws Exception {
        Dish expected = new Dish(null, "newDish", 1000, RESTAURANT1);
        ResultActions action = mockMvc.perform(post(REST_URL + RESTAURANT_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Dish returned = readFromJsonResultActions(action, Dish.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(dishService.getAll(), DISH1, DISH2, DISH3, DISH4, DISH5, DISH6, expected);
        RestaurantTestData.assertMatch(expected.getRestaurant(), RESTAURANT1);
    }
}