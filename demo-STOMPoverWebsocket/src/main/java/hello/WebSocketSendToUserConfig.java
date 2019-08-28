package hello;

import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.web.reactive.socket.server.support.WebSocketHandlerAdapter;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.*;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import org.springframework.web.socket.server.support.DefaultHandshakeHandler;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketSendToUserConfig implements WebSocketMessageBrokerConfigurer {


    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry
                .setMessageSizeLimit(5000 * 1024) // Max incoming message size => 5Mo
                .setSendBufferSizeLimit(5000 * 1024) // Max outgoing buffer size => 5Mo
                .setSendTimeLimit(15*1000) //max time allowed when sending
                .setSendBufferSizeLimit(512*1024); //set 0 to disable buffering
    }


    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic/", "/queue/");
        config.setApplicationDestinationPrefixes("/app");
    }
    
    
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new UserInterceptor(),new StompInterceptor());

    }



    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
    	   registry

           .addEndpoint("/one")
           .setAllowedOrigins("*")
           .setHandshakeHandler(new DefaultHandshakeHandler() {

               public boolean beforeHandshake(
                       ServerHttpRequest request,
                       ServerHttpResponse response,
                       WebSocketHandler wsHandler,
                       Map attributes) throws Exception {

                   if (request instanceof ServletServerHttpRequest) {
                       ServletServerHttpRequest servletRequest
                               = (ServletServerHttpRequest) request;
                       HttpSession session = servletRequest
                               .getServletRequest().getSession();
                       attributes.put("sessionId", session.getId());
                   }
                   return true;
               }
           }).addInterceptors(new HttpHandshakeInterceptor())
                   .withSockJS()
                   .setStreamBytesLimit(512 * 1024)
                   .setHttpMessageCacheSize(1000)
                   .setDisconnectDelay(30 * 1000)
           ;
    }


}
