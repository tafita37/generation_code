package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.MagasinUser;
import com.back.entities.MagasinUserRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/magasinUser")
@CrossOrigin("*")

public class MagasinUserController {
    @Autowired
private MagasinUserRepository repo;

    
    @PostMapping("/insertMagasinUser")
public MagasinUser insert(@RequestBody MagasinUser o){
    return repo.save(o);
}
@GetMapping("/allMagasinUser")
public Iterable<MagasinUser> getAllMagasinUser(){
    return repo.findAll();
}
@PostMapping("/updateMagasinUser")
public MagasinUser update(String idMagasinUser,String nomMagasinUser,String mdp,String role){
    MagasinUser o=new MagasinUser();
    o.setIdMagasinUser(Integer.parseInt(idMagasinUser));
    o.setNomMagasinUser(nomMagasinUser);o.setMdp(mdp);o.setRole(role);
    return repo.save(o);
}
@GetMapping("/deleteMagasinUserById")
public void delete(Integer idMagasinUser){
    repo.deleteById(idMagasinUser);
}

}

