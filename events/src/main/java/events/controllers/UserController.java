package events.controllers;

import events.entities.User;
import events.repos.UserRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepoI repo;

    @PutMapping("/add")
    @Transactional
    public void addUser(@RequestBody User us) {
        repo.save(us);
    }
    @GetMapping("/getByID/{id}")
    public User getByID(@PathVariable int id) {
        return repo.findById(id).orElse(null);
    }

    @GetMapping("/existsByName/{name}")
    public boolean existsByName(@PathVariable String name) {
        return repo.existsByName(name);
    }

    @GetMapping("/getByName/{name}")
    public User getByName(@PathVariable String name) {
        return repo.findByName(name).orElse(null);
    }
}
