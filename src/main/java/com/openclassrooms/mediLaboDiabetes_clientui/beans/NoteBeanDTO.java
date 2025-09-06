package com.openclassrooms.mediLaboDiabetes_clientui.beans;

import lombok.Data;

@Data
public class NoteBeanDTO {
    String id;
    String noteContent;

    public NoteBeanDTO() {}

    public NoteBeanDTO(String id, String noteContent) {
        this.id = id;
        this.noteContent = noteContent;
    }
}
