package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.place.Place;
import com.caudev.roadmap.place.PlaceResponseDto;
import com.caudev.roadmap.spot.SpotResponseDto;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

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

    public Restaurant createRestaurant(RestaurantDto restaurantDto){
        Restaurant restaurant = new Restaurant();
        restaurant = modelMapper.map(restaurantDto,Restaurant.class);
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

    public void deleteRestaurant(Long restaurant_id) throws NotFoundException{
        Optional<Restaurant> find = restaurantRepository.findById(restaurant_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        restaurantRepository.deleteById(restaurant_id);
    }

    public Restaurant modifyRestaurant(Long restaurant_id, RestaurantDto restaurantDto) throws  NotFoundException {
        Optional<Restaurant> find = restaurantRepository.findById(restaurant_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        restaurantRepository.deleteById(restaurant_id);
        Restaurant restaurant = new Restaurant();
        modelMapper.map(restaurantDto,restaurant);
        restaurant = restaurantRepository.save(restaurant);
        return restaurant;
    }

    public Page<Restaurant> search(String keyword, Pageable pageable) {
        Page<Restaurant> restaurant = restaurantRepository.findWithSearchCond(keyword,pageable);
        return restaurant;
    }

    public RestaurantResponseDto createRestaurantResponse(Restaurant restaurant){
        RestaurantResponseDto restaurantResponseDto = modelMapper.map(restaurant,RestaurantResponseDto.class);

        return restaurantResponseDto;
    }

}
