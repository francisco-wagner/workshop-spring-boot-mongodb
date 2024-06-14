package com.personalproject.workshopmongo.services;


import com.personalproject.workshopmongo.domain.User;
import com.personalproject.workshopmongo.dto.UserDTO;
import com.personalproject.workshopmongo.repositories.UserRepository;
import com.personalproject.workshopmongo.services.exeptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        Optional<User> obj = repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
    }

    public User insert(User obj) {
        return repository.insert(obj);
    }

    public User fromDTO(UserDTO objDto) {
        return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
    }

    public void deleteById(String id) {
        findById(id); //call findById(id) here is justified in order to reuse the RunTimeException.
        repository.deleteById(id);
    }

    public User update(User userWithNewData) {
        User newObjToPersist = findById(userWithNewData.getId());
        updateData(newObjToPersist, userWithNewData);

        return repository.save(newObjToPersist);
    }

    public User updateData (User newObjToPersist, User userWithNewData) {
        newObjToPersist.setName(userWithNewData.getName());
        newObjToPersist.setEmail(userWithNewData.getEmail());

        return newObjToPersist;
    }
}
