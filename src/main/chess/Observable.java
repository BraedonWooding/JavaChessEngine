package main.chess;

import java.util.List;
import java.util.ArrayList;

/**
 * Constructs an observable event on an object.
 * 
 * @author Braedon Wooding
 * @param <T> The type that is being observed.
 */
public final class Observable<T> {
    private T _obj;

    private List<ObservableListener<T>> listeners;

    /**
     * Constructs a new observable with a fresh empty listener list.
     */
    public Observable(T obj) {
        this._obj = obj;
        this.listeners = new ArrayList<>();
    }

    /**
     * Observe any changes in this object.
     */
    public void observe(ObservableListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Clears all observables so no one is watching.
     */
    public void clearObservers() {
        listeners.clear();
    }

    public void set(T obj) {
        T old = this._obj;
        this._obj = obj;

        for (ObservableListener<T> listener : listeners) {
            listener.onUpdate(old, obj);
        }
    }

    public T get() {
        return _obj;
    }
}