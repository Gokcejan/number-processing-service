package cz.demo.monetaproject.application.servicelayer;

import cz.demo.monetaproject.application.domain.Ticket;
import cz.demo.monetaproject.application.domain.factory.TicketMapper;
import cz.demo.monetaproject.application.domain.repository.TicketRepository;
import cz.demo.monetaproject.application.exceptions.NotFoundException;
import cz.demo.monetaproject.dto.TicketDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static java.lang.String.format;

@Service
public class TicketService {

    @Autowired
    TicketMapper ticketMapper;

    @Autowired
    TicketRepository ticketRepository;


    public TicketDto createTicket() {
        Ticket ticket = ticketMapper.toEntity();
        Ticket savedTicket = ticketRepository.save(ticket);
        return ticketMapper.toDto(savedTicket);
    }

    public TicketDto getTicked(Long ticketId) {
        Ticket ticket = findTicket(ticketId);
        return ticketMapper.toDto(ticket);
    }

    private Ticket findTicket(Long ticketId) {
        Optional<Ticket> channelOptional = ticketRepository.findOneById(ticketId);
        return channelOptional.orElseThrow(() -> new NotFoundException(format("The Ticket [%s] not found.", ticketId)));
    }

    public void deleteTicket(Long ticketId) {
        Ticket ticket = findTicket(ticketId);
        if (ticket == null) {
            throw new IllegalArgumentException("Ticket not found: " + ticketId);
        }
        ticketRepository.delete(ticket);
    }
}
