package com.JAPKAM.Movieverse.service;

import com.JAPKAM.Movieverse.entity.MovieSession;
import com.JAPKAM.Movieverse.entity.Seat;
import com.JAPKAM.Movieverse.entity.SeatStatus;
import com.JAPKAM.Movieverse.exception.InvalidSeatException;
import com.JAPKAM.Movieverse.exception.MovieSessionNotFoundException;
import com.JAPKAM.Movieverse.exception.UnavailableSeatException;
import com.JAPKAM.Movieverse.repository.MovieSessionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public List<Seat> updateSeatStatus(String id, List<Seat> seats) {
        MovieSession currentMovieSession = movieSessionRepository.findById(id).orElseThrow(MovieSessionNotFoundException::new);
        List<Seat> oldSeats = currentMovieSession.getSeats();
        List <SeatStatus> statusList = oldSeats.stream().map(seat -> seat.getStatus()).collect(Collectors.toList());
        int availableCount = (int)statusList.stream().filter(status -> status == SeatStatus.AVAILABLE).count();
        if (availableCount != seats.size()){
            throw new UnavailableSeatException();
        }
        return seats.stream().map(seat -> updateSeat(id, seat)).collect(Collectors.toList());
    }

    public List<MovieSession> findByMovieId(String id){
        return movieSessionRepository.findByMovieId(id);
    }

    public double getPriceByMovieSessionId(String id) {
        return findById(id).getPrice();
    }
}
