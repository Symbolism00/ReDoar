package eco.solutions.service.user;

import eco.solutions.repository.user.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService implements IUserService{

    private IUserRepository userRepository;

    @Override
    public void getAll() {
        userRepository.getAll();
    }

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
