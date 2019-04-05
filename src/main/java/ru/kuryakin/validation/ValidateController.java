package ru.kuryakin.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class ValidateController {

    @Autowired
    Validation validation;

    /**
     * It waits for POST request by path "/validate".
     *
     * @param xmlFile - XML file
     * @param xsdFile - XSD file
     * @return - JSON result of validation
     */
    @PostMapping("/validate")
    public String validate(@RequestParam("xml") MultipartFile xmlFile,
                           @RequestParam("xsd") MultipartFile xsdFile) {

        if (xmlFile.isEmpty() || xsdFile.isEmpty() || !xmlFile.getOriginalFilename()
                .matches("(.*)(\\.xml)") || !xsdFile.getOriginalFilename().matches("(.*)(\\.xsd)")) {

            return "{\"valid\" : \"incorrect input data!\"}";
        }

        return validation.validate(xmlFile, xsdFile).contains("is valid") ? "{\"valid\" : \"true\"}"
                : "{\"valid\" : \"false\"}";
    }

}