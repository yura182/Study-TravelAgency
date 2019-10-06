package com.yura.domain.tour;

import java.util.Objects;

public class Tour {
    private static Long nextId = 1L;

    private final Long id;
    private final String name;
    private final TourType type;
    private final Transport transport;
    private final Food food;
    private final int duration;
    private final int price;

    private Tour(TourBuilder tourBuilder) {
        this.id = nextId++;
        this.name = tourBuilder.name;
        this.type = tourBuilder.type;
        this.transport = tourBuilder.transport;
        this.food = tourBuilder.food;
        this.duration = tourBuilder.duration;
        this.price = tourBuilder.price;
    }

    public TourBuilder init() {
        return new TourBuilder();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public TourType getType() {
        return type;
    }

    public Transport getTransport() {
        return transport;
    }

    public Food getFood() {
        return food;
    }

    public int getDuration() {
        return duration;
    }

    public int getPrice() {
        return price;
    }

    public static class TourBuilder {
        private String name;
        private TourType type;
        private Transport transport;
        private Food food;
        private int duration;
        private int price;

        private TourBuilder() {

        }

        public TourBuilder withName(String nama) {
            this.name = name;
            return this;
        }

        public TourBuilder withType(TourType type) {
            this.type = type;
            return this;
        }

        public TourBuilder withTransport(Transport transport) {
            this.transport = transport;
            return this;
        }

        public TourBuilder withFodd(Food food) {
            this.food = food;
            return this;
        }

        public TourBuilder withDuration(int duration) {
            this.duration = duration;
            return this;
        }

        public TourBuilder withPrice(int price) {
            this.price = price;
            return this;
        }

        public Tour build() {
            return new Tour(this);
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tour tour = (Tour) obj;
        return duration == tour.duration &&
                price == tour.price &&
                id.equals(tour.id) &&
                name.equals(tour.name) &&
                type == tour.type &&
                transport == tour.transport &&
                food == tour.food;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, transport, food, duration, price);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("| %-2s |", this.id));
        out.append(String.format(" %-10s|", Objects.toString(this.name, "")));
        out.append(String.format(" %-10s|", Objects.toString(this.type, "")));
        out.append(String.format(" %-10s|", Objects.toString(this.transport, "")));
        out.append(String.format(" %-10s|", Objects.toString(this.food, "")));
        out.append(String.format(" %-10s|", Objects.toString(this.duration, "")));
        out.append(String.format(" %-10s|", Objects.toString(this.price + "$", "")));

        return out.toString();
    }
}
