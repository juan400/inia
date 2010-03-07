package com.inia_mscc.modulos.gem.ejb;

import java.io.File;
import java.util.List;

import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;

import com.inia_mscc.modulos.gem.dao.DAOArchivo;
import com.inia_mscc.modulos.gem.entidades.Archivo;
import com.inia_mscc.modulos.gem.entidades.ArchivosTexto;
import com.inia_mscc.modulos.gem.servicios.ServicioArchivo;

@Stateless(name = "EJBArchivo", mappedName = "EJBArchivo")
@Remote(ServicioArchivo.class)
@TransactionManagement(value = TransactionManagementType.CONTAINER)
@TransactionAttribute(value = TransactionAttributeType.REQUIRED)
public class EJBArchivo implements ServicioArchivo {

	private DAOArchivo dao = new DAOArchivo();

	@Override
	public void ActualizarArchivo(Archivo pArchivo) {
		dao.ActualizarArchivo(pArchivo);
	}

	@Override
	public Archivo ObtenerArchivo(Archivo pArchivo) {
		return dao.ObtenerArchivo(pArchivo);
	}

	@Override
	public List<Archivo> ObtenerArchivos(Archivo pArchivo) {
		return dao.ObtenerArchivos(pArchivo);
	}

	@Override
	public Archivo RegistrarArchivo(Archivo pArchivo) throws Exception {
		Archivo archivo = null;
		try {
			File file = new File(pArchivo.get_ubicacion().get_urlPaht() + "/"
					+ pArchivo.get_nombre());
			file.setExecutable(true);
			if (!file.createNewFile()) {
				throw new Exception("No es posible generar el archivo.");
			}
			ArchivosTexto.copiarArchio(pArchivo.get_datos(), file);
			pArchivo.set_datos(file);
			if (!pArchivo.get_datos().exists()) {
				throw new Exception("No se puedo subir el archivo");
			} else {
				archivo = dao.RegistrarArchivo(pArchivo);
				if (archivo == null) {
					throw new Exception("No se puedo subir el archivo");
				}
			}
		} catch (Exception ex) {
			throw ex;
		}
		return archivo;
	}

}