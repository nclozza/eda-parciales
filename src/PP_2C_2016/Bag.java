package PP_2C_2016;

public interface Bag<T> {

    /** Agrega el elemento
     */
    public void add(T value);

    /** Devuelve la cantidad de veces que el elemento pasado por parámetro existe en la colección. */
    public int occurrencesOf(T value);

    /** Elimina el elemento que más veces aparece en ese momento y lo devuelve. */
    public T removeMostPopular();


}
