package be.webtechie.javafxlednumberdisplay.component;

import be.webtechie.javafxlednumberdisplay.definition.DisplaySkin;
import be.webtechie.javafxlednumberdisplay.segment.Dot;
import be.webtechie.javafxlednumberdisplay.util.ColorUtil;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

/**
 * Component visualizing a LED colon display, e.g. to use on a clock.
 */
public class Colon extends Pane {

    private final Dot segmentDotUpper;
    private final Dot segmentDotLower;

    private final Color idleColor;
    private final Color selectedColor;

    public Colon(DisplaySkin displaySkin, Color backgroundColor, Color idleColor, Color selectedColor) {
        this.idleColor = idleColor;
        this.selectedColor = selectedColor;

        // Set the background color
        this.setStyle("-fx-background-color: #" + ColorUtil.colorToHex(backgroundColor, false));

        // Horizontal segments
        this.segmentDotUpper = new Dot(
                displaySkin.getDotDiameter(),
                displaySkin.getDotSpacing(),
                displaySkin.getHeight() / 3,
                idleColor,
                (displaySkin.getDotSpacing() * 2) + displaySkin.getDotDiameter(),
                displaySkin.getHeight());

        this.segmentDotLower = new Dot(
                displaySkin.getDotDiameter(),
                displaySkin.getDotSpacing(),
                (displaySkin.getHeight() / 3) * 2,
                idleColor,
                (displaySkin.getDotSpacing() * 2) + displaySkin.getDotDiameter(),
                displaySkin.getHeight());

        this.getChildren().addAll(this.segmentDotUpper, this.segmentDotLower);
    }

    public void highlight(boolean on) {
        this.segmentDotUpper.setColor(on ? this.selectedColor : this.idleColor);
        this.segmentDotLower.setColor(on ? this.selectedColor : this.idleColor);
    }
}
