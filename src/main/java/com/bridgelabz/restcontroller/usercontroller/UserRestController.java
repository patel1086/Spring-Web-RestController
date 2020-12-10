package com.bridgelabz.restcontroller.usercontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/hello")
public class UserRestController {

    private static Map<String, User> userRepo = new HashMap<>();
    static {
        User user1 = new User();
        user1.setId("1");
        user1.setName("Jitendra");
        userRepo.put(user1.getId(), user1);
        User user2 = new User();
        user2.setId("2");
        user2.setName("Ram");
        userRepo.put(user2.getId(), user2);
    }
    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to Jitendra Patel's RestController Web App";
    }

    @RequestMapping(value={"/welcome"},method= RequestMethod.GET)
    public String getNameWelcome(@RequestParam(value="name",defaultValue = "Jitendra") String name){
        return " Welcome "+name+" !!!";
    }

    @GetMapping("/getwelcome")
    public String getWelcome(){
        return "Welcome in to the get method of REST";
    }

    //for CURL we use this one
//    @PostMapping("/post")
//    public String sayHello(@RequestBody User user){
//        return "Welcome "+user.getId()+" "+user.getName()+"!"
//    }

	
    //POST
    @RequestMapping(value="/user", method=RequestMethod.POST)
    public ResponseEntity<Object> createProduct(@RequestBody User user)
    {
        userRepo.put(user.getId(), user);
        return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
    }

    @RequestMapping(value="/user")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(userRepo.values(), HttpStatus.OK);
    }

    //Put
    @RequestMapping(value="/user/{id}", method=RequestMethod.PUT)
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody User user) {
        userRepo.remove(id);
        user.setId(id);
        userRepo.put(id, user);
        return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
    }

    //Delete
    @RequestMapping(value="/user/{id}", method=RequestMethod.DELETE)
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        userRepo.remove(id);
        return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
    }

}







