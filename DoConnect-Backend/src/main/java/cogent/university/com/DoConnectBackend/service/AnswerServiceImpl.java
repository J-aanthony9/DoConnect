package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.entity.Question;
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

    @Autowired
    private EmailService emailService;

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
    public Answer addAnswer(Answer answer) {


        try{
            emailService.sendEmail("zomgitzjohn@gmail.com", "Need Approval for answer", "Please approve or deny this new answer");
        } catch (Exception e ){
            System.out.println(e);
        }

        return answerRepository.save(answer);

    }




    // simple retrieval of answer entity by id
    @Override
    public Answer getbyId(long id) {

        return answerRepository.findById(id).get();
    }

    @Override
    public Answer updateAnswer(Answer answer, Long id) {


        try{
            emailService.sendEmail("dksfja@gmail.com", "Your answer has been approved", "Congrats your answer has been approved.");
        } catch (Exception e ){
            System.out.println(e);
        }



        Answer answerDb = answerRepository.findById(id).get();
        answerDb.setDescription_answer(answer.getDescription_answer());
        answerDb.setImage_src(answer.getImage_src());
        answerDb.setStatus(answer.getStatus());
        answer.setDatetime(answer.getDatetime());
        answerDb.setApproved_by(answer.getApproved_by());



        return answerRepository.save(answerDb);

    }

    // Gets answer by id and deletes it from repository

//    @Override
//    public void deleteQuestionById(long id) {
//        Question q = questionRepository.findById(id);
//        questionRepository.delete(q);
//
//
//    }
    @Override
    public void deleteAnswerById(long id) {


        try{
            emailService.sendEmail("dksfja@gmail.com", "Your answer has been denied", "Please submit a valid answer");
        } catch (Exception e ){
            System.out.println(e);
        }


        Answer answer = answerRepository.findById(id).orElse(null);
        answerRepository.delete(answer);
    }

    // returns list of answers that coincide with question id
    @Override
    public List<Answer> getAnswersByQuestionId(long questionId) {
//        List<Answer> answers = answerRepository.findAll();
//        List<Answer> answerByQuestionId;
//
//        answerByQuestionId = answers.stream()
//                .filter(answer -> answer.getQuestion().getId() == questionId)
//                .collect(Collectors.toList());
//
//        return answerByQuestionId;
    	return answerRepository.getAllByQuestionId(questionId);
    }

}
