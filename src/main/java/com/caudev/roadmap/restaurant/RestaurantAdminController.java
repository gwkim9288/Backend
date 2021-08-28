package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.Place;
import com.caudev.roadmap.restaurant.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping(path = "/admin/restaurants")
@RequiredArgsConstructor
public class RestaurantAdminController {

    private final RestaurantService restaurantService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @ExceptionHandler(IOException.class)
    public ResponseEntity IOex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @GetMapping
    public ResponseEntity findAllRestaurants(@PageableDefault(size = 10) Pageable pageable, PagedResourcesAssembler<Restaurant> assembler){
        Page<Restaurant> restaurants = restaurantService.findAllRestaurants(pageable);
        PagedModel<EntityModel<RestaurantResponseDto>> model =
                assembler.toModel(restaurants, p -> RestaurantResource.modelOf(restaurantService.createRestaurantResponse(p)));
        return ResponseEntity.ok(model);
    }

//    @GetMapping("/{restaurant_name}")
//    public Page<Restaurant> findByName(Pageable pageable, @PathVariable String restaurant_name){
//        return restaurantService.findRestaurantByName(restaurant_name,pageable);
//    }

    @GetMapping("/search")
    public ResponseEntity findByName(@RequestParam("keyword") String keyword,
                                     @PageableDefault(size = 10) Pageable pageable,
                                     PagedResourcesAssembler<Restaurant> assembler){
        //이전 버전
//        return restaurantService.findrestaurantByName(restaurant_name,pageable);

        // search 적용
        //검색의 경우는 잘못된 값을 집어넣으면 그냥 그거에 따라 결과가안나옴
        //굳이 에러처리 필요 x
        //sort는 직접 넣어보기 sort=~ & dir=desc
        Page<Restaurant> restaurant = restaurantService.search(keyword, pageable);
        PagedModel<EntityModel<RestaurantResponseDto>> model =
                assembler.toModel(restaurant, p -> RestaurantResource.modelOf(restaurantService.createRestaurantResponse(p)));
//        PagedModel<EntityModel<restaurantResponseDto>> result = restaurantService.addLinksWithSearch(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{restaurant_id}")
    public ResponseEntity findById(@PathVariable Long restaurant_id) throws NotFoundException{
        Restaurant restaurant = restaurantService.findRestaurantById(restaurant_id);
        EntityModel<RestaurantResponseDto> model = RestaurantResource.modelOf(restaurantService.createRestaurantResponse(restaurant));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{restaurant_id}")
    public ResponseEntity deleteRestaurant (@PathVariable Long restaurant_id) throws NotFoundException {
        restaurantService.deleteRestaurant(restaurant_id);
        return ResponseEntity.ok().build();
    }

    @PostMapping
    public ResponseEntity createRestaurant (@RequestPart(value = "body") String jsonStr,
                                            @RequestPart(value = "image") MultipartFile image) throws IOException {

        RestaurantDto restaurantDto = new ObjectMapper().readValue(jsonStr, RestaurantDto.class);
        Restaurant restaurant = restaurantService.createRestaurant(restaurantDto,image);
        EntityModel<RestaurantResponseDto> model = RestaurantResource.modelOf(restaurantService.createRestaurantResponse(restaurant));
        return ResponseEntity.ok(model);

    }

    @PutMapping("/{restaurant_id}")
    public ResponseEntity modifyRestaurant (@PathVariable Long restaurant_id,
                                            @RequestBody RestaurantDto restaurantDto) throws NotFoundException {
        Restaurant restaurant = restaurantService.modifyRestaurant(restaurant_id,restaurantDto);
        RestaurantResponseDto restaurantResponseDto = restaurantService.createRestaurantResponse(restaurant);
        EntityModel<RestaurantResponseDto> restaurantResponse = RestaurantResource.modelOf(restaurantResponseDto);
        return ResponseEntity.ok(restaurantResponse);
    }
}
