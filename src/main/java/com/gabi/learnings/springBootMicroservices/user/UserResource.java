package com.gabi.learnings.springBootMicroservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
public class UserResource {

    @Autowired
    private UserDaoService userService;
    @Autowired
    private PostDaoService postService;

    //retrieveAllUsers
    @GetMapping(path = "/users")
    public List<User> retrieveAllUsers(){
        return userService.findAll();
    }

    //retriveUser(int id)
    @GetMapping(path = "/users/{id}")
    public User retriveUser(@PathVariable int id) throws UserNotFoundException {
        User userFound = userService.findOne(id);
        if (userFound == null){
            throw new UserNotFoundException("id-"+id);
        }
        return userFound;
    }

    //creteUser
    //input - details of the user
    //output - Create user and return the created URI
    @PostMapping(path = "/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/users/{id}/posts")
    public List<Post> retriveAllPostFromUser(@PathVariable int id){
        return postService.findAllFromUser(id);
    }

    @PostMapping(path = "/users/{id}/posts")
    public ResponseEntity<Object> createPost(@PathVariable int id, @RequestBody Post post){
        post.setUser_id(id);
        Post savedPost = postService.createPost(post);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{post_id}")
                .buildAndExpand(savedPost.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping(path = "/users/{user_id}/posts/{post_id}")
    public Post retrievePostDetails(@PathVariable int user_id,@PathVariable int post_id){
        Post postFound = postService.findOnePost(post_id, user_id);
        if (postFound == null){
            throw new PostNotFoundException("User_id-"+user_id+ " Post_id:" + post_id);
        }
        return postFound;
    }

    @DeleteMapping(path="/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User userFound = userService.deleteById(id);
        if (userFound == null){
            throw new UserNotFoundException("id-"+id);
        }
        return ;
    }
}
