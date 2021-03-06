/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela;


import ifsp.dsi.bo.VinhoBO;
import ifsp.dsi.entidade.Vinho;
import ifsp.dsi.janela.tabela.ModeloTabelaBebida;
import ifsp.dsi.janela.tabela.ModeloTabelaIngredienteMontavel;
import ifsp.dsi.janela.tabela.ModeloTabelaMontavel;
import ifsp.dsi.janela.tabela.ModeloTabelaVinho;
import java.math.BigDecimal;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author repolho
 */
public class JanelaVinho extends javax.swing.JFrame {

    private Vinho bebida;
    private ModeloTabelaBebida modelBebidas = new ModeloTabelaBebida();
    private ModeloTabelaVinho modelVinhos = new ModeloTabelaVinho();
    ModeloTabelaMontavel modelDrinks;
    private ModeloTabelaIngredienteMontavel modelIngredientes;
    
    /**
     * Creates new form JanelaCadItemProduto
     */
    public JanelaVinho() {
        initComponents();
        
        
        popularTabelaDrinks();
        
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
        txtValorCusto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        btnExcluirSelecionados = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        btnNovo = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        btnSalvar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableBebidas = new javax.swing.JTable();
        jLabel8 = new javax.swing.JLabel();
        txtPercentualLucro = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txtQtdEstoque = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txtQtdMinima = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtSafra = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtUva = new javax.swing.JTextField();

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

        jLabel6.setText("Valor de Custo");

        txtValorCusto.setToolTipText("Valor de custo");

        jLabel1.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Gerenciamento de Vinhos");
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
                .addGap(18, 18, 18)
                .addComponent(btnSalvar)
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnNovo)
                    .addComponent(btnCancelar)
                    .addComponent(btnSalvar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        modelDrinks = new ModeloTabelaMontavel();
        tableBebidas.setModel(modelVinhos);
        tableBebidas.setColumnSelectionAllowed(true);
        tableBebidas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableBebidasMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableBebidas);
        tableBebidas.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        jLabel8.setText("Perc. Lucro");

        txtPercentualLucro.setToolTipText("Valor de custo");

        jLabel10.setText("Qtd Estoque");

        txtQtdEstoque.setToolTipText("Valor de custo");

        jLabel11.setText("Qtd Minima");

        txtQtdMinima.setToolTipText("Valor de custo");

        jLabel12.setText("Safra");

        txtSafra.setToolTipText("Valor de custo");

        jLabel13.setText("Tipo Uva");

        txtUva.setToolTipText("Valor de custo");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 810, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(274, 274, 274)
                                .addComponent(btnExcluirSelecionados)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(txtSafra)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtUva, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(386, 386, 386))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtValorCusto)
                                            .addComponent(txtId, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                            .addComponent(txtQtdEstoque, javax.swing.GroupLayout.Alignment.TRAILING))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(21, 21, 21)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 417, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtPercentualLucro, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel11)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                        .addGap(487, 487, 487)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(184, 184, 184))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addComponent(jLabel1)
                        .addGap(37, 37, 37)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                .addComponent(jLabel2)
                                .addComponent(txtId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel3)
                                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                                    .addComponent(jLabel6)
                                    .addComponent(txtValorCusto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8)
                                    .addComponent(txtPercentualLucro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtQtdEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10)
                            .addComponent(txtQtdMinima, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtSafra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel12))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel13)
                                .addComponent(txtUva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnExcluirSelecionados))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(75, 75, 75))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void actionSalvar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionSalvar
        
        Long id;
        id = Long.parseLong(txtId.getText());
        
        System.out.println(id);
        
        if(!validaForm())
            return;
        
       
        bebida = new Vinho(id ,Double.valueOf(txtPercentualLucro.getText()), 1, Integer.parseInt(txtQtdEstoque.getText()), Integer.parseInt(txtQtdMinima.getText()), txtNome.getText(), BigDecimal.valueOf(Double.valueOf(txtValorCusto.getText())),Integer.parseInt(txtSafra.getText()),txtUva.getText());
        VinhoBO bo = new VinhoBO();
        bo.salvar(bebida);
        modelVinhos.addRow(bebida);
        JOptionPane.showMessageDialog(this,"Salvo com sucesso");
        preparaEditavel(false);
        popularTabelaDrinks();
        btnEditar.setEnabled(true);
        btnNovo.setEnabled(true);
        
    }//GEN-LAST:event_actionSalvar

    private void actionNovo(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionNovo
        preparaNovo();
    }//GEN-LAST:event_actionNovo

    private void actionEditar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionEditar
        preparaEditar();
    }//GEN-LAST:event_actionEditar

    private void btnExcluirSelecionados(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirSelecionados
        List<Vinho> selecionados = modelVinhos.getSelecionados();
        VinhoBO bo = new VinhoBO();
        
        
            for (Vinho b : selecionados) {

                if(bo.apagar(b))
                    modelVinhos.removeRow(b);
            }
        
        
        
    }//GEN-LAST:event_btnExcluirSelecionados

    private void actionaCancelar(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actionaCancelar
        if(bebida != null)
            atualizaValores();
        
        preparaEditavel(false);
        popularTabelaDrinks();
        btnEditar.setEnabled(true);
        btnNovo.setEnabled(true);
    }//GEN-LAST:event_actionaCancelar

    private void tableBebidasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableBebidasMouseClicked
        bebida = modelVinhos.getValueAt(tableBebidas.getSelectedRow());
        atualizaValores();
        preparaEditavel(false);
    }//GEN-LAST:event_tableBebidasMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluirSelecionados;
    private javax.swing.JButton btnNovo;
    private javax.swing.JButton btnSalvar;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableBebidas;
    private javax.swing.JTextField txtId;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtPercentualLucro;
    private javax.swing.JTextField txtQtdEstoque;
    private javax.swing.JTextField txtQtdMinima;
    private javax.swing.JTextField txtSafra;
    private javax.swing.JTextField txtUva;
    private javax.swing.JTextField txtValorCusto;
    // End of variables declaration//GEN-END:variables

    private void preparaNovo() {
        
        preparaEditavel(true);
        
        txtId.setText("0");
        txtNome.setText("");
        txtPercentualLucro.setText("");
        txtValorCusto.setText("");
        txtQtdEstoque.setText("");
        txtQtdMinima.setText("");
        txtSafra.setText("");
        txtUva.setText("");
        
        //modelVinhos.removeAllRows();
        
        txtNome.requestFocus();
        
        btnNovo.setEnabled(false);
        btnEditar.setEnabled(false);
    }

    private void preparaEditavel(boolean editavel){
        
        txtId.setEnabled(false);
        txtValorCusto.setEnabled(editavel);
        txtNome.setEnabled(editavel);
        txtPercentualLucro.setEnabled(editavel);
        txtQtdEstoque.setEnabled(editavel);
        txtQtdMinima.setEnabled(editavel);
        txtSafra.setEnabled(editavel);
        txtUva.setEnabled(editavel);
        
    }
    
    private void preparaEditar() {
        preparaEditavel(true);
        
        txtNome.requestFocus();
        
        btnEditar.setEnabled(false);
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
        
        if(txtPercentualLucro.getText().equals("")){
            txtPercentualLucro.setText("0");
        }else if(Double.valueOf(txtPercentualLucro.getText()) <= 0){
            String aux = "Percentual de lucro inválido !";
            texto += texto.equals("") ? aux : "\n" + aux;
            valid = false;
        }
        
        
        if(!valid){
            JOptionPane.showMessageDialog(this,texto);
        }
        
        return valid;
    }

    
    private void popularTabelaDrinks() {
        
        modelVinhos.removeAllRows();
        VinhoBO bo = new VinhoBO();
        List<Vinho> lista = bo.listarByTipo();
        
        for (Vinho b : lista) {
            modelVinhos.addRow(b);
        }

    }

    private void atualizaValores() {
        
        txtId.setText(
                String.valueOf(bebida.getId())
        );
        
        txtNome.setText(
                bebida.getNome()
        );
        
        txtValorCusto.setText(
                String.valueOf(bebida.getValorCusto())
        );
        
        
        txtPercentualLucro.setText(
                String.valueOf(bebida.getPercentual_lucro())
        );
        
        txtQtdEstoque.setText(String.valueOf(bebida.getQtd_estoque()));
        txtQtdMinima.setText(String.valueOf(bebida.getQtd_minima()));
        
        
    }

    }

    
