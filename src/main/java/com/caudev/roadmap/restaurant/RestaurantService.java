package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.Place;
import com.caudev.roadmap.place.PlaceResponseDto;
import com.caudev.roadmap.spot.SpotResponseDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.*;
import java.nio.channels.MulticastChannel;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Transactional
@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final ModelMapper modelMapper;

    public Page<Restaurant> findAllRestaurants(Pageable pageable){
        Page<Restaurant> restaurantPage = restaurantRepository.findAll(pageable);
        return restaurantPage;
    }

    public Restaurant createRestaurant(RestaurantDto restaurantDto, MultipartFile image) throws IOException {
        Restaurant restaurant;

        restaurant = modelMapper.map(restaurantDto,Restaurant.class);
        if(!image.isEmpty()){
            String imageName = image.getOriginalFilename();
            File file = new File("/Users/guenwoo-kim/tempImage/"+imageName);
            image.transferTo(file);
            restaurant.setImage(imageName);
        }
        restaurant.setViewNum(0L);
        restaurant = restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Restaurant findRestaurantById(Long restaurant_id) throws NotFoundException {
        Optional<Restaurant> restaurant = restaurantRepository.findById(restaurant_id);
        if(restaurant.isEmpty())
            throw new NotFoundException("not found");
        return restaurant.get();
    }

//    public Page<Restaurant> findRestaurantByName(String restaurant_name,Pageable pageable) {
//        Page<Restaurant> restaurants = restaurantRepository.findByName(restaurant_name,pageable);
//        return restaurants;
//    }

    public Long deleteRestaurant(Long restaurant_id) throws NotFoundException{
        Optional<Restaurant> find = restaurantRepository.findById(restaurant_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        Long viewNum = find.get().getViewNum();
        restaurantRepository.deleteById(restaurant_id);
        return viewNum;
    }

    public Restaurant modifyRestaurant(Long restaurant_id, RestaurantDto restaurantDto) throws  NotFoundException {
        Long viewNum = deleteRestaurant(restaurant_id);
        Restaurant restaurant = modelMapper.map(restaurantDto,Restaurant.class);
        restaurant.setViewNum(viewNum);
        restaurant = restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Page<Restaurant> search(String keyword, Pageable pageable) {
        Page<Restaurant> restaurant = restaurantRepository.findWithSearchCond(keyword,pageable);
        return restaurant;
    }

    public RestaurantResponseDto createRestaurantResponse(Restaurant restaurant) {
        RestaurantResponseDto restaurantResponseDto = modelMapper.map(restaurant,RestaurantResponseDto.class);
//        InputStream imageStream = null;
//        try {
//            imageStream = new FileInputStream("/Users/guenwoo-kim/tempImage/"+restaurant.getImage());
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        InputStreamReader inputStreamReader = new InputStreamReader(imageStream);
//
//        Stream<String> streamOfString= new BufferedReader(inputStreamReader).lines();
//        String streamToString = streamOfString.collect(Collectors.joining());
//        restaurantResponseDto.setImage(streamToString);

        return restaurantResponseDto;
    }

    public Restaurant updateRestaurantViewNum (Restaurant restaurant){
        Long viewNum = restaurant.getViewNum();
        viewNum++;
        restaurant.setViewNum(viewNum);
        return restaurantRepository.save(restaurant);
    }


}
