package com.yura.travel.domain.tour;

public enum Transport {
    PLANE(200) {
        @Override
        public String toString() {
            return "Plane";
        }
    },

    BUS(100) {
        @Override
        public String toString() {
            return "Bus";
        }
    },

    TRAIN(150) {
        @Override
        public String toString() {
            return "Train";
        }
    };

    private final Integer cost;

    Transport(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return this.cost;
    }
}
