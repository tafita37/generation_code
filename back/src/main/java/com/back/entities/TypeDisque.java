package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class TypeDisque {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_type_disque;
public Integer getIdTypeDisque(){ return id_type_disque; }
public void setIdTypeDisque(Integer o){ id_type_disque=o; }
private String nom_type_disque;
public String getNomTypeDisque(){ return nom_type_disque; }
public void setNomTypeDisque(String o){ nom_type_disque=o; }

    
}

