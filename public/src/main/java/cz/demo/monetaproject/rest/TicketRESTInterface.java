package cz.demo.monetaproject.rest;

import cz.demo.monetaproject.dto.TicketDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TicketRESTInterface {

    @RequestMapping(value = "/tickets", method = RequestMethod.POST)
    ResponseEntity<TicketDto> createTicket();

    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET)
    ResponseEntity<TicketDto> getTicked(@PathVariable("ticketId") Long ticketId);

    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.DELETE)
    ResponseEntity<TicketDto> deleteTicket(@PathVariable("ticketId") Long ticketId);
}
