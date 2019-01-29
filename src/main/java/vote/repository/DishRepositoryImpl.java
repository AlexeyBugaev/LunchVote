package vote.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vote.model.Dish;
import java.util.List;

@Repository
public class DishRepositoryImpl {

    @Autowired
    private CrudDishRepository crudDishRepository;

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public Dish get(int id) {
        return crudDishRepository.findById(id).orElse(null);
    }

    public boolean delete(int id) {
        return crudDishRepository.delete(id) != 0;
    }

    public List<Dish> getAllByRestaurantId(int restaurantId) {
        return crudDishRepository.getAllByRestaurant(crudRestaurantRepository.getOne(restaurantId));
    }

    public List<Dish> getAll() {
        return crudDishRepository.findAll();
    }

    @Transactional
    public Dish save(Dish dish) {
        return crudDishRepository.save(dish);
    }

}
