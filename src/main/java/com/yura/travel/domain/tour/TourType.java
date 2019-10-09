package com.yura.travel.domain.tour;

public enum TourType {
    PACKAGE {
        @Override
        public String toString() {
            return "Package tour";
        }
    },

    SHOPPING {
        @Override
        public String toString() {
            return "Shopping tour";
        }
    },

    HEALTH {
        @Override
        public String toString() {
            return "Health tour";
        }
    },

    CRUISE {
        @Override
        public String toString() {
            return "Cruise";
        }
    },

    SIGHTSEEING {
        @Override
        public String toString() {
            return "Sightseeing tour";
        }
    }
}
