package htmx.app.service;

import htmx.app.model.User;
import htmx.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository repository;

    @Autowired
    public UserService (final UserRepository repository) {
        this.repository = repository;
    }

    public void createUser(final User user) {
        repository.save(user);
    }
}
