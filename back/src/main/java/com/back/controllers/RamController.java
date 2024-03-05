package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Ram;
import com.back.entities.RamRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/ram")
@CrossOrigin("*")

public class RamController {
    @Autowired
private RamRepository repo;
@Autowired
private com.back.entities.TypeRamRepository repoIdTypeRam;

    
    @PostMapping("/insertRam")
public Ram insert(@RequestBody Ram o){
    return repo.save(o);
}
@GetMapping("/allRam")
public Iterable<Ram> getAllRam(){
    return repo.findAll();
}
@PostMapping("/updateRam")
public Ram update(String idRam,String capacite,String frequence,String idTypeRam){
    Ram o=new Ram();
    o.setIdRam(Integer.parseInt(idRam));
    o.setCapacite(Double.parseDouble(capacite));o.setFrequence(Double.parseDouble(frequence));o.setIdTypeRam(repoIdTypeRam.findById(Integer.parseInt(idTypeRam)).orElseThrow());
    return repo.save(o);
}
@GetMapping("/deleteRamById")
public void delete(Integer idRam){
    repo.deleteById(idRam);
}

}

