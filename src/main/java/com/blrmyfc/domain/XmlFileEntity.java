package com.blrmyfc.domain;


import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class XmlFileEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private String fileName;
    private String fileLink;



    private String valid;

    @Length(max = 4096)
    private String fileStrings;



    public String getValid() {
        return valid;
    }

    public void setValid(String valid) {
        this.valid = valid;
    }

    public String getFileStrings() {
        return fileStrings;
    }

    public void setFileStrings(String fileStrings) {
        this.fileStrings = fileStrings;
    }

    public XmlFileEntity() {
    }

    public XmlFileEntity(String fileName, String fileLink) {
        this.fileName = fileName;
        this.fileLink = fileLink;
    }



    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileLink() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink = fileLink;
    }
}
