package Telas;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;


public class TelaProduto {
	 private JTextField nomeProdutoField;
	    private JTextField quantidadeField;
	    private JButton adicionarButton;
	    private JTextArea listaProdutosArea;
	    private List<Produto> listaProdutos;

    public void ControleEstoqueApp() {
        listaProdutos = new ArrayList<>();

        // Criação dos componentes
        nomeProdutoField = new JTextField(15);
        quantidadeField = new JTextField(5);
        adicionarButton = new JButton("Adicionar ao Estoque");
        listaProdutosArea = new JTextArea(10, 20);
        JScrollPane scrollPane = new JScrollPane(listaProdutosArea);

        // Configuração do layout
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Nome do Produto:"), constraints);

        constraints.gridx = 1;
        panel.add(nomeProdutoField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Quantidade:"), constraints);

        constraints.gridx = 1;
        panel.add(quantidadeField, constraints);

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 2;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(adicionarButton, constraints);

        constraints.gridy = 3;
        panel.add(scrollPane, constraints);

        // Configuração dos eventos
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoEstoque();
            }
        });

        // Criação da janela
        JFrame frame = new JFrame("Controle de Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }

    private void adicionarAoEstoque() {
        String nomeProduto = nomeProdutoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());

        // Verifica se o produto já existe na lista
        boolean produtoExistente = false;
        for (Produto p : listaProdutos) {
            if (p.getNome().equals(nomeProduto)) {
                p.setQuantidade(p.getQuantidade() + quantidade);
                produtoExistente = true;
                break;
            }
        }

        if (!produtoExistente) {
            Produto novoProduto = new Produto(nomeProduto, quantidade);
            listaProdutos.add(novoProduto);
        }

        // Atualiza a área de texto com a lista de produtos
        listaProdutosArea.setText("");
        for (Produto p : listaProdutos) {
            listaProdutosArea.append(p.toString() + "\n");
        }

        // Limpa os campos de entrada
        nomeProdutoField.setText("");
        quantidadeField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TelaProduto();
            }
        });
    }

    public static class Produto {
        private String nome;
        private int quantidade;

        public Produto(String nome, int quantidade) {
            this.nome = nome;
            this.quantidade = quantidade;
        }

        public String getNome() {
            return nome;
        }

        public int getQuantidade() {
            return quantidade;
        }

        public void setQuantidade(int quantidade) {
            this.quantidade = quantidade;
        }

        @Override
        public String toString() {
            return nome + ": " + quantidade;
        }
    }
}
