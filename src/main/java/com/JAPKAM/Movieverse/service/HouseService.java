package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.House;
import com.JAPKAM.Movieverse.exception.HouseNotFoundException;
import com.JAPKAM.Movieverse.repository.HouseMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    HouseMongoRepository houseMongoRepository;

    public HouseService(HouseMongoRepository houseMongoRepository){
        this.houseMongoRepository = houseMongoRepository;
    }

    public List<House> findAll() {
        return houseMongoRepository.findAll();
    }

    public House findById(String id) {
        return houseMongoRepository.findById(id).orElseThrow(HouseNotFoundException::new);
    }
}
