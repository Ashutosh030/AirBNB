package com.airbnb.service;

import com.airbnb.dto.LoginDto;
import com.airbnb.dto.PropertyUserDto;
import com.airbnb.entity.PropertyUser;
import com.airbnb.repository.PropertyUserRepository;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private PropertyUserRepository userRepository;
    private JWTService jwtService;

    public UserService(PropertyUserRepository userRepository, JWTService jwtService) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
    }

    public PropertyUser addUser (PropertyUserDto propertyUserDto){

        PropertyUser user = new PropertyUser();
        user.setFirstName(propertyUserDto.getFirstName());
        user.setLastName(propertyUserDto.getLastName());
        user.setUserName(propertyUserDto.getUserName());
        user.setEmail(propertyUserDto.getEmail());
        //user.setPassword(new BCryptPasswordEncoder(.encode(user.getPassword()))));
        user.setPassword(BCrypt.hashpw(propertyUserDto.getPassword(),BCrypt.gensalt(10)));
        user.setUserRole(propertyUserDto.getUserRole());
        PropertyUser savedUser = userRepository.save(user);
        return savedUser;

    }

    public String verifyLogin(LoginDto loginDto) { // LoginDto , this is where the expected valu is -
        //actual value is in database->
        Optional<PropertyUser> opUser = userRepository.findByUserName(loginDto.getUserName());
        if (opUser.isPresent()){
            PropertyUser propertyUser = opUser.get(); //get method converts the optional object to entity object.

            //now the password here in data is encrypted password, to compare the encrypted password to raw password
            //we have the method->


            //here just compare the password from dto to the password from data-.
            //it will return a boolean value, so give return->

            if (BCrypt.checkpw(loginDto.getPassword(),propertyUser.getPassword())){
                //this is where we are checking if username is valid?? if valid-> call jwt.service method.
                 return   jwtService.genetareToken(propertyUser);
            }
        }
        return null;
    }
}
