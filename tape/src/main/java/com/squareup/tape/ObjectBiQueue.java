package com.squareup.tape;

/**
 * Created by andreas on 05.02.2016.
 */
public interface ObjectBiQueue<T> extends  ObjectQueue<T>  {

    /** Enqueues an entry that can be processed at any time. */
    void addTail(T entry);

    /**
     * Returns the tail of the queue, or {@code null} if the queue is empty. Does not modify the
     * queue.
     */
    T peekTail();

    /** Removes the tail of the queue. */
    void removeTail();

}
