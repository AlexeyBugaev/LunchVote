package vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import vote.model.User;
import vote.model.VoteHistory;
import vote.repository.CrudVoteHistoryRepository;
import vote.service.UserService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.List;

import static vote.Utils.RestaurantUtil.setServletResponseSuccessMessage;
import static vote.Utils.ValidationUtil.assureIdConsistent;

@PreAuthorize("hasRole('ROLE_ADMIN')")
@RestController
@RequestMapping(UserController.REST_URL)
public class UserController {
    static final String REST_URL = "/rest/users";

    @Autowired
    protected UserService userService;

    @Autowired
    private CrudVoteHistoryRepository voteHistoryRepository;

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<User> getAll() {
        return userService.getAll();
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public User get(@PathVariable("id") int id) {
        return userService.get(id);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> create(@RequestBody User user) {
        User created = userService.create(user);
        URI uriOfNewResource = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path(REST_URL + "/{id}")
                .buildAndExpand(created.getId()).toUri();

        return ResponseEntity.created(uriOfNewResource).body(created);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        userService.delete(id);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void update(@RequestBody User user, @PathVariable("id") int id) {
        assureIdConsistent(user, id);
        userService.update(user);
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "/{id}/voteHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<VoteHistory> getVoteHistory(@PathVariable int id) {
        return voteHistoryRepository.findAll(id);
    }

    @GetMapping(value = "/clearVoteHistory", produces = MediaType.APPLICATION_JSON_VALUE)
    public void clearVoteHistory(HttpServletResponse httpServletResponse) throws IOException {
        voteHistoryRepository.findAll().forEach(voteHistory -> voteHistoryRepository.delete(voteHistory));
        setServletResponseSuccessMessage(httpServletResponse, "VoteHistoryClear");
    }

    @GetMapping(value = "/by", produces = MediaType.APPLICATION_JSON_VALUE)
    public User getByMail(@RequestParam("email") String email) {
        return userService.getByEmail(email);
    }
}