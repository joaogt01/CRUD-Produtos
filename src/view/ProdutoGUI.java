package view;

import model.Produto;
import service.ProdutoService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.IOException;
import java.util.List;

public class ProdutoGUI extends JFrame {

    private ProdutoService service;
    private JTable tabela;
    private DefaultTableModel tableModel;

    public ProdutoGUI() throws IOException {
        try {
            service = new ProdutoService();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar produtos: " + e.getMessage(),
                    "Erro", JOptionPane.ERROR_MESSAGE);
            service = new ProdutoService();
        }

        setTitle("Gerenciador de Produtos");
        setSize(1600, 900);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);


        tableModel = new DefaultTableModel(new Object[]{"ID", "Nome", "Preço (R$)", "Quantidade"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return column != 0; // ID não editável
            }
        };
        tabela = new JTable(tableModel);
        tabela.setFillsViewportHeight(true);
        tabela.setRowHeight(25);
        tabela.setSelectionBackground(new Color(173, 216, 230)); // azul claro
        tabela.setSelectionForeground(Color.BLACK);

        JScrollPane scrollPane = new JScrollPane(tabela);


        JPanel botoesPainel = new JPanel();
        JButton btnCadastrar = new JButton("Cadastrar");
        JButton btnDeletar = new JButton("Deletar");

        botoesPainel.add(btnCadastrar);
        botoesPainel.add(btnDeletar);

        add(scrollPane, BorderLayout.CENTER);
        add(botoesPainel, BorderLayout.SOUTH);

        atualizarTabela();


        btnCadastrar.addActionListener(e -> cadastrarProduto());
        btnDeletar.addActionListener(e -> deletarProduto());

        tableModel.addTableModelListener(e -> {
            if (e.getType() == javax.swing.event.TableModelEvent.UPDATE) {
                int row = e.getFirstRow();

                if (row < 0 || row >= service.listar().size()) return;

                Produto p = service.listar().get(row);

                try {
                    String nome = tableModel.getValueAt(row, 1).toString();
                    double preco = Double.parseDouble(tableModel.getValueAt(row, 2).toString());
                    int quantidade = Integer.parseInt(tableModel.getValueAt(row, 3).toString());

                    service.atualizar(row, nome, preco, quantidade);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(ProdutoGUI.this,
                            "Erro ao atualizar produto: " + ex.getMessage(),
                            "Erro", JOptionPane.ERROR_MESSAGE);
                    atualizarTabela(); // volta ao estado anterior
                }
            }
        });
    }

    private void atualizarTabela() {
        tableModel.setRowCount(0);
        List<Produto> produtos = service.listar();
        for (int i = 0; i < produtos.size(); i++) {
            Produto p = produtos.get(i);
            tableModel.addRow(new Object[]{i, p.getNome(), p.getPreco(), p.getQuantidade()});
        }
    }

    private void cadastrarProduto() {
        JTextField nomeField = new JTextField();
        JTextField precoField = new JTextField();
        JTextField quantidadeField = new JTextField();

        Object[] message = {
                "Nome:", nomeField,
                "Preço:", precoField,
                "Quantidade:", quantidadeField
        };

        int option = JOptionPane.showConfirmDialog(this, message, "Cadastrar Produto", JOptionPane.OK_CANCEL_OPTION);
        if (option == JOptionPane.OK_OPTION) {
            try {
                String nome = nomeField.getText();
                double preco = Double.parseDouble(precoField.getText());
                int quantidade = Integer.parseInt(quantidadeField.getText());

                service.cadastrar(nome, preco, quantidade);
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao cadastrar produto: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void deletarProduto() {
        int row = tabela.getSelectedRow();
        if (row < 0) {
            JOptionPane.showMessageDialog(this, "Selecione um produto para deletar.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Tem certeza que deseja deletar este produto?",
                "Confirmar Exclusão",
                JOptionPane.YES_NO_OPTION);

        if (confirm == JOptionPane.YES_OPTION) {
            try {
                service.deletar(row);
                atualizarTabela();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao deletar produto: " + e.getMessage(),
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                new ProdutoGUI().setVisible(true);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
