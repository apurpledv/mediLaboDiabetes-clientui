package com.openclassrooms.mediLaboDiabetes_clientui.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.mediLaboDiabetes_clientui.beans.NoteBean;
import com.openclassrooms.mediLaboDiabetes_clientui.beans.NoteBeanDTO;

@FeignClient(name="mediLaboDiabetes-api-gateway", contextId="ms-notes")
@RibbonClient(name="mediLaboDiabetes-ms-notes")
public interface MSNoteProxy {
    @GetMapping("/medilabodiabetes-ms-notes/note/getNotesOfPatientDTO/{patientId}")
    List<NoteBeanDTO> getNotesOfPatient(@PathVariable String patientId);

    @GetMapping("/medilabodiabetes-ms-notes/note/get/{id}")
    NoteBean getNote(@PathVariable String id);

    @PostMapping("/medilabodiabetes-ms-notes/note/add")
    boolean addNote(@ModelAttribute NoteBean note);

    @PostMapping("/medilabodiabetes-ms-notes/note/update/{id}")
    boolean updateNote(@PathVariable String id, @ModelAttribute NoteBean note);
}
