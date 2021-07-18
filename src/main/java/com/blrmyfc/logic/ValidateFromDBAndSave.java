package com.blrmyfc.logic;

import com.blrmyfc.repos.XmlFileRepo;
import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;

@Service
public class ValidateFromDBAndSave {

    private final XmlFileRepo xmlFileRepo;

    @Autowired
    public ValidateFromDBAndSave(XmlFileRepo xmlFileRepo) {
        this.xmlFileRepo = xmlFileRepo;
    }

    public String printXmlFileStringFromDB(){

        String xmlFileString="";

        xmlFileString=xmlFileRepo.findById(13).get().getFileStrings();

        return xmlFileString;
    }

}
