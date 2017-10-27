package demo.model;

import org.springframework.data.repository.PagingAndSortingRepository;


public interface DishOrderRepository extends PagingAndSortingRepository<DishOrder, Long> {

}
