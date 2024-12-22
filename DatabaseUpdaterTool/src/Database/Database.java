package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	private final Server Server;
	private final String Database_Name;
	private final String Db_Url;
	private Database(Builder bob) {
		this.Server = bob.Server;
		this.Database_Name = bob.Database_Name;
		this.Db_Url = "jdbc:sqlserver://" + bob.Server.getServerName() + ":1433;databaseName=" + bob.Database_Name + ";encrypt=true;trustServerCertificate=true";
	}
	
    public Server getDbServer() { return Server; }
    public String getDbName() { return Database_Name; }
    public String getDbUrl() { return Db_Url;}
    
    public static String ConnectToDB(Database Db) {
        try (Connection connection = DriverManager.getConnection(Db.getDbName(), Db.Server.getUserName(), Db.Server.getPassword())) {
        	return "Connection Successful";
        } catch (SQLException e) {
            return e.toString();
        }    	
    }
	public static class Builder {
	       private Server Server;
	       private String Database_Name;

	       public Builder setServerName(Server Server) {
	           this.Server = Server;
	           return this;
	       }

	       public Builder setDatabaseName(String Name) {
	    	   this.Database_Name = Name;
	    	   return this;
	       }
	       public Database build() {
	           return new Database(this);
	       }
	   }
}