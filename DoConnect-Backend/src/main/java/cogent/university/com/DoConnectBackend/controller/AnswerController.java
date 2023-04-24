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
import org.springframework.web.bind.annotation.RestController;

import cogent.university.com.DoConnectBackend.entity.Answer;
import cogent.university.com.DoConnectBackend.service.AnswerServiceImpl;

@RestController
@RequestMapping("/answer")
@CrossOrigin(origins = "http://localhost:4200")
public class AnswerController {
	
	@Autowired
	private AnswerServiceImpl asi;
	
	@GetMapping("/getallanswers")
	public List<Answer> getAllAnswer(){
		
		return asi.getAllAnswer();
	}
	
	@GetMapping("/getAllAnswerFalse")
	public List<Answer> getAllAnswerFalse(){
		
		return asi.getAllAnswerFalse();
		
	}
	
	@PostMapping("/addanswer")
	public void addAnswer(@RequestBody Answer answer) {
		
		asi.addAnswer(answer);
	}
	
	@GetMapping("/getanswerbyid/{id}")
	public Answer getById(@PathVariable long id) {
		
		return asi.getbyId(id);
	}
	
	@PutMapping("/updateanswer")
	public void updateAnswer(@RequestBody Answer answer) {
		
		asi.updateAnswer(answer);
	}
	
	@DeleteMapping("/deleteanswerbyid/{id}")
	public void deleteAnswerById(@PathVariable long id) {
		
		asi.deleteAnswerbyId(id);
	}
	
/*

	@GetMapping("/getAnswersbyQuestionID/{id}")
	public List<Answer> getAnswerbyQuestionId(@PathVariable long id)
	{
		return asi.getAnswersbyQuestionId(id);
	}
	
*/
	
}
