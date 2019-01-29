package vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import javax.persistence.*;
import java.util.List;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity{

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<Dish> dishes;

    @Column(name = "votes")
    private int votes;

    public Restaurant(){
    }

    public Restaurant (Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getVotes());
    }

    public Restaurant(Integer id, String name, int votes) {
        super(id, name);
        this.votes = votes;
    }

    public Restaurant(List<Dish> dishes, int votes) {
        this.dishes = dishes;
        this.votes = votes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public int getVotes() {
        return votes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }
}
