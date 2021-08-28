package com.caudev.roadmap.restaurant;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantDto {

    private String name;

    private double location_x;

    private double location_y;

    private String detail;

    private Long phoneNum;

    private Long viewNum;
}
