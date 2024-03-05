package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class TransfertLaptop {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_transfert_laptop;
public Integer getIdTransfertLaptop(){ return id_transfert_laptop; }
public void setIdTransfertLaptop(Integer o){ id_transfert_laptop=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_point_vente")
private PointVente id_point_vente;
public PointVente getIdPointVente(){ return id_point_vente; }
public void setIdPointVente(PointVente o){ id_point_vente=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_laptop")
private Laptop id_laptop;
public Laptop getIdLaptop(){ return id_laptop; }
public void setIdLaptop(Laptop o){ id_laptop=o; }
private Double quantite_laptop;
public Double getQuantiteLaptop(){ return quantite_laptop; }
public void setQuantiteLaptop(Double o){ quantite_laptop=o; }
private java.time.LocalDate date_transfert;
public java.time.LocalDate getDateTransfert(){ return date_transfert; }
public void setDateTransfert(java.time.LocalDate o){ date_transfert=o; }
private Integer etat_transfert;
public Integer getEtatTransfert(){ return etat_transfert; }
public void setEtatTransfert(Integer o){ etat_transfert=o; }

    
}

