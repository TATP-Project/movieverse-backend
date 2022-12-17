package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Food;
import com.JAPKAM.Movieverse.repository.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FoodService {
    private FoodRepository foodRepository;
    public List<Food> findAll() {
        return foodRepository.findAll();
    }
}
