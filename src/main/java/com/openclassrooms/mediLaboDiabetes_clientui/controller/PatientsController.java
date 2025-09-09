package com.openclassrooms.mediLaboDiabetes_clientui.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.mediLaboDiabetes_clientui.beans.NoteBeanDTO;
import com.openclassrooms.mediLaboDiabetes_clientui.beans.PatientBean;
import com.openclassrooms.mediLaboDiabetes_clientui.proxies.MSNoteProxy;
import com.openclassrooms.mediLaboDiabetes_clientui.proxies.MSPatientProxy;
import com.openclassrooms.mediLaboDiabetes_clientui.proxies.MSRiskProxy;

import feign.FeignException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PatientsController {
    @Autowired
    MSPatientProxy patientProxy;

    @Autowired
    MSNoteProxy notesProxy;

    @Autowired
    MSRiskProxy riskProxy;

    @GetMapping("/patients")
    public String patientsView(Model model) {
        List<PatientBean> patientsList = patientProxy.getPatients();
        model.addAttribute("patientsList", patientsList);

        return "patients/patients-list";
    }

    @GetMapping("/patients/add")
    public String patientsAddView(Model model) {
        model.addAttribute("addedPatient", new PatientBean());

        return "patients/patients-add";
    }

    @PostMapping("/patients/add")
    public String patientsAddPost(@ModelAttribute PatientBean addedPatient, Model model) {
        patientProxy.savePatient(addedPatient);

        return "redirect:/patients";
    }

    @GetMapping("/patients/infos/{id}")
    public String patientsUpdateView(@PathVariable int id, Model model) {
        PatientBean placeholderPatient = patientProxy.getPatient(id);
        model.addAttribute("placeholderPatient", placeholderPatient);
        model.addAttribute("updatedPatient", new PatientBean());

        try {
            List<NoteBeanDTO> notesList = notesProxy.getNotesOfPatient(String.valueOf(id));
            for (NoteBeanDTO note : notesList)
                note.setNoteContent(note.getNoteContent().replaceAll("\n", "<br>\n"));

            model.addAttribute("notesList", notesList);

            int riskLevel = riskProxy.getRiskLevel(id);
            String riskLevelTitle;
            switch(riskLevel) {
                case 0: riskLevelTitle = "Aucun danger"; break;
                case 1: riskLevelTitle = "Risque limité"; break;
                case 2: riskLevelTitle = "Danger"; break;
                case 3: riskLevelTitle = "Apparition précoce"; break;
                default: riskLevelTitle = "Aucun danger";
            }
            model.addAttribute("riskLevel", riskProxy.getRiskLevel(id));
            model.addAttribute("riskLevelTitle", riskLevelTitle);

        } catch (FeignException e) {
            model.addAttribute("notesList", null);
            model.addAttribute("notesErrorMsg", "Service de notes indisponible.");
            log.error(e.toString());
        }

        return "patients/patients-infos";
    }
    
    @PostMapping("/patients/update/{id}")
    public String patientsUpdatePost(@PathVariable int id, @ModelAttribute("updatedPatient") PatientBean updatedPatient, Model model) {
        patientProxy.updatePatient(id, updatedPatient);

        return "redirect:/patients";
    }
}
