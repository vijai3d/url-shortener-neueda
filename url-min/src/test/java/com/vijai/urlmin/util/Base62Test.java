package com.vijai.urlmin.util;

import org.junit.Assert;
import org.junit.Test;

public class Base62Test {

    @Test
    public void testEncode() throws Exception {
        String result = Base62.encode(123L);
        Assert.assertEquals("b9", result);
    }

    @Test
    public void testDecode() throws Exception {
        Long result = Base62.decode("b9");
        Assert.assertEquals(Long.valueOf(123), result);
    }
}
