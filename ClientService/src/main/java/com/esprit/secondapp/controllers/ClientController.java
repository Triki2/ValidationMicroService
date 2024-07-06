package com.esprit.secondapp.controllers;

import java.util.List;
import java.util.Map;

import com.esprit.secondapp.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.esprit.secondapp.services.IClientService;

@RestController
@RequestMapping("/client")
public class ClientController {
	@Autowired
	private IClientService iClientService;
	
	 @GetMapping("/getAll")
	  public List<Client> getAllTutorials() {
		 return iClientService.getAllClient();
	    
	  }
	 
	 
	 @PostMapping("/save")
	 public Client save(@RequestBody Client client) {
		 return iClientService.addClient(client);
		 
	    
	  }
	 @DeleteMapping("/delete/{id}")
     public void supprimer(@PathVariable("id") String id){
		 iClientService.deleteClientById(id);

     }
     @GetMapping("/getbyid/{id}")
    public Client GetById(@PathVariable("id") String id){
        return iClientService.getClientById(id);
     }
     
     
     @PatchMapping("/update/{id}")
     public Client patchUpdateStock(@PathVariable String id, @RequestBody Map<String, Object> updates) {
         return iClientService.updateClient(id,  updates);

     }

}
