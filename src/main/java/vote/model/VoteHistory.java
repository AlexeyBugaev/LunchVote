package vote.model;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.*;
import java.time.LocalDate;
import static vote.model.BaseEntity.START_SEQ;

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "voteHistory")
public class VoteHistory {

    @Id
    @SequenceGenerator(name = "global_seq", sequenceName = "global_seq", allocationSize = 1, initialValue = START_SEQ)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "global_seq")
    private int id;

    @Column(name = "userId")
    private int userId;

    @Column(name = "date")
    private LocalDate date;

    @Column(name = "dishName")
    private String dishName;

    @Column(name = "price")
    private int price;

    @Column(name = "restaurantName")
    private String restaurantName;

    public VoteHistory(int userId, LocalDate date, String dishName, int price, String restaurantName) {
        this.userId = userId;
        this.date = date;
        this.dishName = dishName;
        this.price = price;
        this.restaurantName = restaurantName;
    }

    public VoteHistory() {
    }
}
