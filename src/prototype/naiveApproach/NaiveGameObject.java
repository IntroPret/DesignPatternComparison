package prototype.naiveApproach;

public class NaiveGameObject {
    private String asset;

    public NaiveGameObject() {
        // Proses inisialisasi yang mahal diulang setiap kali
        try {
            Thread.sleep(1); // DITAMBAHKAN
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.asset = "HeavyAsset";
    }
}