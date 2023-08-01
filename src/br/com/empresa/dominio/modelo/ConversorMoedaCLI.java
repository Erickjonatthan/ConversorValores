package br.com.empresa.dominio.modelo;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ConversorMoedaCLI {
    private Scanner scanner;
    private Map<Integer, String> moedasMap;
    private ConversorMoeda conversorMoeda;

    public ConversorMoedaCLI() {
        scanner = new Scanner(System.in);

        moedasMap = new HashMap<>();

        

        moedasMap.put(1, "BRL");
        moedasMap.put(2, "USD");
        moedasMap.put(3, "EUR");
        moedasMap.put(4, "GBP");
        moedasMap.put(5, "ARS");
        moedasMap.put(6, "CLP");

        conversorMoeda = new ConversorMoeda();
    }

    public void run() {
        int moedaOrigem = obterMoedaOrigem();
        int moedaSaida = obterMoedaSaida();

        String siglaOrigem = moedasMap.get(moedaOrigem);
        String siglaSaida = moedasMap.get(moedaSaida);

        System.out.println("Informe o valor a ser convertido:");
        double valorParaConverter = scanner.nextDouble();

        double valorConvertido = valorParaConverter * conversorMoeda.converte(siglaOrigem, siglaSaida);

        System.out.println(valorParaConverter + " unidades de " + siglaOrigem + " equivalem a " + valorConvertido + " unidades de " + siglaSaida);
    }

    private int obterMoedaOrigem() {
        System.out.println("Selecione a moeda de origem:");
        for (int key : moedasMap.keySet()) {
            System.out.println(key + " - " + moedasMap.get(key));
        }
        return scanner.nextInt();
    }

    private int obterMoedaSaida() {
        System.out.println("Selecione a moeda de saída:");
        for (int key : moedasMap.keySet()) {
            System.out.println(key + " - " + moedasMap.get(key));
        }
        return scanner.nextInt();
    }

}

