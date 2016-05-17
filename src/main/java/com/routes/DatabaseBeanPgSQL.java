package com.routes;
        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;

        import javax.sql.DataSource;
        import java.sql.ResultSet;
        import java.sql.SQLException;
        import java.sql.Statement;


/**
 * Created by VanHelsing on 17.05.2016.
 */

public class DatabaseBeanPgSQL {
    private DataSource dataSource;
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseBeanPgSQL.class);

    public DatabaseBeanPgSQL(){}

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void create() throws SQLException {
        Statement sta = dataSource.getConnection().createStatement();
        try {
            //TODO _ Create Order in Database
            sta.executeUpdate("INSERT SOMETHING");
            //sta.executeUpdate("CREATE SEQUENCE order_id_seq;");
            //sta.executeUpdate("CREATE TABLE orders ( id INT NOT NULL PRIMARY KEY DEFAULT nextval('order_id_seq'), item VARCHAR(50), amount INT, description VARCHAR(300), processed BOOLEAN, consumed BOOLEAN);");
            LOGGER.info("Order has been saved");
        } catch (SQLException e) {
            LOGGER.info("Table orders already exists");
        }
    }

    public String getDataFromCustomer(String customer) throws SQLException{
        Statement sta = dataSource.getConnection().createStatement();
        //TODO hier SELECT!!
        ResultSet rs = sta.executeQuery("SELECT * FROM customers WHERE columnfoo = 500");
        String customerName = "";
        while (rs.next())
        {
            System.out.print("Column 1 returned ");
            System.out.println(rs.getString(1));
            customerName = rs.getString(1);
        } rs.close();
        sta.close();
        return customerName;
    }

    public void destroy() throws SQLException {
        dataSource.getConnection().close();
    }
}