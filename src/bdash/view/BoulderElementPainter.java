package bdash.view;

public class BoulderElementPainter extends IconElementPainter {

    public static final BoulderElementPainter INSTANCE = new BoulderElementPainter();

    private BoulderElementPainter() {
    }

    protected String imagePath() {
        return "/bdash/resources/boulder.png";
    }
}
