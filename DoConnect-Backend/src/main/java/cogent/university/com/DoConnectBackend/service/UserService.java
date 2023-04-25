package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.User;

import java.util.List;

public interface UserService {
    //    User addUser(User user);
    User addNewUser(User user);

    //getLogin()
    List<User> getAllUser();

    User getUserById(int id);

    User updateUser(User user, int id);

    User getByName(String name);

    List<User> getAllByUserType(String userType);
    //userLoginVerify
}
