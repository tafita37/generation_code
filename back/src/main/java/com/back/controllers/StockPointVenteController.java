package com.back.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.back.entities.StockPointVente;
import com.back.entities.StockPointVenteRepository;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/stockPointVente")
@CrossOrigin("*")

public class StockPointVenteController {
    @Autowired
private StockPointVenteRepository repo;
@Autowired
private com.back.entities.PointVenteRepository repoIdPointVente;
@Autowired
private com.back.entities.LaptopRepository repoIdLaptop;

    
    @PostMapping("/insertStockPointVente")
public StockPointVente insert(@RequestBody StockPointVente o){
    return repo.save(o);
}
@GetMapping("/allStockPointVente")
public Iterable<StockPointVente> getAllStockPointVente(){
    return repo.findAll();
}
@PostMapping("/updateStockPointVente")
public StockPointVente update(String idStockPointVente,String idPointVente,String idLaptop,String quantiteLaptopEntrant,String quantiteLaptopSortant,String dateMouvement,String prixAchat,String prixVente){
    StockPointVente o=new StockPointVente();
    o.setIdStockPointVente(Integer.parseInt(idStockPointVente));
    o.setIdPointVente(repoIdPointVente.findById(Integer.parseInt(idPointVente)).orElseThrow());o.setIdLaptop(repoIdLaptop.findById(Integer.parseInt(idLaptop)).orElseThrow());o.setQuantiteLaptopEntrant(Double.parseDouble(quantiteLaptopEntrant));o.setQuantiteLaptopSortant(Double.parseDouble(quantiteLaptopSortant));o.setDateMouvement(java.time.LocalDate.parse(dateMouvement));o.setPrixAchat(Double.parseDouble(prixAchat));o.setPrixVente(Double.parseDouble(prixVente));
    return repo.save(o);
}
@GetMapping("/deleteStockPointVenteById")
public void delete(Integer idStockPointVente){
    repo.deleteById(idStockPointVente);
}

}

