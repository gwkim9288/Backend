package com.caudev.roadmap.review;

import com.caudev.roadmap.restaurant.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Long>,ReviewRepositoryExtension {
    Page<Review> findAllByRestaurant(Restaurant restaurant, Pageable pageable);
}
