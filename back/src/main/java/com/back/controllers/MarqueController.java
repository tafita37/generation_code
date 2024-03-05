package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Marque;
import com.back.entities.MarqueRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/marque")
@CrossOrigin("*")

public class MarqueController {
    @Autowired
private MarqueRepository repo;

    
    @PostMapping("/insertMarque")
public Marque insert(@RequestBody Marque o){
    return repo.save(o);
}
@GetMapping("/allMarque")
public Iterable<Marque> getAllMarque(){
    return repo.findAll();
}
@PostMapping("/updateMarque")
public Marque update(String idMarque,String nomMarque){
    Marque o=new Marque();
    o.setIdMarque(Integer.parseInt(idMarque));
    o.setNomMarque(nomMarque);
    return repo.save(o);
}
@GetMapping("/deleteMarqueById")
public void delete(Integer idMarque){
    repo.deleteById(idMarque);
}

}

