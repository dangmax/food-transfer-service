package demo.model;


import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;


@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class MenuItem {

    @NotNull
    private String name;
    @NotNull
    private double price;
    @NotNull
    private int quantity = 1;

}
