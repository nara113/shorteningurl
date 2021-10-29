package com.shorteningurl.common;

public final class Base62Util {
    private static final String BASE62 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    private static final int RADIX = 62;

    private Base62Util() {
    }

    public static String encode(long id) {
        final StringBuilder sb = new StringBuilder();

        while (id > 0) {
            sb.append(BASE62.charAt((int) id % RADIX));
            id /= RADIX;
        }

        return sb.toString();
    }

    public static long decode(String shortUrl) {
        long sum = 0;
        int power = 1;

        for (int i = 0; i < shortUrl.length(); i++) {
            sum += BASE62.indexOf(shortUrl.charAt(i)) * power;
            power *= RADIX;
        }

        return sum;
    }
}