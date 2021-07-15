package com.caudev.roadmap.place;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/admin/place")
@RequiredArgsConstructor
public class PlaceAdminController {

    private final PlaceService placeService;
}
