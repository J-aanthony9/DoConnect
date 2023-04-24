package cogent.university.com.DoConnectBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.repository.AnswerRepository;

@Service
public class AnswerServiceImpl implements AnswerService{

	@Autowired
	private AnswerRepository answerRepository;
	
	// get all answer from repository
	@Override
	public List<Answer> getAllAnswer() {
		
		return answerRepository.findAll();
	}

	//retrieve list of answers that have the status field value of false
	// assumption is that status is field that holds true or false
	@Override
	public List<Answer> getAllAnswerFalse() {
		
		return answerRepository.findBystatus("false");
	}

	// add an answer entity to repository
	@Override
	public void addAnswer(Answer answer) {
		
		answerRepository.save(answer);
		
	}
	
	
	// simple retrieval of answer entity by id
	@Override
	public Answer getbyId(long id) {
		
		return answerRepository.findById(id).get();
	}

	// add updated answer entity to repository
	@Override
	public void updateAnswer(Answer answer) {
		
		answerRepository.save(answer);
		
	}

	// Gets answer by id and deletes it from repository
	@Override
	public void deleteAnswerbyId(long id) {
		
		Optional<Answer> answer = answerRepository.findById(id);
		answerRepository.delete(answer.get());
		
	}

	// returns list of answers that coincide with question id
	
	@Override
	public List<Answer> getAnswersbyQuestionId(long id) {
		// To be implemented
		return null;
	}

}
