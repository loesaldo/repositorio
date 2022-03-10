package org.service.sregion;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.jws.WebMethod;
import javax.jws.WebService;

/*
 *WEB SERVICE SOAP
 *
 *(SI YA ESTA GENERADO EL WEB SERVICE SOAP)
 *CREACION
 *	1.- LOS METODOS NO PUEDEN CAMBIAR DE NOMBRE, O ESTRUCTURA
 *	2.- CAMBIAR LOS TIPOS DE DATOS
 *	3.- LA CLASE Y LOS METODOS DEBEN TENER @WebService @WebMethod
 *	4.- GUARDAR LA CLASE ANTES DE GENERAR EL WEB SERVICE SOAP
 *
 *(SI ES NECESARIO MODIFICAR O AGREGAR ALGO NUEVO AL WEB SERVICE SOAP)
 *	1.- DETENER EL SERVIDOR
 *	2.- SERVER - ADD AN REMOVE - REMOVER EL PROYECTO
 *	3.- SERVER - CLEAN
 *	4.- WEBCONTENT - ELIMINAR LA CARPETA WSDL
 *	5.- REALIZAR LOS CAMBIOS EN LA CLASE DE SERVICIO SOAP, Y GUARDAR
 *	6.- CREAR EL WEB SERVICE SOAP
 * 
 */

@WebService
public class ServiceCrudS_Region {

	private static Connection connection;
	private static String driver = "oracle.jdbc.driver.OracleDriver";
	private static String URL = "jdbc:oracle:thin:@localhost:1521:orcl";
	
	@WebMethod
	public static void connectDataBaseOracle() throws IOException, SQLException {
		try {
			Class.forName(driver).newInstance();
			System.out.println("Cargo Driver: ojdbc6.jar");
		} catch (Exception e) {
			System.out.println("Excepcion en el driver: " + e.getMessage());
			// TODO: handle exception
		}
		try {
			connection = DriverManager.getConnection(URL, "System", "Chesslopez1990");
			System.out.println("Conexion Exitosa: Oracle11g");
		} catch (Exception e) {
			System.out.println("Error en la conexion: " + e.getMessage());
			// TODO: handle exception
		}
	}
	
	@WebMethod
	public String agregaS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "INSERT INTO S_REGION(ID,NAME) VALUES(?,?)";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setString(2, name);
			ps.executeQuery();

			System.out.println("INSERCION CORRECTA DEL REGISTRO = " + id + ", " + name);
		} catch (Exception e) {
			System.out.println("ERROR EN LA INSERCION: " + e.getMessage());
			// TODO: handle exception
		}
		return "AGREGO CORRECTAMENTE EL REGISTRO:" + id + ", " + name;
	}
	
	@WebMethod
	public String actualizaS_Region(int id, String name) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "UPDATE S_REGION SET NAME = ? WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			ps.setInt(2, id);
			ps.executeQuery();

			System.out.println("ACTUALIZACION CORRECTA DEL REGISTRO = " + id + ", " + name);
		} catch (Exception e) {
			System.out.println("ERROR EN LA ACTUALIZACION: " + e.getMessage());
			// TODO: handle exception
		}
		return "ACTUALIZO CORRECTAMENTE EL REGISTRO: " + name + ", " + id;
	}
	
	@WebMethod
	public String eliminaS_Region(int id) throws IOException, SQLException {
		try {
			connectDataBaseOracle();
			String sql = "DELETE FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeQuery();

			System.out.println("ELIMINACION CORRECTA DEL REGISTRO = " + id);
		} catch (Exception e) {
			System.out.println("ERROR EN LA ELIMINACION: " + e.getMessage());
			// TODO: handle exception
		}
		return "ELIMINO CORRECTAMENTE EL REGISTRO: " + id;
	}
	
	@WebMethod
	public String consultaIndividualS_Region(int id) throws IOException, SQLException {
		String nombre = null;
		try {
			connectDataBaseOracle();
			String sql = "SELECT * FROM S_REGION WHERE ID = ?";
			PreparedStatement ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rSet = ps.executeQuery();

			if (rSet.next()) {
				System.out.println(rSet.getInt("id") + ", " + rSet.getString("name"));
				nombre = rSet.getString("name");
			}

			System.out.println("CONSULTA FINALIZADA DEL REGISTRO = " + id);
		} catch (Exception e) {
			System.out.println("ERROR EN LA CONSULTA: " + e.getMessage());
			// TODO: handle exception
		}
		return "DATO:" + id + ", " + nombre;
	}

}
