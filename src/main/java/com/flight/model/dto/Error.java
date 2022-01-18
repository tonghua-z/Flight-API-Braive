package com.flight.model.dto;

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
public class Error implements Serializable {

    private int code; // Error code
    private String message; // Error message

}
