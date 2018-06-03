package com.gabi.learnings.springBootMicroservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
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
    public User retriveUser(@PathVariable int id){
        return service.findOne(id);
    }

    //creteUser
    //input - details of the user
    //output - Create user and return the created URI
    @PostMapping(path = "/users/")
    public User createUser(@RequestBody User user){
        return service.save(user);
    }

}
