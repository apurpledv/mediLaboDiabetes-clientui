package com.openclassrooms.mediLaboDiabetes_clientui.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="mediLaboDiabetes-api-gateway", contextId="ms-risk")
@RibbonClient(name="mediLaboDiabetes-ms-risk")
public interface MSRiskProxy {
    @GetMapping("/medilabodiabetes-ms-risk/riskLevel/get/{id}")
    int getRiskLevel(@PathVariable int id);
}
