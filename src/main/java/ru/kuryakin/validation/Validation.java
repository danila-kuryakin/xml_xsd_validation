package ru.kuryakin.validation;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.xml.sax.SAXException;

/**
 * Main class for validation.
 */
@Service
public class Validation {

    /**
     * Method of getting result of validation.
     *
     * @param xmlFile - XML file
     * @param xsdFile - XSD file
     * @return - result of validation XML against XSD file
     */
    public String validate(MultipartFile xmlFile, MultipartFile xsdFile) {
        String msg = "";
        try {

            SchemaFactory factory = SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
            Schema schema = factory.newSchema(new StreamSource(xsdFile.getInputStream()));
            Validator validator = schema.newValidator();
            Source source = new StreamSource(xmlFile.getInputStream());
            validator.validate(source);
            msg = xmlFile.getOriginalFilename() + " is valid.";

        } catch (SAXException ex) {
            msg = xmlFile.getOriginalFilename() + " is not valid because " + ex.getMessage();
        } catch (IOException e) {
            e.printStackTrace();
            msg = "Server is broken. Try one else!\n ";
        }
        return msg;
    }

}
