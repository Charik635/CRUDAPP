package web.DAO;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    void updateUser(int id, User updatedUser);

    void removeUser(int id);

    User getUserById(int id);

    List<User> listOfUser();
}
