package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.User;
import cogent.university.com.DoConnectBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public User addNewUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User updateUser(User user, long id) {
        User userDb = userRepository.findById(id).orElse(null);
        userDb.setName(user.getName());
        userDb.setUsername(user.getUsername());
        userDb.setPassword(user.getPassword());
        userDb.setEmail(user.getEmail());
        userDb.setUsertype(user.getUsertype());

        return userRepository.save(userDb);
    }

    @Override
    public User getByName(String name) {
        return userRepository.findByName(name);
    }

    @Override
    public List<User> getAllByUserType(String userType) {
        return userRepository.findByUsertype(userType);
    }

    @Override
    public User findByUserName(String name) {
        return userRepository.findByUsername(name);
    }
}
