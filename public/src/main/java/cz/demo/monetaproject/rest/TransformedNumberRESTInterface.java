package cz.demo.monetaproject.rest;

import cz.demo.monetaproject.dto.TransformedNumberCreateDto;
import cz.demo.monetaproject.dto.TransformedNumberDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface TransformedNumberRESTInterface {

    @RequestMapping(value = "/numbers", method = RequestMethod.POST)
    ResponseEntity<TransformedNumberDto> createTransformedNumber(@RequestBody TransformedNumberCreateDto createDto);
}
