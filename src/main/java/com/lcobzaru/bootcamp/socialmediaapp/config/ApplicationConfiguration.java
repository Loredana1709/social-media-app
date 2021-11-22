package com.lcobzaru.bootcamp.socialmediaapp.config;

import com.lcobzaru.bootcamp.socialmediaapp.dto.UserDTO;
import com.lcobzaru.bootcamp.socialmediaapp.models.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class ApplicationConfiguration {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        Converter<UserDTO, User> userConverter = context -> {
            UserDTO s = context.getSource();
            User d = new User();
            d.setId(s.getId());
            d.setFirstName(s.getFirstName());
            d.setLastName(s.getLastName());
            d.setEmail(s.getEmail());
            d.setGender(s.getGender());
            d.setPassword(s.getPassword());
            d.setBirthDate(s.getBirthDate());
            return d;

        };
        modelMapper.addConverter(userConverter);
        return modelMapper;
    }

//    @Bean
//    public PasswordEncoder encoder() {
//        return new BCryptPasswordEncoder();
//    }
}
