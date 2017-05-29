/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModuloEstudiante;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.JTable;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

/**
 *
 * @author Wilson Xicará
 */
public class ExportarAExcel {
    private File archivoExcel;
    private ArrayList<JTable> listaTablas;
    private ArrayList<String> listaNombresLibrosDeExcel;

    /**
     * Ésta clase sólo soporta exportar archivos de excel con extensión .xls (no .xlsx que son las más recientes)
     * @param archivoExcel
     * @param listaTablas
     * @param listaNombresLibrosDeExcel
     * @throws Exception 
     */
    public ExportarAExcel(File archivoExcel, ArrayList<JTable> listaTablas, ArrayList<String> listaNombresLibrosDeExcel) throws Exception {
        this.archivoExcel = archivoExcel;
        this.listaTablas = listaTablas;
        this.listaNombresLibrosDeExcel = listaNombresLibrosDeExcel;
        if (listaNombresLibrosDeExcel.size()!=listaTablas.size()) {
            throw new Exception ("Error");
        }
    }
    
    public static void exportar_tablas(File archivoExcel, ArrayList<JTable> listaTablas, ArrayList<String> listaNombresLibrosDeExcel, boolean tituloColumnas) {
        // En caso de que no se especifica el nombre de las Hojas de Excel, se agregarán nombres por defecto
        if (listaNombresLibrosDeExcel.isEmpty() || (listaTablas.size()!=listaNombresLibrosDeExcel.size())) {
            listaNombresLibrosDeExcel.clear();
//            int cantidad = listaTablas.get(0).setN
        }
    }
    public boolean exportar_tablas() {
        try {
            WritableWorkbook libroExcel = Workbook.createWorkbook(archivoExcel);
            for (int index=0; index<listaTablas.size(); index++){
                JTable table=listaTablas.get(index);    // Se obtiene la tabla a exportar
                System.out.println("Tabla = "+table.getName());
                WritableSheet hojaExcel = libroExcel.createSheet(listaNombresLibrosDeExcel.get(index), 0);
                int fil, col, contFil = table.getRowCount(), contCol = table.getColumnCount();
                //Para que salga el titulo de las columnas
                for (col=0; col<contCol; col++)
                    hojaExcel.addCell(new Label(col, 0, table.getColumnName(col)));
                // El método .addCell crea una nueva celda. La celda es un Label.
                // El constructor del Label es Label(columna, fila, texto_a_mostrar)
                
                // Ahora se guardan los datos
                for(fil=0; fil<contFil; fil++)
                    for(col=0; col<contCol; col++)
                        hojaExcel.addCell(new Label(col, fil+1, (String)table.getValueAt(fil, col)));
            }
            libroExcel.write();
            libroExcel.close();
            return true;
        }
        catch (IOException | WriteException e) {
            System.out.println("Excepción:\n"+e.getMessage());
            return false;
        }
    }
}
