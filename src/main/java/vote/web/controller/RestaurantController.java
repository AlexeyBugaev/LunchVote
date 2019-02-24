package vote.web.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vote.Utils.IllegalRequestDataException;
import vote.Utils.RestaurantUtil;
import vote.Utils.SecurityUtil;
import vote.model.Restaurant;
import vote.model.User;
import vote.model.VoteHistory;
import vote.repository.CrudDishRepository;
import vote.repository.CrudRestaurantRepository;
import vote.repository.CrudUserRepository;
import vote.repository.CrudVoteHistoryRepository;
import vote.service.UserService;

import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static vote.Utils.RestaurantUtil.*;
import static vote.Utils.ValidationUtil.assureIdConsistent;
import static vote.Utils.ValidationUtil.checkNotFoundWithId;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController {
    static final String REST_URL = "/rest/restaurants";

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudVoteHistoryRepository voteHistoryRepository;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return checkNotFoundWithId(crudRestaurantRepository.findById(id).orElse(null), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        checkNotFoundWithId(crudRestaurantRepository.delete(id)!=0, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public List<Restaurant> getAll() {
        Restaurant withId100000 = crudRestaurantRepository.findById(100000).orElse(null);
        List<Restaurant> restaurants = crudRestaurantRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
        restaurants.remove(withId100000);
        return restaurants;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant, @PathVariable("id") int id) {
        assureIdConsistent(restaurant, id);
        Assert.notNull(restaurant, "restaurant must not be null");
        crudRestaurantRepository.save(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Assert.notNull(restaurant, "restaurant must not be null");
        Restaurant created = crudRestaurantRepository.save(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{restaurantId}/vote")
    public void voteForRestaurant(HttpServletResponse httpServletResponse, @PathVariable("restaurantId") int restaurantId) throws IOException {
        if(restaurantId==100000) throw new IllegalRequestDataException("No restaurant available for id = 100000");
        User user = userService.get(SecurityUtil.authUserId());
        Restaurant voted;
        int votedRestaurantId = user.getRestaurantVotedId();

        if(!user.isVoteMade()){
            voted = incrementVotes(crudRestaurantRepository, restaurantId);
            user.setVoteMade(true);
            voteService(voted, restaurantId, user);
            setServletResponseSuccessMessage(httpServletResponse, voted.getName());
        }

        else if(RestaurantUtil.voteChangeEnabled()){
            if (votedRestaurantId == restaurantId) setServletResponseErrorMessage(httpServletResponse, "You cannot vote for one restaurant twice");
            decrementVotes(crudRestaurantRepository, votedRestaurantId);
            voteHistoryRepository.delete(user.getId(), LocalDate.now());
            voted = incrementVotes(crudRestaurantRepository, restaurantId);
            voteService(voted, restaurantId, user);
            setServletResponseSuccessMessage(httpServletResponse, voted.getName());
        }

        else setServletResponseErrorMessage(httpServletResponse, "Change vote is unavailable after 11 a.m.");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/getVoted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getVoted() {
        return RestaurantUtil.getVoted(crudRestaurantRepository.findAll());
    }

    @GetMapping(value = "/clearVotingData", produces = MediaType.APPLICATION_JSON_VALUE)
    public void clearVotingData(HttpServletResponse httpServletResponse) throws IOException {
        crudRestaurantRepository.findAll().forEach(restaurant -> {
            restaurant.setVotes(new AtomicInteger(0));
            crudRestaurantRepository.save(restaurant);
        });
        userService.getAll().forEach(user -> {
            user.setVoteMade(false);
            user.setRestaurantVotedId(100000);
            userService.update(user);
        });
        setServletResponseSuccessMessage(httpServletResponse, "DataClear");
    }

    private void voteService(Restaurant restaurant, int id, User user){
        user.setRestaurantVotedId(id);
        crudDishRepository.getAllByRestaurant(restaurant).forEach(dish ->
                voteHistoryRepository.save(new VoteHistory(user.getId(), LocalDate.now(), dish.getName(), dish.getPrice(), restaurant.getName()))
        );
        userService.update(user);
    }
}