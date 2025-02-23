package cz.demo.monetaproject.application.rest;

import cz.demo.monetaproject.application.servicelayer.TicketService;
import cz.demo.monetaproject.dto.TicketDto;
import cz.demo.monetaproject.rest.TicketRESTInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TicketRESTController implements TicketRESTInterface {

    @Autowired
    TicketService ticketService;

    @Override
    public ResponseEntity<TicketDto> createTicket() {

        TicketDto response = ticketService.createTicket();
        URI location = URI.create(String.format("/tickets/%d", response.getId()));
        return ResponseEntity.created(location).body(response);
    }

    @Override
    public ResponseEntity<TicketDto> getTicked(@PathVariable("ticketId") Long ticketId) {
        TicketDto response = ticketService.getTicked(ticketId);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<TicketDto> deleteTicket(@PathVariable("ticketId") Long ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }
}
