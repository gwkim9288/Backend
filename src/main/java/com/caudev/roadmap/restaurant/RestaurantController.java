package com.caudev.roadmap.restaurant;


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

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/restaurants")
public class RestaurantController {

    private final RestaurantService restaurantService;

    @GetMapping("/search")
    public ResponseEntity findByName (@RequestParam("keyword") String keyword,
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
    public ResponseEntity findById(@PathVariable Long restaurant_id) throws NotFoundException {
        Restaurant restaurant = restaurantService.findRestaurantById(restaurant_id);
        restaurantService.updateRestaurantViewNum(restaurant);
        EntityModel<RestaurantResponseDto> model = RestaurantResource.modelOf(restaurantService.createRestaurantResponse(restaurant));
        return ResponseEntity.ok(model);
    }
}
