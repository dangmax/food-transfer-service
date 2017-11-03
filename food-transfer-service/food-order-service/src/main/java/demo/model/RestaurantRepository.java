package demo.model;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;


//public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, Long> {
//
//    Page<Restaurant>  findFirstByRestname(@Param("restname") String restname, Pageable pageable);
//
//    Restaurant findByRestname(@Param("restname") String restname);
//
//
//}

public interface RestaurantRepository extends MongoRepository<Restaurant, Long> {

//    Page<Restaurant>  findFirstByRestname(@Param("restname") String restname, Pageable pageable);

    Restaurant findByRestname(@Param("restname") String restname);

    @Override
    void deleteAll();


}