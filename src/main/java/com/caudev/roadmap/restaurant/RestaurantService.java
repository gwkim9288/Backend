package com.caudev.roadmap.restaurant;

import com.caudev.roadmap.Restaurant.Restaurant;
import com.caudev.roadmap.Restaurant.RestaurantDto;
import com.caudev.roadmap.Restaurant.RestaurantRepository;
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
        Page<Restaurant> RestaurantPage = restaurantRepository.findAll(pageable);
        return RestaurantPage;
    }

    public RestaurantDto createRestaurant(RestaurantDto RestaurantDto){
        Restaurant Restaurant = new Restaurant();
        modelMapper.map(RestaurantDto,Restaurant);
        restaurantRepository.save(Restaurant);
        return RestaurantDto;
    }

    public Restaurant findRestaurantById(Long Restaurant_id) throws NotFoundException {
        Optional<Restaurant> Restaurant = restaurantRepository.findById(Restaurant_id);
        if(Restaurant.isEmpty())
            throw new NotFoundException("not found");
        return Restaurant.get();
    }

    public Page<Restaurant> findRestaurantByName(String Restaurant_name,Pageable pageable) {
        Page<Restaurant> restaurants = restaurantRepository.findByName(Restaurant_name,pageable);
        return Restaurants;
    }

    public void deleteRestaurant(Long Restaurant_id) throws NotFoundException{
        Optional<Restaurant> find = restaurantRepository.findById(Restaurant_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        restaurantRepository.deleteById(Restaurant_id);
    }

    public RestaurantDto modifyRestaurant(Long Restaurant_id, RestaurantDto RestaurantDto) throws  NotFoundException {
        Optional<Restaurant> find = RestaurantRepository.findById(Restaurant_id);
        if(find.isEmpty())
            throw new NotFoundException("not found");
        RestaurantRepository.deleteById(Restaurant_id);
        Restaurant Restaurant = new Restaurant();
        modelMapper.map(RestaurantDto,Restaurant);
        RestaurantRepository.save(Restaurant);
        return RestaurantDto;
    }
}
