package cogent.university.com.DoConnectBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.university.com.DoConnectBackend.entity.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {

	List<Question> findBytopic(String topic);
	List<Question> findBystatus(String status);
	Question findById(int id);
	void deleteById(int id);


	List<Question> findAllByTitle(String title);
}
