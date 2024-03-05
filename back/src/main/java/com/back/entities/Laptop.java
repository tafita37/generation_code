package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Laptop {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_laptop;
public Integer getIdLaptop(){ return id_laptop; }
public void setIdLaptop(Integer o){ id_laptop=o; }
private String model;
public String getModel(){ return model; }
public void setModel(String o){ model=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_marque")
private Marque id_marque;
public Marque getIdMarque(){ return id_marque; }
public void setIdMarque(Marque o){ id_marque=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_processeur")
private Processeur id_processeur;
public Processeur getIdProcesseur(){ return id_processeur; }
public void setIdProcesseur(Processeur o){ id_processeur=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_ram")
private Ram id_ram;
public Ram getIdRam(){ return id_ram; }
public void setIdRam(Ram o){ id_ram=o; }
private Double taille_ecran;
public Double getTailleEcran(){ return taille_ecran; }
public void setTailleEcran(Double o){ taille_ecran=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_disque")
private Disque id_disque;
public Disque getIdDisque(){ return id_disque; }
public void setIdDisque(Disque o){ id_disque=o; }
private Double prix_achat;
public Double getPrixAchat(){ return prix_achat; }
public void setPrixAchat(Double o){ prix_achat=o; }
private Double prix_vente;
public Double getPrixVente(){ return prix_vente; }
public void setPrixVente(Double o){ prix_vente=o; }

    
}

