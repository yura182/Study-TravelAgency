package com.yura.travel.domain.tour;

import java.util.Objects;

public class Tour {
    private static Long nextId = 1L;

    private final Long id;
    private final String name;
    private final TourType type;
    private final TourSpecification tourSpecification;
    private final Integer basePrice;
    private Integer fullPrice;

    private Tour(TourBuilder tourBuilder) {
        this.id = nextId++;
        this.name = tourBuilder.name;
        this.type = tourBuilder.type;
        this.tourSpecification = tourBuilder.tourSpecification;
        this.basePrice = tourBuilder.price;
        fullPrice = fullPriceCalculate();
    }

    public Tour(Tour tour, TourSpecification tourSpecification) {
        this.id = tour.id;
        this.name = tour.name;
        this.type = tour.type;
        this.tourSpecification = tourSpecification;
        this.basePrice = tour.basePrice;
        fullPrice = fullPriceCalculate();
    }

    public static TourBuilder init() {
        return new TourBuilder();
    }

    private Integer fullPriceCalculate() {
        int result = basePrice + tourSpecification.getTransport().getCost();
        result += tourSpecification.getFood().getCost() * tourSpecification.getDuration();

        return result;
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

    public TourSpecification getTourSpecification() {
        return tourSpecification;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public Integer getFullPrice() {
        return fullPrice;
    }

    public static class TourBuilder {
        private String name;
        private TourType type;
        private TourSpecification tourSpecification;
        private int price;

        private TourBuilder() {

        }

        public TourBuilder withName(String name) {
            this.name = name;
            return this;
        }

        public TourBuilder withType(TourType type) {
            this.type = type;
            return this;
        }

        public TourBuilder withSpecification(TourSpecification tourSpecification) {
            this.tourSpecification = tourSpecification;
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tour tour = (Tour) o;
        return id.equals(tour.id) &&
                name.equals(tour.name) &&
                type == tour.type &&
                tourSpecification.equals(tour.tourSpecification) &&
                basePrice.equals(tour.basePrice);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, type, tourSpecification, basePrice);
    }

    @Override
    public String toString() {
        StringBuilder out = new StringBuilder();
        out.append(String.format("| %-2s |", this.id));
        out.append(String.format(" %-25s|", Objects.toString(this.name, "")));
        out.append(String.format(" %-20s|", Objects.toString(this.type, "")));
        out.append(String.format(" %-35s|", Objects.toString(this.tourSpecification, "")));
        out.append(String.format(" %-6s|", Objects.toString(this.fullPrice + "$", "")));

        return out.toString();
    }
}
