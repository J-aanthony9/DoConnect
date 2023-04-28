package cogent.university.com.DoConnectBackend.controller;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.service.AnswerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/answer")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {

    @Autowired
    private AnswerServiceImpl asi;

    @GetMapping("/getallanswers")
    public List<Answer> getAllAnswer() {

        return asi.getAllAnswer();
    }

    @GetMapping("/getAllAnswerFalse")
    public List<Answer> getAllAnswerFalse() {

        return asi.getAllAnswerFalse();

    }

    @PostMapping("/addanswer")
    public Answer addAnswer(@RequestBody Answer answer) {

        return asi.addAnswer(answer);
    }

    @GetMapping("/getanswerbyid/{id}")
    public Answer getById(@PathVariable long id) {

        return asi.getbyId(id);
    }

    @PutMapping("/updateanswer/{id}")
    public void updateAnswer(@RequestBody Answer answer,
                             @PathVariable("id") Long id) {

        asi.updateAnswer(answer, id);
    }

    @DeleteMapping("/deleteanswerbyid/{id}")
    public void deleteAnswerById(@PathVariable long id) {

        asi.deleteAnswerbyId(id);
    }


    @GetMapping("/question/{id}")
    public List<Answer> getAnswerbyQuestionId(@PathVariable("id") long questionId) {
        return asi.getAnswersByQuestionId(questionId);
    }


}
