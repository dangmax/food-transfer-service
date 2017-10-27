package demo.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;


@JsonInclude(JsonInclude.Include.NON_NULL)
@Document
@Data
@RequiredArgsConstructor(onConstructor = @__(@PersistenceConstructor))
public class Restaurant {


    @Id
    private long id;

    private String restname;
    private String address;
    private String phone;

    @JsonCreator
    public Restaurant(@JsonProperty("restname") String restname, @JsonProperty("address") String address, @JsonProperty("phone") String phone) {
        this.restname = restname;
        this.address = address;
        this.phone = phone;
    }
}
