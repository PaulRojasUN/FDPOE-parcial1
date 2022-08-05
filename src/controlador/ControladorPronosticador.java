/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Float.parseFloat;
import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;
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
        
        ventana.setVisible(true);
        ventana.setLocationRelativeTo(null);
        
        BtnListener btnListener = new BtnListener();
        ventana.addBtnAgregarYearListener(btnListener);
        ventana.addBtnBorrarYearListener(btnListener);
        ventana.addBtnModificarYearListener(btnListener);
    }
    
    class BtnListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            if (e.getActionCommand().equalsIgnoreCase("Agregar Año"))
            {
                
                if (!"".equals(ventana.getTxtCantidadVenta()))
                {
                    float cantidadVentas = parseFloat(ventana.getTxtCantidadVenta());
                    try 
                    {


                        System.out.println("Cantidad: "+ventana.getCantidadFilasHistorica());
                        if (ventana.getCantidadFilasHistorica() == 0)
                        {
                            ventana.agregarFilaHistorica(1+"", cantidadVentas+"", "", "");
                        }
                        else
                        {
                            System.out.println(ventana.getValoresDeColumnaHistorico(1, 0));
                            ventana.agregarFilaHistorica(ventana.getCantidadFilasHistorica()+1+"", cantidadVentas+"", "", "");
                            ventana.llenarHistorico(modelo.operarCantidadesVenta(ventana.getValoresDeColumnaHistorico(1, 0)));
                            
                            /*
                            int year = ventana.getCantidadFilasHistorica()+1;
                            int cantidad = parseInt(ventana.getTxtCantidadVenta());
                            float diferencia = modelo.diferencia(cantidadVentas, parseFloat(ventana.getValorHistorico(ventana.getCantidadFilasHistorica()-1, 1)));
                            float porcentaje = modelo.calcularPorcentajeVar(cantidadVentas, parseFloat(ventana.getValorHistorico(ventana.getCantidadFilasHistorica()-1,1)));

                            ventana.agregarFilaHistorica(year+"", cantidad+"", diferencia+"", porcentaje+"");
                            */
                            ventana.setTotalPorcentajes(modelo.sumarLista(ventana.getValoresDeColumnaHistorico(3, 1))+"");
                            
                            
                        }
                        ventana.setTxtCantidadVenta("");
                    } 
                    catch (Exception ex)
                    {
                        System.out.println("Algo falló");
                        ex.printStackTrace();
                    }
                } else {
                   System.out.println("Ingrese una cantidad de ventas");
                }
            } 
            else if (e.getActionCommand().equalsIgnoreCase("Borrar año"))
            {
                if (ventana.getFilaSeleccionadaHistorico() == -1)
                {
                    System.out.println("Selecciona primero una fila");
                }
                else
                {
                    ventana.eliminarFilaHistorico(ventana.getFilaSeleccionadaHistorico());
                    ventana.reorganizarYearsAscendente();
                    ventana.llenarHistorico(modelo.operarCantidadesVenta(ventana.getValoresDeColumnaHistorico(1, 0)));
                    ventana.setTotalPorcentajes(modelo.sumarLista(ventana.getValoresDeColumnaHistorico(3, 1))+"");
                }
            }
            else if (e.getActionCommand().equalsIgnoreCase("Modifica Año"))
            {
                if (ventana.getFilaSeleccionadaHistorico() == -1)
                {
                    System.out.println("Selecciona primero una fila");
                }
                else
                {
                    int respuesta;
                    try
                    {
                    respuesta = parseInt(JOptionPane.showInputDialog("Asigne la nueva cantidad"));
                    
                    ventana.setCantidadVentasHistorico(respuesta, ventana.getFilaSeleccionadaHistorico());
                    ventana.llenarHistorico(modelo.operarCantidadesVenta(ventana.getValoresDeColumnaHistorico(1, 0)));
                    ventana.setTotalPorcentajes(modelo.sumarLista(ventana.getValoresDeColumnaHistorico(3, 1))+"");
                    } catch (Exception ex)
                    {
                        System.out.println("Ingrese un dato válido");
                    }
                }
            }
        }
        
    }
}
