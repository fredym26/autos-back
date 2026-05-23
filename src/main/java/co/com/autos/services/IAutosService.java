package co.com.autos.services;

import co.com.autos.model.AutoDTO;

public interface IAutosService {

     boolean registrarAuto(AutoDTO autoDTO);

     boolean eliminarAuto(Integer id);


}
