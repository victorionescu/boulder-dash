package view;

public class DiamondElementPainter extends IconElementPainter {
    public static final DiamondElementPainter INSTANCE = new DiamondElementPainter();

    private DiamondElementPainter() {
    }

    protected String imagePath() {
        return "../resources/diamond.png";
    }
}
