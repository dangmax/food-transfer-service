package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
@Data
@NoArgsConstructor
public class Restaurant {


    @Id
    private long id;

    private String restname;
    private String address;
    private String phone;
    private List<MenuItem>  menuItems;

    @JsonCreator
    public Restaurant(@JsonProperty("id") long id, @JsonProperty("restname") String restname, @JsonProperty("address") String address, @JsonProperty("phone") String phone
    ,@JsonProperty("menuItems") List<MenuItem> menuItems) {
        this.id =id;
        this.restname = restname;
        this.address = address;
        this.phone = phone;
        this.menuItems = menuItems;
    }


}
