/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import modelo.ModeloPronosticador;
import vista.VentanaPrincipal;

/**
 *
 * @author Paul
 */
public class ControladorPronosticador 
{
    ModeloPronosticador modelo;
    VentanaPrincipal ventana;
    public ControladorPronosticador(ModeloPronosticador _modelo, VentanaPrincipal _ventana)
    {
        this.modelo = _modelo;
        this.ventana = _ventana;
    }
    
    class BtnListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getActionCommand().equalsIgnoreCase("Agregar Año"))
            {
                
            }
        }
        
    }
}
