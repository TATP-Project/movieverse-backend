package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.MovieSession;
import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.exception.InvalidSeatException;
import com.JAPKAM.Movieverse.exception.MovieSessionNotFoundException;
import com.JAPKAM.Movieverse.repository.MovieSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieSessionService {

    private MovieSessionRepository movieSessionRepository;

    public MovieSessionService(MovieSessionRepository movieSessionRepository){
        this.movieSessionRepository = movieSessionRepository;
    }

    public List<MovieSession> findAll() {
        return movieSessionRepository.findAll();
    }

    public MovieSession findById(String id) {
        return movieSessionRepository.findById(id).orElseThrow(MovieSessionNotFoundException::new);
    }
    public List<Seat> getSeats(String id){
        return movieSessionRepository.findById(id).orElseThrow(MovieSessionNotFoundException::new).getSeats();
    }
    public Seat updateSeat(String id, Seat seat){
        MovieSession currentMovieSession = movieSessionRepository.findById(id).orElseThrow(MovieSessionNotFoundException::new);
        List<Seat> seatsList = currentMovieSession.getSeats();
        Integer seatIndex = seatsList.stream()
                .filter(s -> s.getId().equals(seat.getId()))
                .map(s -> seatsList.indexOf(s)).findFirst()
                .orElseThrow(InvalidSeatException::new);
        Seat seatToUpdate = seatsList.get(seatIndex);
        seatToUpdate.setStatus(seat.getStatus());
        seatsList.set(seatIndex,seatToUpdate);
        currentMovieSession.setSeats(seatsList);
        movieSessionRepository.save(currentMovieSession);
        return seatToUpdate;
    }
}
