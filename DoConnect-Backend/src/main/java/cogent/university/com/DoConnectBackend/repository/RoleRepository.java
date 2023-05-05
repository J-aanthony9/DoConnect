package cogent.university.com.DoConnectBackend.repository;


import cogent.university.com.DoConnectBackend.entity.Role;
import cogent.university.com.DoConnectBackend.roles.ERole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
