/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import static java.lang.Float.parseFloat;
import java.util.ArrayList;
import java.util.Arrays;

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
     
     public ArrayList<ArrayList<String>> operarCantidadesVenta(ArrayList<String> _valores)
     {
         ArrayList<ArrayList<String>> lista = new ArrayList<ArrayList<String>>();
         int cantidad = _valores.size();
         for (int i = 0; i < cantidad; i++)
         {
             if (i == 0)
             {  
                 lista.add(new ArrayList<String>(Arrays.asList("", "")));
             }
             else
             {
                 float valor1 = parseFloat(_valores.get(i));
                 float valor2 = parseFloat(_valores.get(i-1));
                 
                 lista.add(new ArrayList<String>(Arrays.asList(diferencia(valor1,valor2)+"", calcularPorcentajeVar(valor1,valor2)+"")));
             }
             
         }
         return lista;
     }
     
     public ArrayList<String> calcularPronostico(float _lastCantidad, int _cantidad,float _promedio)
     {
         ArrayList<String> lista = new ArrayList<String>();
         for (int i = 0; i < _cantidad; i++)
         {
             if (i == 0)
             {
                 lista.add(_lastCantidad+_promedio*_lastCantidad+"");
             }
             else
             {
                 lista.add((parseFloat(lista.get(i-1))+(_promedio*parseFloat(lista.get(i-1))))+"");
             }
         }
         return lista;
     }
     
}
