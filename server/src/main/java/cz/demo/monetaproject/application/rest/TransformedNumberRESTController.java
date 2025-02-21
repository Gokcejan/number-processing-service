package cz.demo.monetaproject.application.rest;

import cz.demo.monetaproject.application.servicelayer.TransformedNumberService;
import cz.demo.monetaproject.dto.TransformedNumberCreateDto;
import cz.demo.monetaproject.dto.TransformedNumberDto;
import cz.demo.monetaproject.rest.TransformedNumberRESTInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class TransformedNumberRESTController implements TransformedNumberRESTInterface {

    @Autowired
    TransformedNumberService transformedNumberService;


    @Override
    public ResponseEntity<TransformedNumberDto> createTransformedNumber(TransformedNumberCreateDto createDto) {
        TransformedNumberDto response = transformedNumberService.createTransformedNumber(createDto);
        URI location = URI.create(String.format("/numbers/%d", response.getId()));

        return ResponseEntity.created(location).body(response);
    }
}
