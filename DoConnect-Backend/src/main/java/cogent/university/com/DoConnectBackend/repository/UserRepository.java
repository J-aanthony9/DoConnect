package cogent.university.com.DoConnectBackend.repository;


import cogent.university.com.DoConnectBackend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
    List<User> findByUsertype(String userType);
    User findByUsername(String username);
}
