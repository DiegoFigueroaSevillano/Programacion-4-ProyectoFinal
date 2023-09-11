package com.example.programacion4proyectofinal.Utils.ViewUtils;

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
     * Carga y devuelve la fuente Lato Light con el tamaño de fuente especificado.
     *
     * @param fontSize El tamaño de fuente deseado.
     * @return La fuente cargada.
     */
    public Font latoLight(int fontSize) {
        return Font.loadFont(getClass().getResourceAsStream("/com/example/programacion4proyectofinal/Fonts/Lato-Light.ttf"), fontSize);
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
}
