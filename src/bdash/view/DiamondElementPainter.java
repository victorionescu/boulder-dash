package bdash.view;
/*
    Painter class for diamonds.
 */
public class DiamondElementPainter extends IconElementPainter {
    public static final DiamondElementPainter INSTANCE = new DiamondElementPainter();

    private DiamondElementPainter() {
    }

    protected String imagePath() {
        return "/bdash/resources/diamond.png";
    }
}
