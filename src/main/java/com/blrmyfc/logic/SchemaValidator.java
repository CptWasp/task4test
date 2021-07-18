package com.blrmyfc.logic;


import com.blrmyfc.domain.XmlFileEntity;
import com.blrmyfc.repos.XmlFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;

public class SchemaValidator {


    @Autowired
    private XmlFileRepo xmlFileRepo;

    public static void validate(String xmlFile, String validationFile) throws SAXException, IOException {

        SchemaFactory schemaFactory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        ((schemaFactory.newSchema(new File(validationFile))).newValidator()).validate(new StreamSource(new File(xmlFile)));
    }




}