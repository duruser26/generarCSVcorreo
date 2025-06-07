package generarcsvcontacts;

public class Huesped {
    
    private String fechaCheckIn;
    private String nombreHabitacion;
    private String telefono;
    private String nombreHuesped;

    public Huesped(String fechaCheckIn, String nombreHabitacion, String telefono, String nombreHuesped) {
        this.fechaCheckIn = fechaCheckIn;
        this.nombreHabitacion = nombreHabitacion;
        this.telefono = telefono;
        this.nombreHuesped = nombreHuesped;
    }

    public String getFechaCheckIn() {
        return fechaCheckIn;
    }

    public String getNombreHabitacion() {
        return nombreHabitacion;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getNombreHuesped() {
        return nombreHuesped;
    }

    @Override
    public String toString() {
        return "";
    }

}
