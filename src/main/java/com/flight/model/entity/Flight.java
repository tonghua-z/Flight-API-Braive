package com.flight.model.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;


@Accessors(chain = true)
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Flight implements Serializable {


    private Long id;
    private Long flight_num;
    private String name;
    private String s_date;
    private String a_date;
    private String departure;
    private String destination;
    private Double fare;
    private Double duration;

}
