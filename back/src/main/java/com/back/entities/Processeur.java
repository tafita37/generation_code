package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Processeur {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_processeur;
public Integer getIdProcesseur(){ return id_processeur; }
public void setIdProcesseur(Integer o){ id_processeur=o; }
private String nom_processeur;
public String getNomProcesseur(){ return nom_processeur; }
public void setNomProcesseur(String o){ nom_processeur=o; }
private Double frequence;
public Double getFrequence(){ return frequence; }
public void setFrequence(Double o){ frequence=o; }
private Double nb_coeur;
public Double getNbCoeur(){ return nb_coeur; }
public void setNbCoeur(Double o){ nb_coeur=o; }

    
}

