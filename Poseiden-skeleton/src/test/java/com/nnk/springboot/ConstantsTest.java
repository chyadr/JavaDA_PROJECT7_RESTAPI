package com.nnk.springboot;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public final class ConstantsTest {

    public static final BidList bidList= new BidList().bidListId(2).account("").type("").bidQuantity(1D).askQuantity(3D).bid(5D).ask(2D).benchmark("").bidListDate(new Timestamp(System.currentTimeMillis()));
    public static final List<BidList> bids = Collections.singletonList(bidList);


    public static final CurvePoint curvePoint= new CurvePoint().id(1).curveId(2).asOfDate(new Date()).term(1D).value(1D).creationDate(new Date());
    public static final List<CurvePoint> curvePoints = Collections.singletonList(curvePoint);


    public static final Rating rating= new Rating().id(1).moodysRating("moodys").sandPRating("sand").fitchRating("fitch").orderNumber(1);
    public static final List<Rating> ratings = Collections.singletonList(rating);




}
