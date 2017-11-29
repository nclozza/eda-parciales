package PP_1C_2014;

public interface CustomMap<K,V> {

    /**
     * Retorna el valor asociado a la clave, o null si la clave no existe.
     */
    V get(K key);

    /**
     * Agrega un par clave/valor al mapa. Si la clave ya existe, la actualiza
     * con el nuevo valor (en este caso esta operación se cuenta como un acceso más
     * a la clave).
     */
    void put(K key, V value);

    /**
     * Retorna la clave que más veces fue accedida.
     */
    K getMostAccessed();

    /**
     * Elimina del mapa la clave (y valor) que menos veces fue accedida.
     * Se pueden realizar sucesivas llamadas a este método.
     */
    void removeLeastAccessed();
}
