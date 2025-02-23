package cz.demo.monetaproject.application.domain.factory

import cz.demo.monetaproject.application.domain.Ticket
import cz.demo.monetaproject.application.domain.repository.TicketRepository
import cz.demo.monetaproject.dto.TicketDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class TicketMapper {

    @Autowired
    TicketRepository ticketRepository


    Ticket toEntity() {

        Ticket ticket = new Ticket()
        ticket.createdAt = LocalDateTime.now()

        ticket
    }

    TicketDto toDto(Ticket ticket) {
        TicketDto dto = new TicketDto()

        List<Ticket> tickets = ticketRepository.findAll()
        Collections.sort(tickets)

        dto.id = ticket.id
        dto.createdAt = ticket.createdAt
        dto.orderInQueue = Collections.binarySearch(tickets, ticket)

        dto
    }
}
