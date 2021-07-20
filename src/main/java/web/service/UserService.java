package web.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import web.model.User;

import java.util.List;

public interface UserService  extends UserDetailsService {
    void addUser(User user);


    void updateUser(int id, User updatedUser);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listOfUser();
}
