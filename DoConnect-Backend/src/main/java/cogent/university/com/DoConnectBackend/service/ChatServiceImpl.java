package cogent.university.com.DoConnectBackend.service;

import cogent.university.com.DoConnectBackend.entity.Chat;
import cogent.university.com.DoConnectBackend.repository.ChatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService{

    @Autowired
    ChatRepository chatRepository;

    @Override
    public Chat addMsg(Chat chat) {
        return chatRepository.save(chat);
    }

    @Override
    public void deleteChatById(int id) {
        chatRepository.deleteById(id);

    }

    @Override
    public String getAllMsgLeft() {
        return chatRepository.findAll().toString();
    }
}
