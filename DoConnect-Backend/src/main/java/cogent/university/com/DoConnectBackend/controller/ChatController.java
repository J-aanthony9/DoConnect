package cogent.university.com.DoConnectBackend.controller;

import cogent.university.com.DoConnectBackend.entity.Chat;
import cogent.university.com.DoConnectBackend.service.ChatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/chat")
@CrossOrigin(origins = "http://localhost:4200")
public class ChatController {
    @Autowired
    ChatService chatService;

    @PostMapping("/addMsg")
    public Chat addMsg(@RequestBody Chat chat){
        return chatService.addMsg(chat);
    }

    @DeleteMapping("/deleteChatById")
    public void deleteChatById(@PathVariable("id") int id){
        chatService.deleteChatById(id);
    }

    @GetMapping("getallmsg")
    public String getAllMsg(){
        return chatService.getAllMsgLeft();
    }
}
