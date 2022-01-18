package com.flight.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;


@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class User implements Serializable {


    private Long id;
    private String username;
    private String password;

}
