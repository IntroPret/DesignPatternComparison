package singleton.patternApproach;

//DatabaseConnectionSingleton.java (Pattern)
public class DatabaseConnectionSingleton {
	 private static DatabaseConnectionSingleton instance;
	
	 private DatabaseConnectionSingleton() {
	     try {
	         Thread.sleep(1); // DITAMBAHKAN
	     } catch (InterruptedException e) {
	         e.printStackTrace();
	     }
	 }
	
	 public static synchronized DatabaseConnectionSingleton getInstance() {
	     if (instance == null) {
	         instance = new DatabaseConnectionSingleton();
	     }
	     return instance;
	 }
}
