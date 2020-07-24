package events.repos;

import events.entities.Event;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Repository
public interface EventRepoI extends JpaRepository<Event, String> {

    void deleteByEventID(String eventID);
    List<Event> findByLocation(String location);
    List<Event> findByLocationAndDay(String location, int day);
    List<Event> findByArtist(String destination);
    List<Event> findByArtistAndDay(String destination, int day);
    List<Event> findByLocationAndArtist(String location, String artist);
    List<Event> findByLocationAndArtistAndDay(String location, String artist, int day);



}
