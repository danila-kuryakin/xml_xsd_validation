package ru.kuryakin.validation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UploadController {

    @Autowired
    Validation validation;

    /**
     * Method for uploading XML and XSD files by path "/validate/form".
     *
     * @return JSP form
     */
    @RequestMapping("/validate/form")
    public ModelAndView showUpload() {
        return new ModelAndView("form");
    }

    /**
     * Method for uploading XML and XSD files and for getting result of validation.
     *
     * @param xmlFile - XML file
     * @param xsdFile - XSD file
     * @return - JSP page with results of validation.
     */
    @PostMapping("/validate/form")
    public ModelAndView fileUpload(@RequestParam("xmlFile") MultipartFile xmlFile,
                                   @RequestParam("xsdFile") MultipartFile xsdFile) {

        if (xmlFile.isEmpty() || xsdFile.isEmpty()) {
            return new ModelAndView("form", "message", "Please select a file and try again");
        } else if (!xmlFile.getOriginalFilename().matches("(.*)(\\.xml)")
                || !xsdFile.getOriginalFilename().matches("(.*)(\\.xsd)")) {
            return new ModelAndView("form", "message",
                    "Incorrect input data! Please select a file and try again");
        }

        return new ModelAndView("form", "message", validation.validate(xmlFile, xsdFile));
    }


}

