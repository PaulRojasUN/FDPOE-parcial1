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
        ventana.addBtnNuevoPronosticoListener(btnListener);
        ventana.addBtnCalcularListener(btnListener);
        
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
                    
                    try 
                    {
                        float cantidadVentas = parseFloat(ventana.getTxtCantidadVenta());
                        if (cantidadVentas>0){

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

                                ventana.setTotalPorcentajes(modelo.sumarLista(ventana.getValoresDeColumnaHistorico(3, 1))+"");


                            }
                        }
                        else
                        {
                            System.out.println("Ingrese Valores Mayores a cero");
                        }
                    } 
                    catch (Exception ex)
                    {
                        System.out.println("Ingrese Valores Válidos");
                    }
                    ventana.setTxtCantidadVenta("");
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

                        if (respuesta > 0)
                        {
                        ventana.setCantidadVentasHistorico(respuesta, ventana.getFilaSeleccionadaHistorico());
                        ventana.llenarHistorico(modelo.operarCantidadesVenta(ventana.getValoresDeColumnaHistorico(1, 0)));
                        ventana.setTotalPorcentajes(modelo.sumarLista(ventana.getValoresDeColumnaHistorico(3, 1))+"");
                        }
                        else
                        {
                            System.out.println("Ingrese valores mayores a cero");
                        }
                    } 
                    catch (Exception ex)
                    {
                        System.out.println("Ingrese un dato válido");
                    }
                }
            } 
            else if (e.getActionCommand().equalsIgnoreCase("Nuevo Pronóstico"))
            {
                ventana.vaciarHistorico();
                ventana.vaciarPronostico();
                ventana.setTxtPromedio("");
                ventana.setCantidadPronostico("");
            }
            else if (e.getActionCommand().equalsIgnoreCase("Calcular"))
            {
                try
                {
                    int cantidadPronostico = parseInt(ventana.getTxtCantidad());
                    
                    if (ventana.getCantidadFilasHistorica()>=3)
                    {
                        if (cantidadPronostico>=2)
                        {
                            ventana.vaciarPronostico();
                            ventana.setTxtPromedio(modelo.calcularPromedioVar(ventana.getValoresDeColumnaHistorico(3, 1))+"");
                            ventana.llenarPronosticos(modelo.calcularPronostico(
                                   parseFloat(ventana.getValorHistorico(ventana.getCantidadFilasHistorica()-1,1)),
                                   cantidadPronostico, 
                                   parseFloat(ventana.getTxtPromedio())));
                        }
                        else
                        {
                            System.out.println("Por favor, ingrese un número mayor o igual a dos");
                        }
                    }
                    else
                    {
                        System.out.println("Agregue como mínimo tres cantidades de Ventas");
                    }
                      
                } 
                catch (Exception ex)
                {
                    System.out.println("Ingrese una cantidad entera válida");
                }

                
            }
        }
     
        }
        
    }
