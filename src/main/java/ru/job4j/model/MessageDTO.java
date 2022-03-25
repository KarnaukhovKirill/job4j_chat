package ru.job4j.model;

public class MessageDTO {
    private int personId;
    private String text;
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
