package view;

public class DirtElementPainter {
    public static final DirtElementPainter INSTANCE = new DirtElementPainter();

    private DirtElementPainter() {
    }

    protected String imagePath() {
        return "resources/dirt.jpg";
    }
}
