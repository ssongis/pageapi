package com.example.apibasic.validate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Setter@Getter@ToString
public class Address {
    @NotBlank
    private String streat;
    @NotBlank
    private String postCode;
}
