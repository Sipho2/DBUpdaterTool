package Database;
import java.io.File;
import java.util.ArrayList;

public class ScriptsHandler {
	private final String FileLocation;
	
	private ScriptsHandler(Builder Bob) {
		this.FileLocation = Bob.FileLocation;
	}
	
	public String getFileLocation() { return FileLocation; }

	public ArrayList<String> getScripts() {
	       File directory = new File(this.FileLocation);
	       
	       File[] files = directory.listFiles();
	       ArrayList<String> scripts = new ArrayList<String>();

	       if (files != null) {
	         for (File file : files) {
	             if (file.getName().contains(".sql")){  
	                 scripts.add(file.getName());
	             }
	         }
	       }
	       return scripts;
	}
	
	public static class Builder {
	    private String FileLocation;
	    public Builder SetFileLocation(String FileLocation) {
	        this.FileLocation = FileLocation;
	        return this;
	    }
	    public ScriptsHandler build() {
	        return new ScriptsHandler(this);
	    }
	}

}