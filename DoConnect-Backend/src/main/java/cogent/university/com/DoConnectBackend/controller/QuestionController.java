package cogent.university.com.DoConnectBackend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cogent.university.com.DoConnectBackend.entity.Question;
import cogent.university.com.DoConnectBackend.service.QuestionServiceImpl;

@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "http://localhost:4200")
public class QuestionController {

	@Autowired
	private QuestionServiceImpl qs;
	

	@PostMapping("/addQuestion")
	public Question addQuestion(@RequestBody Question question) {
		
		return qs.addQuestion(question);
	}
	
	@PutMapping("/updateQuestion/{id}")
	public Question updateQuestion(@RequestBody Question question,
								   @PathVariable int id) {
		
		return qs.updateQuestion(question, id);
	}
	

	@DeleteMapping("/deleteQuestionbyid/{id}")
	 public void deleteQuestionById(@PathVariable int id) {

		 
		qs.deleteQuestionbyId(id);
	 }
	
	@GetMapping("/getAllQuestion")
	public List<Question> getAllQuestion(){
		
		return qs.getAllQuestion();
	}
	
	@GetMapping("/getAllQuestionFalse")
	public List<Question> getAllQuestionFalse(){
		
		return qs.getAllQuestionFalse();
	}
	
	@GetMapping("/getQuestionByTopic")
	public List<Question> getQuestionByTopic(@RequestParam String topic) {
		
		return qs.getQuestionByTopic(topic);
	}
	
	@GetMapping("/getQuestionById/{id}")
	public List<Question> getQuestionById(@PathVariable int id) {
		
		return qs.getQuestionById(id);
	}
}
