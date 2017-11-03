package demo.service;

import demo.model.Restaurant;

import java.util.List;


public interface RestaurantService {
    List<Restaurant> saveRestaurants(List<Restaurant> restaurants);

    void deleteAll();

    Restaurant findByName(String restname);


}
