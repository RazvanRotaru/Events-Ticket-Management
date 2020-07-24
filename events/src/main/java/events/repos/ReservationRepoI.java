package events.repos;

import events.entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReservationRepoI extends JpaRepository<Reservation, Integer> {

    @Query("SELECT r from Reservation r WHERE r.user.userID = :id")
    List<Reservation> findByUserID (@Param("id") int id);

}
