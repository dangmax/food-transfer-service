package demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.domain.Payment;
import demo.domain.PaymentRepository;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.amqp.channel.PublishSubscribeAmqpChannel;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.channel.PublishSubscribeChannel;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.Random;


@RestController
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@EnableBinding(Source.class)
public class FoodPaymentController {


//    @Qualifier("directChannel")
//    @Autowired
//    @Qualifier("directChannel")
    private MessageChannel messageChannel = new DirectChannel();

    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private String orderId;

    private Payment payment;

    @RequestMapping(value = "/payment", method = RequestMethod.GET)
    public void gotopay(String payInfo) throws IOException {
//    public Payment gotopay(@RequestBody String payInfo) throws IOException {

        // save payment
//        this.payment = objectMapper.readValue(payInfo,Payment.class);
        this.payment = new Payment();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        this.payment.setPayTime(sdf.format(new Date()));
        // random generate delivery time
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int randome_min = new Random().nextInt(30);
        calendar.set(Calendar.MINUTE,calendar.get(Calendar.MINUTE)+randome_min);
        this.payment.setDeliveryTime(sdf.format(calendar.getTime()));
        Payment out = paymentRepository.save(this.payment);

      this.messageChannel.send(MessageBuilder.withPayload(payment.toString()).build());

//        return out;
    }

//    @Bean
//    public DirectChannel directChannel(){
//        return new DirectChannel();
//    }

}
