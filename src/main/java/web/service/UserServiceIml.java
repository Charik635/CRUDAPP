package web.service;

import org.springframework.stereotype.Service;
import web.DAO.UserDAO;
import web.DAO.UsersDAOIml;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
        User userToBeUpdated = getUserById(id);
        userToBeUpdated.setName(updatedUser.getName());
        userToBeUpdated.setSurName(updatedUser.getSurName());
        userToBeUpdated.setEmail(updatedUser.getEmail());
        userToBeUpdated.setAge(updatedUser.getAge());
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
