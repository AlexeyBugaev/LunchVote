package vote.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import vote.model.Dish;
import vote.model.Restaurant;
import java.util.List;

@Transactional(readOnly = true)
public interface CrudDishRepository extends JpaRepository<Dish, Integer> {

    @Transactional
    @Modifying
    @Query("DELETE FROM Dish d WHERE d.id=:id")
    int delete(@Param("id") int id);

    @Override
    @Transactional
    Dish save(Dish dish);

    @Query("SELECT d FROM Dish d WHERE d.restaurant=:restaurant ORDER BY d.name ASC")
    List<Dish> getAllByRestaurant(@Param("restaurant")Restaurant restaurant);
}