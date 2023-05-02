package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Question;
import cogent.university.com.DoConnectBackend.entity.User;
import cogent.university.com.DoConnectBackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    EmailService emailService;

    @Autowired
    UserServiceImpl userService;

    // add question to repository
    @Override
    public Question addQuestion(Question question) {

        try{
            emailService.sendEmail("dksfja@gmail.com", "New Question For Review", "Please approve or deny this new question");
        } catch (Exception e ){
            System.out.println(e);
        }



        return questionRepository.save(question);

    }


    // update question to repository
    @Override
    public Question updateQuestion(Question q, long id) {
    	
//    	if(!questionRepository.findById(id).isEmpty()) {
    		
        Question questionDb = questionRepository.findById(id);
        questionDb.setDescription_question(q.getDescription_question());
        questionDb.setImage_src(q.getImage_src());
        questionDb.setDatetime(q.getDatetime());
        questionDb.setStatus(q.getStatus());
        questionDb.setTopic(q.getTopic());
        questionDb.setQapproved_by(q.getQapproved_by());
        questionDb.setTitle(q.getTitle());


//        adminUserList.stream().forEach(user -> {
//            user.getEmail();
//        });





        return questionRepository.save(questionDb);
//    	} else {
//    		return null;
//    	}
    }

    // delete question by id
    @Override
    public void deleteQuestionById(long id) {
        Question q = questionRepository.findById(id);
        questionRepository.delete(q);

    	
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
    public Question getQuestionById(long id) {

        return questionRepository.findById(id);
    }

    @Override
    public List<Question> searchQuestions(String title) {
      return (List<Question>) questionRepository.searchQuestion(title);
    }




}
