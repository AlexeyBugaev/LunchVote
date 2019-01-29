package vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import vote.service.DishService;
import vote.service.RestaurantService;
import vote.service.UserService;

public abstract class AbstractController {

    @Autowired
    protected UserService userService;

    @Autowired
    protected RestaurantService restaurantService;

    @Autowired
    protected DishService dishService;

}
