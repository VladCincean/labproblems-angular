package ro.droptable.labproblems.core.service;

import ro.droptable.labproblems.core.model.User;

/**
 * Created by vlad on 07/06/2017.
 */
public interface UserService {

    User getUserByUserName(String userName);
}
