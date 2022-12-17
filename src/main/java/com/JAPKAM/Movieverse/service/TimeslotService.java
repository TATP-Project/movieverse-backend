package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Timeslot;
import com.JAPKAM.Movieverse.exception.TimeslotNotFoundException;
import com.JAPKAM.Movieverse.repository.TimeslotMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotService {

    TimeslotMongoRepository timeslotMongoRepository;

    public TimeslotService(TimeslotMongoRepository timeslotMongoRepository){
        this.timeslotMongoRepository = timeslotMongoRepository;
    }

    public List<Timeslot> findAll() {
        return  timeslotMongoRepository.findAll();
    }

    public Timeslot findById(String id) {
        return timeslotMongoRepository.findById(id).orElseThrow(TimeslotNotFoundException::new);
    }
}
