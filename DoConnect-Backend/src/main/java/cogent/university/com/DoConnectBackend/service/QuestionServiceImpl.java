package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Question;
import cogent.university.com.DoConnectBackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Question updateQuestion(Question q, Long id) {
        Question questionDb = questionRepository.findById(id).get();
        questionDb.setDescription_question(q.getDescription_question());
        questionDb.setImage_src(q.getImage_src());
        questionDb.setDatetime(q.getDatetime());
        questionDb.setStatus(q.getStatus());
        questionDb.setTopic(q.getTopic());
        questionDb.setTitle(q.getTitle());
        return questionRepository.save(questionDb);
    }

    // delete question by id
    @Override
    public void deleteQuestionbyId(Long id) {

        Optional<Question> q = questionRepository.findById(id);
        questionRepository.delete(q.get());
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
    public Question getQuestionById(Long id) {

        return questionRepository.findById(id).get();
    }


}
