package novo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.util.List;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

public class TelaProdutoAdicionar {
    private JTextField nomeProdutoField;
    private JTextField quantidadeField;
    private JTextField precoField;
    private JTextField custoField;
    private JButton adicionarButton;
    private JButton voltarButton;
    private List<Produto> listaProdutos;

    public void controleEstoqueApp() {

        // Criação dos componentes
        nomeProdutoField = new JTextField(15);
        quantidadeField = new JTextField(5);
        precoField = new JTextField(10);
        custoField = new JTextField(10);
        adicionarButton = new JButton("Adicionar ao Estoque");
        voltarButton = new JButton("Voltar");

        // Configuração do layout
        JPanel panel = new JPanel(new GridBagLayout());
        configurarLayout(panel);

        // Criação da janela
        JFrame frame = criarJanela(panel);

        // Configuração dos eventos
        configurarBotoes(frame);
    }

    private void configurarBotoes(final JFrame frame) {
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarAoEstoque();
                frame.dispose(); // Fecha a janela atual ao adicionar um produto
            }
        });

        voltarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose(); // Fecha a janela atual ao voltar
            }
        });
    }

    private JFrame criarJanela(JPanel panel) {
        JFrame frame = new JFrame("Controle de Estoque");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
        return frame;
    }

    private void configurarLayout(JPanel panel) {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.insets = new Insets(5, 5, 5, 5);
        constraints.anchor = GridBagConstraints.WEST;

        criarCampos(constraints, panel);

        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(adicionarButton, constraints);

        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.anchor = GridBagConstraints.CENTER;
        panel.add(voltarButton, constraints);
    }

    private void criarCampos(GridBagConstraints constraints, JPanel panel) {
        // Campo do nome do produto
        constraints.gridx = 0;
        constraints.gridy = 0;
        panel.add(new JLabel("Nome do Produto:"), constraints);

        constraints.gridx = 1;
        panel.add(nomeProdutoField, constraints);

        // Campo de quantidade do produto
        constraints.gridx = 0;
        constraints.gridy = 1;
        panel.add(new JLabel("Quantidade:"), constraints);

        constraints.gridx = 1;
        panel.add(quantidadeField, constraints);

        // Campo de preço do produto
        constraints.gridx = 0;
        constraints.gridy = 2;
        panel.add(new JLabel("Preço:"), constraints);

        constraints.gridx = 1;
        panel.add(precoField, constraints);

        // Campo de custo do produto
        constraints.gridx = 0;
        constraints.gridy = 3;
        panel.add(new JLabel("Custo:"), constraints);

        constraints.gridx = 1;
        panel.add(custoField, constraints);
    }

    private void adicionarAoEstoque() {
        String nomeProduto = nomeProdutoField.getText();
        int quantidade = Integer.parseInt(quantidadeField.getText());
        BigDecimal preco = new BigDecimal(precoField.getText());
        BigDecimal custo = new BigDecimal(custoField.getText());

        listaProdutos = ProdutoRepositorio.BuscarTodosOsProdutos();

        boolean produtoExistente = false;

        for (Produto p : listaProdutos) {
            if (p.getNome().equals(nomeProduto)) {
                p.setQuantEstoque(p.getQuantEstoque() + quantidade);
                produtoExistente = true;
                break;
            }
        }

        if (!produtoExistente) {
            Produto novoProduto = new Produto(quantidade, nomeProduto, quantidade, preco, custo);
            ProdutoRepositorio.CriarProduto(novoProduto);
        }

        // Limpa os campos de entrada
        nomeProdutoField.setText("");
        quantidadeField.setText("");
        precoField.setText("");
        custoField.setText("");
    }
}
