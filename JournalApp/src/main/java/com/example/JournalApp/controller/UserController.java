package com.example.JournalApp.controller;

import com.example.JournalApp.entity.User;
import com.example.JournalApp.repository.UserRepository;
import com.example.JournalApp.service.UserService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody User user)
    {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @GetMapping("/getById/{id}")
//    public ResponseEntity<?> getUserById(@PathVariable ObjectId id) {
//        User user =   userService.findById(id).orElseThrow(null);
//        return new ResponseEntity<>(user,HttpStatus.NO_CONTENT);
//    }

    @PutMapping("updateById/{id}")
    public ResponseEntity<?> updateUser(@PathVariable ObjectId id ,@RequestBody User user) {

        Optional<User> optionalUser= userService.findById(id);
       if(optionalUser.isPresent())
       {
            User user1 = optionalUser.get();
            user1.setUserName(user.getUserName());
            user1.setEmail(user.getEmail());
            user1.setPassword(user.getPassword());
           userService.saveUser(user1);
       }
       return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/deleteById/{id}")
    public ResponseEntity<?> deleteUserById(@PathVariable ObjectId id) {
        userService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}
