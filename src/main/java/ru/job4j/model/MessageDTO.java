package ru.job4j.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class MessageDTO {
    @NotNull(message = "Please, enter person's id")
    private int personId;
    @NotBlank(message = "Please, write text. Message must be non null")
    private String text;
    @NotNull(message = "Please, enter room's id")
    private int roomId;

    public MessageDTO() {
    }

    public MessageDTO(int personId, String text, int roomId) {
        this.personId = personId;
        this.text = text;
        this.roomId = roomId;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    @Override
    public String toString() {
        return "MessageDTO{"
                + "personId=" + personId
                + ", text='" + text + '\''
                + ", roomId=" + roomId
                + '}';
    }
}
