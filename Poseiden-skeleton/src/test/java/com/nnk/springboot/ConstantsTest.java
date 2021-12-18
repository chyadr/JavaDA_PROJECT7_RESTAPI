package com.nnk.springboot;

import com.nnk.springboot.domain.*;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;
import java.util.List;


public final class ConstantsTest {

    public static final BidList bidList= new BidList().bidListId(1).account("account").type("type").bidQuantity(1D).askQuantity(3D).bid(5D).ask(2D).benchmark("benchmark").bidListDate(new Timestamp(System.currentTimeMillis()));
    public static final BidList updateBidList= new BidList().bidListId(1).account("newAccount").type("newType").bidQuantity(1D).askQuantity(3D).bid(5D).ask(2D).benchmark("benchmark").bidListDate(new Timestamp(System.currentTimeMillis()));
    public static final BidList invalidBidList= new BidList();
    public static final List<BidList> bids = Collections.singletonList(bidList);


    public static final CurvePoint curvePoint= new CurvePoint().id(1).curveId(2).asOfDate(new Date()).term(1D).value(1D).creationDate(new Date());
    public static final CurvePoint updateCurvePoint= new CurvePoint().id(1).curveId(9).asOfDate(new Date()).term(1D).value(1D).creationDate(new Date());
    public static final CurvePoint invalidCurvePoint= new CurvePoint();
    public static final List<CurvePoint> curvePoints = Collections.singletonList(curvePoint);


    public static final Rating rating= new Rating().id(1).moodysRating("moodys").sandPRating("sand").fitchRating("fitch").orderNumber(1);
    public static final Rating updateRating= new Rating().id(1).moodysRating("moodys").sandPRating("give").fitchRating("fitch").orderNumber(1);
    public static final Rating invalidRating= new Rating();
    public static final List<Rating> ratings = Collections.singletonList(rating);

    public static final RuleName ruleName= new RuleName().name("Rule").description("Description").json("Json").template("Template").sqlStr("SqlStr").sqlPart("SqlPart");
    public static final RuleName updateRuleName= new RuleName().name("Right").description("Description").json("Json").template("Template").sqlStr("SqlStr").sqlPart("SqlPart");
    public static final RuleName invalidRuleName= new RuleName();
    public static final List<RuleName> ruleNames=Collections.singletonList(ruleName);


    public static final Trade trade= new Trade().tradeId(1).account("account").type("type").buyQuantity(1D).sellQuantity(2D).sellPrice(10D).buyPrice(2D).benchmark("benchmark");
    public static final Trade updateTrade= new Trade().tradeId(1).account("MyAccount").type("type").buyQuantity(1D).sellQuantity(2D).sellPrice(10D).buyPrice(2D).benchmark("benchmark");
    public static final Trade invalidTrade= new Trade();
    public static final List<Trade> trades=Collections.singletonList(trade);


    public static final User user= new User().id(1).username("username").password("abcA*HTT22222").fullname("fullname").role("ADMIN");
    public static final User updateUser= new User().id(1).username("MyUserName").password("abcA*HTT22222").fullname("fullname").role("ADMIN");
    public static final List<User> users=Collections.singletonList(user);


}
