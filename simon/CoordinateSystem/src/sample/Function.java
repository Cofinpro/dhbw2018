package sample;

public abstract class Function implements Comparable<Function>{

    private String name;

    public Function(String name) {
        this.name = name;
    }

    public abstract double getY(double x);

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Function o) {
        return getName().compareTo(o.getName());
    }
}
