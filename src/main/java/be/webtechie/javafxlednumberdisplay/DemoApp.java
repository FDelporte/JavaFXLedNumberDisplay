package be.webtechie.javafxlednumberdisplay;

import be.webtechie.javafxlednumberdisplay.component.Colon;
import be.webtechie.javafxlednumberdisplay.component.LedNumber;
import be.webtechie.javafxlednumberdisplay.definition.DisplaySkin;
import be.webtechie.javafxlednumberdisplay.definition.HighlightType;
import be.webtechie.javafxlednumberdisplay.util.ColorUtil;
import java.time.LocalDateTime;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Box;
import javafx.stage.Stage;

/**
 * JavaFX demo application with some sample use of the provided component
 */
public class DemoApp extends Application {

    private LedNumber ledNumber;
    private ComboBox<HighlightType> selectHighLightType;
    private CheckBox enableDot;

    @Override
    public void start(Stage stage) {
        VBox demo = new VBox();
        demo.setPadding(new Insets(10));
        demo.setSpacing(25);

        demo.getChildren().add(this.getSingleWithInputs());
        demo.getChildren().add(this.getDateClock(Color.BLACK, Color.DARKSLATEGRAY, Color.LIGHTGREEN));

        var scene = new Scene(demo, 640, 480);
        stage.setTitle("JavaFX LED number display demos");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    private HBox getSingleWithInputs() {
        HBox holder = new HBox();
        holder.setSpacing(10);
        holder.setAlignment(Pos.CENTER);

        this.ledNumber = new LedNumber(DisplaySkin.CLASSIC, Color.BLACK, Color.DARKGRAY, Color.RED);
        holder.getChildren().add(this.ledNumber);

        VBox inputs = new VBox();
        inputs.setSpacing(10);
        holder.getChildren().add(inputs);

        this.selectHighLightType = new ComboBox<>();
        this.selectHighLightType.getItems().setAll(HighlightType.values());
        this.selectHighLightType.setOnAction(this::updateHighlights);
        inputs.getChildren().add(this.selectHighLightType);

        this.enableDot = new CheckBox("Dot");
        this.enableDot.setOnAction(this::updateHighlights);
        inputs.getChildren().add(this.enableDot);

        return holder;
    }

    private void updateHighlights(ActionEvent actionEvent) {
        this.ledNumber.highlight(this.selectHighLightType.getValue(), this.enableDot.isSelected());
    }

    private VBox getDateClock(Color bgColor, Color idleColor, Color selectedColor) {
        VBox holder = new VBox();
        holder.setPadding(new Insets(10));
        holder.setSpacing(20);
        holder.setStyle("-fx-background-color: #" + ColorUtil.colorToHex(bgColor, false));

        // Date
        HBox holderDate = new HBox();
        holderDate.setSpacing(5);
        holderDate.setAlignment(Pos.CENTER);
        holder.getChildren().add(holderDate);

        LedNumber dayMajor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber dayMinor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber monthMajor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber monthMinor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber yearMajor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber yearMinor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);

        Box spacer1 = new Box();
        spacer1.setWidth(20);

        Box spacer2 = new Box();
        spacer2.setWidth(20);

        holderDate.getChildren().addAll(dayMajor, dayMinor, spacer1, monthMajor, monthMinor, spacer2, yearMajor, yearMinor);

        // Clock
        HBox holderClock = new HBox();
        holderClock.setSpacing(5);
        holderClock.setAlignment(Pos.CENTER);
        holder.getChildren().add(holderClock);

        LedNumber hoursMajor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber hoursMinor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber minutesMajor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber minutesMinor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber secondsMajor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);
        LedNumber secondsMinor = new LedNumber(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor, false);

        Colon colon1 = new Colon(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor);
        Colon colon2 = new Colon(DisplaySkin.CLASSIC, bgColor, idleColor, selectedColor);

        holderClock.getChildren()
                .addAll(hoursMajor, hoursMinor, colon1, minutesMajor, minutesMinor, colon2, secondsMajor, secondsMinor);

        (new Thread(() -> {
            boolean highligtColons = false;

            while (true) {
                LocalDateTime now = LocalDateTime.now();

                dayMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getDayOfMonth())));
                dayMinor.highlight(HighlightType.getByNumber(now.getDayOfMonth() % 10));

                monthMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getMonthValue())));
                monthMinor.highlight(HighlightType.getByNumber(now.getMonthValue() % 10));

                yearMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getYear() % 100)));
                yearMinor.highlight(HighlightType.getByNumber(now.getYear() % 10));

                hoursMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getHour())));
                hoursMinor.highlight(HighlightType.getByNumber(now.getHour() % 10));

                minutesMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getMinute())));
                minutesMinor.highlight(HighlightType.getByNumber(now.getMinute() % 10));

                secondsMajor.highlight(HighlightType.getByNumber(getMajorDigit(now.getSecond())));
                secondsMinor.highlight(HighlightType.getByNumber(now.getSecond() % 10));

                colon1.highlight(highligtColons);
                colon2.highlight(highligtColons);

                highligtColons = !highligtColons;

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    System.err.println("The clock thread was interrupted");
                }
            }
        })).start();

        return holder;
    }

    private int getMajorDigit(int value) {
        return (value - (value % 10)) / 10;
    }
}