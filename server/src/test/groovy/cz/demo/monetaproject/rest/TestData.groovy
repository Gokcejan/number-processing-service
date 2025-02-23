package cz.demo.monetaproject.rest

import cz.demo.monetaproject.application.domain.Ticket

import java.time.LocalDateTime

class TestData {


    static List<Ticket> createTestTickets() {

        LocalDateTime now = LocalDateTime.now()

        int secondsToAdd = 0

        Ticket ticket1 = new Ticket()
        ticket1.setCreatedAt(now.plusSeconds(secondsToAdd++))

        Ticket ticket2 = new Ticket()
        ticket2.setCreatedAt(now.plusSeconds(secondsToAdd++))

        Ticket ticket3 = new Ticket()
        ticket3.setCreatedAt(now.plusSeconds(secondsToAdd))

        return Arrays.asList(ticket1, ticket2, ticket3)

    }

    static final String defaultNumberBody() {
        return """
            {
                "inputNumber": 43256791
            }
        """
    }

}
