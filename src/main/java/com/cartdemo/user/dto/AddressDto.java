package com.cartdemo.user.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper=true)
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class AddressDto extends BaseDto implements Serializable {
    private static final long serialVersionUID = -4988877493778646081L;
    private String type;
    private String country;
    private String province;
    private String city;
    private String cap;
    private String street;
    private String houseNumber;

    @Override
    public String toString() {
        return "AddressDto{" +
                "type=" + type +
                ", country='" + country + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", cap='" + cap + '\'' +
                ", street='" + street + '\'' +
                ", houseNumber='" + houseNumber + '\'' +
                ", id=" + id +
                '}';
    }
}
