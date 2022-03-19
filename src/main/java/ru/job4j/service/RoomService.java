package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.model.Room;
import ru.job4j.repository.RoomRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class RoomService implements Service<Room> {
    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<Room> getAll() {
        return StreamSupport.stream(
                        roomRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Room> getById(int id) {
        return roomRepository.findById(id);
    }

    @Override
    public Room save(Room model) {
        return roomRepository.save(model);
    }

    @Override
    public void delete(Room model) {
        roomRepository.delete(model);
    }
}
