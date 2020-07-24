package events.repos;

import events.entities.HasReservation;
import events.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HasResRepoI extends JpaRepository<HasReservation, Integer> {

    @Query("SELECT t.reservation FROM HasReservation t WHERE t.ticket.ticketID = :id")
    List<Reservation> findAllByTicket(@Param("id") int id);
}
