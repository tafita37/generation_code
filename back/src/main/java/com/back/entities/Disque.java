package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Disque {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_disque;
public Integer getIdDisque(){ return id_disque; }
public void setIdDisque(Integer o){ id_disque=o; }
private Double capacite;
public Double getCapacite(){ return capacite; }
public void setCapacite(Double o){ capacite=o; }
private Double vitesse;
public Double getVitesse(){ return vitesse; }
public void setVitesse(Double o){ vitesse=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_type_disque")
private TypeDisque id_type_disque;
public TypeDisque getIdTypeDisque(){ return id_type_disque; }
public void setIdTypeDisque(TypeDisque o){ id_type_disque=o; }

    
}

