package Entity;

public class Posizione {

        double latitudine;
        double longitudine;

        public Posizione(double latitudine, double longitudine) {
            this.latitudine = latitudine;
            this.longitudine = longitudine;
        }

        public double getLatitudine() {
            return latitudine;
        }

        public void setLatitudine(double latitudine) {
            this.latitudine = latitudine;
        }

        public double getLongitudine() {
            return longitudine;
        }

        public void setLongitudine(double longitudine) {
            this.longitudine = longitudine;
        }
}
