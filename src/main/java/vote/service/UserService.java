package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import vote.model.User;
import vote.repository.UserRepositoryImpl;
import vote.web.AuthorizedUser;

import java.util.List;

import static vote.Utils.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService implements UserDetailsService {

    private final UserRepositoryImpl userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepositoryImpl userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void delete(int id){
        checkNotFoundWithId(userRepository.delete(id),id);
    }

    public void update(User user){
        Assert.notNull(user, "user must not be null");
        userRepository.save(user);
    }

    public User create(User user){
        Assert.notNull(user, "user must not be null");
        return userRepository.save(user);
    }

    public User get(int id){
        return checkNotFoundWithId(userRepository.get(id),id);
    }

    public List<User> getAll(){
        return userRepository.getAll();
    }

    public User getByEmail(String email){return userRepository.getByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
