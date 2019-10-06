package com.yura.domain.tour;

public enum Food {
    BB {
        @Override
        public String toString() {
            return "Bed and Breakfast";
        }
    },

    HB {
        @Override
        public String toString() {
            return "Half Board";
        }
    },

    FB {
        @Override
        public String toString() {
            return "Full board";
        }
    },

    ALL {
        @Override
        public String toString() {
            return "All inclusive";
        }
    }
}
