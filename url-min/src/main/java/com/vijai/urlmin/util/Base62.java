package com.vijai.urlmin.util;

import java.util.ArrayList;
import java.util.List;

public class Base62 {

    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int BASE = ALPHABET.length();

    public static String encode(long nextNumber) {
        StringBuilder sb = new StringBuilder("");
        while (nextNumber > 0) {
            int reminder = (int) (nextNumber % BASE);
            sb.append(ALPHABET.charAt(reminder));
            nextNumber = nextNumber / BASE;
        }
        return sb.reverse().toString();
    }

    public static Long decode(String uniqueID) {
        List<Character> base62Number = new ArrayList<>();
        for (int i = 0; i < uniqueID.length(); ++i) {
            base62Number.add(uniqueID.charAt(i));
        }
        return convertBase62ToBase10ID(base62Number);
    }

    private static Long convertBase62ToBase10ID(List<Character> ids) {
        long id = 0L;
        int exp = ids.size() - 1;
        for (int i = 0; i < ids.size(); ++i, --exp) {
            int base10 = ALPHABET.indexOf(ids.get(i));
            id += (base10 * Math.pow(62.0, exp));
        }
        return id;
    }

}
