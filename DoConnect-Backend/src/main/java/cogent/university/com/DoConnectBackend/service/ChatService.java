package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Chat;
import org.springframework.stereotype.Service;

public interface ChatService {
    Chat addMsg(Chat chat);
    void deleteChatById(int id);
    String getAllMsgLeft();





}
