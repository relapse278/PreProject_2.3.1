package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
//    @PersistenceContext
//    private EntityManager entityManager;
private EntityManagerFactory emf;
    @PersistenceUnit
    public void setEntityManagerFactory(EntityManagerFactory emf) {
        this.emf = emf;
    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.detach(user);
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeUser(long id) {
        EntityManager entityManager = emf.createEntityManager();
        entityManager.getTransaction().begin();
        User user = entityManager.find(User.class, id);
        if (null == user) {
            throw new NullPointerException("User with id " + id + " not found!");
        }
        entityManager.remove(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public User getUserById(long id) {
        EntityManager entityManager = emf.createEntityManager();
        User user = entityManager.find(User.class, id);
        if (null == user) {
            throw new NullPointerException("User with id " + id + " not found!");
        }
        entityManager.detach(user);
        return user;
    }

    @Override
    public List<User> listUsers() {
        EntityManager entityManager = emf.createEntityManager();
        List<User> users = new ArrayList<>();
        users = (List<User>)entityManager.createQuery("from User").getResultList();
        return users;
    }
}
