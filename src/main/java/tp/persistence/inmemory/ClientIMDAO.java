package tp.persistence.inmemory;

import tp.model.client.Client;
import tp.persistence.repositories.client.ClientDAO;

public class ClientIMDAO extends BasicInMemoryRepository<Client> implements ClientDAO {
}
