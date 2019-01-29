package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vote.model.Dish;
import vote.model.Restaurant;
import vote.repository.DishRepositoryImpl;
import java.util.List;
import static vote.Utils.ValidationUtil.checkNotFoundWithId;

@Service
public class DishService {

    private final DishRepositoryImpl dishRepository;

    @Autowired
    public DishService(DishRepositoryImpl dishRepository) {
        this.dishRepository = dishRepository;
    }

    public Dish get(int id){
        return checkNotFoundWithId(dishRepository.get(id),id);
    }

    public void delete(int id){
        checkNotFoundWithId(dishRepository.delete(id),id);
    }

    public List<Dish> getAllByRestaurantId(int restaurantId){
        return dishRepository.getAllByRestaurantId(restaurantId);
    }

    public List<Dish> getAll(){
        return dishRepository.getAll();
    }

    public Dish update(Dish dish){
        Assert.notNull(dish, "dish must not be null");
        return dishRepository.save(dish);
    }

    public Dish create(Dish dish, Restaurant restaurant){
        Assert.notNull(dish, "dish must not be null");
        dish.setRestaurant(restaurant);
        return dishRepository.save(dish);
    }
}
