package com.vijai.urlmin.rest.v1;

import com.vijai.urlmin.service.UrlIdService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest/v1/url")
@RestController
public class UrlResource {

    @Autowired
    private UrlIdService urlIdService;

    @GetMapping("/{id}")
    public ResponseEntity getUrl(@PathVariable String id) {

        String url = urlIdService.getLongUrl(id);
        if (url == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(url);
    }

    @PostMapping
    public ResponseEntity create(@RequestBody String url) {

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
            String id = urlIdService.getShortUrl(url);
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}