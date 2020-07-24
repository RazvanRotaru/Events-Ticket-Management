package events.controllers;

import events.entities.*;
import events.repos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    @Autowired
    ReservationRepoI repo;

    @Autowired
    UserRepoI userRepo;

    @Autowired
    EventRepoI eventRepo;

    @Autowired
    TicketRepoI ticketRepo;

    @Autowired
    HasResRepoI hsRepo;

    @PutMapping("/add/{username}/{event}")
    @Transactional
    int addRes(@PathVariable String username, @PathVariable String event) {
        User us = userRepo.findByName(username).orElse(null);
        Event e = eventRepo.findById(event).orElse(null);

        if (us != null && e != null) {
            Reservation res = repo.save(new Reservation(us, e));
            Ticket ticket = ticketRepo.save(new Ticket(us));
            hsRepo.save(new HasReservation(ticket, res));
            return ticket.getTicketID();
        } else {
            return -1;
        }
    }
}
