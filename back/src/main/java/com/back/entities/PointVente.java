package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class PointVente {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_point_vente;
public Integer getIdPointVente(){ return id_point_vente; }
public void setIdPointVente(Integer o){ id_point_vente=o; }
private String nom_point_vente;
public String getNomPointVente(){ return nom_point_vente; }
public void setNomPointVente(String o){ nom_point_vente=o; }

    
}

