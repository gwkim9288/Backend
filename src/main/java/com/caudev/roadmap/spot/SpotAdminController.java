package com.caudev.roadmap.spot;

import com.caudev.roadmap.restaurant.RestaurantDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/admin/spots")
@RequiredArgsConstructor
public class SpotAdminController {

    private final SpotService spotService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity IOex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @GetMapping
    public ResponseEntity getSpotList(){
        List<Spot> spotList = spotService.findAllSpots();
        return ResponseEntity.ok(spotList);
    }

    @PostMapping
    public ResponseEntity createSpot(@RequestPart(value = "body") String jsonStr,
                                     @RequestPart(value = "image") MultipartFile image) throws IOException {
        SpotDto spotDto = new ObjectMapper().readValue(jsonStr, SpotDto.class);
        spotService.createSpot(spotDto,image);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{spot_id}")
    public ResponseEntity deleteSpot (@PathVariable Long spot_id) throws NotFoundException{
        spotService.deleteSpot(spot_id);
        return ResponseEntity.ok().build();
    }
}
