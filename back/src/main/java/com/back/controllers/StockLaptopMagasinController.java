package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.StockLaptopMagasin;
import com.back.entities.StockLaptopMagasinRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/stockLaptopMagasin")
@CrossOrigin("*")

public class StockLaptopMagasinController {
    @Autowired
private StockLaptopMagasinRepository repo;
@Autowired
private com.back.entities.LaptopRepository repoIdLaptop;

    
    @PostMapping("/insertStockLaptopMagasin")
public StockLaptopMagasin insert(@RequestBody StockLaptopMagasin o){
    return repo.save(o);
}
@GetMapping("/allStockLaptopMagasin")
public Iterable<StockLaptopMagasin> getAllStockLaptopMagasin(){
    return repo.findAll();
}
@PostMapping("/updateStockLaptopMagasin")
public StockLaptopMagasin update(String idStockLaptopMagasin,String idLaptop,String quantiteLaptopEntrant,String quantiteLaptopSortant,String dateMouvement,String prixAchat,String prixVente){
    StockLaptopMagasin o=new StockLaptopMagasin();
    o.setIdStockLaptopMagasin(Integer.parseInt(idStockLaptopMagasin));
    o.setIdLaptop(repoIdLaptop.findById(Integer.parseInt(idLaptop)).orElseThrow());o.setQuantiteLaptopEntrant(Double.parseDouble(quantiteLaptopEntrant));o.setQuantiteLaptopSortant(Double.parseDouble(quantiteLaptopSortant));o.setDateMouvement(java.time.LocalDate.parse(dateMouvement));o.setPrixAchat(Double.parseDouble(prixAchat));o.setPrixVente(Double.parseDouble(prixVente));
    return repo.save(o);
}
@GetMapping("/deleteStockLaptopMagasinById")
public void delete(Integer idStockLaptopMagasin){
    repo.deleteById(idStockLaptopMagasin);
}

}

