package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Marque {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_marque;
public Integer getIdMarque(){ return id_marque; }
public void setIdMarque(Integer o){ id_marque=o; }
private String nom_marque;
public String getNomMarque(){ return nom_marque; }
public void setNomMarque(String o){ nom_marque=o; }

    
}

