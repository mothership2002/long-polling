package hello.longpollling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.concurrent.Callable;

@Controller
@RequestMapping("/long-polling")
public class TempControllerV2 {

    @GetMapping("/data")
    @ResponseBody
    public Callable<ResponseEntity<String>> getData() {
        return () -> {
            Thread.sleep(2000);
            String newData = "New data is available!";
            return ResponseEntity.ok(newData);
        };
    }
}