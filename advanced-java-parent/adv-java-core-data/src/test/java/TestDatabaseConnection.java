import fr.epita.advjava.UsersDAO;
import fr.epita.advjava.datamodel.User;
import fr.epita.advjava.services.exceptions.DatamodelCreationException;
import fr.epita.advjava.services.exceptions.DatamodelSearchException;
import org.junit.jupiter.api.*;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

public class TestDatabaseConnection {


    private static DriverManagerDataSource ds;
    private Connection connection;

    @BeforeAll
    public static void before(){
        ds = new DriverManagerDataSource("jdbc:h2:mem:test", "user","user");
    }


    @BeforeEach
    public void setup() throws SQLException {
        this.connection = DriverManager.getConnection("jdbc:h2:mem:test", "user", "user");
        PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS  USERS (ID INT, NAME VARCHAR(255))");
        preparedStatement.execute();
    }



    @Test
    public void testCreate() throws DatamodelCreationException, SQLException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO(ds);
        User user = new User();
        user.setId(1);
        user.setName("Thomas");


        //when
        dao.create(user);

        //then
        ResultSet resultSet = this.connection.prepareStatement("SELECT * FROM USERS WHERE ID = 1").executeQuery();
        String name = null;
        while (resultSet.next()){
            name = resultSet.getString("NAME");
        }

        Assertions.assertEquals(name, "Thomas");

    }    @Test
    public void testSearch() throws SQLException, DatamodelSearchException {

        //given (handled by setup())
        UsersDAO dao = new UsersDAO(ds);
        this.connection.prepareStatement("INSERT INTO USERS(ID, NAME) VALUES (1, 'Thomas')").execute();

        //when
        User criteria = new User();
        criteria.setId(1);
        criteria.setName("Thomas");
        List<User> users = dao.search(criteria);

        //then
        Assertions.assertEquals(users.size(), 1);


    }

    @AfterEach
    public void tearDown() throws SQLException {
        connection.prepareStatement("DROP TABLE USERS").execute();
        connection.close();
    }
}
