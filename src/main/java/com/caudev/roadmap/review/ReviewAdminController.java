package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.Restaurant;
import com.caudev.roadmap.restaurant.RestaurantResource;
import com.caudev.roadmap.restaurant.RestaurantResponseDto;
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
@RequestMapping(path = "/admin/restaurants")
@RequiredArgsConstructor
public class ReviewAdminController {

    private final ReviewService reviewService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @GetMapping
    public ResponseEntity findAllReview(@PageableDefault(size = 10) Pageable pageable,
                                        PagedResourcesAssembler<Review> assembler){
        Page<Review> reviews = reviewService.findAllReviews(pageable);
        PagedModel<EntityModel<ReviewResponseDto>> model =
                assembler.toModel(reviews, p -> ReviewResource.modelOf(reviewService.createReviewResponse(p)));
        return ResponseEntity.ok(model);
    }

    @GetMapping("/search")
    public ResponseEntity findByName(@RequestParam("keyword") String keyword,
                                     @PageableDefault(size = 10) Pageable pageable,
                                     PagedResourcesAssembler<Review> assembler){
        //이전 버전
//        return restaurantService.findrestaurantByName(restaurant_name,pageable);

        // search 적용
        //검색의 경우는 잘못된 값을 집어넣으면 그냥 그거에 따라 결과가안나옴
        //굳이 에러처리 필요 x
        //sort는 직접 넣어보기 sort=~ & dir=desc
        Page<Review> review = reviewService.searchByContent(keyword, pageable);
        PagedModel<EntityModel<ReviewResponseDto>> model =
                assembler.toModel(review, p -> ReviewResource.modelOf(reviewService.createReviewResponse(p)));
//        PagedModel<EntityModel<restaurantResponseDto>> result = restaurantService.addLinksWithSearch(model);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/{restaurant_id}")
    public ResponseEntity findReviewByRestaurant(@PathVariable Long restaurant_id,@PageableDefault(size = 10) Pageable pageable,
                                           PagedResourcesAssembler<Review> assembler) throws NotFoundException {
        Page<Review> reviews = reviewService.findReviewByRestaurant(restaurant_id);
        PagedModel<EntityModel<ReviewResponseDto>> model =
                assembler.toModel(reviews, p -> ReviewResource.modelOf(reviewService.createReviewResponse(p)));
        return ResponseEntity.ok(model);
    }

    @DeleteMapping("/{review_id}")
    public ResponseEntity deleteReview (@PathVariable Long review_id) throws NotFoundException {
        reviewService.deleteReview(review_id);
        return ResponseEntity.ok().build();
    }
}
