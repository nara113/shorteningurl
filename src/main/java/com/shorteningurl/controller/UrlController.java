package com.shorteningurl.controller;

import com.shorteningurl.service.UrlService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class UrlController {
    private static final String BASE_URL = "http://localhost:8080/short/";

    private final UrlService urlService;

    @PostMapping("/url")
    public String getShortUrl(@RequestParam String originalUrl) {

        return BASE_URL + urlService.getShortUrl(originalUrl);
    }

    @GetMapping("/short/{shortUrl}")
    public RedirectView getOriginalUrl(@PathVariable String shortUrl) {

        return new RedirectView(urlService.getOriginalUrl(shortUrl));
    }
}