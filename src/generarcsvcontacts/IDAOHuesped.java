package generarcsvcontacts;

import java.util.List;

public interface IDAOHuesped {
    
    public List<Huesped> obtenerHuespedes() throws ErrorDAO;

}
