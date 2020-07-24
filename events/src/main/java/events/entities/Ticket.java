package events.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "`ticket`")
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketID;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user")
    private User user;

    @ManyToMany
    @JoinTable(name = "HasReservation",
                joinColumns = @JoinColumn(name = "ticket"),
                inverseJoinColumns = @JoinColumn(name = "reservation"))
    private Set<Reservation> reservations = new HashSet<>();

    public Ticket() {
    }

    public Ticket(User user) {
        this.user = user;
    }

    public int getTicketID() {
        return ticketID;
    }

    public void setTicketID(int reservationID) {
        this.ticketID = reservationID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Reservation> getEvents() {
        return reservations;
    }

    public void setEvents(Set<Reservation> reservations) {
        this.reservations = reservations;
    }
}
