package com.caudev.roadmap.review;

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
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity notFoundex(NotFoundException e){
        return ResponseEntity.notFound().eTag(e.getMessage()).build();
    }

    @PostMapping("restaurant/{restaurant_id}")
    public ResponseEntity createReview(@RequestBody ReviewDto reviewDto, @PathVariable Long restaurant_id) throws NotFoundException{
        Review review = reviewService.createReview(reviewDto, restaurant_id);
        ReviewResponseDto responseDto = reviewService.createReviewResponse(review);
        EntityModel<ReviewResponseDto> model = ReviewResource.modelOf(responseDto);
        return ResponseEntity.ok(model);
    }

    @GetMapping("/restaurant/{restaurant_id}")
    public ResponseEntity findReviewsByRestaurant(@PathVariable Long restaurant_id , @PageableDefault(size = 10) Pageable pageable,
                                                  PagedResourcesAssembler<Review> assembler) throws  NotFoundException{
        Page<Review> reviews = reviewService.findReviewByRestaurant(restaurant_id,pageable);
        PagedModel<EntityModel<ReviewResponseDto>> model =
                assembler.toModel(reviews, p -> ReviewResource.modelOf(reviewService.createReviewResponse(p)));
        return ResponseEntity.ok(model);
    }


}
