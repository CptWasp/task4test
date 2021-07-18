package com.blrmyfc.controller;


import com.blrmyfc.domain.XmlFileEntity;
import com.blrmyfc.logic.SchemaValidator;
import com.blrmyfc.logic.ValidateFromDBAndSave;
import com.blrmyfc.logic.XmlViewer;
import com.blrmyfc.repos.XmlFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

//@Controller
@RestController
public class ShowController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private XmlFileRepo xmlFileRepo;

    @PostMapping("/validate")
    public String validateFiles(@RequestParam("xmls") String xmls, @RequestParam("xsds") String xsds, Model model){

        SchemaValidator schemaValidator = new SchemaValidator();

        boolean flag = true;
        try{
            schemaValidator.validate(uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(xmls)).get().getFileLink()),
                    uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(xsds)).get().getFileLink()));
//            schemaValidator.validate("./upload-dir/students.xml",
//                    "./upload-dir/students.xsd");


        }catch (SAXException e){
            flag=false;
            e.printStackTrace();
        }catch (IOException e){
            flag=false;
            e.printStackTrace();
        }catch (NullPointerException e){
            flag=false;
            e.printStackTrace();
        }

        if(flag){
            XmlFileEntity xmlFileEntity = xmlFileRepo.findById(Integer.parseInt(xmls)).get();
            xmlFileEntity.setValid("true");
            XmlViewer xmlViewer = new XmlViewer();
            xmlFileEntity.setFileStrings(xmlViewer.getStrings(uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(xmls)).get().getFileLink())));
            xmlFileRepo.save(xmlFileEntity);


        }else {
            xmlFileRepo.findById(Integer.parseInt(xmls)).get().setValid("false");
        }

        return "Валидация: "+flag;
//        return "Воть (xmls)(xsds)==>"+xmls+xsds+"+_=-=-=-"+uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(xmls)).get().getFileLink() )+"-------"+uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(xsds)).get().getFileLink());
    }



    @GetMapping("/soli")
    public String soli(){

        return new ValidateFromDBAndSave(xmlFileRepo).printXmlFileStringFromDB();
//        return xmlFileRepo.findById(13).get().getFileStrings();
    }


    @PostMapping("/redact")
    public String redactFile(@RequestParam String id, @RequestParam String text){

//        XmlFileEntity xmlFileEntity = new XmlFileEntity();

        String filename = uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(id)).get().getFileLink());

        File fold=new File(filename);
        fold.delete();
        File fnew=new File(filename);
        System.out.println(text);

        try {
            FileWriter f2 = new FileWriter(fnew, false);
            f2.write(text);
            f2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }



        return "файл отредактирован!";
    }



}
