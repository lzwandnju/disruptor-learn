package com.dis.learn;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
public class DataEvent implements Serializable {
    private static final long serialVersionUID = -7218233944115519074L;

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DataEvent{" +
                "value=" + value +
                '}';
    }
}
