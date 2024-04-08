package DTO;

public class ReservacionDTO {
    private String asiento;


    public ReservacionDTO() {
    }

    public String getAsiento() {
        return asiento;
    }

    public void setAsiento(String asiento) {
        this.asiento = asiento;
    }

    @Override
    public String toString() {
        return "ReservacionDTO{" +
                "asiento='" + asiento + '\'' +
                '}';
    }
}
