package com.lcobzaru.bootcamp.socialmediaapp.service;

import com.lcobzaru.bootcamp.socialmediaapp.dto.UserDTO;
import com.lcobzaru.bootcamp.socialmediaapp.exeption.EmailAlreadyUsedExemption;
import com.lcobzaru.bootcamp.socialmediaapp.models.User;
import com.lcobzaru.bootcamp.socialmediaapp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserDTO registerNewUserAccount(User user) throws EmailAlreadyUsedExemption{
        // Build the entity object and do any changes before persisting (like password encryption)
        User customUser = new User();
        customUser.setFirstName(user.getFirstName());
        customUser.setLastName((user.getLastName()));
        customUser.setEmail(user.getEmail());
//        customUser.setPassword(passwordEncoder.encode(user.getPassword()));
        customUser.setPassword(user.getPassword());
        customUser.setGender(user.getGender());
        customUser.setUsername(user.getUsername());
        customUser.setBirthDate(user.getBirthDate());

        // Persist user in database
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(user.getEmail());
        if(existingUser.isPresent()){
            throw new EmailAlreadyUsedExemption();
        }
//        return userRepository.save(user);
        User persistedUser = this.userRepository.save(customUser);

//        // Build DTO to be returned to FE
        return UserDTO.builder()
                .id(persistedUser.getId())
                .email(persistedUser.getEmail())
                .firstName(persistedUser.getFirstName())
                .lastName(persistedUser.getLastName())
                .password(persistedUser.getPassword())
                .gender(persistedUser.getGender())
                .username(persistedUser.getUsername())
                .birthDate(persistedUser.getBirthDate())
                .build();

    }

    public Optional<User> getById(Long id){
        return userRepository.findById(id);
    }

    public Optional<User> getByUsername(String username){
        return userRepository.findByUsername(username);
    }

    public void delete (User user){
        userRepository.delete(user);
    }

    public UserDTO getUserProfileForMyUser(User user){

        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .email(user.getEmail())
                .gender(user.getGender())
                .birthDate(user.getBirthDate())
                .build();
    }

    public UserDTO getUserProfileForAnotherUser(User user){
        return UserDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .username(user.getUsername())
                .build();
    }

    public UserDTO updateUserProfile(User user){
        User updatedUser = new User();
        updatedUser.setFirstName(user.getFirstName());
        updatedUser.setLastName(user.getLastName());
        updatedUser.setUsername(user.getUsername());
        updatedUser.setEmail(user.getEmail());
        updatedUser.setPassword(user.getPassword());
        updatedUser.setGender(user.getGender());
        updatedUser.setBirthDate(user.getBirthDate());

        Optional<User> userDB = userRepository.findOneByEmailIgnoreCase(user.getEmail());
        if(!userDB.isPresent()){
            throw new EmailAlreadyUsedExemption();
        }
        User persistedUser = this.userRepository.save(updatedUser);

        return UserDTO.builder()
                .firstName(persistedUser.getFirstName())
                .lastName(persistedUser.getLastName())
                .username(persistedUser.getUsername())
                .email(persistedUser.getEmail())
                .gender(persistedUser.getGender())
                .birthDate(user.getBirthDate())
                .build();
    }


}
