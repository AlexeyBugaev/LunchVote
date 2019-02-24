package vote.model;

import org.springframework.util.CollectionUtils;
import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity{

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"))
    @ElementCollection(fetch = FetchType.EAGER)
    @Column(name = "role")
    private Set<Role> roles;

    @Column(name = "restaurantVotedId")
    private int restaurantVotedId;

    @Column(name = "voteMade")
    private boolean voteMade;

    public User() {
        super();
    }

    public User(Integer id, String name, String email, String password, int restaurantVotedId, Collection<Role> roles) {
        super(id, name);
        this.email = email;
        this.password = password;
        this.restaurantVotedId = restaurantVotedId;
        setRoles(roles);
    }

    public User(User user) {
        this(user.getId(), user.getName(), user.getEmail(), user.getPassword(), user.getRestaurantVotedId(), user.getRoles());
    }

    public User(Integer id, String name, String email, String password, int restaurantVotedId, Role role, Role... roles) {
        this(id, name, email, password, restaurantVotedId, EnumSet.of(role, roles));
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getRestaurantVotedId() {
        return restaurantVotedId;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public boolean isVoteMade() {
        return voteMade;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRestaurantVotedId(int restaurantVotedId) {
        this.restaurantVotedId = restaurantVotedId;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = CollectionUtils.isEmpty(roles) ? EnumSet.noneOf(Role.class) : EnumSet.copyOf(roles);
    }

    public void setVoteMade(boolean voteMade) {
        this.voteMade = voteMade;
    }
}
