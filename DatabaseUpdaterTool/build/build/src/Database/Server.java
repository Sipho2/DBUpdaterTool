package Database;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


/**server instance class to separate logic from the database,
 * realised that some login parameters belong to the class/object server and not the database object.
 * The server handles who can login, and the database can only control access via permissions and other restrictions.
 */
public class Server {
private final String Server_Name, Password, User_Name, Server_Url;
private Server(Builder bob) {
	this.Server_Name = bob.ServerName;
	this.Password = bob.Password;
	this.User_Name = bob.UserName;
	this.Server_Url = "jdbc:sqlserver://" + bob.ServerName + ":1433;encrypt=true;trustServerCertificate=true";
}

public String getPassword() { return Password; }
public String getUserName() { return User_Name; }
public String getServerName() { return Server_Name; }
public String getServerUrl() {return Server_Url; }

public String Authenticate() {
	try (Connection connection = DriverManager.getConnection(this.getServerUrl(), this.getUserName(), this.getPassword())) {
		return "Successful Connection";
	} catch (SQLException e) {
		return e.toString();
	}
}

public static class Builder {
    private String Password;
    private String UserName;
    private String ServerName;

    public Builder setPassword(String Password) {
        this.Password = Password;
        return this;
    }

    public Builder setUserName(String UserName) {
        this.UserName = UserName;
        return this;
    }

    public Builder setServerName(String ServerName) {
        this.ServerName = ServerName;
        return this;
    }
    
    public Server build() {
        return new Server(this);
    }
}
}
