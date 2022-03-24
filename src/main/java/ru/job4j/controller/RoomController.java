package ru.job4j.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
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
    public ResponseEntity<List<Room>> getAll() {
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<Room> getById(@PathVariable int id) {
        var room = service.getById(id);
        if (room.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room is not found");
        } else {
            return new ResponseEntity<>(room.get(), HttpStatus.OK);
        }
    }

    @Override
    @PostMapping("/")
    public ResponseEntity<Room> save(@RequestBody Room room) {
        if (room.getName().length() < 1) {
            throw new NullPointerException("Room's name is empty");
        }
        return new ResponseEntity<>(
                this.service.save(room),
                HttpStatus.CREATED
        );
    }

    @Override
    @PutMapping("/")
    public ResponseEntity<Void> update(@RequestBody Room room) {
        if (room.getName().length() < 1) {
            throw new NullPointerException("Room's name is empty");
        }
        this.service.save(room);
        return ResponseEntity.ok().build();
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        Room room = new Room();
        room.setId(id);
        if (service.getById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Room is not found");
        }
        service.delete(room);
        return ResponseEntity.ok().build();
    }
}
