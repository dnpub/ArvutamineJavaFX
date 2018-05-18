import java.io.Console;
import java.util.Random;

public class Jagamine extends Ülesanne {
    private String tehe = "/";
    private int a;
    private int b;
    private int JagamiseVastus;

    public Jagamine(int ülempiir) {
        super(ülempiir);
        Random random = new Random();
        boolean k = true;
        while (k) {
            JagamiseVastus = (int) Math.round(random.nextGaussian() * 50 + 0.3 * ülempiir);
            b = (int) Math.round(Math.random() * (ülempiir * 0.5) + 2);
            // b = (int) Math.round(random.nextGaussian() * 35 + 0.5 * ülempiir);
            a = b * JagamiseVastus;
            if (a < ülempiir && JagamiseVastus > 0 && a != b) {
                k = false;
            }
        }

    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = ?";
    }

    @Override
    String getVastus() {
        return String.valueOf(JagamiseVastus);

    }
}

