package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;

import java.util.List;
import java.util.Optional;

public interface ICurvePointService {

    void createCurvePoint(CurvePoint curvePoint);
    List<CurvePoint> findAll();
    Optional<CurvePoint> findById(Integer id);

    void updateCurvePoint(CurvePoint curvePoint);

    void deleteCurvePoint(CurvePoint curvePoint);


}