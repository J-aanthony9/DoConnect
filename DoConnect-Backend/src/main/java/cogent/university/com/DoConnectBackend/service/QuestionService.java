package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Question;

import java.util.List;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question, int id);

    public void deleteQuestionbyId(int id);

    public List<Question> getAllQuestion();

    public List<Question> getAllQuestionFalse();

    public List<Question> getQuestionByTopic(String topic);

    public List<Question> getQuestionById(int id);


    //retrieve list of all items
}
