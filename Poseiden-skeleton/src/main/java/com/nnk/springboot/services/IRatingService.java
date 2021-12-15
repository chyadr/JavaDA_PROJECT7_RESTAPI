package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;

import java.util.List;
import java.util.Optional;

public interface IRatingService {

    void createRating(Rating rating);
    List<Rating> findAll();
    Optional<Rating> findById(Integer id);
    void updateRating(Rating rating);
    void deleteRating(Rating rating);
}
