package hello;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class TimerService {
	
	private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");

	@Autowired
	private  SimpMessagingTemplate messagingTemplate;

	
	@Scheduled(fixedRate = 1000000)
	public void scheduleTaskWithFixedRate() {
		//messagingTemplate.convertAndSend("/topic/greetings", "Timer server send");
		System.out.println("Schedule start");
		messagingTemplate.convertAndSend("/topic/greetings", new Greeting(5, "Hello, " + "TimerServer Sent " +  dateTimeFormatter.format(LocalDateTime.now())));
	}
	
	@Scheduled(fixedRate = 10000)
	public void schedule2TaskWithFixedRate() {
		System.out.println("Schedule start: SendToUser 222");
		messagingTemplate.convertAndSendToUser("jang", "/queue/jang/reply", new Greeting(5, "Hello, " + "TimerServer Sent " +  dateTimeFormatter.format(LocalDateTime.now())));
	}
	

}
