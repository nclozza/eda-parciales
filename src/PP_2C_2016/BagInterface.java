package PP_2C_2016;

/**
 * Created by nclozza on 04/12/17.
 */
public interface BagInterface<T> {

  /**
   * Agrega el elemento
   */
  public void add(T value);

  /**
   * Devuelve la cantidad de veces que el elemento pasado por parámetro existe en la colección.
   */
  public int occurrencesOf(T value);

  /**
   * Elimina el elemento que más veces aparece en ese momento y lo devuelv­­­e.
   */
  public T removeMostPopular();


}
