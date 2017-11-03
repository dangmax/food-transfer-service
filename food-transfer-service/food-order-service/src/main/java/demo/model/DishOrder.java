package demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class DishOrder {


    private long id;
    private String note;
    private double totalPrice;
    private String deliveryAddress;
    private String ordertime;

    private List<MenuItem> selectItems;

}
