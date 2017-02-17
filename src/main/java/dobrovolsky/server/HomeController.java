package dobrovolsky.server;

import com.google.gson.JsonObject;
import dobrovolsky.server.domain.Credentials;
import dobrovolsky.server.domain.User;
import dobrovolsky.server.domain.mapper.ListMapper;
import dobrovolsky.server.domain.mapper.UserMapper;
import dobrovolsky.server.service.UserService;
import dobrovolsky.shared.UserDto;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class);

    private final UserService userService;

    @Autowired
    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getHomePage(ModelMap modelMap) {
        return "index";
    }

    @RequestMapping(
            value = "auth",
            method = RequestMethod.POST,
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<String> auth(@RequestBody Credentials credentials) {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("token", credentials.getLogin() + " " + credentials.getPass());
        return ResponseEntity.ok(jsonObject.toString());
    }

    @RequestMapping(
            value = "user/{id}",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public @ResponseBody ResponseEntity<UserDto> getUser(@PathVariable long id, @RequestHeader("X-TOKEN") String token) {
        User user = userService.findById(id);
        UserDto userDto = new UserMapper().map(user);
        return ResponseEntity.ok(userDto);
    }

    @RequestMapping(
            value = "/user",
            method = RequestMethod.GET,
            produces = {MediaType.APPLICATION_JSON_VALUE},
            headers = {"X-TOKEN"})
    public @ResponseBody List<UserDto> getUsers() {
        List<User> users = userService.findAll();
        ListMapper<User, UserDto> usersMapper = new ListMapper<>(new UserMapper());
        return usersMapper.map(users);
    }
}
