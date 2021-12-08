package com.nnk.springboot.services.impl;


import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.ICurvePointService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CurvePointService  implements ICurvePointService {

    private final CurvePointRepository curvePointRepository;

    public CurvePointService(CurvePointRepository curvePointRepository) {
        this.curvePointRepository = curvePointRepository;
    }
    @Override
    public void createCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    @Override
    public   List<CurvePoint> findAll() {
        return curvePointRepository.findAll();
    }

    @Override
    public Optional<CurvePoint> findById(Integer id) {
        return curvePointRepository.findById(id);
    }

    @Override
    public void updateCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.save(curvePoint);
    }

    @Override
    public void deleteCurvePoint(CurvePoint curvePoint) {
        curvePointRepository.delete(curvePoint);
    }


}

