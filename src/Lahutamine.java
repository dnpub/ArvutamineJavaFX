public class Lahutamine extends Ülesanne {
    private String tehe = "-";
    private int a;
    private int b;
    private int LahutamiseVastus;

    public Lahutamine(int ülempiir) {
        super(ülempiir);


        boolean sobib = true;
        while (sobib) {
            a = (int) Math.round(Math.random() * (ülempiir - 1) + 1);
            b = (int) Math.round(Math.random() * (ülempiir - ülempiir * 0.1) + ülempiir * 0.1);
            if (b < a) {
                sobib = false;
            }
        }
        LahutamiseVastus = a - b;
    }

    @Override
    String getÜlesanne() {
        return a + " " + tehe + " " + b + " = ?";
    }

    @Override
    String getVastus() {
        return String.valueOf(LahutamiseVastus);
    }
}
