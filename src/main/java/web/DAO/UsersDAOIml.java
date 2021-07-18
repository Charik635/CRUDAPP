package web.DAO;

import org.springframework.stereotype.Repository;
import web.model.User;

import javax.persistence.*;
import java.util.List;


@Repository
public class UsersDAOIml implements UserDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.close();
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
        String sql = "DELETE FROM users WHERE id = " + id;
        entityManager.createNativeQuery(sql).executeUpdate();

    }

    @Override
    public User getUserById(int id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User  u where u.id=:id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> listOfUser() {
        List<User> s = entityManager.createQuery("select u from User u", User.class).getResultList();
        return s;

    }
}
