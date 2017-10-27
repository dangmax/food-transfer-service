package demo.rest;

import demo.domain.Payment;
import demo.domain.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;


@Controller
@EnableBinding(Source.class)
public class FoodPaymentController {

    @Autowired
    private MessageChannel messageChannel;

    @Autowired
    private PaymentRepository paymentRepository;

    private String orderId;

    @RequestMapping(value = "/api/payment", method = RequestMethod.GET)
    public String gotopay(@RequestBody String orderInfo) {
        // get orderId
        this.orderId = orderInfo.substring(0, 8);
        // go to payment view
        return "pay_order";
    }

    //validate payment
    @RequestMapping(value = "/api/realpay", method = RequestMethod.POST)
    public String realpay(Map map) {
        String response_page;
        // validate credit card number, expiration date, and security code

        // generate payment object and save
        Payment payment = new Payment();
        Payment result = paymentRepository.save(payment);
        // if save success then publish message to MQ
        if (result != null) {
            this.messageChannel.send(MessageBuilder.withPayload(payment.toString()).build());

            //save pay message in mongodb

            response_page = "pay_success";
        } else {
            response_page = "error";
        }
        return response_page;
    }
}
