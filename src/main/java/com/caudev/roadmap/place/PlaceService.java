package com.caudev.roadmap.place;

import com.caudev.roadmap.spot.Spot;
import com.caudev.roadmap.spot.SpotRepository;
import com.caudev.roadmap.spot.SpotResponseDto;
import com.caudev.roadmap.spot.SpotService;

import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.swing.text.html.Option;
import javax.transaction.Transactional;
import java.io.*;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@RequiredArgsConstructor
@Service
public class PlaceService {

    private final PlaceRepository placeRepository;
    private final SpotRepository spotRepository;
    private final ModelMapper modelMapper;
    private final SpotService spotService;

    public Page<Place> findAllPlaces(Pageable pageable){
        Page<Place> placePage = placeRepository.findAll(pageable);
        return placePage;
    }

    public Place createPlace(PlaceDto placeDto, MultipartFile image) throws NotFoundException, IOException {

        Place place = modelMapper.map(placeDto,Place.class);

        Optional<Spot> spotOpt = spotRepository.findById(placeDto.getSpotId());

        if(spotOpt.isEmpty())
            throw new NotFoundException("not found : spot");

        if(!image.isEmpty()){
            String imageName = image.getOriginalFilename();
            File file = new File("/Users/guenwoo-kim/tempImage/"+imageName);
            image.transferTo(file);
            place.setImage(imageName);
        } else {
            place.setImage(spotOpt.get().getImage());
        }

        place.setSpot(spotOpt.get());

        return placeRepository.save(place);


    }

    public Place findPlaceById(Long place_id) throws NotFoundException {
        Optional<Place> place = placeRepository.findById(place_id);
        if(place.isEmpty())
            throw new NotFoundException("not found : place");
        return place.get();
    }

    //현재 사용안함
//    public Page<Place> findPlaceByName(String place_name,Pageable pageable) {
//        Page<Place> places = placeRepository.findByName(place_name,pageable);
//        return places;
//    }

    public void deletePlace(Long place_id) throws NotFoundException{
        Optional<Place> find = placeRepository.findById(place_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        placeRepository.deleteById(place_id);
    }

    public Place modifyPlace(Long place_id, PlaceDto placeDto) throws  NotFoundException {
        Optional<Place> findPlace = placeRepository.findById(place_id);
        Optional<Spot> findSpot = spotRepository.findById(placeDto.getSpotId());
        if(findPlace.isEmpty())
            throw new NotFoundException("not found : place");
        if(findSpot.isEmpty())
            throw new NotFoundException("not found : spot");
        placeRepository.deleteById(place_id);
        Place place = new Place();
        modelMapper.map(placeDto,place);
        place.setSpot(findSpot.get());
        return placeRepository.save(place);
        //delete 사용 안 하고 mapper 만 써서 id 값 유지 가능할까

    }

    public PlaceResponseDto createPlaceResponse(Place place){
        PlaceResponseDto placeResponseDto = modelMapper.map(place,PlaceResponseDto.class);
        SpotResponseDto spotResponseDto = spotService.createSpotResponseDto(place.getSpot());
//        InputStream imageStream = null;
//        try {
//            imageStream = new FileInputStream("/Users/guenwoo-kim/tempImage/"+place.getImage());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        InputStreamReader inputStreamReader = new InputStreamReader(imageStream);
//
//        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
//        String streamToString = streamOfString.collect(Collectors.joining());
//        placeResponseDto.setImage(streamToString);

        placeResponseDto.setSpotResponseDto(spotResponseDto);

        return placeResponseDto;
    }

    public Page<Place> search(String keyword,Pageable pageable) {
        Page<Place> place = placeRepository.findWithSearchCond(keyword,pageable);
        return place;
    }
}
