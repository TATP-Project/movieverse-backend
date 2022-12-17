package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.House;
import com.JAPKAM.Movieverse.exception.HouseNotFoundException;
import com.JAPKAM.Movieverse.repository.HouseRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HouseService {

    private HouseRepository houseRepository;

    public HouseService(HouseRepository houseRepository){
        this.houseRepository = houseRepository;
    }

    public List<House> findAll() {
        return houseRepository.findAll();
    }

    public House findById(String id) {
        return houseRepository.findById(id).orElseThrow(HouseNotFoundException::new);
    }
}
