package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Testss;
import com.back.entities.TestssRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.data.domain.PageRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import com.JwtService;


import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

@RestController
@RequestMapping("/testss")
@CrossOrigin("*")

public class TestssController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Autowired
private TestssRepository repo;
@Autowired
private com.back.entities.TestRepository repoIdtest;

    
    @PostMapping("/insertTestss")
public Testss insert(@RequestBody Testss o){
    
    return repo.save(o);
}
@GetMapping("/allTestss/{pageNumber}")
public Iterable<Testss> getAllTestss(@PathVariable int pageNumber){
    if(pageNumber<=0) {
        return repo.findAll();
    }
    return repo.findAll(PageRequest.of(pageNumber-1, 5));
}
@GetMapping("/countTestssOnePage")
public int getTotalPages() {
    long totalElements = repo.count();
    double div = (double) totalElements/(double) 5;
    return (int) Math.ceil(div);
}
@PostMapping("/updateTestss")
public Testss update(String idtestss,String nomtestss,String idtest){
    Testss o=new Testss();
    o.setIdtestss(Integer.parseInt(idtestss));
    o.setNomtestss(nomtestss);o.setIdtest(repoIdtest.findById(Integer.parseInt(idtest)).orElseThrow());
    return repo.save(o);
}
@GetMapping("/deleteTestssById")
public void delete(Integer idtestss){
    repo.deleteById(idtestss);
}

}

