package hotel;

import java.util.*; 

public class Hotel {
   
    private int numeroQuartos;
    private int id;

    public Hotel(){
    }


    public void setId(int id){
        this.id = id;
    }

    public int getId(){
        return this.id;
    }

    public void setNumero_quartos(int numeroQuartos){
        this.numeroQuartos = numeroQuartos;
    }

    public int getNumero_quartos(){
        return this.numeroQuartos;
    }

}
