/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela;


import ifsp.dsi.bo.CardapioBO;
import ifsp.dsi.bo.ProdutoBaseBO;
import ifsp.dsi.entidade.Cardapio;
import ifsp.dsi.entidade.ProdutoBase;
import ifsp.dsi.janela.tabela.ModeloTabelaCardapio;
import ifsp.dsi.janela.tabela.ModeloTabelaCardapioProdutos;
import ifsp.dsi.janela.util.MessageBox;
import java.util.List;

/**
 *
 * @author repolho
 */
public class JanelaManterCardapio extends javax.swing.JFrame {

    
    private CardapioBO mCardapioBO;
    private Cardapio mCardapio;
    
    private ModeloTabelaCardapioProdutos modelProdutos;
    private ModeloTabelaCardapio modelCardapios;
    
    /**
     * Creates new form JanelaCadItemProduto
     */
    public JanelaManterCardapio() {
        initComponents();
        
        mCardapioBO = new CardapioBO();
        
        popularTabelaCardapios();
        populaComboCouvert();
        
        preparaEditavel(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtId = new javax.swing.JTextField();
        txtNome = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnExcluirSelecionados = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelaCardapios = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProdutos = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        cboCouvert = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 32, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 45, Short.MAX_VALUE)
        );

        jLabel2.setText("Id");

        jLabel3.setText("Nome");

        txtId.setEditable(false);
        txtId.setToolTipText("Id gerado automaticamente");

        txtNome.setToolTipText("Nome do ingrediente");

        jLabel6.setText("Couvert");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gerenciamento dos Drinks");
        jLabel1.setToolTipText("");

        btnExcluirSelecionados.setText("Excluir selecionado(s)");
        btnExcluirSelecionados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirSelecionados(evt);
            }
        });

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnNovo.setText("Novo");
        btnNovo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionNovo(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionEditar(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionExcluir(evt);
            }
        });

        btnSalvar.setText("Salvar");
        btnSalvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionSalvar(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actionaCancelar(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnNovo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEditar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCancelar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnExcluir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSalvar)
                .addGap(21, 21, 21))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnSalvar)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnEditar)
                        .addComponent(btnNovo)
                        .addComponent(btnCancelar)
                        .addComponent(btnExcluir)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modelCardapios = new ModeloTabelaCardapio();
        tabelaCardapios.setModel(modelCardapios);
        tabelaCardapios.setColumnSelectionAllowed(true);
        tabelaCardapios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaCardapiosMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelaCardapios);
        tabelaCardapios.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        modelProdutos = new ModeloTabelaCardapioProdutos();
        tabelaProdutos.setModel(modelProdutos);
        tabelaProdutos.setToolTipText("Produtos que fazem parte do cardápio.");
        jScrollPane2.setViewportView(tabelaProdutos);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("Produtos que compõe o cardápio");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(45, 45, 45)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(58, 58, 58)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(cboCouvert, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(125, 125, 125)))
                                .addGap(29, 29, 29))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(211, 211, 211)
                                        .addComponent(btnExcluirSelecionados)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addComponent(jScrollPane2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(184, 184, 184))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel2)
                                    .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)
                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnExcluirSelecionados))
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 495, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                            .addComponent(jLabel6)
                            .addComponent(cboCouvert, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(75, 75, 75))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionSalvar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSalvar
        
        if(!validaForm())
            return;
        
        List<ProdutoBase> lista = modelProdutos.getList();
        ProdutoBase couvert = (ProdutoBase)cboCouvert.getSelectedItem();
        
        Cardapio cardapio = new Cardapio(
                    Long.valueOf(txtId.getText()),
                    txtNome.getText(), 
                    couvert, 
                    lista,
                    false);
        
        mCardapioBO.salvar(
                cardapio
        );
        
        MessageBox.showInfo("Salvo com sucesso !");
        popularTabelaCardapios();
    }//GEN-LAST:event_actionSalvar

    private void actionNovo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionNovo
        preparaNovo();
    }//GEN-LAST:event_actionNovo

    private void actionEditar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionEditar
        preparaEditar();
    }//GEN-LAST:event_actionEditar

    private void actionExcluir(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionExcluir
        if(MessageBox.showAskYesNo("Excluir ?") == MessageBox.YES_OPTION){
            mCardapioBO.apagar(mCardapio); 
            modelCardapios.removeRow(mCardapio);
        }        
                
    }//GEN-LAST:event_actionExcluir

    private void btnExcluirSelecionados(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSelecionados
        List<Cardapio> selecionados = modelCardapios.getSelecionados();
        
        if(MessageBox.showAskYesNo("Excluir ?") == MessageBox.YES_OPTION){
            for (Cardapio c : selecionados) {

                if(mCardapioBO.apagar(c))
                    modelCardapios.removeRow(c);
            }
        }
        
        
    }//GEN-LAST:event_btnExcluirSelecionados

    private void actionaCancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionaCancelar
        if(mCardapio != null)
            atualizaValores();
        
        preparaEditavel(false);
    }//GEN-LAST:event_actionaCancelar

    private void tabelaCardapiosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaCardapiosMouseClicked
        mCardapio = modelCardapios.getValueAt(tabelaCardapios.getSelectedRow());
        
        populaTabelaProdutos();
                
        atualizaValores();
        
        preparaEditavel(false);
    }//GEN-LAST:event_tabelaCardapiosMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnExcluirSelecionados;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JComboBox<ProdutoBase> cboCouvert;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabelaCardapios;
    private javax.swing.JTable tabelaProdutos;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    // End of variables declaration//GEN-END:variables

    private void preparaNovo() {
        
        preparaEditavel(true);
        
        txtId.setText("0");
        txtNome.setText("");
        
        populaTabelaProdutos();
        
        txtNome.requestFocus();
        
        btnNovo.setEnabled(false);
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }

    private void preparaEditavel(boolean editavel){
        
        txtId.setEnabled(false);
        
        
        txtNome.setEnabled(editavel);
        cboCouvert.setEnabled(editavel);
        tabelaProdutos.setEnabled(editavel);
        
    }
    
    private void preparaEditar() {
        preparaEditavel(true);
        
        txtNome.requestFocus();
        
        btnEditar.setEnabled(false);
        btnExcluir.setEnabled(true);
        btnCancelar.setEnabled(true);
        btnNovo.setEnabled(false);
    }

    private boolean validaForm() {
        boolean valid = true;
        String texto = "";
        
        if(txtNome.getText().equals("")){
            texto = "Nome não pode estar vazio !";
            valid = false;
        }
        
        
        if(modelProdutos.getSelecionados().size() <= 0){
            String aux = "Deve have pelo menos um produto adicionado no cardapio !";
            texto += texto.equals("") ? aux : "\n" + aux;
            valid = false;
        }
        
        if(!valid){
            MessageBox.showWarning(texto);
        }
        
        return valid;
    }

    private void popularTabelaCardapios() {
        
        modelCardapios.removeAllRows();
        
        List<Cardapio> lista = mCardapioBO.listarTodos();
        
        for (Cardapio c : lista) {
            modelCardapios.addRow(c);
        }

    }

    private void atualizaValores() {
       
        txtId.setText(
                String.valueOf(mCardapio.getId())
        );
        
        txtNome.setText(
                mCardapio.getNome()
        );
        
        cboCouvert.setSelectedItem(
                mCardapio.getCouvert()
        );
        
        atualizarTabelaProdutos();
        
    }

    private void atualizarTabelaProdutos() {
       
        List<ProdutoBase> lista = modelProdutos.getList();
        
        List<ProdutoBase> listaCardapio = mCardapio.getProdutos();
        
        for (int i = 0; i < lista.size() -1 ; i++) {
            if(listaCardapio.contains(lista.get(i)))
                lista.get(i).setSelecionado(true);
            else
                lista.get(i).setSelecionado(false);
        }
        
    }

    private void populaComboCouvert() {
        ProdutoBaseBO bo = new ProdutoBaseBO();
        
        cboCouvert.removeAllItems();
        
        List<ProdutoBase> lista = bo.listarTodos();
        
        for (ProdutoBase p : lista) {
            cboCouvert.addItem(p);
        }
 
    }

    private void populaTabelaProdutos() {
        
        modelProdutos.removeAllRows();
        
        ProdutoBaseBO bo = new ProdutoBaseBO();
        
        List<ProdutoBase> lista = bo.listarTodos();
        
        for (ProdutoBase p : lista) {
            modelProdutos.addRow(p);
        }
    }
}
