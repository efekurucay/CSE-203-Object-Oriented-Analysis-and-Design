import java.util.*;

public class GuitarApp {

    // Enum for Guitar Builder
    public enum Builder {
        FENDER, MARTIN, GIBSON, COLLINGS, OLSON, RYAN, PRS, ANY;

        @Override
        public String toString() {
            switch (this) {
                case FENDER: return "Fender";
                case MARTIN: return "Martin";
                case GIBSON: return "Gibson";
                case COLLINGS: return "Collings";
                case OLSON: return "Olson";
                case RYAN: return "Ryan";
                case PRS: return "PRS";
                default: return "Unspecified";
            }
        }
    }

    // Enum for Guitar Type
    public enum Type {
        ACOUSTIC, ELECTRIC;

        @Override
        public String toString() {
            switch (this) {
                case ACOUSTIC: return "acoustic";
                case ELECTRIC: return "electric";
                default: return "unspecified";
            }
        }
    }

    // Enum for Guitar Wood
    public enum Wood {
        INDIAN_ROSEWOOD, BRAZILIAN_ROSEWOOD, MAHOGANY, MAPLE, COCOBOLO, CEDAR, ALDER, SITKA;

        @Override
        public String toString() {
            switch (this) {
                case INDIAN_ROSEWOOD: return "Indian Rosewood";
                case BRAZILIAN_ROSEWOOD: return "Brazilian Rosewood";
                case MAHOGANY: return "Mahogany";
                case MAPLE: return "Maple";
                case COCOBOLO: return "Cocobolo";
                case CEDAR: return "Cedar";
                case ALDER: return "Alder";
                case SITKA: return "Sitka";
                default: return "unspecified";
            }
        }
    }

    // Guitar Class
    public static class Guitar {
        private String serialNumber;
        private double price;
        private Builder builder;
        private String model;
        private Type type;
        private Wood backWood;
        private Wood topWood;

        public Guitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood, Wood topWood) {
            this.serialNumber = serialNumber;
            this.price = price;
            this.builder = builder;
            this.model = model;
            this.type = type;
            this.backWood = backWood;
            this.topWood = topWood;
        }

        public String getSerialNumber() { return serialNumber; }
        public double getPrice() { return price; }
        public void setPrice(double newPrice) { this.price = newPrice; }
        public Builder getBuilder() { return builder; }
        public String getModel() { return model; }
        public Type getType() { return type; }
        public Wood getBackWood() { return backWood; }
        public Wood getTopWood() { return topWood; }
    }

    // Inventory Class
    public static class Inventory {
        private List<Guitar> guitars;

        public Inventory() {
            guitars = new LinkedList<>();
        }

        public void addGuitar(String serialNumber, double price, Builder builder, String model, Type type, Wood backWood, Wood topWood) {
            Guitar guitar = new Guitar(serialNumber, price, builder, model, type, backWood, topWood);
            guitars.add(guitar);
        }

        public Guitar getGuitar(String serialNumber) {
            for (Guitar guitar : guitars) {
                if (guitar.getSerialNumber().equals(serialNumber)) {
                    return guitar;
                }
            }
            return null;
        }

        public List<Guitar> search(Guitar searchGuitar) {
            List<Guitar> matchingGuitars = new LinkedList<>();
            for (Guitar guitar : guitars) {
                if (searchGuitar.getBuilder() != guitar.getBuilder()) continue;
                if (!searchGuitar.getModel().toLowerCase().equals(guitar.getModel().toLowerCase())) continue;
                if (searchGuitar.getType() != guitar.getType()) continue;
                if (searchGuitar.getBackWood() != guitar.getBackWood()) continue;
                if (searchGuitar.getTopWood() != guitar.getTopWood()) continue;
                matchingGuitars.add(guitar);
            }
            return matchingGuitars;
        }
    }

    // Main Class to Test the Application
    public static class FindGuitarTester {
        public static void main(String[] args) {
            // Set up Rick's guitar inventory
            Inventory inventory = new Inventory();
            initializeInventory(inventory);

            Guitar whatErinLikes = new Guitar("", 0, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);

            List<Guitar> matchingGuitars = inventory.search(whatErinLikes);
            if (!matchingGuitars.isEmpty()) {
                System.out.println("Erin, you might like these guitars:");
                for (Guitar guitar : matchingGuitars) {
                    System.out.println(" We have a " +
                        guitar.getBuilder() + " " +
                        guitar.getModel() + " " +
                        guitar.getType() + " guitar:\n " +
                        guitar.getBackWood() + " back and sides,\n " +
                        guitar.getTopWood() + " top.\n You can have it for only $" +
                        guitar.getPrice() + "!\n ----");
                }
            } else {
                System.out.println("Sorry, Erin, we have nothing for you.");
            }
        }

        private static void initializeInventory(Inventory inventory) {
            inventory.addGuitar("V95693", 1499.95, Builder.FENDER, "Stratocastor", Type.ELECTRIC, Wood.ALDER, Wood.ALDER);
            // Add other guitars to the inventory as needed...
        }
    }
}
