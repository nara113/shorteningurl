package com.shorteningurl.mapper;

import com.shorteningurl.domain.Url;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UrlMapper {
    Url selectUrl(String originalUrl);
    void updateCount(long urlId);
    void insertUrl(Url url);
    String selectOriginalUrl(long urlId);
}