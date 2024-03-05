package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class StockPointVente {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_stock_point_vente;
public Integer getIdStockPointVente(){ return id_stock_point_vente; }
public void setIdStockPointVente(Integer o){ id_stock_point_vente=o; }
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
private Double quantite_laptop_entrant;
public Double getQuantiteLaptopEntrant(){ return quantite_laptop_entrant; }
public void setQuantiteLaptopEntrant(Double o){ quantite_laptop_entrant=o; }
private Double quantite_laptop_sortant;
public Double getQuantiteLaptopSortant(){ return quantite_laptop_sortant; }
public void setQuantiteLaptopSortant(Double o){ quantite_laptop_sortant=o; }
private java.time.LocalDate date_mouvement;
public java.time.LocalDate getDateMouvement(){ return date_mouvement; }
public void setDateMouvement(java.time.LocalDate o){ date_mouvement=o; }
private Double prix_achat;
public Double getPrixAchat(){ return prix_achat; }
public void setPrixAchat(Double o){ prix_achat=o; }
private Double prix_vente;
public Double getPrixVente(){ return prix_vente; }
public void setPrixVente(Double o){ prix_vente=o; }

    
}

