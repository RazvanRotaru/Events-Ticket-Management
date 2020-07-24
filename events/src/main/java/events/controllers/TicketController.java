package events.controllers;

import events.entities.HasReservation;
import events.entities.Reservation;
import events.repos.HasResRepoI;
import events.repos.ReservationRepoI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    HasResRepoI hasResRepo;

    @Autowired
    ReservationRepoI resRepo;

    @DeleteMapping("/payTicket/{id}")
    @Transactional
    List<String> payTicket(@PathVariable int id) {

        List<String> fls = new ArrayList<String>();
        List<Reservation> ress = hasResRepo.findAllByTicket(id);

        if (ress == null) return null;

        for (Reservation res : ress) {
            fls.add(res.getEvent().getEventID());
        }

        return fls;
    }


}
