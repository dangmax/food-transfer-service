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

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.model.DishOrder;
import demo.service.DishOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@Slf4j
@RequestMapping(value = "/dishorder")
@RestController
public class DishOrderController {

    @Autowired
    private RestTemplate restTemplate;

    private DishOrderService dishOrderService;

    @Autowired
    public DishOrderController(DishOrderService dishOrderService){
        this.dishOrderService = dishOrderService;
    }

    @Value("${com.dang.food.payment}")
    private String foodPayment;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @HystrixCommand(fallbackMethod = "processpayFallback")
    public void saveDishOrder(DishOrder dishorder) {
        DishOrder dishOrder = this.dishOrderService.save(dishorder);

        // if save success ,forward to food-payment-service
        if (dishOrder != null) {
            log.info("save dishorder success,forward to payment!");
            this.restTemplate.postForLocation(foodPayment + "/api/payment", dishorder);
        }
    }

    public void processpayFallback(long id, DishOrder dishorder) {
        log.error("Hystrix Fallback Method. Unable to send order to payment!");
//        LOGGER.error("Hystrix Fallback Method. Unable to send message for distribution.");
    }

}
