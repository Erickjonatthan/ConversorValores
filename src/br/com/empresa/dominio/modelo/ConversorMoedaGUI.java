package br.com.empresa.dominio.modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ConversorMoedaGUI {
    private JFrame frame;
    private JComboBox<String> comboBoxOrigem;
    private JComboBox<String> comboBoxSaida;
    private JTextField textFieldValor;
    private JLabel labelResultado;

    public ConversorMoedaGUI() {
        
        frame = new JFrame("Conversor de Moeda");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 200);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        frame.add(panel);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        String[] opcoes = { "BRL", "USD", "EUR", "GBP","ARS", "CLP" };

        comboBoxOrigem = new JComboBox<>(opcoes);
        comboBoxSaida = new JComboBox<>(opcoes);
        textFieldValor = new JTextField(10);
        textFieldValor.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if (!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE)) {
                    e.consume(); // Impede a inserção do caractere
                    JOptionPane.showMessageDialog(null, "Valor inválido!", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        labelResultado = new JLabel();

        JButton buttonConverter = new JButton("Converter");
        buttonConverter.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String siglaOrigem = (String) comboBoxOrigem.getSelectedItem();
                String siglaSaida = (String) comboBoxSaida.getSelectedItem();
                double valorParaConverter = Double.parseDouble(textFieldValor.getText());

                ConversorMoeda conversorMoeda = new ConversorMoeda();
                double valorConvertido = valorParaConverter * conversorMoeda.converte(siglaOrigem, siglaSaida);
                String resultadoFormatado = String.format("%.2f unidades de %s equivalem a %.2f unidades de %s",valorParaConverter, siglaOrigem, valorConvertido, siglaSaida);
                labelResultado.setText(resultadoFormatado);
                // Perguntar ao usuário se deseja continuar no programa
                int resposta = JOptionPane.showConfirmDialog(null, "Deseja continuar no programa?", "Continuar?",
                        JOptionPane.YES_NO_CANCEL_OPTION);
                if (resposta == JOptionPane.YES_OPTION) {
                    // Voltar ao menu principal
                    labelResultado.setText(""); // Limpar o resultado
                    // ... Código para retornar ao menu principal
                } else if (resposta == JOptionPane.NO_OPTION) {
                    // Mostrar mensagem "Programa finalizado"
                    JOptionPane.showMessageDialog(null, "Programa finalizado", "Fim", JOptionPane.INFORMATION_MESSAGE);
                    System.exit(0);
                } else if (resposta == JOptionPane.CANCEL_OPTION) {
                    // Mostrar mensagem "Programa concluído"
                    JOptionPane.showMessageDialog(null, "Programa concluído", "Fim", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Moeda de Origem:"), gbc);
        gbc.gridx = 1;
        panel.add(comboBoxOrigem, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Moeda de Saída:"), gbc);
        gbc.gridx = 1;
        panel.add(comboBoxSaida, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Valor a ser convertido:"), gbc);
        gbc.gridx = 1;
        panel.add(textFieldValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; 
        panel.add(buttonConverter, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; 
        panel.add(labelResultado, gbc);

        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new ConversorMoedaGUI();
            }
        });
    }
}