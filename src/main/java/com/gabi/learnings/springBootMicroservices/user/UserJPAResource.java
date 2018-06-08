package com.gabi.learnings.springBootMicroservices.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {

    @Autowired
    private UserDaoService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PostDaoService postService;

    //retrieveAllUsers
    @GetMapping(path = "/jpa/users")
    public List<User> retrieveAllUsers(){
        return userRepository.findAll();
    }

    //retriveUser(int id)
    @GetMapping(path = "/jpa/users/{id}")
    public Resource<User> retriveUser(@PathVariable int id) throws UserNotFoundException {
//        System.out.println(String.format("Searching id %d", id));
        Optional<User> userFound = userRepository.findById(id);
//        System.out.println(userFound.toString());

        if (!userFound.isPresent()){
//            System.out.println("Not found");
            throw new UserNotFoundException("id-"+id);
        }
//        System.out.println("Found");
        Resource<User> resource = new Resource<User>(userFound.get());
//        System.out.println(String.format("User Recovered %s", userFound.toString()));
        ControllerLinkBuilder linkToAll = ControllerLinkBuilder
                        .linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retrieveAllUsers());

        ControllerLinkBuilder linkToPosts = ControllerLinkBuilder
                .linkTo(ControllerLinkBuilder.methodOn(this.getClass()).retriveAllPostFromUser(id));

        resource.add(linkToAll.withRel("all-users"));
        resource.add(linkToPosts.withRel("post-list"));



        return resource;
    }

    //creteUser
    //input - details of the user
    //output - Create user and return the created URI
    @PostMapping(path = "/jpa/users")
    public ResponseEntity<Object> createUser(@Valid @RequestBody User user){
        User savedUser = userService.save(user);
        URI location = ServletUriComponentsBuilder
                    .fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(savedUser.getId())
                    .toUri();

        return ResponseEntity.created(location).build();

    }

    @GetMapping(path = "/jpa/users/{id}/posts")
    public List<Post> retriveAllPostFromUser(@PathVariable int id){
        return postService.findAllFromUser(id);
    }

    @PostMapping(path = "/jpa/users/{id}/posts")
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

    @GetMapping(path = "/jpa/users/{user_id}/posts/{post_id}")
    public Post retrievePostDetails(@PathVariable int user_id,@PathVariable int post_id){
        Post postFound = postService.findOnePost(post_id, user_id);
        if (postFound == null){
            throw new PostNotFoundException("User_id-"+user_id+ " Post_id:" + post_id);
        }
        return postFound;
    }

    @DeleteMapping(path="/jpa/users/{id}")
    public void deleteUserById(@PathVariable int id){
        User userFound = userService.deleteById(id);
        if (userFound == null){
            throw new UserNotFoundException("id-"+id);
        }
        return ;
    }
}
