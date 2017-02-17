package dobrovolsky.server.repository;

import dobrovolsky.server.domain.User;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public class UserRepositoryImpl extends GenericSqlRepository<User, Long> implements UserRepository {

    protected UserRepositoryImpl() {
        super(User.class);
    }
}
