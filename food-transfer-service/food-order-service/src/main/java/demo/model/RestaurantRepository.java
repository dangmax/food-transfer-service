package demo.model;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;


public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Restaurant findFirstByRestname(@Param("restname") String restname);
}
