package cogent.university.com.DoConnectBackend.controller;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.entity.Question;
import cogent.university.com.DoConnectBackend.service.AnswerServiceImpl;
import cogent.university.com.DoConnectBackend.service.QuestionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl asi;

    @Autowired
    private QuestionServiceImpl qsi;

    @GetMapping("/getallanswers")
    public List<Answer> getAllAnswer() {

        return asi.getAllAnswer();
    }

    @GetMapping("/getAllAnswerFalse")
    public List<Answer> getAllAnswerFalse() {

        return asi.getAllAnswerFalse();

    }

    @PostMapping("/addAnswer")
    public Answer addAnswer(@RequestBody Answer answer) {
        return asi.addAnswer(answer);
    }

    @PutMapping("/question/{id}")
    public Answer assignAnswerToQuestion(@PathVariable("id") int id,
                                         @RequestBody Answer answer) {

        Question question = qsi.getQuestionById(id);
        answer.setQuestion(question);
        return asi.addAnswer(answer);
    }



    @GetMapping("/getanswerbyid/{id}")
    public Answer getById(@PathVariable long id) {

        return asi.getbyId(id);
    }

    @PutMapping("/updateanswer/{id}")
    public Answer updateAnswer(@RequestBody Answer answer,
                             @PathVariable("id") Long id) {

        return asi.updateAnswer(answer, id);
    }

    @DeleteMapping("/deleteanswerbyid/{id}")
    public void deleteAnswerById(@PathVariable long id) {

         asi.deleteAnswerbyId(id);
    }


    @GetMapping("/question/{id}")
    public List<Answer> getAnswerbyQuestionId(@PathVariable("id") int questionId) {
        return asi.getAnswersByQuestionId(questionId);
    }


}
