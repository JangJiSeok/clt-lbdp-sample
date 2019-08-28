package hello;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;

@Controller
public class WebsocketSendToUserController {

    private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
	
	@Autowired
    private SimpMessageSendingOperations messagingTemplate;
	
    private Gson gson = new Gson();

    //public String processMessageFromClient(@Payload String message, Principal principal) throws Exception {
    //@Payload Message msg
    //public String processMessageFromClient(@Payload String message,@Header("simpSessionId") String sessionId) throws Exception {
    
    
    @MessageMapping("/message")
    @RequestMapping("/message")
    //@SendToUser("/queue/jang/reply")
    public String processMessageFromClient(Message<Object> message,@Payload String msg, @Header("simpSessionId") String sessionId) throws Exception {
    	System.out.println("one obj Message Received!" + message);
    	System.out.println("one Message Received!" + msg);
        String rS=gson.fromJson(msg, Map.class).get("name").toString();
        Map map=new HashMap();
        map.put("name",rS);
        map.put("simpSessionId",sessionId);
        String rS2=gson.toJson(map);
        
        String username="queue/reply-user"+ sessionId;
        System.out.println("username:" + username);
        String authedSender="";
        try {
        Principal principal = message.getHeaders().get(SimpMessageHeaderAccessor.USER_HEADER, Principal.class);
        authedSender = principal.getName();
        System.out.println("authedSender:" + authedSender);
        } catch(Exception ex) {
        	ex.printStackTrace();
        }
        
        sendMessage(username,"user/queue/reply", rS2);
        
       // messagingTemplate.convertAndSend("/queue/"+ authedSender +"/reply",  "jangjjjjj");
        messagingTemplate.convertAndSendToUser(authedSender, "/queue/"+authedSender+"/reply", new Greeting(5, "Hello, " + "Message Mapping Op Sent " +  dateTimeFormatter.format(LocalDateTime.now())));
       // messagingTemplate.convertAndSend("/queue/"+ message +"/reply",  "jangjjjjj");
        
    	return rS2;
    }

    @MessageExceptionHandler
    @SendToUser("/queue/jang/errors")
    public String handleException(Throwable exception) {
    	System.out.println("one Message Received! But Error!");
    	
        return exception.getMessage();
    }
    

    
    private void sendMessage(String user,String dest, String payload) {
    	messagingTemplate.convertAndSendToUser(user, dest, payload);
    	
    }
    
    
}
