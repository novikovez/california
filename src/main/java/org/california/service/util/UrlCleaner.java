package org.california.service.util;

import org.springframework.stereotype.Service;

@Service
public class UrlCleaner {
    public static String cleanUrl(String url) {
        String cleanedUrl = url.replaceAll("([&?])srsltid[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])opi[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])sa[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])usg[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])ved[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])source[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])merchant_center[^\"&]*", "");
        cleanedUrl = cleanedUrl.replaceAll("([&?])utm_l[^\"&]*", "");
        url = cleanedUrl.replaceAll("\" aria-hidden=\"true", "");
        return url;
    }
}

