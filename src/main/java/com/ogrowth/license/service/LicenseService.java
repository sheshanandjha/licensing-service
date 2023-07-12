package com.ogrowth.license.service;

import com.ogrowth.license.model.License;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class LicenseService {

    Logger logger = LoggerFactory.getLogger(LicenseService.class);
    final MessageSource messageSource;

    public LicenseService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public License getLicense(String licenseId, String organizationId) {
        logger.info("LicenseService.getLicense: licenseId: {}, organizationId: {}", licenseId, organizationId);
        License license = new License();
        license.setId(new Random().nextInt(1000));
        license.setLicenseId(licenseId);
        license.setOrganizationId(organizationId);
        license.setDescription("Software product");
        license.setProductName("Ostock");
        license.setLicenseType("Full");
        return license;
    }

    public String createLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.create.message", null, locale)
                    ,license.toString());
        }
        logger.info("LicenseService.createLicense: responseMessage: {}", responseMessage);
        return responseMessage;
    }

    public String updateLicense(License license, String organizationId, Locale locale) {
        String responseMessage = null;
        if (license != null){
            license.setOrganizationId(organizationId);
            responseMessage = String.format(messageSource.getMessage("license.update.message", null, locale)
                    ,license.toString());
        }
        return responseMessage;
    }

    public String deleteLicense(String licenseId, String organizationId) {
        String responseMessage = null;
        responseMessage = String.format(messageSource.getMessage("license.delete.message", null, null)
                ,licenseId, organizationId);
        logger.info("LicenseService.createLicense: responseMessage: {}", responseMessage);
        return responseMessage;
    }
}
