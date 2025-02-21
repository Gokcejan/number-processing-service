package cz.demo.monetaproject.application.domain.repository;

import cz.demo.monetaproject.application.domain.TransformedNumber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransformedNumberRepository extends JpaRepository<TransformedNumber, Long>, JpaSpecificationExecutor<TransformedNumber> {
}
