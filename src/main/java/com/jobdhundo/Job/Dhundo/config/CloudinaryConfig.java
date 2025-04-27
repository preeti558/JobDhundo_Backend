package com.jobdhundo.Job.Dhundo.config;

import com.cloudinary.Cloudinary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.Map;

@Configuration
public class CloudinaryConfig {

    @Bean
    public Cloudinary cloudinary() {
        Map<String, String> config = Map.of(
            "cloud_name", "dnqhi1x2g",
            "api_key", "267115958619544",
            "api_secret", "d3TfVt9FAD9zTEDUsAbQCXC06ao"
        );
        return new Cloudinary(config);
    }
}
