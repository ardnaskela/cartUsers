package com.cartdemo.user;

import com.cartdemo.user.dto.AddressDto;
import com.cartdemo.user.dto.UserDto;
import com.cartdemo.user.entity.AddressEntity;
import com.cartdemo.user.entity.UserEntity;
import com.cartdemo.user.enumeration.AddressType;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class TestData {

    public static final List<UserEntity> USER_ENTITY_LST = Arrays.asList(
            new UserEntity("lnglns34t70b525n@mailstop.it", "pass1", "Luminosa", "Languasco", LocalDate.parse("1954-03-01"), "3701865836", true),
            new UserEntity("cntvia67s56c681v@mailstop.it", "pass2", "Iva", "Centanaro", LocalDate.parse("1943-07-06"), "39050944556", true),
            new UserEntity("tsdgnz76e10e213l@mailstop.it", "pass3", "Gaudenzio", "Tuseddu", LocalDate.parse("1980-11-23"), "3843657143", false)
        );

    public static List<UserEntity> getUserEntityTestListWithId() {
        List<UserEntity> lst = USER_ENTITY_LST;
        for (int i = 0; i < lst.size(); i++)
            lst.get(i).setId((long)i);
        return lst;
    }

    public static final List<UserDto> USER_DTO_LST = Arrays.asList(
            new UserDto("firstDto@mail.com", "pass1", "FirstNameFirstDto", "LastNameFirstDto", "1952-03-01", "3701865836", true),
            new UserDto("secondDto@mail.com", "pass2", "FirstNameSecondDto", "LastNameSecondDto", "1941-07-06", "050944556", false),
            new UserDto("thirdDto@mail.com", "pass3", "FirstNameThirdDto", "LastNameThirdDto", "1989-11-23", "3661111111", false)
    );

    public static List<UserDto> getUserDtoTestListWithId() {
        List<UserDto> lst = USER_DTO_LST;
        for (int i = 0; i < lst.size(); i++)
            lst.get(i).setId((long) i);
        return lst;
    }

    public static final List<AddressDto> ADDRESS_DTO_LST = Arrays.asList(
            new AddressDto("BILLING", "IT", "AL", "MONTECASTELLO", "10043", "Via Rosselli", "7"),
            new AddressDto("DELIVERY", "IT", "SA", "SANT'ANGELO A FASANELLA", "38019", "Via Moscovia", "19"),
            new AddressDto("BILLING", "IT", "UD", "PASIAN DI PRATO", "33037", "Via Orientale", "47"),
            new AddressDto("DELIVERY", "IT", "VI", "SAN GERMANO DEI BERICI", "10144", "Via Don Bosco", "77")
    );

    public static final List<AddressEntity> ADDRESS_ENTITY_LST = Arrays.asList(
            new AddressEntity(new UserEntity(), AddressType.BILLING, "IT", "AL", "MONTECASTELLO", "10043", "Via Rosselli", "7"),
            new AddressEntity(new UserEntity(), AddressType.DELIVERY, "IT", "SA", "SANT'ANGELO A FASANELLA", "38019", "Via Moscovia", "19"),
            new AddressEntity(new UserEntity(), AddressType.BILLING, "IT", "UD", "PASIAN DI PRATO", "33037", "Via Orientale", "47"),
            new AddressEntity(new UserEntity(), AddressType.DELIVERY, "IT", "VI", "SAN GERMANO DEI BERICI", "10144", "Via Don Bosco", "77")
    );

    public static List<AddressDto> getAddressDtoTestListWithId() {
        List<AddressDto> lst = ADDRESS_DTO_LST;
        for (int i = 0; i < lst.size(); i++)
            lst.get(i).setId((long) i);
        return lst;
    }

    public static List<AddressEntity> getAddressEntityTestListWithId() {
        List<AddressEntity> lst = ADDRESS_ENTITY_LST;
        for (int i = 0; i < lst.size(); i++)
            lst.get(i).setId((long) i);
        return lst;
    }
    
}
