package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Message;
import ru.job4j.service.MessageService;
import ru.job4j.service.Service;
import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController implements Controller<Message> {
    private final Service<Message> service;

    public MessageController(final MessageService messageService) {
        this.service = messageService;
    }

    @Override
    @GetMapping("/")
    public List<Message> getAll() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable int id) {
        var message = service.getById(id);
        return new ResponseEntity<>(
                message.orElse(new Message()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody Message message) {
        return new ResponseEntity<>(
                this.service.save(message),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        this.service.save(message);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Message message = new Message();
        message.setId(id);
        service.delete(message);
        return ResponseEntity.ok().build();
    }
}
