package events.repos;

import events.entities.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepoI extends JpaRepository<Ticket, Integer> {

}
