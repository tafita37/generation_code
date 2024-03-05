package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class MagasinUser {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_magasin_user;
public Integer getIdMagasinUser(){ return id_magasin_user; }
public void setIdMagasinUser(Integer o){ id_magasin_user=o; }
private String nom_magasin_user;
public String getNomMagasinUser(){ return nom_magasin_user; }
public void setNomMagasinUser(String o){ nom_magasin_user=o; }
private String mdp;
public String getMdp(){ return mdp; }
public void setMdp(String o){ mdp=o; }
private String role;
public String getRole(){ return role; }
public void setRole(String o){ role=o; }

    
}

