package com.blrmyfc.logic;

import com.blrmyfc.domain.XmlFileEntity;
import com.blrmyfc.repos.XmlFileRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class XmlViewer {

    public String getStrings(String filename){

        String S="";
        try(FileReader reader = new FileReader(filename))
        {
            // читаем посимвольно
            int c;
            while((c=reader.read())!=-1){
                S+=(char)c;
            }
            return S;
        }
        catch(IOException ex){
            return "Не удалось записать файл";
        }
    }





}
