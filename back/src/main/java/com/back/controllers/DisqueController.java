package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Disque;
import com.back.entities.DisqueRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/disque")
@CrossOrigin("*")

public class DisqueController {
    @Autowired
private DisqueRepository repo;
@Autowired
private com.back.entities.TypeDisqueRepository repoIdTypeDisque;

    
    @PostMapping("/insertDisque")
public Disque insert(@RequestBody Disque o){
    return repo.save(o);
}
@GetMapping("/allDisque")
public Iterable<Disque> getAllDisque(){
    return repo.findAll();
}
@PostMapping("/updateDisque")
public Disque update(String idDisque,String capacite,String vitesse,String idTypeDisque){
    Disque o=new Disque();
    o.setIdDisque(Integer.parseInt(idDisque));
    o.setCapacite(Double.parseDouble(capacite));o.setVitesse(Double.parseDouble(vitesse));o.setIdTypeDisque(repoIdTypeDisque.findById(Integer.parseInt(idTypeDisque)).orElseThrow());
    return repo.save(o);
}
@GetMapping("/deleteDisqueById")
public void delete(Integer idDisque){
    repo.deleteById(idDisque);
}

}

