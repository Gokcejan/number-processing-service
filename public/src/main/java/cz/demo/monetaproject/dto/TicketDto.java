package cz.demo.monetaproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDateTime;

@Getter
@Setter
@Relation(collectionRelation = "tickets")
public class TicketDto {

    private Long id;
    private LocalDateTime createdAt;
    private Long orderInQueue;
}
