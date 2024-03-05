package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Laptop;
import com.back.entities.LaptopRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/laptop")
@CrossOrigin("*")

public class LaptopController {
    @Autowired
private LaptopRepository repo;
@Autowired
private com.back.entities.MarqueRepository repoIdMarque;
@Autowired
private com.back.entities.ProcesseurRepository repoIdProcesseur;
@Autowired
private com.back.entities.RamRepository repoIdRam;
@Autowired
private com.back.entities.DisqueRepository repoIdDisque;

    
    @PostMapping("/insertLaptop")
public Laptop insert(@RequestBody Laptop o){
    return repo.save(o);
}
@GetMapping("/allLaptop")
public Iterable<Laptop> getAllLaptop(){
    return repo.findAll();
}
@PostMapping("/updateLaptop")
public Laptop update(String idLaptop,String model,String idMarque,String idProcesseur,String idRam,String tailleEcran,String idDisque,String prixAchat,String prixVente){
    Laptop o=new Laptop();
    o.setIdLaptop(Integer.parseInt(idLaptop));
    o.setModel(model);o.setIdMarque(repoIdMarque.findById(Integer.parseInt(idMarque)).orElseThrow());o.setIdProcesseur(repoIdProcesseur.findById(Integer.parseInt(idProcesseur)).orElseThrow());o.setIdRam(repoIdRam.findById(Integer.parseInt(idRam)).orElseThrow());o.setTailleEcran(Double.parseDouble(tailleEcran));o.setIdDisque(repoIdDisque.findById(Integer.parseInt(idDisque)).orElseThrow());o.setPrixAchat(Double.parseDouble(prixAchat));o.setPrixVente(Double.parseDouble(prixVente));
    return repo.save(o);
}
@GetMapping("/deleteLaptopById")
public void delete(Integer idLaptop){
    repo.deleteById(idLaptop);
}

}

