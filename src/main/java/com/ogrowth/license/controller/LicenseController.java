package com.ogrowth.license.controller;

import com.ogrowth.license.model.License;
import com.ogrowth.license.service.LicenseService;
import com.ogrowth.license.utils.UserContextHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping(value = "/v1/organization/{organizationId}/license")
public class LicenseController {

    private static final Logger logger = LoggerFactory.getLogger(LicenseController.class);

    private final LicenseService licenseService;

    public LicenseController(LicenseService licenseService) {
        this.licenseService = licenseService;
    }

    @GetMapping(value = "/{licenseId}")
    public ResponseEntity<License> getLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable("licenseId") String licenseId){
        logger.debug("LicenseController Correlation id: {}",
                UserContextHolder.getContext().getCorrelationId());
        License license = licenseService.getLicense(licenseId,organizationId);
        return ResponseEntity.ok(license);
    }

    @PutMapping(value = "/{licenseId}")
    public ResponseEntity<String> updateLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(licenseService.updateLicense(request, organizationId, locale));
    }

    @PostMapping(value = "/{licenseId}")
    public ResponseEntity<String> createLicense(
            @PathVariable("organizationId") String organizationId,
            @RequestBody License request,
            @RequestHeader(value = "Accept-Language", required = false) Locale locale){
        return ResponseEntity.ok(licenseService.createLicense(request, organizationId, locale));
    }

    @DeleteMapping(value = "/{licenseId}")
    public ResponseEntity<String> deleteLicense(
            @PathVariable("organizationId") String organizationId,
            @PathVariable ("licenseId") String licenseId){
        return ResponseEntity.ok(licenseService.deleteLicense(licenseId, organizationId));
    }

}
