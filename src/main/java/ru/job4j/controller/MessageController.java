package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import ru.job4j.model.Message;
import ru.job4j.service.MessageService;
import ru.job4j.service.Service;
import ru.job4j.util.ReflectForPatching;
import javax.validation.Valid;
import java.lang.reflect.InvocationTargetException;
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
    public ResponseEntity<List<Message>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Message> getById(@PathVariable int id) {
        var message = service.getById(id);
        if (message.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message is not found");
        } else {
            return new ResponseEntity<>(message.get(), HttpStatus.OK);
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Message> save(@RequestBody Message message) {
        if (message.getPerson() == null) {
            throw new NullPointerException("Person not specified ");
        }
        if (message.getText().length() < 1) {
            throw new IllegalArgumentException("Text is empty");
        }
        return new ResponseEntity<>(
                this.service.save(message),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Message message) {
        if (message.getPerson() == null) {
            throw new NullPointerException("Person not specified ");
        }
        if (message.getText().length() < 1) {
            throw new IllegalArgumentException("Text is empty");
        }
        this.service.save(message);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Message message = new Message();
        message.setId(id);
        if (service.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Message is not found");
        }
        service.delete(message);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/")
    public ResponseEntity<Message> patchMessage(@Valid @RequestBody Message newMessage) throws InvocationTargetException, IllegalAccessException {
        var currentMessage = service.getById(newMessage.getId()).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        ReflectForPatching.reflect(currentMessage, newMessage);
        service.save(newMessage);
        return new ResponseEntity<>(
                this.service.save(newMessage),
                HttpStatus.CREATED
        );
    }
}
