package hello.longpollling.controller;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;

import java.time.LocalDate;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@RequestMapping("/api")
public class TempControllerV3 {

    private final long TIME_OUT = 3000;
    private final ExecutorService executor = Executors.newFixedThreadPool(5);

    @GetMapping("/result")
    public DeferredResult<ResponseEntity<String>> getResult(@RequestParam(required = false) String message) {

        ResponseEntity<Object> pendingOnTimeOut = ResponseEntity.accepted().build();
        DeferredResult<ResponseEntity<String>> output = new DeferredResult<>(TIME_OUT, pendingOnTimeOut);

        executor.execute(() -> {
            try {
                Thread.sleep(1500L);
                System.out.println(Thread.currentThread().getName());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            MultiValueMap<String, String> header = new LinkedMultiValueMap<>();
            header.add(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE);

            output.setResult(new ResponseEntity<>(message + " : " + LocalDate.now(), header, HttpStatus.OK));
        });
        return output;
    }
}
