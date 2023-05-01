package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Question;

import java.util.List;

public interface QuestionService {

    public Question addQuestion(Question question);

    public Question updateQuestion(Question question, long id);

    public void deleteQuestionById(long id);

    public List<Question> getAllQuestion();

    public List<Question> getAllQuestionFalse();

    public List<Question> getQuestionByTopic(String topic);

    public Question getQuestionById(long id);

    public List<Question> searchQuestions(String title);



    //retrieve list of all items
}
