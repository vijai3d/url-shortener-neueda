package com.vijai.urlmin.service;

import com.vijai.urlmin.util.Base62;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.RedisConnectionFailureException;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.annotation.RequestScope;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Service for urls getting and shortening. Request scope provides here thread safety and
 * helps in unique id generation.
 */
@Service
@RequestScope
public class UrlIdService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrlIdService.class);

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * Gets original url stored as value in db by id(key)
     * @param id - short url
     * @return long url
     */
    public String getLongUrl(String id) {
        String longUrl;
        try {
            longUrl = redisTemplate.opsForValue().get(id);
            LOGGER.debug("Url retrieved for id: " + id);
        } catch (RedisConnectionFailureException e) {
            LOGGER.error("There is no connection with database.", e);
            throw new RedisConnectionFailureException("Unable to connect to db", e);
        }
        return longUrl;
    }

    /**
     * Generates shortened url for long and stores in db
     * @param url - long url
     * @return - short url (id)
     */
    public String getShortUrl(@RequestBody String url) {
        String id = getUniqueId();
        LOGGER.info("URL Id generated: "+ id);
        try {
            redisTemplate.opsForValue().set(id, url);
            LOGGER.info("URL saved in db.");
        } catch (RedisConnectionFailureException e) {
            LOGGER.error("There is no connection with database.", e);
            throw new RedisConnectionFailureException("Unable to connect to db", e);
        }
        return id;
    }

    /**
     * Generates unique id. Salt is using to add more randomness in id.
     * @return String unique id.
     */
    private String getUniqueId() {
        final int salt = ThreadLocalRandom.current().nextInt(10, 99);
        final long uniqueNumber = Long.valueOf("" + salt + System.currentTimeMillis());
        return Base62.encode(uniqueNumber);
    }

}
