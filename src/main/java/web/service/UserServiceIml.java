package web.service;

import org.springframework.stereotype.Service;
import web.DAO.UserDAO;
import web.model.User;

import javax.transaction.Transactional;
import java.util.List;

@Transactional

@Service
public class UserServiceIml implements UserService {
    private UserDAO usersDAO;

    public UserServiceIml(UserDAO usersDAO) {
        this.usersDAO = usersDAO;
    }

    @Override
    public void addUser(User user) {
        usersDAO.addUser(user);
    }

    @Override
    public void updateUser(int id, User updatedUser) {
        usersDAO.updateUser(id, updatedUser);
    }

    @Override
    public void removeUser(int id) {
        usersDAO.removeUser(id);
    }

    @Override
    public User getUserById(int id) {
        return usersDAO.getUserById(id);
    }

    @Override
    public List<User> listOfUser() {
        return usersDAO.listOfUser();
    }
}
