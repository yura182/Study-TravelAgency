package com.yura.travel.domain.tour;

public enum Food {
    BB(10) {
        @Override
        public String toString() {
            return "Bed and Breakfast";
        }
    },

    HB(15) {
        @Override
        public String toString() {
            return "Half Board";
        }
    },

    FB(20) {
        @Override
        public String toString() {
            return "Full board";
        }
    },

    ALL(25) {
        @Override
        public String toString() {
            return "All inclusive";
        }
    };

    private final Integer cost;

    Food(Integer cost) {
        this.cost = cost;
    }

    public Integer getCost() {
        return cost;
    }
}
