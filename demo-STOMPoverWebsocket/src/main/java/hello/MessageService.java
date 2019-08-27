package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class MessageService  {
	
	   @Autowired
 	  private  SimpMessagingTemplate messagingTemplate;

	  public void broadcastMessage(String msg){
		  messagingTemplate.convertAndSend("/topic/greetings", new Greeting(3, "Hello, " + msg + "!!1111999!!!!!!")); 
	   }
	
}

