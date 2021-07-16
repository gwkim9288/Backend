package com.caudev.roadmap.place;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.aspectj.weaver.ast.Not;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/admin/places")
@RequiredArgsConstructor
public class PlaceAdminController {

    private final PlaceService placeService;

    @GetMapping
    public Page<Place> findAllPlaces(Pageable pageable){
        return placeService.findAllPlaces(pageable);
    }

    @GetMapping("/{place_name}")
    public Page<Place> findByName(Pageable pageable, @PathVariable String place_name){
        return placeService.findPlaceByName(place_name,pageable);
    }

    @GetMapping("/{place_id}")
    public ResponseEntity findById(@PathVariable Long place_id){
        Place place;
        try{
            place  = placeService.findPlaceById(place_id);
        } catch (NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(place);
    }

    @DeleteMapping("/{place_id}")
    public ResponseEntity deletePlace (@PathVariable Long place_id) {
        try{
            placeService.deletePlace(place_id);
        } catch(NotFoundException e) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createPlace (@RequestBody PlaceDto placeDto){
        placeService.createPlace(placeDto);
    }

    @PutMapping("/{place_id}")
    public ResponseEntity modifyPlace (@PathVariable Long place_id, @RequestBody PlaceDto placeDto){
        Place place;
        try{
            place = placeService.modifyPlace(place_id,placeDto);
        } catch (NotFoundException e){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(place);
    }
}
