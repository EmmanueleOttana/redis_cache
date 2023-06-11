package com.develhope.redis.controllers;

import com.develhope.redis.entities.jpa.UserJPA;
import com.develhope.redis.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

@Autowired
private UserService userService;


    @PostMapping
    public Object create(@RequestBody UserJPA user){
        return userService.create(user);
    }

    @GetMapping
    public List<? extends Object> readAll(){
        return userService.readAll();
    }

    @Cacheable(value = "Leggi", key = "1user")
    @GetMapping("/{id}")
    public Object readOne(@PathVariable Long id){
        return userService.readOne(id);
    }
    @Cacheable(value = "modifica", key = "1user")
    @PutMapping("/{id}")
    public Object update(@PathVariable Long id, @RequestBody UserJPA user){
        return userService.update(id,user);
    }

    @Cacheable(value = "Cancella", key = "1user")
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        userService.delete(id);
    }


}
