import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

abstract class Ülesanne {
    private int ülempiir;

    public Ülesanne(int ülempiir) {
        this.ülempiir = ülempiir;
    }

    public int getÜlempiir() {
        return ülempiir;
    }


    abstract String getÜlesanne();

    abstract String getVastus();

    @Override
    public String toString() {
        return getÜlesanne();
    }
}
