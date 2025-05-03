package com.jobdhundo.Job.Dhundo.controllers;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.jobdhundo.Job.Dhundo.entities.Testimonial;
import com.jobdhundo.Job.Dhundo.services.TestimonialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/testimonials")
@CrossOrigin(origins = {"http://localhost:5173", "https://job-dhundo.netlify.app"})
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @Autowired
    private Cloudinary cloudinary;

    @PostMapping
    public Testimonial addTestimonial(
            @RequestParam("name") String name,
            @RequestParam("jobRole") String jobRole,
            @RequestParam("quote") String quote,
            @RequestParam("date") String date,
            @RequestParam("rating") int rating,
            @RequestParam("details") String details,
            @RequestParam("image") MultipartFile image
    ) {
        try {
            // Correct method to upload image
            Map uploadResult = cloudinary.uploader().upload(image.getBytes(), ObjectUtils.emptyMap());
            String imageUrl = (String) uploadResult.get("url");

            Testimonial testimonial = new Testimonial();
            testimonial.setName(name);
            testimonial.setJobRole(jobRole);
            testimonial.setQuote(quote);
            testimonial.setDate(date);
            testimonial.setRating(rating);
            testimonial.setDetails(details);
            testimonial.setImageUrl(imageUrl);

            return testimonialService.saveTestimonial(testimonial);

        } catch (IOException e) {
            throw new RuntimeException("Image upload failed", e);
        }
    }

    @GetMapping
    public List<Testimonial> getTestimonials() {
        return testimonialService.getAllTestimonials();
    }
    @DeleteMapping("/{id}")
    public void deleteTestimonial(@PathVariable int id) {
        testimonialService.deleteTestimonial(id);
    }

}
