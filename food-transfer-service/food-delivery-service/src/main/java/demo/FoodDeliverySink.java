package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.io.IOException;

@EnableBinding(Sink.class)
@Slf4j
//@MessageEndpoint
public class FoodDeliverySink {

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void delivery(String payInfo) throws IOException {
        log.info("delivery pay_order: " + payInfo);
    }
}
