package be.webtechie.javafxlednumberdisplay.segment;

import be.webtechie.javafxlednumberdisplay.definition.LineDefinition;
import be.webtechie.javafxlednumberdisplay.segment.itf.SegmentItf;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Line extends Canvas implements SegmentItf {

    final GraphicsContext gc;

    final LineDefinition lineDefinition;
    final int width;
    final int height;

    public Line(LineDefinition lineDefinition, Color fillColor, int width, int height) {
        super(width, height);

        this.gc = this.getGraphicsContext2D();
        this.lineDefinition = lineDefinition;
        this.width = width;
        this.height = height;

        this.draw(fillColor);

    }

    @Override
    public void setColor(Color color) {
        this.draw(color);
    }

    private void draw(Color color) {
        this.gc.clearRect(0, 0, this.width, this.height);
        this.gc.setFill(color);
        this.gc.fillPolygon(
                lineDefinition.getPointsX(),
                lineDefinition.getPointsY(),
                lineDefinition.getPointsX().length);
    }
}
