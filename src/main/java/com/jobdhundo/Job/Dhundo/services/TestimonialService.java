package com.jobdhundo.Job.Dhundo.services;

import com.jobdhundo.Job.Dhundo.entities.Testimonial;
import com.jobdhundo.Job.Dhundo.repositories.TestimonialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    public Testimonial saveTestimonial(Testimonial testimonial) {
        return testimonialRepository.save(testimonial);
    }

    public List<Testimonial> getAllTestimonials() {
        return testimonialRepository.findAll();
    }
}
