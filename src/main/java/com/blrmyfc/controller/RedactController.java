package com.blrmyfc.controller;


import com.blrmyfc.repos.XmlFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;


@RequestMapping("/redact")
@Controller
public class RedactController {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private XmlFileRepo xmlFileRepo;

    @GetMapping("{id}")
    public String showRedactWindow(@PathVariable String id, Model model){
//        model.addAttribute("id", id);

        StringBuilder contentBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(uploadPath+"/"+(xmlFileRepo.findById(Integer.parseInt(id)).get().getFileLink()))))
        {
            String sCurrentLine;
            while ((sCurrentLine = br.readLine()) != null)
            {
                contentBuilder.append(sCurrentLine).append("\n");
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        String s1 = contentBuilder.toString();


        model.addAttribute("id", s1);
        model.addAttribute("fileId", id);


        return "redact";
    }



    @GetMapping()
    public String showRedactWindowAll(Model model){

        model.addAttribute("greetingText", "Тыкайте по файлам на этой странице (НЕ СКАЧАТЬ)");

        return "redact";
    }




}
