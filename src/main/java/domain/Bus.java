package domain;

import java.util.Objects;

public class Bus implements Comparable<Bus> {
    private final String number;
    private final String model;
    private final int mileage;

    private Bus(Builder builder) {
        this.number = builder.number;
        this.model = builder.model;
        this.mileage = builder.mileage;
    }

    public String getNumber() {
        return number;
    }

    public String getModel() {
        return model;
    }

    public int getMileage() {
        return mileage;
    }

    public static class Builder {
        private String number;
        private String model;
        private int mileage;

        public Builder setNumber(String number) {
            if (!isValidNumber(number)) {
                throw new IllegalArgumentException(
                    "Неверный формат номера. Ожидается формат: буквы-цифры (например, А123БВ)");
            }
            this.number = number.trim();
            return this;
        }

        public Builder setModel(String model) {
            if (model == null || model.trim().isEmpty()) {
                throw new IllegalArgumentException("Модель не может быть пустой");
            }
            this.model = model.trim();
            return this;
        }

        public Builder setMileage(int mileage) {
            if (mileage < 0) {
                throw new IllegalArgumentException("Пробег не может быть отрицательным");
            }
            if (mileage > 1000000) {
                throw new IllegalArgumentException("Пробег не может превышать 1 000 000 км");
            }
            this.mileage = mileage;
            return this;
        }

        public Bus build() {
            if (number == null) {
                throw new IllegalStateException("Номер автобуса должен быть установлен");
            }
            if (model == null) {
                throw new IllegalStateException("Модель автобуса должна быть установлена");
            }
            return new Bus(this);
        }

        private boolean isValidNumber(String number) {
            if (number == null || number.trim().isEmpty()) {
                return false;
            }
            String trimmed = number.trim();
            return trimmed.matches("[А-ЯA-Z]{1,2}\\d{3,4}[А-ЯA-Z]{2,3}");
        }
    }

    @Override
    public int compareTo(Bus other) {
        if (this.number == null || other.number == null) {
            throw new NullPointerException("Номер автобуса не может быть null");
        }
        
        int numberComparison = this.number.compareTo(other.number);
        if (numberComparison != 0) {
            return numberComparison;
        }
        
        int modelComparison = this.model.compareTo(other.model);
        if (modelComparison != 0) {
            return modelComparison;
        }
        
        return Integer.compare(this.mileage, other.mileage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bus bus = (Bus) o;
        return mileage == bus.mileage &&
               Objects.equals(number, bus.number) &&
               Objects.equals(model, bus.model);
    }

    @Override
    public int hashCode() {
        return Objects.hash(number, model, mileage);
    }

    @Override
    public String toString() {
        return String.format("Автобус{номер='%s', модель='%s', пробег=%d км}", 
                           number, model, mileage);
    }
}