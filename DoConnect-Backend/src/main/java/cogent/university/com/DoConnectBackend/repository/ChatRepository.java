package cogent.university.com.DoConnectBackend.repository;

import cogent.university.com.DoConnectBackend.entity.Chat;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ChatRepository extends JpaRepository<Chat, Integer> {

	@Query(value ="SELECT * From chat_tbl where (from_user = ?1 and to_user = ?2) "
			,nativeQuery = true)
	List<Chat> findAllBetweenTwoUsers(String from_user, String to_user);
}
