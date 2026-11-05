package com.craft;

import lombok.Data;
import lombok.ToString;
import org.springframework.stereotype.Component;

@Data
@ToString
@Component("address")
public class Address {
    private String city = "Addis";
    private String country = "Ethiopia";
}