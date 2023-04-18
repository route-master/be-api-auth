package org.routemaster.api.auth;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/temp")
public class TempRestController {

    @Value("${temp-string}")
    private String tempString;

    @GetMapping
    public String temp() {
        return tempString;
    }
}
