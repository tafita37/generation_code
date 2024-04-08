package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.Login;
import com.back.entities.LoginRepository;
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
@RequestMapping("/login")
@CrossOrigin("*")

public class LoginController {
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtService jwtService;

    @Autowired
private LoginRepository repo;

    
    @PostMapping("/insertLogin")
public Login insert(@RequestBody Login o){
    o.setMdp(passwordEncoder.encode(o.getMdp()));
    return repo.save(o);
}
@GetMapping("/allLogin/{pageNumber}")
public Iterable<Login> getAllLogin(@PathVariable int pageNumber){
    if(pageNumber<=0) {
        return repo.findAll();
    }
    return repo.findAll(PageRequest.of(pageNumber-1, 5));
}
@GetMapping("/countLoginOnePage")
public int getTotalPages() {
    long totalElements = repo.count();
    double div = (double) totalElements/(double) 5;
    return (int) Math.ceil(div);
}
@PostMapping("/updateLogin")
public Login update(String id,String mail,String mdp){
    Login o=new Login();
    o.setId(Integer.parseInt(id));
    o.setMail(mail);o.setMdp(mdp);
    return repo.save(o);
}
@GetMapping("/deleteLoginById")
public void delete(Integer id){
    repo.deleteById(id);
}
@PostMapping("/loginLogin")
public String login(@RequestBody Login o) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(o.getUsername(), o.getPassword())
    );
    var user = repo.findByMail(o.getUsername()).orElseThrow(); 
    return jwtService.generateToken(user);
}

}

