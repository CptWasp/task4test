package com.blrmyfc.logic;

import com.blrmyfc.domain.XmlFileEntity;
import com.blrmyfc.repos.XmlFileRepo;
import org.springframework.beans.factory.annotation.Autowired;

public class DBWriterFromFile {

    @Autowired
    public XmlFileRepo xmlFileRepo;

    public String saveStrings(int id, String text){
        String response="";

//            XmlFileEntity xmlFileEntity = xmlFileRepo.findOne(id);
            XmlFileEntity xmlFileEntity = xmlFileRepo.findById(id).get();
            xmlFileEntity.setValid("true");
            xmlFileEntity.setFileStrings(text);
            xmlFileRepo.save(xmlFileEntity);

        return response;
    }







}
