package singleton.naiveApproach;

//DatabaseConnection.java (Naive)
public class DatabaseConnection {
	 public DatabaseConnection() {
	     // Simulate initialization delay
	     try {
	         Thread.sleep(1); // DITAMBAHKAN
	     } catch (InterruptedException e) {
	         e.printStackTrace();
	     }
	 }
}