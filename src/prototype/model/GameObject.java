package prototype.model;

public class GameObject implements Cloneable {
    private String asset;

    public GameObject() {
        // Simulasi proses inisialisasi yang mahal
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.asset = "HeavyAsset";
    }

    @Override
    public GameObject clone() {
        try {
            return (GameObject) super.clone();
        } catch (CloneNotSupportedException e) {
            return null;
        }
    }
}