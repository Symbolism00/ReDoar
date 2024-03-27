package eco.solutions.repository.user;

import eco.solutions.model.User;
import lf.sol.genericrepository.repository.IGenericRepository;

public interface IUserRepository extends IGenericRepository<User, Long> {

    void getAll();

}
