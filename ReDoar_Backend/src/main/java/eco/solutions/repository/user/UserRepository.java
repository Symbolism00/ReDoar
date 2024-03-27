package eco.solutions.repository.user;

import eco.solutions.model.User;
import lf.sol.genericrepository.repository.GenericCondition;
import lf.sol.genericrepository.repository.GenericRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository extends GenericRepository<User, Long> implements IUserRepository {
    public UserRepository() {
        super(User.class);
    }

    @Override
    public void getAll() {
        GenericCondition<User> condition = initCondition().isFalse("test");
        getAll(condition);
    }
}
