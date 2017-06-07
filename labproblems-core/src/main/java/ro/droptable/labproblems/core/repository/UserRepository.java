package ro.droptable.labproblems.core.repository;

import org.springframework.data.jpa.repository.Query;
import ro.droptable.labproblems.core.model.User;

/**
 * Created by vlad on 07/06/2017.
 */
public interface UserRepository extends LabProblemsRepository<User, Long> {

    @Query("SELECT u FROM User u WHERE u.userName = ?1")
    User getUserByUserName(String userName);
}
