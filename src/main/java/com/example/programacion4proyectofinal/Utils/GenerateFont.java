package com.example.programacion4proyectofinal.Utils;

import javafx.scene.text.Font;

/**
 * Esta clase proporciona métodos para cargar diferentes estilos de la fuente Lato desde archivos de fuente externos.
 */
public class GenerateFont {

    /**
     * Carga y devuelve la fuente Lato Black con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoBlack(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Black.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Black Italic con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoBlackItalic(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-BlackItalic.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Bold con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoBold(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Bold.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Bold Italic con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoBoldItalic(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-BoldItalic.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Italic con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoItalic(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Italic.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Light con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoLight(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Light.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Light Italic con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoLightItalic(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-LightItalic.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Regular con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoRegular(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Regular.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Thin con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoThin(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Thin.ttf"), fontSize);
    }

    /**
     * Carga y devuelve la fuente Lato Thin Italic con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoThinItalic(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-ThinItalic.ttf"), fontSize);
    }
}
