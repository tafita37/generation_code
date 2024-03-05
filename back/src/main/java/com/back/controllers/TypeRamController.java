package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.TypeRam;
import com.back.entities.TypeRamRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/typeRam")
@CrossOrigin("*")

public class TypeRamController {
    @Autowired
private TypeRamRepository repo;

    
    @PostMapping("/insertTypeRam")
public TypeRam insert(@RequestBody TypeRam o){
    return repo.save(o);
}
@GetMapping("/allTypeRam")
public Iterable<TypeRam> getAllTypeRam(){
    return repo.findAll();
}
@PostMapping("/updateTypeRam")
public TypeRam update(String idTypeRam,String nomTypeRam){
    TypeRam o=new TypeRam();
    o.setIdTypeRam(Integer.parseInt(idTypeRam));
    o.setNomTypeRam(nomTypeRam);
    return repo.save(o);
}
@GetMapping("/deleteTypeRamById")
public void delete(Integer idTypeRam){
    repo.deleteById(idTypeRam);
}

}

