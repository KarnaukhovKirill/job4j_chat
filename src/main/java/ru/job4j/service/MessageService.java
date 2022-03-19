package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.job4j.model.Message;
import ru.job4j.repository.MessageRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@org.springframework.stereotype.Service
public class MessageService implements Service<Message> {
    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<Message> getAll() {
        return StreamSupport.stream(
                messageRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Message> getById(int id) {
        return messageRepository.findById(id);
    }

    @Override
    public Message save(Message model) {
        return messageRepository.save(model);
    }

    @Override
    public void delete(Message model) {
        messageRepository.delete(model);
    }
}
