package jdbcModule6.dao;

import jdbcModule6.entity.Client;
import jdbcModule6.utils.ConnectionManager;
import jdbcModule6.utils.DaoException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientService {
    private static final ClientService INSTANCE = new ClientService();
    private static final String DELETE_SQL = """
            DELETE  FROM client WHERE id = ?;
            """;
    private static final String CREATE_SQL = """
            INSERT INTO client (name)
            VALUES (?);
            """;

    private static final String UPDATE_SQL = """
            UPDATE client
            SET name = ?
            WHERE id = ?;                
            """;

    private static final String GET_BY_ID_SQL = """
            SELECT id, name
            FROM client
            WHERE id = ?;                
            """;
    private static final String GET_ALL_SQL = """
            SELECT id, name 
            FROM client;                
            """;

    private ClientService() {
    }

    public Optional<Client> getById(long id){
        Client client = null;
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_BY_ID_SQL);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                client = buildClient(resultSet);
            }
            return Optional.ofNullable(client);
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public List<Optional<Client>> getAll(){
        List<Optional<Client>> result = new ArrayList<>();
       Client client = null;
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(GET_ALL_SQL);

            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                client = buildClient(resultSet);

                result.add(Optional.ofNullable(client));
            }
            return result;
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public void setName(long id, String name) {
        try(Connection connection = ConnectionManager.open()) {
            final Client clientById = getById(id).get();
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1,name);
            preparedStatement.setLong(2,clientById.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public void update(Client client) {
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SQL);
            preparedStatement.setString(1,client.getName());
            preparedStatement.setLong(2,client.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }
    public long create(String name) {
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE_SQL, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,name);

            preparedStatement.executeUpdate();
            ResultSet generatedKeys = preparedStatement.getGeneratedKeys();
            if (generatedKeys.next()){

            }
            return generatedKeys.getLong("id");
        } catch (SQLException e) {
            throw new DaoException(e);
        }
    }

    public boolean deleteById(Long id){
        try(Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_SQL);
            preparedStatement.setLong(1,id);

            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DaoException(e);
        }

    }
    public static ClientService getInstance(){
        return INSTANCE;
    }
    private static Client buildClient(ResultSet resultSet) throws SQLException {
        Client client;
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");

        client = new Client(id, name);
        return client;
    }
}
