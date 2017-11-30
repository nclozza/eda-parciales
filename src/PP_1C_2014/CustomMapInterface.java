package PP_1C_2014;

/**
 * Created by nclozza on 30/11/17.
 */
public interface CustomMapInterface<K, V> {

  /**
   * Retorna el valor asociado a la clave, o null si la clave no existe.
   */
  public V get(K key);

  /**
   * Agrega un par clave/valor al mapa. Si la clave ya existe, la actualiza
   * con el nuevo valor (en este caso esta operación se cuenta como un acceso más
   * a la clave).
   */
  public void put(K key, V value);

  /**
   * Retorna la clave que más veces fue accedida.
   */
  public K getMostAccessed();

  /**
   * Elimina del mapa la clave (y valor) que menos veces fue accedida.
   * Se pueden realizar sucesivas llamadas a este método.
   */
  public void removeLeastAccessed();
}
