package vote.web.controller;


import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.ResultActions;
import vote.UserTestData;
import vote.model.Restaurant;
import vote.model.Role;
import vote.model.User;
import vote.web.json.JsonUtil;

import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static vote.RestaurantTestData.*;
import static vote.TestUtil.readFromJsonResultActions;
import static vote.TestUtil.userHttpBasic;
import static vote.UserTestData.*;

@Sql(scripts = "classpath:db/populateDB.sql")
class RestaurantControllerTest extends AbstractControllerTest{

    private static final String REST_URL = RestaurantController.REST_URL + '/';

    @Test
    void getRestaurant() throws Exception {
        mockMvc.perform(get(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(UserTestData.ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getRestaurantMatcher(RESTAURANT1));
    }

    @Test
    void deleteRestaurant() throws Exception {
        mockMvc.perform(delete(REST_URL + RESTAURANT_ID)
                .with(userHttpBasic(ADMIN)))
                .andDo(print())
                .andExpect(status().isNoContent());
        assertMatch(crudRestaurantRepository.findAll(),RESTAURANT, RESTAURANT2, RESTAURANT3);
    }

    @Test
    void getAllRestaurants() throws Exception {
        mockMvc.perform(get(REST_URL)
                .with(userHttpBasic(UserTestData.USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getMatcher(RESTAURANT1, RESTAURANT3, RESTAURANT2));
    }

    @Test
    void updateRestaurant() throws Exception {
            Restaurant updated = new Restaurant(RESTAURANT1);
            updated.setName("UpdatedName");
            mockMvc.perform(put (REST_URL + RESTAURANT_ID)
                    .contentType(MediaType.APPLICATION_JSON)
                    .with(userHttpBasic(ADMIN))
                    .content(JsonUtil.writeValue(updated)))
                    .andExpect(status().isNoContent());

            assertMatch(crudRestaurantRepository.getOne(RESTAURANT_ID), updated);
    }

    @Test
    void createRestaurant() throws Exception {
        Restaurant expected = new Restaurant(null, "Shashlykoff", new AtomicInteger(3));
        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .with(userHttpBasic(ADMIN))
                .content(JsonUtil.writeValue(expected)))
                .andExpect(status().isCreated());

        Restaurant returned = readFromJsonResultActions(action, Restaurant.class);
        expected.setId(returned.getId());

        assertMatch(returned, expected);
        assertMatch(crudRestaurantRepository.findAll(new Sort(Sort.Direction.ASC, "name")), RESTAURANT1, RESTAURANT3, RESTAURANT, expected, RESTAURANT2);
    }

    @Test
    void voteForRestaurant() throws Exception {
        Restaurant restaurant = new Restaurant(RESTAURANT2);
        User user = new User(USER);
        int votesForRestaurant = restaurant.getVotes().get();
        ResultActions action = mockMvc.perform(get(REST_URL + restaurant.getId()+ "/vote")
                .with(userHttpBasic(user)))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        assertThat(crudRestaurantRepository.getOne(restaurant.getId()).getVotes().get()).isEqualTo(votesForRestaurant+1);
        assertThat(action.andReturn().getResponse().getContentAsString()).isEqualTo("{\"Restaurant voted\":\"Shinok\"}");
    }

   @Test
    void voteForRestaurantTwice() throws Exception {
       Restaurant restaurant = new Restaurant(RESTAURANT1);
       User user = new User(VotedUser);
       ResultActions action = mockMvc.perform(get(REST_URL + restaurant.getId()+ "/vote")
               .with(userHttpBasic(user)))
               .andExpect(status().isForbidden())
               .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

       assertThat(action.andReturn().getResponse().getContentAsString()).isEqualTo("{\"errorMessage\":\"You cannot vote for one restaurant twice\"}");
    }

    @Test
    void changeVoteAfterEleven() throws Exception {
        Restaurant restaurant = new Restaurant(RESTAURANT2);
        User user = new User(VotedUser);
        ResultActions action = mockMvc.perform(get(REST_URL + restaurant.getId()+ "/vote")
                .with(userHttpBasic(user)))
                .andExpect(status().isForbidden())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));

        assertThat(action.andReturn().getResponse().getContentAsString()).isEqualTo("{\"errorMessage\":\"Change vote is unavailable after 11 a.m.\"}");
    }

    @Test
    void getVotedRestaurant() throws Exception {
        crudRestaurantRepository.getOne(100002).setVotes(new AtomicInteger(1));
        mockMvc.perform(get(REST_URL + "getVoted")
                .with(userHttpBasic(USER)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(getRestaurantMatcher(RESTAURANT2));
    }

    @Test
    void clearVotingData() throws Exception {
        mockMvc.perform(get(REST_URL + "clearVotingData")
                .with(userHttpBasic(UserTestData.ADMIN)))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
        crudRestaurantRepository.findAll().forEach(r -> assertThat(r.getVotes().get()).isEqualTo(0));
        userService.getAll().forEach(user -> assertThat(user.getRestaurantVotedId()).isEqualTo(100000));
    }
}