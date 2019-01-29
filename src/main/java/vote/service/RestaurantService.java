package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vote.model.Restaurant;
import vote.repository.RestaurantRepositoryImpl;

import java.util.List;

import static vote.Utils.ValidationUtil.checkNotFoundWithId;

@Service
public class RestaurantService {

    private final RestaurantRepositoryImpl restaurantRepository;

    @Autowired
    public RestaurantService(RestaurantRepositoryImpl restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public void delete(int id){
        checkNotFoundWithId(restaurantRepository.delete(id),id);
        }

    public Restaurant get(int id){
        return checkNotFoundWithId(restaurantRepository.get(id),id);
    }

    public Restaurant update(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant); }

    public Restaurant create(Restaurant restaurant){
        Assert.notNull(restaurant, "restaurant must not be null");
        return restaurantRepository.save(restaurant);
        }

    public List<Restaurant> getAll(){
            return restaurantRepository.getAll();
        }

}


