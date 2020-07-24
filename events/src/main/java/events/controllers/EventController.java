package events.controllers;

import events.entities.Event;
import events.repos.EventRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/event")
public class EventController {

    @Autowired
    private EventRepoI repo;

    @PutMapping("/addEvent")
    void addEvent(@RequestBody Event event) {
        repo.save(event);
    }

    @DeleteMapping("/deleteByID/{id}")
    void deleteById(@PathVariable String id) {
        repo.deleteById(id);

    }

    @RequestMapping("/findByLocation/{location}")
    List<Event> findByLocation(@PathVariable String location) {
        return repo.findByLocation(location);
    }

    @RequestMapping("/findByLocationAndDay/{location}/{day}")
    List<Event> findByLocationAndDay(@PathVariable(name = "location") String location, @PathVariable(name = "day") int day){
        return repo.findByLocationAndDay(location, day);
    }

    List<Event> findByArtist(String artist) {
        return repo.findByArtist(artist);
    }

    List<Event> findByArtistAndDay(String artist, int day) {
        return repo.findByArtistAndDay(artist, day);
    }

    List<Event> findByLocationAndArtist(String location, String artist) {
        return repo.findByLocationAndArtist(location, artist);
    }

    @RequestMapping("/findByLocationAndArtistAndDay/{location}/{artist}/{day}")
    List<Event> findByLocationAndArtistAndDay(@PathVariable String location, @PathVariable String artist, @PathVariable int day) {
        return repo.findByLocationAndArtistAndDay(location, artist, day);
    }
}
