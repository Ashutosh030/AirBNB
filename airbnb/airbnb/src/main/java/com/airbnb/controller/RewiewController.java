package com.airbnb.controller;


import com.airbnb.entity.Property;
import com.airbnb.entity.PropertyUser;
import com.airbnb.entity.Review;
import com.airbnb.repository.PropertyRepository;
import com.airbnb.repository.ReviewRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/reviews")
public class RewiewController {

    private ReviewRepository reviewRepository;

    private PropertyRepository  propertyRepository;

    public RewiewController(ReviewRepository reviewRepository, PropertyRepository propertyRepository) {
        this.reviewRepository = reviewRepository;
        this.propertyRepository = propertyRepository;
    }

    @PostMapping("/addReview/{propertyId}")
    public ResponseEntity<String> addReview (
            @PathVariable long propertyId,
            @RequestBody Review review,
            @AuthenticationPrincipal PropertyUser propertyUser){

        Optional<Property> opProperty = propertyRepository.findById(propertyId);
        Property property = opProperty.get();

        /*Review r = reviewRepository.findReviewByUserIdAndPropertyId(propertyId, propertyUser.getId());
        if (r!=null){
            return new ResponseEntity<>("Review already added..!!", HttpStatus.BAD_REQUEST);
        }*/

        Review r1 = reviewRepository.findReviewByUser(property, propertyUser);
        if (r1!=null){
            return new ResponseEntity<>("Review already added for this property", HttpStatus.BAD_REQUEST);
        }
        review.setProperty(property);
        review.setPropertyUser(propertyUser);

        reviewRepository.save(review);
        return new ResponseEntity<>("Review added..!", HttpStatus.CREATED);
    }

    @GetMapping("/userReview")
    public ResponseEntity<List<Review>> userReviews (@AuthenticationPrincipal PropertyUser propertyUser ){
        List<Review> reviews = reviewRepository.findByPropertyUser(propertyUser);
        return new ResponseEntity<>(reviews,HttpStatus.OK);
    }
}
