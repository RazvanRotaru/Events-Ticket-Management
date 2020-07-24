package events.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "`event`")
public class Event {

    @Id
    @Column(name = "ID", unique = true)
    private String eventID;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "artist", nullable = false)
    private String artist;

    @Column(name = "beginh", nullable = false)
    private int hour;

    @Column(name = "begind", nullable = false)
    private int day;

    @Column(name = "duration", nullable = false)
    private int duration;

    @Column(name = "seats", nullable = false)
    private int seats;

    public String getEventID() {
        return eventID;
    }

    public void setEventID(String eventID) {
        this.eventID = eventID;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        this.hour = hour;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }
}
