package demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Payment {

    @Id
    @GeneratedValue
    private long payId;
    private String payTime;
    private String deliveryTime;
    private long orderId;
    private double totalPrice;
    private String deliveryAddress;


    @Override
    public String toString() {
        return "Payment{" +
                "payId=" + payId +
                ", payTime=" + payTime +
                ", deliveryTime='" + deliveryTime + '\'' +
                ", orderId=" + orderId +
                ", totalPrice=" + totalPrice +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                '}';
    }
}
