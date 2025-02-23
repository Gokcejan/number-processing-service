package cz.demo.monetaproject.rest

import cz.demo.monetaproject.application.domain.Ticket
import cz.demo.monetaproject.application.domain.repository.TicketRepository
import cz.demo.monetaproject.core.CleanUpDb
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.web.servlet.ResultActions

import java.util.stream.Collectors

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import static cz.demo.monetaproject.rest.TestData.createTestTickets

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
class TicketApiSpec extends BaseSpec implements CleanUpDb {

    @Autowired
    TicketRepository ticketRepository

    def cleanup() {
        cleanUpDb()
    }


    def "should create a new ticket and update queue order after deletion"() {

        given: "Create 3 test tickets in DB"
        createTestTicketsInDB()

        when: "Create a new ticket"
        ResultActions postTicketResponse = mockMvc.perform(post("/tickets")
                .contentType(MediaType.APPLICATION_JSON)
                .content()
        )

        def postTicketResponseBody = extractBodyFromResponseAsMap(postTicketResponse)

        then:
        postTicketResponse.andExpect(status().isCreated())
        postTicketResponseBody.id == 4
        postTicketResponseBody.orderInQueue == 3

        when: "Get the ticket with index 0"
        List<Ticket> tickets = ticketRepository.findAll()
        tickets.sort { a, b -> (a.id <=> b.id) }
        Long ticketId = tickets.get(0).id

        ResultActions getTicketResult = mockMvc.perform(get("/tickets/${ticketId}"))

        def getTicketResponseBody = extractBodyFromResponseAsMap(getTicketResult)

        then:
        getTicketResult.andExpect(status().isOk())
        getTicketResponseBody.id == 1
        getTicketResponseBody.orderInQueue == 0

        when: "Delete the ticket with index 0"
        ResultActions deleteTicketResult = mockMvc.perform(delete("/tickets/${ticketId}"))

        then:
        deleteTicketResult.andExpect(status().isNoContent())

        when: "Get created ticket after deletion and check if the order in queue is correct"
        Long createdTicketId = extractBodyFromResponseAsMap(postTicketResponse).id as Long
        ResultActions getTicketResultAfterDelete = mockMvc.perform(get("/tickets/${createdTicketId}"))

        def getTicketResultAfterDeleteResponseBody = extractBodyFromResponseAsMap(getTicketResultAfterDelete)

        then:
        getTicketResultAfterDelete.andExpect(status().isOk())
        getTicketResultAfterDeleteResponseBody.id == 4
        getTicketResultAfterDeleteResponseBody.orderInQueue == 2

        when: "Get the ticket with index 0 after deletion and check if the order in queue and ID is correct"
        List<Ticket> ticketsAfterDelete = ticketRepository.findAll()
        ticketsAfterDelete.sort { a, b -> (a.id <=> b.id) }
        Long ticketAfterDeleteId = ticketsAfterDelete.get(0).id
        ResultActions getTicketResult2 = mockMvc.perform(get("/tickets/${ticketAfterDeleteId}"))

        def getTicketResponseBody2 = extractBodyFromResponseAsMap(getTicketResult2)

        then:
        getTicketResult2.andExpect(status().isOk())
        getTicketResponseBody2.id == 2
        getTicketResponseBody2.orderInQueue == 0

    }

    List<Ticket> createTestTicketsInDB() {
        return createTestTickets().stream()
                .map({ ticket -> ticketRepository.save(ticket) })
                .collect(Collectors.toList())
    }
}
