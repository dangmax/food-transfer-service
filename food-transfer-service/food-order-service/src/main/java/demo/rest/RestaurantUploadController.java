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

import demo.model.DishOrderRepository;
import demo.model.Restaurant;
import demo.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/restaurant")
public class RestaurantUploadController {


    private RestaurantService restaurantService;

    private DishOrderRepository orderRepository;

    public RestaurantUploadController() {
    }

    @Autowired
    public RestaurantUploadController(DishOrderRepository orderRepository,RestaurantService restaurantService) {
		 this.orderRepository = orderRepository;
		 this.restaurantService = restaurantService;
    }

    //batch upload restaurant
    @RequestMapping(value = "/bulk/supplyRestaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void upload(@RequestBody List<Restaurant> restaurants) {
        this.restaurantService.saveRestaurants(restaurants);
    }

    //purge
    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.restaurantService.deleteAll();
    }

    //find restaurant by name
    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public String findRestByName(@RequestParam String restname,Model model) {
        log.info("begin findbyname");
        String goPage;
        Restaurant restaurant = this.restaurantService.findByName(restname);
        if (restaurant == null) {
            log.info("can not find restaurant!");
            model.addAttribute("errorMsg","can not find restaurant!");
            goPage = "/error";
        } else {
            goPage = "make_order";
        }
        return goPage;
    }

    // for unit test use
    public List<Restaurant> bulkUpload(List<Restaurant> restaurants) {
        return this.restaurantService.saveRestaurants(restaurants);
    }

}
