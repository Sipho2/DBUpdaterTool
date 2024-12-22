package application;
/*example for object creation
Connection connection1 = new Connection.Builder()
.setServerName("Server1")
.setDatabaseName("DB1")
.setUser("admin")
.setPassword("password123")
.setScriptsFolder("C:/scripts")
.setScriptsLogTable("UpdateLog")
.build();*/

/*Why Use the Builder Pattern?

-Readability: Object creation code is clean and readable.
-Flexibility: You don’t need to pass all parameters; only required ones.
-Extensibility: Easy to add new fields without breaking existing code.
Immutability
By making fields private final and avoiding setters, your class becomes immutable, which is a best practice for thread safety and maintainability. The builder pattern ensures values are set only once.
*/

public class Connection {
    private final String serverName;
    private final String databaseName;
    private final String user;
    private final String password;
    private final String scriptsFolder;
    private final String scriptsLogTable;

    // Private constructor
    private Connection(Builder builder) {
        this.serverName = builder.serverName;
        this.databaseName = builder.databaseName;
        this.user = builder.user;
        this.password = builder.password;
        this.scriptsFolder = builder.scriptsFolder;
        this.scriptsLogTable = builder.scriptsLogTable;
    }

    // Getters
    public String getServerName() { return serverName; }
    public String getDatabaseName() { return databaseName; }
    public String getUser() { return user; }
    public String getPassword() { return password; }
    public String getScriptsFolder() { return scriptsFolder; }
    public String getScriptsLogTable() { return scriptsLogTable; }

    // Builder Class From the builder pattern, shortens the list of parameters in a constructor. Makes object creation cleaner and easier to read.
    public static class Builder {
        private String serverName;
        private String databaseName;
        private String user;
        private String password;
        private String scriptsFolder;
        private String scriptsLogTable;

        public Builder setServerName(String serverName) {
            this.serverName = serverName;
            return this;
        }

        public Builder setDatabaseName(String databaseName) {
            this.databaseName = databaseName;
            return this;
        }

        public Builder setUser(String user) {
            this.user = user;
            return this;
        }

        public Builder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Builder setScriptsFolder(String scriptsFolder) {
            this.scriptsFolder = scriptsFolder;
            return this;
        }

        public Builder setScriptsLogTable(String scriptsLogTable) {
            this.scriptsLogTable = scriptsLogTable;
            return this;
        }

        public Connection build() {
            return new Connection(this);
        }
    }

	@Override
	public String toString() {
		return "Connection [serverName=" + serverName + ", databaseName=" + databaseName + ", user=" + user
				+ ", password=" + password + ", scriptsFolder=" + scriptsFolder + ", scriptsLogTable=" + scriptsLogTable
				+ "]";
	}
}
