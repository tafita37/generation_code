package com.back.entities;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity

public class Testss {
    @Id
@GeneratedValue(strategy=GenerationType.AUTO)
private Integer idtestss;
public Integer getIdtestss(){ return idtestss; }
public void setIdtestss(Integer o){ idtestss=o; }
private String nomtestss;
public String getNomtestss(){ return nomtestss; }
public void setNomtestss(String o){ nomtestss=o; }
@jakarta.persistence.ManyToOne
@jakarta.persistence.JoinColumn(name="idtest")
private Test idtest;
public Test getIdtest(){ return idtest; }
public void setIdtest(Test o){ idtest=o; }

    
}

