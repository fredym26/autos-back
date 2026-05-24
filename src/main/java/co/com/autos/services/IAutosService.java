package co.com.autos.services;

import co.com.autos.model.AutoDTO;
import co.com.autos.model.RespuestaDTO;

public interface IAutosService {

     RespuestaDTO registrarAuto(AutoDTO autoDTO);

     boolean eliminarAuto(Integer id);

     boolean validarPlaca(String placa);

     boolean validarAnio(Integer anio);


}
