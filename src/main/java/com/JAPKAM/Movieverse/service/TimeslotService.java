package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.Timeslot;
import com.JAPKAM.Movieverse.exception.TimeslotNotFoundException;
import com.JAPKAM.Movieverse.repository.TimeslotRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeslotService {

    private TimeslotRepository timeslotRepository;

    public TimeslotService(TimeslotRepository timeslotRepository){
        this.timeslotRepository = timeslotRepository;
    }

    public List<Timeslot> findAll() {
        return  timeslotRepository.findAll();
    }

    public Timeslot findById(String id) {
        return timeslotRepository.findById(id).orElseThrow(TimeslotNotFoundException::new);
    }
}
