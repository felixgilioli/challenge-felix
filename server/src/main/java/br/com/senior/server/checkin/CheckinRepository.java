package br.com.senior.server.checkin;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.Optional;

public interface CheckinRepository extends JpaRepository<Checkin, Long> {

    @Query("select c from Checkin c where c.person.id = :personId " +
            "and (c.departureTime is null or c.departureTime > :entryTime)")
    Optional<Checkin> findActiveCheckinByPerson(@Param("personId") Long personId,
                                                @Param("entryTime") LocalDateTime entryTime);
}
