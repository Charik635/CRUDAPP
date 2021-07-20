package web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.Role;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class RoleDAOIml implements RoleDAO{
private Role user;
private Role admin;
@PersistenceContext
private EntityManager entityManager;

    @Override
    public  void addRoles() {
        user.setRole("ROLE_USER");
        admin.setRole("ROLE_ADMIN");
        entityManager.persist(user);
        entityManager.persist(admin);

    }

}
