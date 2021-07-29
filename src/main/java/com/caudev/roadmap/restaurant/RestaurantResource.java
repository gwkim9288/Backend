package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.PlaceController;
import com.caudev.roadmap.place.PlaceResponseDto;
import org.springframework.hateoas.EntityModel;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class RestaurantResource extends EntityModel<RestaurantResponseDto> {
    public static EntityModel<RestaurantResponseDto> modelOf(RestaurantResponseDto restaurantResponseDto) {
        EntityModel<RestaurantResponseDto> restaurantResource = EntityModel.of(restaurantResponseDto);
        restaurantResource.add(linkTo(RestaurantController.class).slash("search").withRel("search"));
        return restaurantResource;
    }
}
