package com.caudev.roadmap.spot;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface SpotRepository extends JpaRepository<Spot,Long> {

}
