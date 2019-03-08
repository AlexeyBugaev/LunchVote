package vote.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import vote.model.User;
import vote.repository.CrudUserRepository;
import vote.web.AuthorizedUser;

import java.util.List;

import static vote.Utils.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserService implements UserDetailsService {
    private static final Sort SORT_NAME_EMAIL = new Sort(Sort.Direction.ASC, "name", "email");

    private final CrudUserRepository crudUserRepository;
    private final PasswordEncoder passwordEncoder;

    public static User prepareToSave(User user, PasswordEncoder passwordEncoder){
        String password = user.getPassword();
        user.setPassword(StringUtils.isEmpty(password)? password : passwordEncoder.encode(password));
        user.setEmail(user.getEmail().toLowerCase());
        return user;
    }

    @Autowired
    public UserService(CrudUserRepository crudUserRepository, PasswordEncoder passwordEncoder) {
        this.crudUserRepository = crudUserRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void delete(int id){
        checkNotFoundWithId(crudUserRepository.delete(id)!=0,id);
    }

    public void update(User user){
        Assert.notNull(user, "user must not be null");
        crudUserRepository.save(prepareToSave(user, passwordEncoder));
    }

    public User create(User user){
        Assert.notNull(user, "user must not be null");
        return crudUserRepository.save(prepareToSave(user, passwordEncoder));
    }

    public User get(int id){
        return checkNotFoundWithId(crudUserRepository.findById(id).orElse(null), id);
    }

    public List<User> getAll(){
        return crudUserRepository.findAll(SORT_NAME_EMAIL);
    }

    public User getByEmail(String email){return crudUserRepository.getByEmail(email);}

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = crudUserRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }
}
