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
import vote.Utils.RestaurantUtil;
import vote.Utils.SecurityUtil;
import vote.model.Restaurant;
import vote.model.User;
import vote.repository.CrudRestaurantRepository;
import vote.repository.CrudUserRepository;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;
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
    private CrudUserRepository crudUserRepository;

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
        return crudRestaurantRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
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

    //In method below previously user voted restaurant votes are decreased, currently voted restaurant votes are increased

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{restaurantId}/vote")
    public void voteForRestaurant(HttpServletResponse httpServletResponse, @PathVariable("restaurantId") int restaurantId) throws IOException {
        if(RestaurantUtil.timeForVoting()){
            User user = crudUserRepository.getOne(SecurityUtil.authUserId());
            Restaurant voted;
            int votedRestaurantId = user.getRestaurantVotedId();

            if(votedRestaurantId==restaurantId) setServletResponseErrorMessage(httpServletResponse, "You cannot vote for one restaurant twice");

            if((voted=crudRestaurantRepository.getOne(votedRestaurantId))!=null) {
                decrementVotes(voted);
                crudRestaurantRepository.save(voted);
            }

            voted = crudRestaurantRepository.getOne(restaurantId);
            user.setRestaurantVotedId(restaurantId);
            incrementVotes(voted);
            crudRestaurantRepository.save(voted);
            crudUserRepository.save(user);

            setServletResponseSuccessMessage(httpServletResponse, voted.getName());
        }
        else setServletResponseErrorMessage(httpServletResponse, "Voting is available before 11 a.m.");
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/getVoted", produces = MediaType.APPLICATION_JSON_VALUE)
    public Restaurant getVoted() {
        return RestaurantUtil.getVoted(crudRestaurantRepository.findAll());
    }
}