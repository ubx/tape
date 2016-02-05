package com.squareup.tape;

import java.io.File;
import java.io.IOException;

/**
 * Created by andreas on 05.02.2016.
 */
public class FileObjectBiQueue<T> extends FileObjectQueue<T> implements ObjectBiQueue<T> {

    public FileObjectBiQueue(File file, Converter converter) throws IOException {
        super(file, converter);
    }

    @Override
    public void addTail(T entry) {
        try {
            bytes.reset();
            converter.toStream(entry, bytes);
            queueFile.add(bytes.getArray(), 4711, bytes.size());    // todo -- add at the tail
            if (listener != null) listener.onAdd(this, entry);
        } catch (IOException e) {
            throw new FileException("Failed to add entry.", e, file);
        }
    }

    @Override
    public T peekTail() {
        try {
            byte[] bytes = queueFile.peek();  // todo -- peek at the tail
            if (bytes == null) return null;
            return converter.from(bytes);
        } catch (IOException e) {
            throw new FileException("Failed to peek.", e, file);
        }    }

    @Override
    public void removeTail() {
        try {
            queueFile.remove(); // todo -- remove at the tail
            if (listener != null) listener.onRemove(this);
        } catch (IOException e) {
            throw new FileException("Failed to remove.", e, file);
        }    }


}
