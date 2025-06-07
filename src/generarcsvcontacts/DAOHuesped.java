package generarcsvcontacts;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DAOHuesped implements IDAOHuesped {
    
    private File xslxHuespedes;

    public DAOHuesped(File archivoHuespedes) {
        this.xslxHuespedes = archivoHuespedes;
    }

    // Devuelve una fecha en formato yymmdd a partir de dd/MM/yyyy en una sola línea
    private static String obtenerFechaCompacta(String fecha) {
        return fecha.length() == 10 ? fecha.substring(8, 10) + fecha.substring(3, 5) + fecha.substring(0, 2) : fecha;
    }

    @Override
    public List<Huesped> obtenerHuespedes() throws ErrorDAO {
        List<Huesped> listaHuespedes = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(this.xslxHuespedes))) {
            String linea;
            br.readLine(); // Saltar la cabecera
            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(";", -1);
                if (partes.length >= 48) {
                    String fechaCheckIn = obtenerFechaCompacta(partes[2]);
                    String nombreHabitacion = partes[6];
                    String telefono = partes[14];
                    String nombreHuesped = partes[18];
                    Huesped huesped = new Huesped(fechaCheckIn, nombreHabitacion, telefono, nombreHuesped);
                    listaHuespedes.add(huesped);
                }
            }
        } catch (IOException e) {
            throw new ErrorDAO("Error al cargar los huéspedes desde el archivo: " + xslxHuespedes.getAbsolutePath());
        }
        return listaHuespedes;
    }

}
