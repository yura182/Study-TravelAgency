package com.yura.domain.tour;

public enum Transport {
    PLANE {
        @Override
        public String toString() {
            return "Plane";
        }
    },

    BUS {
        @Override
        public String toString() {
            return "Bus";
        }
    },

    TRAIN {
        @Override
        public String toString() {
            return "Train";
        }
    }
}
