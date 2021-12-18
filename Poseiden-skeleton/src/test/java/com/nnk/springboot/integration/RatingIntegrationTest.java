package com.nnk.springboot.integration;

import com.nnk.springboot.ConstantsTest;
import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.services.impl.CurvePointService;
import com.nnk.springboot.services.impl.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@WithMockUser(username = "user", authorities = { "USER" })
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_CLASS)
public class RatingIntegrationTest {

        @Autowired
        private MockMvc mvc;
        @Autowired
        private RatingService ratingService;


        @Test
        public void a_create()throws Exception{

            mvc.perform(post("/rating/validate").flashAttr("rating",ConstantsTest.rating)
                            .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

            Optional<Rating> rating = ratingService.findById(1);
            assertNotNull(rating);
            assertTrue(rating.isPresent());
        }


    @Test
    public void b_update()throws Exception{

        mvc.perform(post("/rating/update/1").flashAttr("rating",ConstantsTest.updateRating)
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        Optional<Rating> rating = ratingService.findById(1);
        assertNotNull(rating);
        assertTrue(rating.isPresent());
        assertEquals(rating.get().getSandPRating(),("give"));
    }

    @Test
    public void c_delete()throws Exception{

        mvc.perform(get("/rating/delete/1")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE));

        Optional<Rating> rating = ratingService.findById(1);
        assertFalse(rating.isPresent());
    }


    }

