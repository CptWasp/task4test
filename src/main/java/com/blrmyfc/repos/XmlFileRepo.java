package com.blrmyfc.repos;

import com.blrmyfc.domain.XmlFileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
public interface XmlFileRepo extends JpaRepository<XmlFileEntity, Integer> {


    XmlFileEntity findById(Long id);

//    XmlFileEntity findOne(int id);


//    XmlFileEntity findByFilename(String filename);

}
