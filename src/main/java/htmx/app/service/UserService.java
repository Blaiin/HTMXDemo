package htmx.app.service;

import htmx.app.constants.Constants;
import htmx.app.dto.UserDTO;
import htmx.app.errors.exception.BusinessException;
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

    public void createUser(final UserDTO userDTO) throws BusinessException {
        if(repository.existsByEmail(userDTO.getEmail())) {
            throw new BusinessException.BusinessExceptionBuilder(BusinessException.Reason.ALREADY_IN_USE)
                    .addProblematicElement(Constants.EMAIL)
                    .build();
        }
        if(repository.existsByUsername(userDTO.getUsername())) {
            throw new BusinessException.BusinessExceptionBuilder(BusinessException.Reason.ALREADY_IN_USE)
                    .addProblematicElement(Constants.USERNAME)
                    .build();
        }
        repository.save(User.fromDTO(userDTO));
    }

    public boolean exists(String identifier) {
        return repository.existsByEmail(identifier) || repository.existsByUsername(identifier);
    }
}
