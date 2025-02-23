package cz.demo.monetaproject.application.domain.repository;

import cz.demo.monetaproject.application.domain.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Optional;

public interface TicketRepository extends JpaRepository<Ticket, Long>, JpaSpecificationExecutor<Ticket> {

    Optional<Ticket> findOneById(Long ticketId);
}
