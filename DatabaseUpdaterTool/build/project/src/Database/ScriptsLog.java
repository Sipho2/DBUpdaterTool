package Database;

public class ScriptsLog{
	private String TableName;
	
	public ScriptsLog(String TblName) {
		this.TableName = TblName;
	}
	
	public String getTableName() {
		// TODO Auto-generated method stub
		return this.TableName;
	}
	
	public String insertRow() {
		// TODO Auto-generated method stub
		return "CREATE TABLE " +  this.TableName + "(Id INT PRIMARY KEY,Name VARCHAR(100) NOT NULL,Query VARCHAR(MAX) NOT NULL);";
	}

	public String fetchRows() {
		// TODO Auto-generated method stub
		return "SELECT Id,Name,Query FROM " + this.TableName;
	}
}