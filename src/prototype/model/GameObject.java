package prototype.model;

public class GameObject implements Cloneable {
    private String asset;

    public GameObject() {
        this.asset = "HeavyAsset";
    }

    @Override
    public GameObject clone() {
        try {
            return (GameObject) super.clone();
        } catch (CloneNotSupportedException e) {
            // Ini tidak akan pernah terjadi karena kelas ini mengimplementasikan Cloneable
            return null;
        }
    }
}