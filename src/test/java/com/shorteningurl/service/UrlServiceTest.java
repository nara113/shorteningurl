package com.shorteningurl.service;

import com.shorteningurl.common.Base62Util;
import com.shorteningurl.domain.Url;
import com.shorteningurl.mapper.UrlMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
public class UrlServiceTest {
    @InjectMocks
    private UrlService urlService;

    @Mock
    private UrlMapper urlMapper;

    @DisplayName("URL 형식 불일치.")
    @Test
    void getShortUrlTest() {
        String originalUrl = "naver.com";

        Assertions.assertThrows(IllegalArgumentException.class, () -> urlService.getShortUrl(originalUrl));
    }

    @DisplayName("URL이 존재함.")
    @Test
    void getShortUrlExistTest() {
        long urlId = 1234;
        String originalUrl = "http://naver.com";
        Url url = Url.builder()
                .urlId(urlId)
                .originalUrl(originalUrl)
                .build();

        given(urlMapper.selectUrl(originalUrl)).willReturn(url);

        String shortUrl = urlService.getShortUrl(originalUrl);

        assertEquals(shortUrl, Base62Util.encode(urlId));

        then(urlMapper).should().selectUrl(originalUrl);
        then(urlMapper).should().updateCount(urlId);
        then(urlMapper).should(never()).insertUrl(any());
    }

    @DisplayName("URL이 존재하지 않음.")
    @Test
    void getShortUrlNotExistTest() {
        String originalUrl = "http://naver.com";

        given(urlMapper.selectUrl(originalUrl)).willReturn(null);

        urlService.getShortUrl(originalUrl);

        then(urlMapper).should().selectUrl(originalUrl);
        then(urlMapper).should(never()).updateCount(anyLong());
        then(urlMapper).should().insertUrl(any());
    }

    @DisplayName("URL이 존재하지 않음.")
    @Test
    void getOriginalUrlNotExistTest() {
        String shortUrl = "4T";
        long urlId = Base62Util.decode(shortUrl);

        given(urlMapper.selectOriginalUrl(urlId)).willReturn(null);

        Assertions.assertThrows(IllegalArgumentException.class, () -> urlService.getOriginalUrl(shortUrl));

        then(urlMapper).should().selectOriginalUrl(urlId);
    }

    @DisplayName("URL이 존재함.")
    @Test
    void getOriginalUrlExistTest() {
        String shortUrl = "4T";
        long urlId = Base62Util.decode(shortUrl);
        String originalUrl = "http://naver.com";

        given(urlMapper.selectOriginalUrl(urlId)).willReturn(originalUrl);

        String actual = urlService.getOriginalUrl(shortUrl);

        assertEquals(actual, originalUrl);

        then(urlMapper).should().selectOriginalUrl(urlId);
    }
}