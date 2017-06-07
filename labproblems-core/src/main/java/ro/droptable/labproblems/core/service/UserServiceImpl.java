package ro.droptable.labproblems.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.droptable.labproblems.core.model.User;
import ro.droptable.labproblems.core.repository.UserRepository;

/**
 * Created by vlad on 07/06/2017.
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User getUserByUserName(String userName) {

        return userRepository.getUserByUserName(userName);
    }
}
