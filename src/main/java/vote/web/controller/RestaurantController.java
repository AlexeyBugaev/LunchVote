package vote.web.controller;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vote.Utils.RestaurantUtil;
import vote.Utils.SecurityUtil;
import vote.model.Restaurant;
import vote.model.User;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
import static vote.Utils.RestaurantUtil.*;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = RestaurantController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class RestaurantController extends AbstractController{
    static final String REST_URL = "/restaurants";

    @GetMapping("/{id}")
    public Restaurant get(@PathVariable("id") int id) {
        return restaurantService.get(id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        restaurantService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public List<Restaurant> getAll() {
        return restaurantService.getAll();
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Restaurant restaurant) {
        restaurantService.update(restaurant);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Restaurant> create(@RequestBody Restaurant restaurant) {
        Restaurant created = restaurantService.create(restaurant);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    //In method below previously user voted restaurant votes are decreased, currently voted restaurant votes are increased

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/voteForRestaurant/{restaurantId}")
    public void voteForRestaurant(HttpServletResponse httpServletResponse, @PathVariable("restaurantId") int restaurantId) throws IOException {
        if(RestaurantUtil.timeForVoting()){
            User user = userService.get(SecurityUtil.authUserId());
            Restaurant voted;
            int votedRestaurantId = user.getRestaurantVotedId();

            if(votedRestaurantId==restaurantId) setServletResponseErrorMessage(httpServletResponse, "You cannot vote for one restaurant twice");

            if((voted=restaurantService.get(votedRestaurantId))!=null) {
                decrementVotes(voted);
                restaurantService.update(voted);
            }

            voted = restaurantService.get(restaurantId);
            user.setRestaurantVotedId(restaurantId);
            incrementVotes(voted);
            restaurantService.update(voted);
            userService.update(user);

            setServletResponseSuccessMessage(httpServletResponse, voted.getName());
        }
        else setServletResponseErrorMessage(httpServletResponse, "Voting is available before 11 a.m.");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/getVoted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getVoted() {
        return RestaurantUtil.getVoted(restaurantService.getAll());
    }
}