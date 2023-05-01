package cogent.university.com.DoConnectBackend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.entity.Question;


public interface AnswerRepository extends JpaRepository<Answer, Long> {

	List<Answer> findBystatus(String status);
	//List<Answer> findByQuestion(Question q);

	List<Answer> getAllByQuestionId(int questionId);
}
