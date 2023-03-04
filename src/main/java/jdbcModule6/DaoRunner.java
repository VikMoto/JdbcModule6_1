package jdbcModule6;


import jdbcModule6.dao.ClientService;
import jdbcModule6.entity.Client;

import java.util.List;
import java.util.Optional;

public class DaoRunner {
    public static void main(String[] args) {
      deleteTest(7L);

    }

    private static void ListAllTest() {
        ClientService clientService = ClientService.getInstance();
        List<Optional<Client>> allClients = clientService.getAll();

        for (Optional<Client> client : allClients) {
            System.out.println(client.get());
        }
    }

    private static void updateTest() {
        ClientService clientService = ClientService.getInstance();
        Optional<Client> clientDaoById = clientService.getById(2L);
        System.out.println("clientDaoById = " + clientDaoById.get());
        clientDaoById.ifPresent(client -> {
            client.setName("newName");
            clientService.update(client);
        });
        Optional<Client> clientUpdeted = clientService.getById(2L);
        System.out.println("clientDaoByIdUpd.get() = " + clientUpdeted.get());
    }
    private static void setNameTest(long id, String name) {
        ClientService clientService = ClientService.getInstance();

        Optional<Client> clientDaoById = clientService.getById(id);
        System.out.println("clientDaoByIdd = " + clientDaoById.get());

        clientDaoById.ifPresent(client -> {
            client.setName(name);
            clientService.setName(id,name);
        });

        Optional<Client> clientUpdeted = clientService.getById(id);
        System.out.println("clientDaoByIdUpd.get() = " + clientUpdeted.get());
    }

    private static void deleteTest(long id) {
        ClientService clientService = ClientService.getInstance();
        boolean deleteById = clientService.deleteById(id);
        System.out.println("delete = " + deleteById);
    }

    private static void createTest() {
        ClientService clientService = ClientService.getInstance();
        String name = "Gagagaga";
        long id = clientService.create(name);
        System.out.println("name = " + name);
        System.out.println("id = " + id);
    }
}
