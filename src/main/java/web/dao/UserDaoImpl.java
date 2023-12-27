package web.dao;

import web.model.User;

import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void addUser(User user) {
        entityManager.persist(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void updateUser(User user) {
        entityManager.detach(user);
        entityManager.getTransaction().begin();
        entityManager.merge(user);
        entityManager.getTransaction().commit();
    }

    @Override
    public void removeUser(long id) {
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
        User user = entityManager.find(User.class, id);
        if (null == user) {
            throw new NullPointerException("User with id " + id + " not found!");
        }
        entityManager.detach(user);
        return user;
    }

    @Override
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        users = (List<User>)entityManager.createQuery("from User").getResultList();
        return users;
    }
}
