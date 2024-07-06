package com.esprit.secondapp.services;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

import com.esprit.secondapp.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.esprit.secondapp.repository.ClientRepository;

@Service
public class ClientService implements IClientService {

	@Autowired
	private ClientRepository clientRepository;

	@Override
	public Client addClient(Client client) {
		clientRepository.save(client);
		return client;
	}

	@Override
	public Client updateClient(String id, Map<String, Object> client) {
		Client existingClient = clientRepository.findById(id).get();

		if (existingClient == null) {

			return null;
		}

		for (Map.Entry<String, Object> entry : client.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			try {
				Field field = existingClient.getClass().getDeclaredField(key);
				field.setAccessible(true);
				field.set(existingClient, value);
			} catch (NoSuchFieldException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return clientRepository.save(existingClient);
		 

	}

	@Override
	public List<Client> getAllClient() {
		return clientRepository.findAll();
	}

	@Override
	public Client getClientById(String id) {
		return clientRepository.findById(id).get();
	}

	@Override
	public void deleteClientById(String id) {
		clientRepository.deleteById(id);

	}

}
