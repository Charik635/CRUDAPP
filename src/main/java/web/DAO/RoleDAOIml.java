package web.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import web.model.Role;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

@Repository
public class RoleDAOIml implements RoleDAO{
private Role user;
private Role admin;
@PersistenceContext
private EntityManager entityManager;

    @Override
    public Role getUserById(long id) {
        TypedQuery<Role> q = entityManager.createQuery("select r from Role  r where r.id=:id", Role.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

}
