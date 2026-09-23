package unidad1;

import java.io.File;

public class Ejercicio1 {
	
	private static int jerLv=0; //Jerarchy Level, Nivel de Jerarquía
	
	/**
	* Muestra la lista de ficheros en el directorio actual
	* Se utiliza método list() que devuelve un array de String con los nombres de los ficheros y directorios
	* Para indicarle directorio actual creamos vble dir=. y se la pasamos al objeto File
	* El segundo objeto File lo utilizamos para saber si es un fichero o directorio. Utilizamos el segundo contructor.
	*
	*/
	
	public static void main(String[] args) {
		verDirectorio("C:\\");
		//verDirectorio("."); //Directorio actual
	}
	
	/*private static void verDirectorio(String dir) {
		//String dir = "C:\\Usuarios\\Tarde\\Datos";
		jerLv+=1;
		if(jerLv>=10) {
			System.err.println("Demasiadas carpetas");
			System.exit(-1);
		}
		
		File file= new File(dir);
		File file2;
		for(String f: file.list()) {
			file2 = new File(f);
			
			for(int i=0; i<jerLv; i++)
				System.out.print("\t");
			
			System.out.printf("* %s ///// ", f);
			System.out.printf("Directorio?: %s; Fichero?: %s\n",
								file2.isDirectory() ? "Y":"N",
								file2.isFile() ? "Y":"N");
			
			if(file2.isDirectory())
				verDirectorio(f);
		}
		
		jerLv-=1;
	}*/

	private static void verDirectorio(String dir) {
		//String dir = "C:\\Usuarios\\Tarde\\Datos";
		jerLv+=1;
		if(jerLv>=10) {
			System.err.println("Demasiadas carpetas");
			System.exit(-1);
		}
		
		File[] files= new File(dir).listFiles();
		if(files!=null)
			for(File f: files) {
				for(int i=0; i<jerLv; i++)
					System.out.print("\t");
				
				System.out.printf("* %s ///// ", f.getAbsolutePath());
				System.out.printf("Directorio?: %s; Fichero?: %s\n",
									f.isDirectory() ? "Y":"N",
									f.isFile() ? "Y":"N");
				
				if(f.isDirectory())
					verDirectorio(f.getAbsolutePath());
			}
		/*else {
			File fff= new File(dir);
			System.err.println("\n." + fff.getName() + " /// " + fff.list() + " /// " + fff.canWrite());
		} //Por algún motivo devuelve null al listar los archivos dentro de la carpeta Program Files */
		
		jerLv-=1;
	}
}
