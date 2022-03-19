package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.job4j.model.Room;
import ru.job4j.service.RoomService;
import ru.job4j.service.Service;

import java.util.List;

@RestController
@RequestMapping("/room")
public class RoomController implements Controller<Room> {
    private final Service<Room> service;

    public RoomController(final RoomService roomService) {
        this.service = roomService;
    }

    @Override
    @GetMapping("/")
    public List<Room> getAll() {
        return service.getAll();
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable int id) {
        var message = service.getById(id);
        return new ResponseEntity<>(
                message.orElse(new Room()),
                message.isPresent() ? HttpStatus.OK : HttpStatus.NOT_FOUND
        );
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Room> save(@RequestBody Room room) {
        return new ResponseEntity<>(
                this.service.save(room),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        this.service.save(room);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Room room = new Room();
        room.setId(id);
        service.delete(room);
        return ResponseEntity.ok().build();
    }
}
