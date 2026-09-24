package unidad1;

import java.io.File;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Propuesta3 {
	/* Ha de crear un directorio NUEVODIR, (mkdir) y a continuación dos ficheros  vacíos en dicho 
		directorio y renombrar uno de ellos (Utilizar el tercer constructor File (File directorio, String 
		nombrefichero)). En el primero indicamos directorio donde se creará el fichero y en el segundo el nombre 
		del fichero.   */
	
	public static void main(String[] args) {
		try {
			crearDiryFich("./Borrar");
			borrar(Paths.get("./Borrar")); //TODO Probar en clase
		}catch(IOException e) {
			System.err.println("Hubo un problema de E/S: " + e.getLocalizedMessage());
			e.printStackTrace();
		}
	}
	
	private static void crearDiryFich(String ruta) throws IOException {
		File dir= new File(ruta);
		if(dir.mkdir())
			System.out.println("DIRECTORIO CREADO");
		else {
			System.err.println("NO SE PUDO CREAR EL DIRECTORIO");
			System.exit(0);
		}
		
		//Creando un fichero vacío en el directorio
		File f1= new File(ruta, "fich1.txt"); 
		File f2= new File(ruta, "fich2.txt");
		crearFichero(f1);
		crearFichero(f2);
		
		//Renombrar
		f2.renameTo(new File(dir, "fich.txt"));
		
		System.out.println(dir.list());
	}

	private static void crearFichero(File file) throws IOException {
		if(file.createNewFile())
			System.out.println("Se ha conseguido crear el fichero: " + file.getName());
		else
			System.out.println("Ha habido un problema con la creación del fichero: " + file.getName());
	}
	
	private static void borrar(Path ruta) throws IOException {
		if(Files.isDirectory(ruta)) 
			try(DirectoryStream<Path> stream= Files.newDirectoryStream(ruta)){
				for(Path p: stream) 
					borrar(p); //Si es un directorio, saldrá del método cuando borre todos sus ficheros
				
			}
		//Sea fichero, o carpeta (después de borrar su ficheros), se eliminará
		if(Files.deleteIfExists(ruta))
			System.out.println("Borrado exitosamente: " + ruta.toString());
	}

}
