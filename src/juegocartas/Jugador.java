
package juegocartas;

import java.util.Random;
import javax.swing.JPanel;

public class Jugador {
    
    public int TOTAL_CARTAS = 10;
    
    private Random r = new Random();
    
    private Carta[] cartas;
    
    public void repartir(){
        
        cartas = new Carta[TOTAL_CARTAS];
        
        for(int i=0; i< cartas.length; i++){
             cartas [i] = new Carta(r);   
        }
    }
    
    public void mostrar (JPanel pnl){
        pnl.removeAll();
        
         for(int i=0; i< cartas.length; i++){
           cartas [i].mostrar(pnl, 5+i*40, 5);   
         }
         pnl.repaint();
    }
    
    public String getGrupos(){
    
        String mensaje = "No hay grupos";
        
        int[] contadores = new int [NombreCarta.values().length];
        
        for (int i = 0; i < cartas.length; i++){
            
            contadores[cartas[i].getNombre().ordinal()]++;
    
        }
        
        int totalGrupos=0;
        
        for (int i = 0; i < contadores.length; i++){
            if(contadores[i] >= 2){
                totalGrupos++;
            }    
        }
        
        if(totalGrupos > 0){
            mensaje = "los grupos encontrados fueron : \n";
            for (int i = 0; i < contadores.length; i++){
                if (contadores[i] >= 2){
                    mensaje += Grupo.values()[contadores[i]] + " de "+ NombreCarta.values()[i]+"\n";
                }
            }
        }
        
    return mensaje;
    
    }

    public String getEscaleras() {
        StringBuilder mensaje = new StringBuilder("El jugador tiene las siguientes escaleras:\n");

        for (Pinta pinta : Pinta.values()) {

            boolean[] cartasEnEscalera = new boolean[NombreCarta.values().length];
            boolean escaleraEncontrada = false;

            for (NombreCarta nombre : NombreCarta.values()) {
                int contador = 0;

                for (int i = 0; i < 10; i++) {
                    if (cartas[i].getPinta() == pinta && cartas[i].getNombre() == nombre) {
                        contador++;
                    }
                }

                if (contador >= 1) {
                    cartasEnEscalera[nombre.ordinal()] = true;
                }
            }

            for (int i = 1; i < cartasEnEscalera.length - 3; i++) {
                if (cartasEnEscalera[i] && cartasEnEscalera[i + 1] && cartasEnEscalera[i + 2] && cartasEnEscalera[i + 3]) {
                    escaleraEncontrada = true;
                    mensaje.append("Pinta ").append(pinta.name()).append(": ");
                    mensaje.append(NombreCarta.values()[i]).append(", ");
                    mensaje.append(NombreCarta.values()[i + 1]).append(", ");
                    mensaje.append(NombreCarta.values()[i + 2]).append(", ");
                    mensaje.append(NombreCarta.values()[i + 3]).append("\n");
                }
            }

            if (!escaleraEncontrada) {
                mensaje.append("No hay escaleras en la pinta ").append(pinta.name()).append("\n");
            }
        }

        return mensaje.toString();
    }


    public int calcularPuntaje() {
        int puntaje = 0;

        for (int i = 0; i < TOTAL_CARTAS; i++) {
            NombreCarta nombre = cartas[i].getNombre();

            if (nombre == NombreCarta.AS || nombre == NombreCarta.JACK || nombre == NombreCarta.QUEEN || nombre == NombreCarta.KING) {
                puntaje += 10;
            } else {
                puntaje += nombre.ordinal() + 1;
            }
        }

        return puntaje;
    }

}