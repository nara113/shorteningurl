package com.shorteningurl.service;

import com.shorteningurl.common.Base62Util;
import com.shorteningurl.domain.Url;
import com.shorteningurl.mapper.UrlMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UrlService {
    private final UrlMapper urlMapper;

    @Transactional
    public String getShortUrl(String originalUrl) {
        if (!validateUrl(originalUrl)) {
            throw new IllegalArgumentException("url is invalid.");
        }

        Url url = urlMapper.selectUrl(originalUrl);

        if (url != null) {
            urlMapper.updateCount(url.getUrlId());

            return Base62Util.encode(url.getUrlId());
        }

        url = Url.builder()
                .originalUrl(originalUrl)
                .build();

        urlMapper.insertUrl(url);

        return Base62Util.encode(url.getUrlId());
    }

    public String getOriginalUrl(String shortUrl) {
        long urlId = Base62Util.decode(shortUrl);

        String originalUrl = urlMapper.selectOriginalUrl(urlId);

        if (originalUrl == null) {
            throw new IllegalArgumentException("url is not exist.");
        }

        return originalUrl;
    }

    private boolean validateUrl(String url) {
        return UrlValidator.getInstance().isValid(url) && StringUtils.isNotBlank(url);
    }
}