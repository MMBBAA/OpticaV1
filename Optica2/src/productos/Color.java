package productos;

/**
 * @author Alberto
 */
public enum Color {

    /**
     *
     */
    Cr("rojo"),

    /**
     *
     */
    Cv("verde"),

    /**
     *
     */
    Ca("azul"),

    /**
     *
     */
    Cn("naranja"),

    /**
     *
     */
    Cb("sin color");

    private String color;

    Color(String c) {

        color = c;
    }

    /**
     *
     * @return
     */
    public String getColor() {
        return color;
    }

    /**
     *
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return this.color;
    }
}