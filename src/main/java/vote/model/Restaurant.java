package vote.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Cache;
import vote.Utils.AtomicIntegerConverter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "restaurants")
public class Restaurant extends BaseEntity{

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "restaurant")
    protected List<Dish> dishes;

    @JsonDeserialize(using = AtomicIntegerConverter.class)
    @Convert(converter = AtomicIntegerConverter.class)
    @Column(name = "votes")
    private AtomicInteger votes;

    public Restaurant(){
    }

    public Restaurant (Restaurant restaurant){
        this(restaurant.getId(), restaurant.getName(), restaurant.getVotes());
    }

    public Restaurant(Integer id, String name, AtomicInteger votes) {
        super(id, name);
        this.votes = votes;
    }

    public Restaurant(List<Dish> dishes, AtomicInteger votes) {
        this.dishes = dishes;
        this.votes = votes;
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public AtomicInteger getVotes() {
        return votes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void setVotes(AtomicInteger votes) {
        this.votes = votes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Restaurant that = (Restaurant) o;
        return votes.get()==that.votes.get();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), votes);
    }
}
