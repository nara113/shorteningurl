package com.shorteningurl.common;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Base62UtilTest {

    @DisplayName("Base62 encode 테스트")
    @Test
    void encode() {
        int urlId = 1234;
        assertAll(() -> assertEquals(Base62Util.encode(urlId), "4T"),
                () -> assertEquals(Base62Util.encode(urlId), "4T"));
    }

    @DisplayName("Base62 decode 테스트")
    @Test
    void decode() {
        String shortUrl = "4T";
        assertAll(() -> assertEquals(Base62Util.decode(shortUrl), 1234),
                () -> assertEquals(Base62Util.decode(shortUrl), 1234));
    }
}