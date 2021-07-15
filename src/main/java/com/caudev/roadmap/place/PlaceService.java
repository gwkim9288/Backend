package com.caudev.roadmap.place;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final ModelMapper modelMapper;

    public Page<Place> findAllPlaces(Pageable pageable){
        Page<Place> placePage = placeRepository.findAll(pageable);
        return placePage;
    }

    public PlaceDto createPlace(){
        return null;
    }


}
