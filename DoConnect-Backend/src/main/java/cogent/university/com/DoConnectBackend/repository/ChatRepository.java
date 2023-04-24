package cogent.university.com.DoConnectBackend.repository;

import cogent.university.com.DoConnectBackend.entity.Chat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatRepository extends JpaRepository<Chat, Integer> {
}
