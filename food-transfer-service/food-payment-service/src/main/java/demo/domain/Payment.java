package demo.domain;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Payment {

    @Id
    @GeneratedValue
    private long pay_id;

    private Date pay_time;
    private String delivery_time;
    private long dish_order_id;

}
