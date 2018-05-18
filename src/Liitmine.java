import java.util.List;

public class Liitmine extends Ülesanne {
    private String tehe = "+";
    private int a;
    private int b;
    private int LiitmiseVastus;


    public Liitmine(int ülempiir) {
        super(ülempiir);
        a = (int) Math.round(Math.random() * (ülempiir-1) + 1);
        b = (int) Math.round(Math.random() * (ülempiir - a-1) + 1);
        LiitmiseVastus = a + b;
    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = ?";
    }

    @Override
    String getVastus() {
        return String.valueOf(LiitmiseVastus);
    }
}
