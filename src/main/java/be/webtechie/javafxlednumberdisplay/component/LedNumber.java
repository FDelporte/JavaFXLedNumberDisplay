package be.webtechie.javafxlednumberdisplay.component;

import be.webtechie.javafxlednumberdisplay.definition.DisplaySkin;
import be.webtechie.javafxlednumberdisplay.definition.HighlightType;
import be.webtechie.javafxlednumberdisplay.segment.Dot;
import be.webtechie.javafxlednumberdisplay.segment.Line;
import be.webtechie.javafxlednumberdisplay.util.ColorUtil;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Component visualizing a LED number display.
 *
 * Inspired by
 *   https://riptutorial.com/javafx/example/27795/basic-shapes
 *   https://www.dummies.com/programming/java/how-to-translate-scale-and-rotate-in-javafx/
 */
public class LedNumber extends Pane {

    private final Line segmentA;
    private final Line segmentB;
    private final Line segmentC;
    private final Line segmentD;
    private final Line segmentE;
    private final Line segmentF;
    private final Line segmentG;
    private final Dot segmentDot;

    private final Color idleColor;
    private final Color selectedColor;

    public LedNumber(DisplaySkin displaySkin, Color backgroundColor, Color idleColor, Color selectedColor) {
        this(displaySkin, backgroundColor, idleColor, selectedColor, true);
    }

    public LedNumber(DisplaySkin displaySkin, Color backgroundColor, Color idleColor, Color selectedColor, boolean showDot) {
        this.idleColor = idleColor;
        this.selectedColor = selectedColor;

        // Set the background color
        this.setStyle("-fx-background-color: #" + ColorUtil.colorToHex(backgroundColor, false));

        // Horizontal segments
        this.segmentA = new Line(displaySkin.getSegmentDefinition("A"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentB = new Line(displaySkin.getSegmentDefinition("B"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentC = new Line(displaySkin.getSegmentDefinition("C"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentD = new Line(displaySkin.getSegmentDefinition("D"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentE = new Line(displaySkin.getSegmentDefinition("E"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentF = new Line(displaySkin.getSegmentDefinition("F"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.segmentG = new Line(displaySkin.getSegmentDefinition("G"), idleColor, displaySkin.getWidth(showDot), displaySkin.getHeight());
        this.getChildren().addAll(this.segmentA, this.segmentB, this.segmentC, this.segmentD, this.segmentE, this.segmentF, this.segmentG);

        this.segmentDot = new Dot(
                displaySkin.getDotDiameter(),
                displaySkin.getWidth(showDot) - displaySkin.getDotSpacing() - displaySkin.getDotDiameter(),
                displaySkin.getHeight() - displaySkin.getDotDiameter(),
                idleColor,
                displaySkin.getWidth(showDot),
                displaySkin.getHeight());

        if (showDot) {
            this.getChildren().add(this.segmentDot);
        }
    }

    public void highlight(HighlightType type) {
        this.highlight(type, false);
    }

    public void highlight(HighlightType type, boolean dot) {
        if (type == null) {
            type = HighlightType.CLEAR;
        }

        this.highlight(type.isA(), type.isB(), type.isC(), type.isD(), type.isE(), type.isF(), type.isG(), dot);
    }

    public void highlight(boolean a, boolean b, boolean c, boolean d, boolean e, boolean f, boolean g, boolean dot) {
        this.segmentA.setColor(a ? this.selectedColor : this.idleColor);
        this.segmentB.setColor(b ? this.selectedColor : this.idleColor);
        this.segmentC.setColor(c ? this.selectedColor : this.idleColor);
        this.segmentD.setColor(d ? this.selectedColor : this.idleColor);
        this.segmentE.setColor(e ? this.selectedColor : this.idleColor);
        this.segmentF.setColor(f ? this.selectedColor : this.idleColor);
        this.segmentG.setColor(g ? this.selectedColor : this.idleColor);

        this.segmentDot.setColor(dot ? this.selectedColor : this.idleColor);
    }
}
