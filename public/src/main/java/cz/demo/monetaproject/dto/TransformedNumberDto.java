package cz.demo.monetaproject.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Setter
@Relation(collectionRelation = "transformedNumbers")
public class TransformedNumberDto {

    private Long id;
    private Long inputNumber;
    private Long transformedNumber;
}
