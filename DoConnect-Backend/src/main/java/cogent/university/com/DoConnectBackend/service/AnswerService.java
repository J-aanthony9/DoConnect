package cogent.university.com.DoConnectBackend.service;

import java.util.List;

import cogent.university.com.DoConnectBackend.entity.Answer;

public interface AnswerService {
	
	public List<Answer> getAllAnswer();
	public List<Answer> getAllAnswerFalse();
	public void addAnswer(Answer answer);
	public Answer getbyId(long id);
	public void updateAnswer(Answer answer);
	public void deleteAnswerbyId(long id);
	public List<Answer> getAnswersbyQuestionId(long id);
}
