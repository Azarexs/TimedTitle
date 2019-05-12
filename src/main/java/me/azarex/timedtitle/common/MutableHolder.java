package me.azarex.timedtitle.common;

import java.util.Objects;

public class MutableHolder<T> implements Holder<T> {

    private T value;

    public MutableHolder() {
        this(null);
    }

    public MutableHolder(T value) {
        this.value = value;
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void set(T value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MutableHolder<?> that = (MutableHolder<?>) o;
        return Objects.equals(value, that.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }
}
