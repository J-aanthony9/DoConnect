package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Question;
import cogent.university.com.DoConnectBackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    // add question to repository
    @Override
    public Question addQuestion(Question question) {

        return questionRepository.save(question);

    }

    // update question to repository
    @Override
    public Question updateQuestion(Question q, int id) {
    	
//    	if(!questionRepository.findById(id).isEmpty()) {
    		
        Question questionDb = questionRepository.findById(id);
        questionDb.setDescription_question(q.getDescription_question());
        questionDb.setImage_src(q.getImage_src());
        questionDb.setDatetime(q.getDatetime());
        questionDb.setStatus(q.getStatus());
        questionDb.setTopic(q.getTopic());
        questionDb.setTitle(q.getTitle());
        
        return questionRepository.save(questionDb);
//    	} else {
//    		return null;
//    	}
    }

    // delete question by id
    @Override
    public void deleteQuestionbyId(int id) {


    	
//    	if(!questionRepository.findById(id).isEmpty()) {
//
//    		Question q = questionRepository.findById(id).get(0);
//
//            	questionRepository.delete(q);
//        	}
    	
    }

    // retrieve list of all question in repository
    @Override
    public List<Question> getAllQuestion() {

        return (List<Question>) questionRepository.findAll();
    }

    // retrieve list of all question in repository that are false
    // assumption that status is the field that holds true or false

    @Override
    public List<Question> getAllQuestionFalse() {
        return (List<Question>) questionRepository.findBystatus("false");
    }

    // retrieve list of all question that are specific topic
    @Override
    public List<Question> getQuestionByTopic(String topic) {

        return (List<Question>) questionRepository.findBytopic(topic);
    }

    //retrieve list of all items
    @Override
    public Question getQuestionById(int id) {

        return questionRepository.findById(id);
    }

    @Override
    public List<Question> getQuestionByTitle(String title) {
        return (List<Question>) questionRepository.findAllByTitle(title);
    }


}
