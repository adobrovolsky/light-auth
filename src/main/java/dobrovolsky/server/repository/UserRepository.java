package dobrovolsky.server.repository;

import dobrovolsky.server.domain.User;

public interface UserRepository extends Repository<User, Long> {

    User findByLogin(String login);

}

