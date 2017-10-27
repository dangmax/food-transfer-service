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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(value = "/restaurant")
public class RestaurantUploadController {

    @Autowired
    private RestaurantService restaurantService;

    @Autowired
    private DishOrderRepository orderRepository;

    @Autowired
    private RestTemplate restTemplate;

//    @Autowired
//    public RestaurantUploadController(RestaurantRepository repository,DishOrderRepository orderRepository) {
//		 this.repository      = repository;
//		 this.orderRepository = orderRepository;
//    }


    @RequestMapping(value = "/bulk/supplyRestaurants", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Restaurant> upload(@RequestBody List<Restaurant> restaurants) {
        return this.restaurantService.saveRestaurants(restaurants);
    }

    @RequestMapping(value = "/purge", method = RequestMethod.DELETE)
    public void purge() {
        this.restaurantService.deleteAll();
    }

    @RequestMapping(value = "/findbyname", method = RequestMethod.GET)
    public String findRestByName(String restname) {
        String goPage;
        Restaurant restaurant = this.restaurantService.findByName(restname);
        if (restaurant == null) {
            log.info("can not find restaurant!");
            goPage = "error";
        } else {
            goPage = "make_order";
        }
        return goPage;
    }

    //@RequestMapping(value = "/running/runningId/{runningId}", method = RequestMethod.GET)
    //public Page<Location> findByRunningId(@PathVariable String runningId, @RequestParam(name = "page") int page, @RequestParam(name = "size") int size) {
    //    return this.locationService.findByRunningId(runningId, new PageRequest(page, size));
    //}

}
