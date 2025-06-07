package generarcsvcontacts;

public class GenerarCSVContactos {
    
    private IDAOHuesped daoHuespedes;

    public GenerarCSVContactos(IDAOHuesped daoHuespedes) {
        this.daoHuespedes = daoHuespedes;
    }

    private static String generarNombreCorrecto(Huesped huesped) {
        return huesped.getFechaCheckIn() + " - " + huesped.getNombreHabitacion() + " - " + huesped.getNombreHuesped();
    }

    public String generarCSV() throws ErrorDAO {
        StringBuilder contenido = new StringBuilder();
        contenido.append("First Name,Middle Name,Last Name,Phonetic First Name,Phonetic Middle Name,Phonetic Last Name,Name Prefix,Name Suffix,Nickname,File As,Organization Name,Organization Title,Organization Department,Birthday,Notes,Photo,Labels,E-mail 1 - Label,E-mail 1 - Value,E-mail 2 - Label,E-mail 2 - Value,Phone 1 - Label,Phone 1 - Value\n");

        for (Huesped huesped : daoHuespedes.obtenerHuespedes()) {
            contenido.append(generarNombreCorrecto(huesped)).append(",,,,,,,,,,,,,,,,")
                    .append("* myContacts").append(",,,,,,")
                    .append(huesped.getTelefono()).append("\n");
        }
        return contenido.toString();
    }

}
