package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.TypeDisque;
import com.back.entities.TypeDisqueRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/typeDisque")
@CrossOrigin("*")

public class TypeDisqueController {
    @Autowired
private TypeDisqueRepository repo;

    
    @PostMapping("/insertTypeDisque")
public TypeDisque insert(@RequestBody TypeDisque o){
    return repo.save(o);
}
@GetMapping("/allTypeDisque")
public Iterable<TypeDisque> getAllTypeDisque(){
    return repo.findAll();
}
@PostMapping("/updateTypeDisque")
public TypeDisque update(String idTypeDisque,String nomTypeDisque){
    TypeDisque o=new TypeDisque();
    o.setIdTypeDisque(Integer.parseInt(idTypeDisque));
    o.setNomTypeDisque(nomTypeDisque);
    return repo.save(o);
}
@GetMapping("/deleteTypeDisqueById")
public void delete(Integer idTypeDisque){
    repo.deleteById(idTypeDisque);
}

}

