/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package demo.rest;

//import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.DishOrder;
import demo.model.MenuItem;
import demo.model.Payment;
import demo.service.DishOrderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.support.BindingAwareModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

//import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Data
@RequestMapping(value = "/dishorder")
@Controller
public class DishOrderController {

//    @Autowired
    private RestTemplate restTemplate;

    private DishOrderService dishOrderService;
	
	private Payment payment;

    private Map<String,String> map = new HashMap<String,String>();

    @Autowired
    public DishOrderController(DishOrderService dishOrderService){
        this.dishOrderService = dishOrderService;
    }

    @Value("${com.dang.food.payment}")
    private String foodPayment;

    //save dishorder
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveDishOrder(@ModelAttribute("dishorder") DishOrder dishorder) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
        dishorder.setOrdertime(sdf.format(new Date()));
        double totalprice=0;
        for(MenuItem item: dishorder.getSelectItems()){
            totalprice+=item.getPrice()*item.getQuantity();
        }
        dishorder.setTotalPrice(totalprice);
        dishorder.setId(new Random().nextInt(100));
        DishOrder result = this.dishOrderService.save(dishorder);
        log.info("save dishorder success!");
		payment = new Payment();
		payment.setOrderId(result.getId());
		payment.setTotalPrice(result.getTotalPrice());
		payment.setDeliveryAddress(result.getDeliveryAddress());
        // go to pay page
		return "pay_order";
    }

    //validate info and foward to pay service
//    @HystrixCommand(fallbackMethod = "processpayFallback")
	@RequestMapping(value = "/realpay", method = RequestMethod.GET)
    public ModelAndView pay(String credit_card, String expiration_date, String security_code) {
        //validate credit_card valid
//        if(StringUtils.isEmpty(credit_card)
//                ||StringUtils.isEmpty(expiration_date)
//                ||StringUtils.isEmpty(security_code)){
//            map.put("errorMsg","input pay info error!");
//        }

        log.info("forward to payment service!");
//        String foodPayment ="http://food-payment-service";
        payment = new Payment();
        payment.setOrderId(11);
        payment.setDeliveryAddress("beijing road");
        payment.setTotalPrice(26);

        restTemplate = new RestTemplate();
        Payment out = this.restTemplate.postForObject(foodPayment + "/payment", payment,Payment.class);

        return new ModelAndView("pay_result","payinfo",out);
    }

    public ModelAndView processpayFallback(String credit_card, String expiration_date, String security_code) {
        log.error("Hystrix Fallback Method. Unable to send order to payment!");
//      LOGGER.error("Hystrix Fallback Method. Unable to send message for distribution.");
        return new ModelAndView("error",map);
    }

//    @Bean
//    public RestTemplate restTemplate(){
//        return new RestTemplate();
//    }

}
