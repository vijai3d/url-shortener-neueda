package com.vijai.urlmin.service;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;


/**
 * Check if all db request are made.
 */
public class UrlIdServiceTest {

    @Mock
    StringRedisTemplate redisTemplate;
    @Mock
    ValueOperations<String, String> ops;

    @InjectMocks
    UrlIdService urlIdService;

    private static final String LONG_URL="http://www.google.com/...W1234sx!@?&";

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetLongUrl() throws Exception {
        when(redisTemplate.opsForValue()).thenReturn(ops);
        when(ops.get(anyString())).thenReturn(LONG_URL);
        String result = urlIdService.getLongUrl("1234FGds");
        assertEquals(result, LONG_URL);
    }

    @Test
    public void testGetShortUrl() throws Exception {
        when(redisTemplate.hasKey(anyString())).thenReturn(false);
        when(redisTemplate.opsForValue()).thenReturn(ops);
        doNothing().when(ops).set(anyString(), anyString());
        urlIdService.getShortUrl(LONG_URL);
        verify(ops, times(2)).set(anyString(), anyString());
    }
}