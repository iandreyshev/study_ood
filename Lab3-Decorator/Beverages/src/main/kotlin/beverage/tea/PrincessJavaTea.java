package beverage.tea;

public class PrincessJavaTea extends Tea {
    public PrincessJavaTea() {
        super("Princess Java tea");
    }

    @Override
    public double getCost() {
        return 42.0;
    }
}
