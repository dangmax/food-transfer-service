package demo.service.impl;

import demo.model.DishOrder;
import demo.model.DishOrderRepository;
import demo.service.DishOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DishOrderServiceImpl implements DishOrderService {

    @Autowired
    private DishOrderRepository dishOrderRepository;

    @Override
    public DishOrder save(DishOrder dishOrder) {
        return dishOrderRepository.save(dishOrder);
    }

}
