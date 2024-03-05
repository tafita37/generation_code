package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.TransfertLaptop;
import com.back.entities.TransfertLaptopRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/transfertLaptop")
@CrossOrigin("*")

public class TransfertLaptopController {
    @Autowired
private TransfertLaptopRepository repo;
@Autowired
private com.back.entities.PointVenteRepository repoIdPointVente;
@Autowired
private com.back.entities.LaptopRepository repoIdLaptop;

    
    @PostMapping("/insertTransfertLaptop")
public TransfertLaptop insert(@RequestBody TransfertLaptop o){
    return repo.save(o);
}
@GetMapping("/allTransfertLaptop")
public Iterable<TransfertLaptop> getAllTransfertLaptop(){
    return repo.findAll();
}
@PostMapping("/updateTransfertLaptop")
public TransfertLaptop update(String idTransfertLaptop,String idPointVente,String idLaptop,String quantiteLaptop,String dateTransfert,String etatTransfert){
    TransfertLaptop o=new TransfertLaptop();
    o.setIdTransfertLaptop(Integer.parseInt(idTransfertLaptop));
    o.setIdPointVente(repoIdPointVente.findById(Integer.parseInt(idPointVente)).orElseThrow());o.setIdLaptop(repoIdLaptop.findById(Integer.parseInt(idLaptop)).orElseThrow());o.setQuantiteLaptop(Double.parseDouble(quantiteLaptop));o.setDateTransfert(java.time.LocalDate.parse(dateTransfert));o.setEtatTransfert(Integer.parseInt(etatTransfert));
    return repo.save(o);
}
@GetMapping("/deleteTransfertLaptopById")
public void delete(Integer idTransfertLaptop){
    repo.deleteById(idTransfertLaptop);
}

}

