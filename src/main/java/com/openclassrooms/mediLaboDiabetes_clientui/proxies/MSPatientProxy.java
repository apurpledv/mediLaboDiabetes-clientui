package com.openclassrooms.mediLaboDiabetes_clientui.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.openclassrooms.mediLaboDiabetes_clientui.beans.PatientBean;

@FeignClient(name="mediLaboDiabetes-api-gateway", contextId="ms-patient")
@RibbonClient(name="mediLaboDiabetes-ms-patient")
public interface MSPatientProxy {
    @GetMapping("/medilabodiabetes-ms-patient/patient/getAll")
    List<PatientBean> getPatients();

    @GetMapping("/medilabodiabetes-ms-patient/patient/get/{id}")
    PatientBean getPatient(@PathVariable int id);

    @PostMapping("/medilabodiabetes-ms-patient/patient/add")
    Boolean savePatient(@ModelAttribute PatientBean patient);

    @PostMapping("/medilabodiabetes-ms-patient/patient/update/{id}")
    Boolean updatePatient(@PathVariable int id, @ModelAttribute PatientBean patient);
}
