package com.caudev.roadmap.restaurant;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestaurantResponseDto {

    private Long id;

    private String name;

    private double location_x;

    private double location_y;

    private String detail;

    private Long phoneNum;

    private Long viewNum;

    private String image;
}
