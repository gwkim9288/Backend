package com.caudev.roadmap.place;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/places")
public class PlaceController {

    private final PlaceService placeService;


}
