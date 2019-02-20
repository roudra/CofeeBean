package rnr.home.panicreducer.controller;

import io.micrometer.core.annotation.Timed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@Timed
public class TestController {
    @RequestMapping(value = "/one", method = RequestMethod.GET)
    public String one() throws InterruptedException{
        Thread.sleep(10000);return "Method One";
    }

    @RequestMapping(value = "/two", method = RequestMethod.GET)
    public String two() {
        return "Method Two";
    }
}
