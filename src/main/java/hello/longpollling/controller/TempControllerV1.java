package hello.longpollling.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@RestController
@RequestMapping("/api")
public class TempControllerV1 {

    private final BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    @GetMapping("/getMessage")
    public ResponseEntity<String> getMessage() throws InterruptedException {
        String message = messageQueue.take();
        return ResponseEntity.ok(message);
    }

    @GetMapping("/sendMessage")
    public ResponseEntity<String> sendMessage(String message) {
        messageQueue.offer(message);
        return ResponseEntity.ok("Message sent: " + message);
    }
}
