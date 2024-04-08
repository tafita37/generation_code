package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Test {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer idtest;
public Integer getIdtest(){ return idtest; }
public void setIdtest(Integer o){ idtest=o; }
private String nomtest;
public String getNomtest(){ return nomtest; }
public void setNomtest(String o){ nomtest=o; }
private String label;
public String getLabel(){ return label; }
public void setLabel(String o){ label=o; }

    
}

