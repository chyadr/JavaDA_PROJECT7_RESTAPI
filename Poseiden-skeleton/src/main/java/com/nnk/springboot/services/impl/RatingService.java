package com.nnk.springboot.services.impl;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.IRatingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RatingService implements IRatingService {
    private final RatingRepository ratingRepository;

    public RatingService(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    @Override
    public void createRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public
   List<Rating> findAll() {
      return ratingRepository.findAll();
    }

    @Override
    public Optional<Rating> findById(Integer id) {
       return ratingRepository.findById(id);
    }

    @Override
    public void updateRating(Rating rating) {
        ratingRepository.save(rating);
    }

    @Override
    public void deleteRating(Rating rating) {
    ratingRepository.delete(rating);
    }
}
