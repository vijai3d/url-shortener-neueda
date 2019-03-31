package com.vijai.urlmin.rest.v1;

import com.sun.tools.internal.ws.processor.model.Response;
import com.vijai.urlmin.service.UrlIdService;
import com.vijai.urlmin.exceptions.UnknownShortenedUrlException;
import org.apache.commons.validator.routines.UrlValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/rest/v1/url")
@RestController
public class UrlResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlResource.class);

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private UrlIdService urlIdService;

    @GetMapping("/{id}")
    public ResponseEntity getUrl(@PathVariable String id) {

        String url = redisTemplate.opsForValue().get(id);
        LOGGER.info("URL Retrieved: " + url);

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
            String id = urlIdService.getUniqueId();
            LOGGER.info("URL Id generated: "+ id);
            try {
                redisTemplate.opsForValue().set(id, url);
                LOGGER.info("URL saved in db.");
            } catch (RedisConnectionFailureException e) {
                LOGGER.error("Unable to save in db.", e);
            }
            return ResponseEntity.status(HttpStatus.OK).body(id);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
    }
}