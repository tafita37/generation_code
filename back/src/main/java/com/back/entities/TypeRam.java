package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class TypeRam {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_type_ram;
public Integer getIdTypeRam(){ return id_type_ram; }
public void setIdTypeRam(Integer o){ id_type_ram=o; }
private String nom_type_ram;
public String getNomTypeRam(){ return nom_type_ram; }
public void setNomTypeRam(String o){ nom_type_ram=o; }

    
}

