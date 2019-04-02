package com.vijai.urlmin.rest.v1;

import com.vijai.urlmin.service.UrlIdService;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/rest/v1/url")
@RestController
public class UrlResource {

    @Autowired
    private UrlIdService urlIdService;

    @PostMapping("/get")
    public ResponseEntity getUrl(@RequestBody String id) {

        String url = urlIdService.getLongUrl(id);
        if (url == null) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(null);
        }
        Map<String, String> result = new HashMap<>();
        result.put("url", url);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PostMapping("/create")
    public ResponseEntity create(@RequestBody String url) {

        UrlValidator urlValidator = new UrlValidator(
                new String[]{"http", "https"}
        );

        if (urlValidator.isValid(url)) {
            String id = urlIdService.getShortUrl(url);
            Map<String, String> result = new HashMap<>();
            result.put("id", id);
            return ResponseEntity.status(HttpStatus.OK).body(result);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }


}