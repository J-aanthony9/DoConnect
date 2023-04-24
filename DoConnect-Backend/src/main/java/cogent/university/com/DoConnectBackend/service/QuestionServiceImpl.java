package cogent.university.com.DoConnectBackend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cogent.university.com.DoConnectBackend.entity.Question;
import cogent.university.com.DoConnectBackend.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

	@Autowired
	private QuestionRepository questionRepository;
	
	// add question to repository
	@Override
	public Question AddQuestion(Question question) {
		
		return questionRepository.save(question);
		
	}
	
	// update question to repository
	@Override
	public Question UpdateQuestion(Question q) {
	
		return questionRepository.save(q);
	}

	// delete question by id 
	@Override
	public void DeleteQuestionbyId(Long id) {
		
		Optional<Question> q = questionRepository.findById(id);
		questionRepository.delete(q.get());
	}

	// retrieve list of all question in repository
	@Override
	public List<Question> GetAllQuestion() {
		
		return (List<Question>) questionRepository.findAll();
	}

	// retrieve list of all question in repository that are false
	// assumption that status is the field that holds true or false
	
	@Override
	public List<Question> GetAllQuestionFalse() {
		// TODO Auto-generated method stub
		return (List<Question>) questionRepository.findBystatus("false");
	}

	// retrieve list of all question that are specific topic
	@Override
	public List<Question> GetQuestionByTopic(String topic) {
		
		return  (List<Question>) questionRepository.findBytopic(topic);
	}

	//retrieve list of all items 
	@Override
	public Question GetQuestionbyId(Long id) {
	
		return questionRepository.findById(id).get();
	}

	

}
