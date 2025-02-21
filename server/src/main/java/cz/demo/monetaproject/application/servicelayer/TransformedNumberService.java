package cz.demo.monetaproject.application.servicelayer;

import cz.demo.monetaproject.application.domain.TransformedNumber;
import cz.demo.monetaproject.application.domain.factory.TransformedNumberMapper;
import cz.demo.monetaproject.application.domain.repository.TransformedNumberRepository;
import cz.demo.monetaproject.dto.TransformedNumberCreateDto;
import cz.demo.monetaproject.dto.TransformedNumberDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransformedNumberService {

    @Autowired
    TransformedNumberMapper transformedNumberMapper;

    @Autowired
    TransformedNumberRepository transformedNumberRepository;

    public TransformedNumberDto createTransformedNumber(TransformedNumberCreateDto createDto) {
        TransformedNumber transformedNumber = transformedNumberMapper.toEntity(createDto);
        TransformedNumber savedTransformedNumber = transformedNumberRepository.save(transformedNumber);
        return transformedNumberMapper.toDto(savedTransformedNumber);
    }
}
