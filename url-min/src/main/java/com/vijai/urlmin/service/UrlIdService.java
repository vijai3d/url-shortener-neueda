package com.vijai.urlmin.service;

import com.vijai.urlmin.util.Base62;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UrlIdService {

    public String getUniqueId() {
        final long nextNumber = Long.valueOf("" + System.currentTimeMillis());
        return Base62.encode(nextNumber);
    }

}
