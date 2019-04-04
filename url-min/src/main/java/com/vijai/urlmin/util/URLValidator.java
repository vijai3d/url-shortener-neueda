package com.vijai.urlmin.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLValidator {
    public static final URLValidator INSTANCE = new URLValidator();
    private static final String URL_REGEX = "^(http:\\/\\/www\\.|https:\\/\\/www\\.|http:\\/\\/|https:\\/\\/)?[a-z0-9]+([\\-\\.]{1}[a-z0-9]+)*\\.[a-z]{2,5}(:[0-9]{1,5})?(\\/.*)?$";
    private static final String SHORT_URL = "^(http:\\/\\/sho.rt\\/)?[a-zA-Z0-9]{8}$";

    private static final Pattern URL_PATTERN = Pattern.compile(URL_REGEX);
    private static final Pattern SHORT_URL_PATTERN = Pattern.compile(SHORT_URL);

    private URLValidator() {
    }

    public boolean isValid(String url) {
        Matcher m = URL_PATTERN.matcher(url);
        return m.matches();
    }

    public boolean isValidShort(String shortUrl) {
        Matcher m = SHORT_URL_PATTERN.matcher(shortUrl);
        return m.matches();
    }
}
