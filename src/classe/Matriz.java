/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package classe;

/**
 *
 * @author Gabriel Derrel & Lucas Nunes
 */
public class Matriz {
    double[][] matriz = null;

    public double[][] getMatriz() throws Exception{
        return matriz;
    }

    public void setMatriz(double[][] matriz) throws Exception{
        this.matriz = matriz;
    }

    public Matriz(double[][] matriz) {
        this.matriz = matriz;
    }
    
    private double[][] copiarMatriz(double[][] matriz) throws Exception{
        double[][] matrizCopia = new double[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizCopia[i][j] = matriz[i][j];
            }
        }
        return matrizCopia;
    }
    
    public static String printMatriz(double[][] matriz) throws Exception{
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

    /*public static String printVetor(double[] vetor) throws Exception{
        String saida = "";
        for (int i = 0; i < vetor.length; i++) {
            saida += " " + vetor[i];
        }
        saida += "\n";
        return saida;
    }*/
    
    private double calcularDeterminante(double[][] matriz) throws Exception{
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
            for(int j = i+1; j<=matrizAuxiliar.length-1; j++){
                elemento = matrizAuxiliar[j][i];
                multiplicador = elemento/pivot;
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
    
    public double[] resolverSistema(double[] vetor) throws Exception{
        //Método de Cramer
        double[] solucao = new double[vetor.length];
        double determinantePrincipal = calcularDeterminante(matriz);
        //Vetor das matrizes Mx, My, Mz, Mw e etc.
        double[][][] vetorDeMatrizesSecundarias = new double[matriz.length][matriz.length][matriz.length];
        double[][] matrizAuxiliar;
        if(determinantePrincipal == 0) throw new Exception("O Sistema não possui solução");
        //Substitui a coluna k pelo vetor b
        for(int k = 0; k < matriz.length; k++){
            vetorDeMatrizesSecundarias[k] = copiarMatriz(matriz);
            for(int i = 0; i < matriz.length; i++){
                vetorDeMatrizesSecundarias[k][i][k] = vetor[i];
            }
        }
        //Aplicação da regra de Cramer
        for(int i = 0; i < matriz.length; i++){
            matrizAuxiliar = copiarMatriz(vetorDeMatrizesSecundarias[i]);
            solucao[i] = calcularDeterminante(matrizAuxiliar)/determinantePrincipal;
        }
        return solucao;
    }
}
