package dobrovolsky.server.repository;

import dobrovolsky.server.domain.User;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;

@Repository("userRepository")
public class UserRepositoryImpl extends GenericSqlRepository<User, Long> implements UserRepository {

    protected UserRepositoryImpl() {
        super(User.class);
    }

    @Override
    public User findByLogin(String login) {
        try {
            return entityManager
                    .createQuery("select u from " + entityClass.getSimpleName() + " as u where u.login like :login", User.class)
                    .setParameter("login", login)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
