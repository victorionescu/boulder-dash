package bdash.view;
/*
 * Painter class for dirt.
 */
public class DirtElementPainter extends IconElementPainter {
    public static final DirtElementPainter INSTANCE = new DirtElementPainter();

    private DirtElementPainter() {
    }

    protected String imagePath() {
        return "/bdash/resources/dirt.jpg";
    }
}
