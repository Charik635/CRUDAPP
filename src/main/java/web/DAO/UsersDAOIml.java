package web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.*;
import java.util.List;


@Repository
public class UsersDAOIml implements UsersDAO {
    @PersistenceContext
    private EntityManager entityManager;


    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.close();
    }

    @Override

    public void updateUser(User user) {
    }

    @Override
    public void removeUser(int id) {
        TypedQuery<User> q = entityManager.createQuery("DELETE  from User u where u.id = :id", User.class);
        q.setParameter("id", id);
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
