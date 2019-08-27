package hello;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

@RestController
public class GreetingController2 {
   
	private boolean isStarted=false;
   
   @Autowired
   TimerService timerService;

   @Autowired
   MessageService messageService;

   
   @Autowired
	private  SimpMessagingTemplate messagingTemplate;
    
   @Autowired
   private SimpMessagingTemplate messageSender;   

    @MessageMapping("/hello2")
    @SendTo("/topic/greetings")
    private Greeting greeting(HelloMessage message) throws Exception {
        //Thread.sleep(1000); // simulated delay
    	//timerService.startTimer();
    	System.out.println("Serversend Message Called 1");
    
    	System.out.println("Serversend Message Called 2");
    	
    	
    	
        return new Greeting(2, "Hello, " + HtmlUtils.htmlEscape(message.getName()) + " Server sent!");
    }

    
    @MessageMapping("/news")
    public void broadcastNews(HelloMessage message,@Header("simpSessionId") String sessionId) throws Exception {
    	System.out.println("news received "+ message.getName());
    	System.out.println("news received "+ message.getMessage());
    	System.out.println("news received "+ message.getSessionId());
    	System.out.println("news received sessionId:"+ sessionId);
    	
    	
    	
    	try {
    		//messageSender.convertAndSend("/topic/greetings", "Hello ...., " + HtmlUtils.htmlEscape(message.getName()) + " Server sent!");
    		messagingTemplate.convertAndSend("/topic/greetings", new Greeting(2, "Hello, " + message.getName() + "!!555!!!!!!"));
        
    		messageService.broadcastMessage(message.getName());
//    		this.messagingTemplate.convertAndSend("/topic/greetings", "Hello ...., " + HtmlUtils.htmlEscape(message.getName()) + " Server sent!");
    	}catch(Exception ex) {
    		ex.printStackTrace();
    	}
      
    }

	
}
