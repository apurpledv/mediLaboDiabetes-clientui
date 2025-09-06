package com.openclassrooms.mediLaboDiabetes_clientui.beans;

import lombok.Data;

@Data
public class NoteBean {
    String id;
    String patientId;
    String patientLastName;
    String noteContent;

    public NoteBean() {}

    public NoteBean(String id, String patientId, String patientLastName, String noteContent) {
        this.id = id;
        this.patientId = patientId;
        this.patientLastName = patientLastName;
        this.noteContent = noteContent;
    }

}
