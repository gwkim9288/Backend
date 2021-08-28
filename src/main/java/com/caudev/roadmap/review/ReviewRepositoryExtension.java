package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface ReviewRepositoryExtension {
    Page<Review> findWithSearchCond(String keyword, Pageable pageable);

}
