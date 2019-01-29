package vote.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import vote.model.Dish;
import vote.model.Restaurant;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RestaurantRepositoryImpl {

    @Autowired
    private CrudRestaurantRepository crudRestaurantRepository;

    public Restaurant get(int id) {
        return crudRestaurantRepository.findById(id).orElse(null);
    }

    public boolean delete(int id) {
        return crudRestaurantRepository.delete(id) != 0;
    }

    @Transactional
    public Restaurant save(Restaurant restaurant) {
        return crudRestaurantRepository.save(restaurant);
    }

    public List<Restaurant> getAll() {
        return crudRestaurantRepository.findAll(new Sort(Sort.Direction.ASC, "name"));
    }
}
