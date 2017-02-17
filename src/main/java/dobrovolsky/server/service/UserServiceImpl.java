package dobrovolsky.server.service;

import dobrovolsky.server.domain.User;
import dobrovolsky.server.repository.UserRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {

    private static final Logger LOGGER = Logger.getLogger(UserServiceImpl.class);

    @Autowired
    @Qualifier("userRepository")
    private UserRepository repository;

    @Override
    @Transactional(readOnly = false)
    public void save(User user) {
        Objects.requireNonNull(user);
        repository.save(user);
    }

    @Override
    @Transactional(readOnly = false)
    public void remove(User user) {
        Objects.requireNonNull(user);
        repository.remove(user);
    }

    @Override
    public User findById(Long id) {
        Objects.requireNonNull(id);
        return repository.findById(id);
    }

    @Override
    public List<User> findAll() {
        List<User> users = repository.findAll();
        if (users == null) {
            return Collections.emptyList();
        }
        return users;
    }

    @Override
    public User findByLogin(String login) {
        Objects.requireNonNull(login);
        return repository.findByLogin(login);
    }
}
