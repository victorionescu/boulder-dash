package bdash.view;

public class DirtElementPainter extends IconElementPainter {
    public static final DirtElementPainter INSTANCE = new DirtElementPainter();

    private DirtElementPainter() {
    }

    protected String imagePath() {
        return "/bdash/resources/dirt.jpg";
    }
}
