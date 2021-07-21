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
    public void removeUser(int id) {
        Query query = entityManager.createNativeQuery("delete from user_role where id=:id",User.class);
        Query query1 = entityManager.createNativeQuery("DELETE FROM users WHERE id = :id",User.class);
        query.setParameter("id",id);
        query1.setParameter("id",id);
        query.executeUpdate();
        query1.executeUpdate();
    }


    @Override
    public User getUserById(int id) {
        TypedQuery<User> q = entityManager.createQuery("select u from User  u where u.id=:id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public User getUserByEmail(String email) {
        TypedQuery<User> q = entityManager.createQuery("select u from User  u where u.username=:email", User.class);
        q.setParameter("email", email);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> listOfUser() {
        List<User> s = entityManager.createQuery("select u from User u", User.class).getResultList();
        return s;

    }
}
