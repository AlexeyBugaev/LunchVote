package vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import vote.model.Dish;
import java.net.URI;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vote.repository.CrudDishRepository;
import vote.repository.CrudRestaurantRepository;
import java.util.List;
import static vote.Utils.ValidationUtil.checkNotFoundWithId;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(value = DishController.REST_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class DishController {
    static final String REST_URL = "/rest/dishes";

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    @GetMapping("/{id}")
    public Dish get(@PathVariable("id") int id) {
        return checkNotFoundWithId(crudDishRepository.findById(id).orElse(null), id);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        checkNotFoundWithId(crudDishRepository.delete(id)!=0, id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{restaurantId}/getAll")
    public List<Dish> getAllByRestaurantId(@PathVariable("restaurantId") int restaurantId) {
        checkNotFoundWithId(crudRestaurantRepository.getOne(restaurantId), restaurantId);
        return crudDishRepository.getAllByRestaurant(crudRestaurantRepository.getOne(restaurantId));
    }

    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody Dish dish) {
        Assert.notNull(dish, "dish must not be null");
        crudDishRepository.save(dish);
    }

    @PostMapping(value = "/{restaurantId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Dish> create(@RequestBody Dish dish, @PathVariable("restaurantId") int restaurantId) {
        Assert.notNull(dish, "dish must not be null");
        checkNotFoundWithId(crudRestaurantRepository.getOne(restaurantId), restaurantId);
        dish.setRestaurant(crudRestaurantRepository.getOne(restaurantId));
        Dish created = crudDishRepository.save(dish);

        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }
}