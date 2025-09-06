package com.openclassrooms.mediLaboDiabetes_clientui.beans;

import lombok.Data;

@Data
public class PatientBean {
    int id;
    String firstName;
    String lastName;
    String birthDate;
    String gender;
    String address;
    String phone;

    public PatientBean() {}

    public PatientBean(String firstName, String lastName, String birthDate, String gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
    }
}
