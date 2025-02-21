package cz.demo.monetaproject.application.domain

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EntityListeners
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@EntityListeners(AuditingEntityListener.class)
@Entity(name = "TRANSFORMED_NUMBER")
class TransformedNumber {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tableGenerator")
    @Column(name = "ID", unique = true, nullable = false, updatable = false)
    Long id

    @Column(name = "INPUT_NUMBER", nullable = false)
    Long inputNumber

    @Column(name = "FINAL_RESULT", nullable = false)
    Long transformedNumber
}
