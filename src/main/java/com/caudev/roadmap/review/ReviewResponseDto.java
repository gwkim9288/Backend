package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.RestaurantResponseDto;
import lombok.*;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Data
public class ReviewResponseDto {

    private String title;

    private String content;

    private Long star;

    private String writer;

    private RestaurantResponseDto restaurantResponseDto;

}
