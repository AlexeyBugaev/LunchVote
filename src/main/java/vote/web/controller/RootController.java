package vote.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import vote.model.Role;
import vote.model.User;
import vote.service.UserService;

import java.util.List;

@Controller
public class RootController {

    @Autowired
    protected UserService userService;

   @GetMapping("/")
    public String index() {
        return "index.html";
    }

    @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void registerNewUser (@RequestBody User user, Model model) {
       user.setRoles(List.of(Role.ROLE_USER));
       userService.create(user);
    }

}
