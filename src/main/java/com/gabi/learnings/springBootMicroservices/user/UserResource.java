package com.gabi.learnings.springBootMicroservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService service;

    //retrieveAllUsers
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    //retriveUser(int id)
    @GetMapping(path = "/users/{id}")
    public User retriveUser(@PathVariable int id) throws UserNotFoundException {
        User userFound = service.findOne(id);
        if (userFound == null){
            throw new UserNotFoundException("id-"+id);
        }
        return userFound;
    }


    //creteUser
    //input - details of the user
    //output - Create user and return the created URI
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@RequestBody User user){
        User savedUser = service.save(user);
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

        return ResponseEntity.created(location).build();

    }

}
