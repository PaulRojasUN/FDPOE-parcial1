/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static java.lang.Float.parseFloat;
import java.util.ArrayList;

/**
 *
 * @author Paul
 */
public class ModeloPronosticador
{
     public ModeloPronosticador()
     {
         
     }
     
     public float diferencia(float _value1, float _value2)
     {
         return _value1 - _value2;
     }
     
     public float calcularPorcentajeVar(float _ventas1, float _ventas2)
     {
         return diferencia(_ventas1, _ventas2)/_ventas2;
     }
    
     public float sumarLista(ArrayList<String> _listaValores)
     {
         float total = 0;
         for (String valor: _listaValores)
         {
             total += parseFloat(valor);
         }
         return total;
     }
     
     public float calcularPromedioVar(ArrayList<String> _listaValores)
     {
         int cant = _listaValores.size();
         return sumarLista(_listaValores)/cant;
     }
     
}
