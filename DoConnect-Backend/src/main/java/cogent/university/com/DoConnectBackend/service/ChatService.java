package cogent.university.com.DoConnectBackend.service;

import java.util.List;

import cogent.university.com.DoConnectBackend.entity.Chat;

public interface ChatService {
    Chat addMsg(Chat chat);

    void deleteChatById(int id);

    List<Chat> getAllMsgLeft();

	List<Chat> getAllMsgBetweenUser(String from_user, String to_user);


}
