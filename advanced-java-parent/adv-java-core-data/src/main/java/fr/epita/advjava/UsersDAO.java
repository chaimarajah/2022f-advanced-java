package fr.epita.advjava;

import fr.epita.advjava.datamodel.User;
import fr.epita.advjava.services.Configuration;
import fr.epita.advjava.services.exceptions.DatamodelCreationException;
import fr.epita.advjava.services.exceptions.DatamodelSearchException;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {


    private final DataSource source;

    public UsersDAO(DataSource source){
        this.source = source;
    }

    public void create(User user) throws DatamodelCreationException {
        try {
            Connection connection = source.getConnection();
            PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (?, ?)");
            insertStatement.setInt(1, user.getId());
            insertStatement.setString(2, user.getName());
            insertStatement.execute();
        }catch (Exception e){
            DatamodelCreationException creationException = new DatamodelCreationException();
            creationException.initCause(e);
            throw creationException;
        }
    }


    public List<User> search(User criteria) throws DatamodelSearchException {
        List<User> users = new ArrayList<User>();
        try ( Connection connection = source.getConnection();){

            PreparedStatement selectStatement =
                    connection.prepareStatement("SELECT ID,NAME FROM USERS WHERE ID = ? AND NAME = ?");
            selectStatement.setString(2, criteria.getName());
            selectStatement.setInt(1, criteria.getId());

            ResultSet resultSet = selectStatement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("ID");
                String name = resultSet.getString("NAME");
                User user = new User();
                user.setName(name);
                user.setId(id);
                users.add(user);
            }
        }catch (SQLException e){
            DatamodelSearchException dse = new DatamodelSearchException();
            dse.initCause(e);
            throw dse;
        }
        return users;
    }
}
