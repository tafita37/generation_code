package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Processeur;
import com.back.entities.ProcesseurRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/processeur")
@CrossOrigin("*")

public class ProcesseurController {
    @Autowired
private ProcesseurRepository repo;

    
    @PostMapping("/insertProcesseur")
public Processeur insert(@RequestBody Processeur o){
    return repo.save(o);
}
@GetMapping("/allProcesseur")
public Iterable<Processeur> getAllProcesseur(){
    return repo.findAll();
}
@PostMapping("/updateProcesseur")
public Processeur update(String idProcesseur,String nomProcesseur,String frequence,String nbCoeur){
    Processeur o=new Processeur();
    o.setIdProcesseur(Integer.parseInt(idProcesseur));
    o.setNomProcesseur(nomProcesseur);o.setFrequence(Double.parseDouble(frequence));o.setNbCoeur(Double.parseDouble(nbCoeur));
    return repo.save(o);
}
@GetMapping("/deleteProcesseurById")
public void delete(Integer idProcesseur){
    repo.deleteById(idProcesseur);
}

}

