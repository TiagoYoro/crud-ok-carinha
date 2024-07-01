package novo;

import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Produto;
import Repositorio.ProdutoRepositorio;

//metodo para mostra a tabela por completa 
public class TelaProdutoTabela {
	
	 private JButton addButton;
     private JButton editButton;
     private JButton deleteButton;
     private JButton backButton;
	 private List<Produto> listaProduto;
	 private JFrame frame;
	 private JTable table;

    public TelaProdutoTabela() { //metodo para mostrar janela da  tabela 
    	//titulo
	    frame = new JFrame("Produto");
	   //para sair da tabela 
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	    //chamando o medoto criartablea 
	    CreateTable(); 
	    //cria botoes (Adicionar Produto, Editar Produto, Excluir Produto, Voltar)
	    CreateButtons();         
	    // ajuste automaticamente o tamanho da janela 
	    frame.pack();
	    //Esta linha faz com que a janela seja centralizada na tela quando é exibida
	    frame.setLocationRelativeTo(null);
	    //exibe o jframe na tela 
	    frame.setVisible(true);
    }
    
    private void CreateTable(){//metodo para criar tabela 
    	//vetor 
    	String[] columnNames = {"Id", "Nome", "Quantidade", "Preço", "Custo"};
        //um modelo de tabela usando o columnNames como padrao , inicia com zero linhas 
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        //usamos a listaProduto ,estanciada nesta class ,
        //esta lista recebe o metodo de estanciar produtos (ou seje estanciamos objetos aqui nessa tb)
        //em seguida chama o metodo BuscarTodosOsProdutos() , que busca todos os itens do BD
        listaProduto = ProdutoRepositorio.BuscarTodosOsProdutos();
        
        for(Produto produto: listaProduto) {//percorre a listaProduto
        	      //é usado para adicionar uma nova linha à tabela
        	model.addRow(new Object[] {produto.getId(), produto.getNome(), produto.getQuantEstoque(),
        			produto.getPrecoVenda(), produto.getPrecoCompra()});
        }        
        table = new JTable(model);
        
        //responssavel pela escrolagem da tabela 
        JScrollPane scrollPane = new JScrollPane(table);
	    frame.add(scrollPane, BorderLayout.CENTER);
    }
    //criando os botoes da tb 
    private void CreateButtons() {
    	JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        addButton = new JButton("Adicionar Produto");
        editButton = new JButton("Editar Produto");
        deleteButton = new JButton("Excluir Produto");
        backButton = new JButton("Voltar");
        
        AddFunctionButtons(frame);

        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(backButton);
        
        //adiciona botoes na parte inferio da tb 
        frame.add(buttonPanel, BorderLayout.SOUTH);

    } 
    //adiciona funcionalidade aos botoes 
    private void AddFunctionButtons(final JFrame frame) {
    	//Button Add function
    	addButton.addActionListener(new ActionListener() {
    		
           //para definir o que acontece quando o botão é clicado.
            public void actionPerformed(ActionEvent e) {
            	TelaProdutoAdicionar tela = new TelaProdutoAdicionar();
            	tela.controleEstoqueApp();
                frame.dispose();
            }
        });
    	
    	//Button Edit function
    	
    	//Button Delet function
    	deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            	int produtoId = GetProdutoRow();
            	//pucha a class  ProdutoRepositorio junto com metodo dee delatar 
            	ProdutoRepositorio.DeletarProduto(produtoId);
            	//é usado para liberar os recursos alocados pelo quadro, encerrando sua execução.
            	frame.dispose();
            }
    	});
    }
    
    
    private int GetProdutoRow() {
    	int linhaSelecionada = table.getSelectedRow();

    	// Obter o valor na coluna "Id" da linha selecionada
    	Object valorId = table.getValueAt(linhaSelecionada, 0); // 0 é o índice da coluna "Id"
    	int produtoId = ((Integer) valorId).intValue();
    	//retorna o produto com o id selecionado 
    	return produtoId;

    }
    
    
    public static void main(String[] args) {
	}
}