package com.liu.web;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by jam on 2017/1/22.
 */
@RestController
@Data
public class SampleController {
    @Value("${user.age}")
    private String age;
    @RequestMapping("/")
    String home() {
        return "Hello World!"+age;
    }
}
