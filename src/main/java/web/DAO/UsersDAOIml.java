package web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;
import java.util.List;


@Service

public class UsersDAOIml  implements UsersDAO {

    private  EntityManagerFactory entityManagerFactory;

    @Autowired

    public UsersDAOIml(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }


    @Override

    public void addUser(User user) {
     EntityManager entityManager =  entityManagerFactory.createEntityManager();
     EntityTransaction transaction =  entityManager.getTransaction();
     transaction.begin();
     entityManager.persist(user);
    transaction.commit();
     entityManager.close();
    }

    @Override

    public void updateUser(User user) {
    }

    @Override

    public void removeUser(int id) {
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        TypedQuery<User> q =  entityManager.createQuery("DELETE  from User u where u.id = :id",User.class);
        q.setParameter("id",id);
        transaction.commit();
        entityManager.close();
    }

    @Override
    public User getUserById(int id) {
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        TypedQuery<User> q = entityManager.createQuery("select u from User  u where u.id=:id",User.class);
        q.setParameter("id",id);
        transaction.commit();
     return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    public List<User> listOfUser() {
        EntityManager entityManager =  entityManagerFactory.createEntityManager();
        EntityTransaction transaction =  entityManager.getTransaction();
        transaction.begin();
        List<User> s = entityManager.createQuery("select u from User u",User.class).getResultList();
        transaction.commit();
        return s;

    }
}
