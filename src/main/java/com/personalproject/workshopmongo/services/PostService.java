package com.personalproject.workshopmongo.services;

import com.personalproject.workshopmongo.domain.Post;
import com.personalproject.workshopmongo.domain.User;
import com.personalproject.workshopmongo.repositories.PostRepository;
import com.personalproject.workshopmongo.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PostService {

    @Autowired
    private PostRepository repository;




    public Post findById(String id) {
        Optional<Post> obj = repository.findById(id);
        return  obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }
}
