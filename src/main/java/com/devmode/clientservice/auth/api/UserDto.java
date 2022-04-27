package com.devmode.clientservice.auth.api;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class UserDto {

    private Integer id;

    private String name;
}
