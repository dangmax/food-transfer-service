package demo.service.impl;

import demo.model.Restaurant;
import demo.model.RestaurantRepository;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantServiceImpl implements RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public List<Restaurant> saveRestaurants(List<Restaurant> restaurants) {
        return this.restaurantRepository.save(restaurants);

    }

    @Override
    public void deleteAll() {
        restaurantRepository.deleteAll();
    }

    @Override
    public Restaurant findByName(String restname) {

        return restaurantRepository.findByRestname(restname);
    }
}
