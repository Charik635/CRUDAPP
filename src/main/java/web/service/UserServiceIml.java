package web.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.DAO.RoleDAO;
import web.DAO.UserDAO;
import web.model.Role;
import web.model.User;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Transactional

@Service
public class UserServiceIml implements UserService, UserDetailsService {
    private UserDAO usersDAO;
    private RoleDAO roleDAO;
@Autowired
    public UserServiceIml(UserDAO usersDAO, RoleDAO roleDAO) {
        this.usersDAO = usersDAO;
        this.roleDAO = roleDAO;
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

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User usr = usersDAO.getUserByEmail(s);
        if(usr == null ) {
            throw new UsernameNotFoundException("username not found");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        for (Role role : usr.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getRole()));
        }
        return new org.springframework.security.core.userdetails.User(usr.getUsername(), usr.getPassword(),
                grantedAuthorities);
    }
}
