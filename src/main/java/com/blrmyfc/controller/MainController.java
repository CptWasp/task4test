package com.blrmyfc.controller;

import com.blrmyfc.domain.XmlFileEntity;
import com.blrmyfc.logic.ValidateFromDBAndSave;
import com.blrmyfc.repos.XmlFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class MainController {

    @Autowired
    private XmlFileRepo xmlFileRepo;

    @Value("${upload.path}")
    private String uploadPath;

    @GetMapping("/")
    public String entering(){

        return "redirect:/main";
    }


    @GetMapping("/main")
    public String greeting(Model model) {
        model.addAttribute("xmlFiles", xmlFileRepo.findAll());
        return "upload";
    }


    @GetMapping("/show")
    public String showFiles(Model model){
        Iterable<XmlFileEntity> xmlFileEntities = xmlFileRepo.findAll();
        model.addAttribute("Files", xmlFileEntities);

        return "show";
    }



    @PostMapping("/main")
    public String saveFileInDB(Model model, @RequestParam("file") MultipartFile file) throws IOException {

        if(file !=null){

            File uploadDir = new File(uploadPath);

            if(!uploadDir.exists()){
                uploadDir.mkdir();
            }

            String uuidFile = UUID.randomUUID().toString();
            String resultFileName = uuidFile + "." + file.getOriginalFilename();

            file.transferTo(new File(uploadPath+"/"+resultFileName));


            XmlFileEntity xmlFileEntity = new XmlFileEntity(file.getOriginalFilename(), resultFileName);
            xmlFileRepo.save(xmlFileEntity);
            model.addAttribute("xmlFiles", xmlFileRepo.findAll());

        }else{

        }


        return "upload";
    }




}
