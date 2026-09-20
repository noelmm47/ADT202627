package unidad1;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Ejercicio4 {

private static int jerLv=0; //Jerarchy Level, Nivel de Jerarquía
	
	/**
	* Muestra la lista de ficheros en el directorio actual CON JAVA.NIO
	* Se utiliza método list() que devuelve un array de String con los nombres de los ficheros y directorios
	* Para indicarle directorio actual creamos vble dir=. y se la pasamos al objeto File
	* El segundo objeto File lo utilizamos para saber si es un fichero o directorio. Utilizamos el segundo contructor.
	*
	*/
	
	public static void main(String[] args) {
		//verDirectorio("C:\\");
		verDirectorio("."); //Directorio actual
	}
	
	private static void verDirectorio(String dir) {
		//String dir = "C:\\Usuarios\\Tarde\\Datos";
		jerLv+=1;
		if(jerLv>=10) {
			System.err.println("Demasiadas carpetas");
			System.exit(-1);
		}
		
		Path file= Paths.get(dir); //Path es una clase de instancias que localizan archivos
		
		try {
			try(DirectoryStream<Path> stream = Files.newDirectoryStream(file)){
				int count=0;
				for(Path _: stream)
					count++;
				
				System.out.printf("Archivos: %d\n", count);
				
				//Se han recorrido todos los Path del Stream, así que hay que empezar otro Stream
				try(DirectoryStream<Path> stream2= Files.newDirectoryStream(file)){
					for(int i=0; i<jerLv; i++)
						System.out.print("\t");
					
					for(Path p: stream2) {
						String name= p.toFile().getName();
						System.out.printf("* %s ///// ", name);
						System.out.printf("Directorio?: %s; Fichero?: %s\n",
											Files.isDirectory(p) ? "Y":"N",
											Files.isRegularFile(p) ? "Y":"N");
						
						if(Files.isDirectory(p))
							verDirectorio(name);
					}
					
					
				}
			}
		}catch(IOException e) {
			System.err.println("Hubo un problema para entrar en una carpeta");
		}
		
		jerLv-=1;
	}
	
}
