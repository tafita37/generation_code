package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Ram {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer id_ram;
public Integer getIdRam(){ return id_ram; }
public void setIdRam(Integer o){ id_ram=o; }
private Double capacite;
public Double getCapacite(){ return capacite; }
public void setCapacite(Double o){ capacite=o; }
private Double frequence;
public Double getFrequence(){ return frequence; }
public void setFrequence(Double o){ frequence=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="id_type_ram")
private TypeRam id_type_ram;
public TypeRam getIdTypeRam(){ return id_type_ram; }
public void setIdTypeRam(TypeRam o){ id_type_ram=o; }

    
}

