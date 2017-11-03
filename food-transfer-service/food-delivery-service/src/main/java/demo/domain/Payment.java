package demo.domain;

import lombok.Data;

import java.util.Date;

@Data
public class Payment {

    private long payId;
    private Date payTime;
    private String deliveryTime;
    private long orderId;
    private double totalPrice;
    private String deliveryAddress;

}
