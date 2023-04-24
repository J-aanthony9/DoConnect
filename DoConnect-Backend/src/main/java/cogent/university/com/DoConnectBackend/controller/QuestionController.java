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
	
	@PostMapping("/addquestion")
	public void AddQuestion(@RequestBody Question question) {
		
		qs.AddQuestion(question);
	}
	
	@PutMapping("/updatequestion")
	public Question UpdateQuestion(@RequestBody Question question) {
		
		return qs.UpdateQuestion(question);
	}
	
	@DeleteMapping("/deletequestionbyid/{id}")
	 public void DeleteQuestionbyId(@PathVariable long id) {
		 
		qs.DeleteQuestionbyId(id);
	 }
	
	@GetMapping("/getallquestion")
	public List<Question> GetAllQuestion(){
		
		return qs.GetAllQuestion();
	}
	
	@GetMapping("/getallquestionFalse")
	public List<Question> GetAllQuestionFalse(){
		
		return qs.GetAllQuestionFalse();
	}
	
	@GetMapping("/getQuestionbytopic")
	public List<Question> GetQuestionbyTopic(@RequestParam String topic) {
		
		return qs.GetQuestionByTopic(topic);
	}
	
	@GetMapping("/getQuestionbyid/{id}")
	public Question GetQuestionbyId(@PathVariable long id) {
		
		return qs.GetQuestionbyId(id);
	}
}
