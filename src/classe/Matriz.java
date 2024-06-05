/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classe;

/**
 *
 * @author misuka
 */
public class Matriz {
    public static double[][] copiarMatriz(double[][] matriz) {
        double[][] matrizCopia = new double[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizCopia[i][j] = matriz[i][j];
            }
        }
        return matrizCopia;
    }
    
    public static String printMatriz(double[][] matriz) {
        String saida = "";
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                saida += " " + matriz[i][j];
            }
            saida += "\n";
        }
        saida += "\n\n";
        return saida;
    }

    public static String printVetor(double[] vetor) {
        String saida = "";
        for (int i = 0; i < vetor.length; i++) {
            saida += " " + vetor[i];
        }
        saida += "\n";
        return saida;
    }
    
    public static double calcularDeterminante(double[][] matriz) {
        double pivot, multiplicador, determinante = 1, aux, elemento;
        if (matriz.length == 1) {
            return matriz[0][0];
        }
        if(matriz.length == 2){
            return matriz[0][0]*matriz[1][1] - matriz[0][1]*matriz[1][0];
        }
        double[][] matrizAuxiliar = copiarMatriz(matriz);
        //Algoritmo de Gauss-Jordan
        for(int i = 0; i<= matrizAuxiliar.length-2; i++){
            pivot = matrizAuxiliar[i][i];
            //Se o pivô é igual a 0, troca a linha atual com a primeira linha que não possui um elemento = 0 na coluna atual
            if(pivot == 0){
                for(int p = i+1; p<=matrizAuxiliar.length-1; p++){
                    if(matrizAuxiliar[p][i]!= 0){
                        for(int q = 0; q<=matrizAuxiliar.length-1; q++){
                            aux = matrizAuxiliar[i][q];
                            matrizAuxiliar[i][q] = matrizAuxiliar[p][q];
                            matrizAuxiliar[p][q] = aux;
                        }
                        break;
                    }
                }
                //Troca o sinal da determinante e atualiza o pivô
                determinante = determinante*(-1);
                pivot = matrizAuxiliar[i][i];
            }
            /*System.out.println("Pivot: ");
            System.out.println(pivot);*/
            for(int j = i+1; j<=matrizAuxiliar.length-1; j++){
                elemento = matrizAuxiliar[j][i];
                /*System.out.println("Elemento: ");
                System.out.println(elemento);*/
                multiplicador = elemento/pivot;
                /*System.out.println("Multiplicador: ");
                System.out.println(multiplicador);*/
                for(int k = 0; k<=matrizAuxiliar.length-1; k++){
                    matrizAuxiliar[j][k] = matrizAuxiliar[j][k] - multiplicador*matrizAuxiliar[i][k];
                }
            }
        }
        //printMatriz(matriz);
        for(int l = 0; l <= matrizAuxiliar.length-1; l++){
            determinante = determinante*matrizAuxiliar[l][l];
        }
        return Math.round(determinante);
    }
    
    public static double[] resolverSistema(double[][] matriz, double[] vetor) {
        double[] solucao = new double[vetor.length];
        double determinantePrincipal = calcularDeterminante(matriz);
        double[][][] vetorDeMatrizesSecundarias = new double[matriz.length][matriz.length][matriz.length];
        double[][] matrizAuxiliar;
        if(determinantePrincipal == 0){
            return null;
        }
        //System.out.println("Determinante Principal: " + determinantePrincipal);
        for(int k = 0; k < matriz.length; k++){
            vetorDeMatrizesSecundarias[k] = copiarMatriz(matriz);
            for(int i = 0; i < matriz.length; i++){
                vetorDeMatrizesSecundarias[k][i][k] = vetor[i];
            }
            //printMatriz(vetorDeMatrizesSecundarias[k]);
        }
        for(int i = 0; i < matriz.length; i++){
            matrizAuxiliar = copiarMatriz(vetorDeMatrizesSecundarias[i]);
            //System.out.println(calcularDeterminante(matrizAuxiliar));
            solucao[i] = calcularDeterminante(matrizAuxiliar)/determinantePrincipal;
        }
        return solucao;
    }
}
