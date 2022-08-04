/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package pronosticadorventas;

import controlador.ControladorPronosticador;
import modelo.ModeloPronosticador;
import vista.VentanaPrincipal;

/**
 *
 * @author Paul
 */
public class PronosticadorVentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) 
    {
        ModeloPronosticador modelo = new ModeloPronosticador();
        VentanaPrincipal ventana = new VentanaPrincipal();
        ControladorPronosticador controlador =  new ControladorPronosticador(modelo, ventana);
    }
    
}
