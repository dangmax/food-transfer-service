package demo.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Payment {

    private long payId;
    private String payTime;
    private String deliveryTime;
    private long orderId;
    private double totalPrice;
    private String deliveryAddress;

}
