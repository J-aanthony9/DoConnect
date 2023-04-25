package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.repository.AnswerRepository;
import cogent.university.com.DoConnectBackend.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

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

    @Override
    public void updateAnswer(Answer answer, Long id) {
        Answer answerDb = answerRepository.findById(id).get();
        answerDb.setDescription_answer(answer.getDescription_answer());
        answerDb.setImg_src(answer.getImg_src());
        answerDb.setStatus(answer.getStatus());
        answer.setDatetime(answer.getDatetime());

        answerRepository.save(answerDb);

    }

    // Gets answer by id and deletes it from repository
    @Override
    public void deleteAnswerbyId(long id) {

        Optional<Answer> answer = answerRepository.findById(id);
        answerRepository.delete(answer.get());

    }

    // returns list of answers that coincide with question id
    @Override
    public List<Answer> getAnswersByQuestionId(long questionId) {
        List<Answer> answers = answerRepository.findAll();
        List<Answer> answerByQuestionId;

        answerByQuestionId = answers.stream()
                .filter(answer -> answer.getQuestion().getId() == questionId)
                .collect(Collectors.toList());

        return answerByQuestionId;
    }

}
