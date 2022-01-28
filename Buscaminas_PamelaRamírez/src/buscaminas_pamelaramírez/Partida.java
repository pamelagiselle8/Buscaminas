
package buscaminas_pamelaramírez;

import java.util.Random;
import java.util.Scanner;


public class Partida {
    Scanner lea = new Scanner(System.in);
    Random r = new Random();
    
    int nivel = 0, correcta = 0;
    char[][] tab, tabm;
    char mina = 'x', casilla = ' ';
    
    public void menú(){
        System.out.println("Niveles del juego:");
        System.out.println("1) 4x4, 4 minas");
        System.out.println("2) 8x8, 8 minas");
        System.out.println("3) 16x16, 16 minas");
        System.out.println("0) Salir" + "\n");
        System.out.print("Ingrese el nivel a jugar: ");
        nivel = lea.nextInt();
        System.out.println();
        correcta = 0;
        
        switch (nivel){
            case 1: {
                nivel = 4;
                jugar();
                break;
            }
            case 2: {
                nivel = 8;
                jugar();
                break;
            }
            case 3: {
                nivel = 16;
                jugar();
                break;
            }
            case 0: {
                System.out.println("¡Gracias por jugar Buscaminas!");
                break;
            }
            default: {
                System.out.println("\n" + "Ingrese una opción válida." + "\n");
                menú();
                break;
            }
        }
    }
    
    public void jugar(){
        tab = tablero();
        tabm = tablero();
        minas();
        numeros();
        imprimir(tab, 0, 0);
        System.out.println("\n");
        mostrar();
    }
    
    public void mostrar(){
        System.out.print("Ingrese fila: ");
        int x = lea.nextInt();
        System.out.print("Ingrese columna: ");
        int y = lea.nextInt();
        x--;
        y--;
        if (correcta >= (nivel * nivel) - nivel - 1) {
            System.out.println("\n" + "¡Felicidades!" + "\n");
            fin();
        }
        else{
            if (tabm[x][y] != mina) {
                correcta++;
                tab[x][y] = tabm[x][y];
                System.out.println("\n");
                imprimir(tab, 0, 0);
                System.out.println("\n");
                mostrar();
            }
            else{
                System.out.println("\n" +"Game over!" + "\n");
                imprimir(tabm, 0, 0);
                fin();
            }
        }
    }
    
    public void fin(){
        System.out.println("\n" + "\n" + "¿Desea seguir jugando?");
        System.out.println("1) Seguir jugando");
        System.out.println("2) Salir");
        System.out.print("Ingrese una opción: ");
        int ciclo = lea.nextInt();
        switch(ciclo){
            case 1: {
                System.out.println("\n");
                menú();
                break;
            }
            case 2: {
                break;
            }
            default: {
                System.out.println("\n" + "Ingrese una opción válida." + "\n");
            }
        }
    }
    
    public void imprimir(char[][] matriz, int i, int j){
        if (i == matriz.length - 1 && j == matriz[0].length - 1) {
            System.out.print("[ " + matriz[i][j] + " ]");
        } 
        else {
            if (j == matriz[0].length - 1) {
                System.out.println("[ " + matriz[i][j] + " ]" + "\n"); 
                imprimir(matriz, i + 1, 0);
            } else {
                System.out.print("[ " + matriz[i][j] + " ] ");
                imprimir(matriz, i, j + 1);
            }
        }
    }
    
    public char[][] tablero(){
        char[][] tablero = new char[nivel][nivel];
        for (int i = 0; i < nivel; i++) {
            for (int j = 0; j < nivel; j++) {
                tablero[i][j] = casilla; 
            }
        }
        return tablero;
    }
    
    public void minas(){
        int cont = 0;
        while (cont < nivel) {
            int filas = r.nextInt(nivel - 1);
            int colum = r.nextInt(nivel - 1);
            if (tabm[filas][colum] != mina) {
                tabm[filas][colum] = mina;
                cont++;
            }
        }
    }
    
    public void numeros(){
        for (int i = 0; i < nivel; i++) {
            for (int j = 0; j < nivel; j++) {
                if (tabm[i][j] != mina) {
                    int cont = 0;
                    for (int k = -1; k < 2; k++) {
                        for (int l = -1; l < 2; l++) {
                            try{
                                if (tabm[i + k][j + l] == mina) {
                                    cont++;
                                }
                            }
                            catch(Exception e){
                                
                            }
                        }
                    }
                    if (cont > 0) {
                        tabm[i][j] = (char) (cont + '0');
                    }
                }
            }
        }
    }
}
