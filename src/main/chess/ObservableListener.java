package main.chess;

/**
 * Listens to an observable event.
 * 
 * @author Braedon Wooding
 * @param <T> The type that is being observed.
 */
public interface ObservableListener<T> {
    /**
     * Is called upon an update to the observable
     */
    public void onUpdate(T oldValue, T newValue);
}