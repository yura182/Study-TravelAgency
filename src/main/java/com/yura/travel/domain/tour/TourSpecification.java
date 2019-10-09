package com.yura.travel.domain.tour;

import java.util.Objects;

public class TourSpecification {
    private final Food food;
    private final Transport transport;
    private final Integer duration;

    public TourSpecification(Food food, Transport transport, Integer duration) {
        this.food = food;
        this.transport = transport;
        this.duration = duration;
    }

    public Food getFood() {
        return food;
    }

    public Transport getTransport() {
        return transport;
    }

    public Integer getDuration() {
        return duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TourSpecification that = (TourSpecification) o;
        return food == that.food &&
                transport == that.transport &&
                duration.equals(that.duration);
    }

    @Override
    public int hashCode() {
        return Objects.hash(food, transport, duration);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(Objects.toString(this.transport, ""));
        out.append(Objects.toString(", " + this.food, ""));
        out.append(Objects.toString(", " + this.duration + " days", ""));

        return out.toString();
    }
}
