package com.caudev.roadmap.place;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

public class PlaceResource extends EntityModel<PlaceDto> {
    public static EntityModel<PlaceDto> modelOf(PlaceDto placeDto) {
        EntityModel<PlaceDto> placeResource = EntityModel.of(placeDto);
        placeResource.add(linkTo(PlaceController.class).slash("search").withRel("search"));
        return placeResource;
    }
}
