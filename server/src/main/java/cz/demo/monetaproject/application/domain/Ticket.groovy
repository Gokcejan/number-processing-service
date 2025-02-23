package cz.demo.monetaproject.application.domain

import jakarta.persistence.*

import java.time.LocalDateTime

@Entity(name = "TICKET")
class Ticket implements Comparable<Ticket>{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tableGenerator")
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    Long id

    @Column(name = "CREATED_AT", nullable = false)
    LocalDateTime createdAt

    @Override
    int compareTo(Ticket o) {
        return Long.compare(this.id, o.id)
    }
}
