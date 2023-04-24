package cogent.university.com.DoConnectBackend.service;

import java.util.List;


import cogent.university.com.DoConnectBackend.entity.Question;

public interface QuestionService {

	public Question AddQuestion(Question question);
	public Question UpdateQuestion(Question question);
	public void DeleteQuestionbyId(Long id);
	public List<Question> GetAllQuestion();
	public List<Question> GetAllQuestionFalse();
	public List<Question> GetQuestionByTopic(String topic);
	public Question GetQuestionbyId(Long id);
	
	
}
