package ru.javawebinar.topjava.repository.mock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.util.UsersUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryUserRepositoryImpl implements UserRepository {
    private static final Logger LOG = LoggerFactory.getLogger(InMemoryUserRepositoryImpl.class);

    private Map<Integer, User> repository = new ConcurrentHashMap<>();
    private AtomicInteger counter = new AtomicInteger(0);

    {
        UsersUtil.USERS.forEach(this::save);
    }

    @Override
    public boolean delete(int id) {
        LOG.info("delete " + id);
        User user = repository.remove(id);

        return user != null;
    }

    @Override
    public User save(User user) {
        LOG.info("save " + user);

        if (user.isNew())
        {
            user.setId(counter.getAndIncrement());
        }

        repository.put(user.getId(), user);

        return user;
    }

    @Override
    public User get(int id) {
        LOG.info("get " + id);
        User user = repository.get(id);
        if (user == null)
            throw new NotFoundException("user not found by id: " + id);
        return user;
    }

    @Override
    public List<User> getAll() {
        LOG.info("getAll");
        return UsersUtil.getFiltered((List<User>) repository.values());
    }

    @Override
    public User getByEmail(String email) {
        LOG.info("getByEmail " + email);

        User user = null;

        for (Map.Entry<Integer, User> entry : repository.entrySet())
        {
            if (entry.getValue().getEmail().equals(email))
            {
                user = entry.getValue();
                break;
            }
        }

        if (user == null)
            throw new NotFoundException("user not found by email: " + email);
        else
            return user;
    }
}
