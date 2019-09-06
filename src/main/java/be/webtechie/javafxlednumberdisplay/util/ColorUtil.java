package be.webtechie.javafxlednumberdisplay.util;

import javafx.scene.paint.Color;

/**
 * Util to convert Color to hex string.
 * Inspired by https://stackoverflow.com/questions/17925318/how-to-get-hex-web-string-from-javafx-colorpicker-color
 */
public class ColorUtil {

    private ColorUtil() {
        // NOP
    }

    public static String colorToHex(Color color, boolean includeOpacity) {
        StringBuilder rt = new StringBuilder();
        rt.append(colorChanelToHex(color.getRed()))
                .append(colorChanelToHex(color.getGreen()))
                .append(colorChanelToHex(color.getBlue()));

        if (includeOpacity) {
            rt.append(colorChanelToHex(color.getOpacity()));
        }

        return rt.toString();
    }

    private static String colorChanelToHex(double chanelValue) {
        String rtn = Integer.toHexString((int) Math.min(Math.round(chanelValue * 255), 255));
        if (rtn.length() == 1) {
            rtn = "0" + rtn;
        }
        return rtn;
    }
}
