package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.PointVente;
import com.back.entities.PointVenteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/pointVente")
@CrossOrigin("*")

public class PointVenteController {
    @Autowired
private PointVenteRepository repo;

    
    @PostMapping("/insertPointVente")
public PointVente insert(@RequestBody PointVente o){
    return repo.save(o);
}
@GetMapping("/allPointVente")
public Iterable<PointVente> getAllPointVente(){
    return repo.findAll();
}
@PostMapping("/updatePointVente")
public PointVente update(String idPointVente,String nomPointVente){
    PointVente o=new PointVente();
    o.setIdPointVente(Integer.parseInt(idPointVente));
    o.setNomPointVente(nomPointVente);
    return repo.save(o);
}
@GetMapping("/deletePointVenteById")
public void delete(Integer idPointVente){
    repo.deleteById(idPointVente);
}

}

