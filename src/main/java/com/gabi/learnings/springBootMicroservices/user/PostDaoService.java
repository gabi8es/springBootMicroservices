package com.gabi.learnings.springBootMicroservices.user;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component
public class PostDaoService {
    private static List<Post> posts = new ArrayList<>();
    private static int postsCount = 5;

    static {
        posts.add(new Post(1,1,new Date(),"Titulo 1", "Details post 1"));
        posts.add(new Post(2,2,new Date(),"Titulo 2", "Details post 2"));
        posts.add(new Post(3,3,new Date(),"Titulo 3", "Details post 3"));
        posts.add(new Post(4,3,new Date(),"Titulo 4", "Details post 4"));
        posts.add(new Post(5,1,new Date(),"Titulo 5", "Details post 5"));
    }

    List<Post> findAll(){
        return posts;
    }

    List<Post> findAllFromUser(int user_id)
    {
        List<Post> postFromUser = new ArrayList<>();
        for (Post post:posts){
            if (post.getUser_id()==user_id){
                postFromUser.add(post);
            }
        }
        return postFromUser;
    }

    Post createPost(Post post ) {
        if (post.getId()==null){
            postsCount = postsCount + 1;
            post.setId(postsCount);
        }
        posts.add(post);
        return post;
    }

    Post findOnePost(int post_id, int user_id)
    {
        for (Post post:posts){
            if ((post.getUser_id()==user_id) && (post.getId()==post_id)){
                return post;
            }
        }
        return null;
    }
}
