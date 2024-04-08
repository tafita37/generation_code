package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Test;
import com.back.entities.TestRepository;
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
@RequestMapping("/test")
@CrossOrigin("*")

public class TestController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Autowired
private TestRepository repo;

    
    @PostMapping("/insertTest")
public Test insert(@RequestBody Test o){
    
    return repo.save(o);
}
@GetMapping("/allTest/{pageNumber}")
public Iterable<Test> getAllTest(@PathVariable int pageNumber){
    if(pageNumber<=0) {
        return repo.findAll();
    }
    return repo.findAll(PageRequest.of(pageNumber-1, 5));
}
@GetMapping("/countTestOnePage")
public int getTotalPages() {
    long totalElements = repo.count();
    double div = (double) totalElements/(double) 5;
    return (int) Math.ceil(div);
}
@PostMapping("/updateTest")
public Test update(String idtest,String nomtest,String label){
    Test o=new Test();
    o.setIdtest(Integer.parseInt(idtest));
    o.setNomtest(nomtest);o.setLabel(label);
    return repo.save(o);
}
@GetMapping("/deleteTestById")
public void delete(Integer idtest){
    repo.deleteById(idtest);
}

}

