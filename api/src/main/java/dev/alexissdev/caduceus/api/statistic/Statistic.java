package dev.alexissdev.caduceus.api.statistic;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents a statistic with a modifiable integer value. This class provides
 * functionalities to add to, subtract from, increment, and decrement the stored value.
 * It also includes a factory method for initializing the statistic with a default value.
 */

@Data
@Builder
@AllArgsConstructor
@Getter
@Setter
public class Statistic {

    private int value;

    public static Statistic initial() {
        return Statistic.builder().value(0).build();
    }

    /**
     * Adds the specified value to the current value of this object.
     *
     * @param value the value to be added to the current value
     */

    public void add(int value) {
        this.value += value;
    }

    /**
     * Subtracts the specified value from the current value of this object.
     *
     * @param value the value to subtract from the current value
     */

    public void subtract(int value) {
        this.value -= value;
    }

    public void increment() {
        this.value++;
    }

    public void decrement() {
        this.value--;
    }
}
