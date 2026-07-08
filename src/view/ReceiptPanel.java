/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import controller.AnimalController;
import controller.ClientController;
import controller.DoctorController;
import controller.MedicalRecordController;
import controller.MedicineController;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import model.Animal;
import model.Client;
import model.Doctor;
import model.MedicalRecord;
import model.Medicine;

/**
 *
 * @author user
 */
public class ReceiptPanel extends javax.swing.JPanel {

    private final ClientController clientController = new ClientController();
    private final AnimalController animalController = new AnimalController();
    private final DoctorController doctorController = new DoctorController();
    private final MedicineController medicineController = new MedicineController();
    private final MedicalRecordController medicalRecordController = new MedicalRecordController();

    private List<Doctor> doctorList = new ArrayList<>();
    private List<Medicine> medicineList = new ArrayList<>();

    private Client currentClient = null;
    private Animal currentAnimal = null;

    /**
     * One line item shown in the temporary Receipt (cart) table.
     * It only becomes a permanent Medical Record once "Final Save" is pressed;
     * the Receipt table itself is never persisted to the database.
     */
    private static class CartItem {
        Client client;
        Animal animal;
        Doctor doctor;
        Medicine medicine;
        int qty;
        String diagnosis;
        String suggest;
        double subtotal;
    }

    private final List<CartItem> cartItems = new ArrayList<>();

    /**
     * Creates new form ReceiptPanel
     */
    public ReceiptPanel() {
        initComponents();
        jTextFieldClientName.setEditable(false);
        jTextFieldType.setEditable(false);
        loadDoctorCombo();
        loadMedicineCombo();
        attachAutoFillListeners();
        clearEntryFields();
        refreshCartTable();
    }

    
    private void attachAutoFillListeners() {
        jTextFieldClientID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { lookupClientById(); }
            @Override
            public void removeUpdate(DocumentEvent e) { lookupClientById(); }
            @Override
            public void changedUpdate(DocumentEvent e) { lookupClientById(); }
        });

        jTextFieldAnimalID.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { lookupAnimalById(); }
            @Override
            public void removeUpdate(DocumentEvent e) { lookupAnimalById(); }
            @Override
            public void changedUpdate(DocumentEvent e) { lookupAnimalById(); }
        });
    }

    private void lookupClientById() {
        String id = jTextFieldClientID.getText().trim();

        if (id.isEmpty()) {
            currentClient = null;
            jTextFieldClientName.setText("");
            return;
        }

        Client found = clientController.getById(id);
        if (found != null) {
            currentClient = found;
            jTextFieldClientName.setText(found.getName());
        } else {
            currentClient = null;
            jTextFieldClientName.setText("Client not found");
        }
    }

    private void lookupAnimalById() {
        String id = jTextFieldAnimalID.getText().trim();

        if (id.isEmpty()) {
            currentAnimal = null;
            jTextFieldType.setText("");
            return;
        }

        Animal found = animalController.getById(id);
        if (found != null) {
            currentAnimal = found;
            String typeText = found.getType();
            String ownerId = (found.getClient() != null) ? found.getClient().getIdClient() : null;
            if (currentClient != null) {
                if (ownerId == null || ownerId.isEmpty()) {
                    typeText += "  (no owner assigned to this animal!)";
                } else if (!ownerId.equals(currentClient.getIdClient())) {
                    typeText += "  (not owned by this client!)";
                }
            }
            jTextFieldType.setText(typeText);
        } else {
            currentAnimal = null;
            jTextFieldType.setText("Animal not found");
        }
    }

    // ===================== COMBO BOX DATA =====================

    private void loadDoctorCombo() {
        doctorList = doctorController.getAll();
        jComboBoxDoctor.removeAllItems();
        for (Doctor d : doctorList) {
            jComboBoxDoctor.addItem(d.getIdDoctor() + " - " + d.getName() + " (Rp " + String.format("%,.0f", d.getPrice()) + ")");
        }
    }

    private void loadMedicineCombo() {
        medicineList = medicineController.getAll();
        jComboBoxMedicine.removeAllItems();
        for (Medicine m : medicineList) {
            jComboBoxMedicine.addItem(m.getIdMedicine() + " - " + m.getName() + " (Stock: " + m.getStock() + ", Rp " + String.format("%,.0f", m.getPrice()) + ")");
        }
    }

    private Doctor getSelectedDoctor() {
        Object item = jComboBoxDoctor.getSelectedItem();
        if (item == null) return null;
        String id = item.toString().split(" - ", 2)[0];
        for (Doctor d : doctorList) {
            if (d.getIdDoctor().equals(id)) return d;
        }
        return null;
    }

    private Medicine getSelectedMedicine() {
        Object item = jComboBoxMedicine.getSelectedItem();
        if (item == null) return null;
        String id = item.toString().split(" - ", 2)[0];
        for (Medicine m : medicineList) {
            if (m.getIdMedicine().equals(id)) return m;
        }
        return null;
    }

    // ===================== CART (jTable3) MANAGEMENT =====================

    private void refreshCartTable() {
        String[] columns = {"Client", "Animal", "Doctor", "Diagnosis", "Medicine", "Qty", "Subtotal"};
        Object[][] data = new Object[cartItems.size()][7];

        double total = 0;
        for (int i = 0; i < cartItems.size(); i++) {
            CartItem item = cartItems.get(i);
            data[i][0] = item.client.getName() + " (" + item.client.getIdClient() + ")";
            data[i][1] = item.animal.getIdAnimal() + " - " + item.animal.getType();
            data[i][2] = item.doctor.getName();
            data[i][3] = item.diagnosis;
            data[i][4] = item.medicine.getName();
            data[i][5] = item.qty;
            data[i][6] = "Rp " + String.format("%,.0f", item.subtotal);
            total += item.subtotal;
        }

        jTable3.setModel(new DefaultTableModel(data, columns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });

        jLabelTotal.setText("Rp " + String.format("%,.0f", total));
    }

    private void clearEntryFields() {
        jTextFieldClientID.setText("");
        jTextFieldClientName.setText("");
        jTextFieldAnimalID.setText("");
        jTextFieldType.setText("");
        jTextFieldDiagnosis.setText("");
        jTextFieldSuggest.setText("");
        currentClient = null;
        currentAnimal = null;
        if (jComboBoxDoctor.getItemCount() > 0) jComboBoxDoctor.setSelectedIndex(0);
        if (jComboBoxMedicine.getItemCount() > 0) jComboBoxMedicine.setSelectedIndex(0);
    }

    private void clearAll() {
        clearEntryFields();
        cartItems.clear();
        refreshCartTable();
    }

    // ===================== BUTTON HANDLERS =====================

    private void jButtonSaveActionPerformed(java.awt.event.ActionEvent evt) {
        if (currentClient == null) {
            JOptionPane.showMessageDialog(this, "Please input a valid Client ID first!");
            return;
        }
        if (currentAnimal == null) {
            JOptionPane.showMessageDialog(this, "Please input a valid Animal ID first!");
            return;
        }

        Doctor doctor = getSelectedDoctor();
        Medicine medicine = getSelectedMedicine();
        if (doctor == null || medicine == null) {
            JOptionPane.showMessageDialog(this, "Please select a Doctor and a Medicine!");
            return;
        }

        String diagnosis = jTextFieldDiagnosis.getText().trim();
        String suggest = jTextFieldSuggest.getText().trim();
        if (diagnosis.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please fill in the Diagnosis!");
            return;
        }

        String qtyStr = JOptionPane.showInputDialog(this, "Medicine quantity for " + medicine.getName() + ":", "1");
        if (qtyStr == null) return;

        int qty;
        try {
            qty = Integer.parseInt(qtyStr.trim());
            if (qty <= 0) throw new NumberFormatException();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Quantity must be a positive whole number!");
            return;
        }

        if (qty > medicine.getStock()) {
            JOptionPane.showMessageDialog(this, "Not enough stock! Available: " + medicine.getStock());
            return;
        }

        CartItem item = new CartItem();
        item.client = currentClient;
        item.animal = currentAnimal;
        item.doctor = doctor;
        item.medicine = medicine;
        item.qty = qty;
        item.diagnosis = diagnosis;
        item.suggest = suggest;
        item.subtotal = doctor.getPrice() + (medicine.getPrice() * qty);

        cartItems.add(item);
        refreshCartTable();

        // keep the Client so the user can keep adding more animals/treatments
        // to the same receipt, only clear the per-item fields
        jTextFieldAnimalID.setText("");
        jTextFieldType.setText("");
        jTextFieldDiagnosis.setText("");
        jTextFieldSuggest.setText("");
        currentAnimal = null;
        if (jComboBoxDoctor.getItemCount() > 0) jComboBoxDoctor.setSelectedIndex(0);
        if (jComboBoxMedicine.getItemCount() > 0) jComboBoxMedicine.setSelectedIndex(0);

        JOptionPane.showMessageDialog(this, "Item added to receipt!");
    }

    private void jButtonDeleteActionPerformed(java.awt.event.ActionEvent evt) {
        int row = jTable3.getSelectedRow();
        if (row == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row from the receipt table first!");
            return;
        }
        cartItems.remove(row);
        refreshCartTable();
    }

    private void jButtonCancelActionPerformed(java.awt.event.ActionEvent evt) {
        clearEntryFields();
    }

    private void jButtonRefreshActionPerformed(java.awt.event.ActionEvent evt) {
        loadDoctorCombo();
        loadMedicineCombo();
        clearAll();
    }

    private void jButtonFinalActionPerformed(java.awt.event.ActionEvent evt) {
        if (cartItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please add at least one item to the receipt!");
            return;
        }

        Client billedClient = cartItems.get(0).client;
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.subtotal;
        }

        int confirm = JOptionPane.showConfirmDialog(this,
                "Save medical record(s) for " + billedClient.getName() + " with total Rp " + String.format("%,.0f", total) + "?",
                "Confirm Final Save", JOptionPane.YES_NO_OPTION);
        if (confirm != JOptionPane.YES_OPTION) return;

        try {
            for (CartItem item : cartItems) {
                MedicalRecord medicalRecord = new MedicalRecord(
                        "",
                        item.animal,
                        item.doctor,
                        item.medicine,
                        item.qty,
                        item.diagnosis,
                        item.suggest,
                        new Date(System.currentTimeMillis())
                );
                medicalRecordController.create(medicalRecord);

                // reduce medicine stock accordingly
                Medicine med = item.medicine;
                Medicine updated = new Medicine(med.getIdMedicine(), med.getName(), med.getPrice(), med.getStock() - item.qty);
                medicineController.update(updated, med.getIdMedicine());
            }

            jLabelTotal.setText("Rp " + String.format("%,.0f", total));
            JOptionPane.showMessageDialog(this, "Medical record(s) saved successfully!");

            loadMedicineCombo();
            clearAll();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Failed to save medical record: " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jTextFieldClientID = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jTextFieldClientName = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jTextFieldType = new javax.swing.JTextField();
        jTextFieldAnimalID = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        jTextFieldDiagnosis = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jTextFieldSuggest = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jComboBoxMedicine = new javax.swing.JComboBox<>();
        jComboBoxDoctor = new javax.swing.JComboBox<>();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButtonFinal = new javax.swing.JButton();
        jButtonSave = new javax.swing.JButton();
        jButtonDelete = new javax.swing.JButton();
        jButtonCancel = new javax.swing.JButton();
        jButtonRefresh = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabelTotal = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(jTable2);

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Ebrima", 1, 28)); // NOI18N
        jLabel1.setText("ADD RECEIPT");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)), "Choose Client", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ebrima", 1, 16))); // NOI18N

        jLabel2.setFont(new java.awt.Font("Ebrima", 1, 16)); // NOI18N

        jLabel3.setText("Input Client ID");

        jLabel6.setText("Client Name");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldClientID, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldClientName, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel3)
                        .addComponent(jTextFieldClientID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jTextFieldClientName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)), "Choose Animal", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ebrima", 1, 16))); // NOI18N

        jLabel4.setFont(new java.awt.Font("Ebrima", 1, 16)); // NOI18N

        jLabel5.setText("Input Animal ID");

        jLabel7.setText("Animal Type");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldAnimalID, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(jTextFieldAnimalID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel4))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jTextFieldType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 153, 51)), "Receipt", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Ebrima", 1, 14))); // NOI18N

        jLabel8.setText("Doctor");

        jLabel10.setText("Diagnosis");

        jLabel11.setText("Suggestion");

        jLabel12.setText("Medicine");

        jComboBoxMedicine.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboBoxDoctor.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12))
                .addGap(66, 66, 66)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldDiagnosis, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jTextFieldSuggest, javax.swing.GroupLayout.DEFAULT_SIZE, 190, Short.MAX_VALUE)
                    .addComponent(jComboBoxMedicine, 0, 190, Short.MAX_VALUE)
                    .addComponent(jComboBoxDoctor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(82, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jComboBoxDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDiagnosis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSuggest, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jComboBoxMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Client", "Animal", "Doctor", "Diagnosis", "Medicine", "Qty", "Subtotal"
            }
        ));
        jScrollPane3.setViewportView(jTable3);

        jButtonFinal.setBackground(new java.awt.Color(51, 51, 51));
        jButtonFinal.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButtonFinal.setForeground(new java.awt.Color(255, 255, 255));
        jButtonFinal.setText("Final Save");
        jButtonFinal.addActionListener(this::jButtonFinalActionPerformed);

        jButtonSave.setBackground(new java.awt.Color(0, 255, 51));
        jButtonSave.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonSave.setForeground(new java.awt.Color(255, 255, 255));
        jButtonSave.setText("Save");
        jButtonSave.addActionListener(this::jButtonSaveActionPerformed);

        jButtonDelete.setBackground(new java.awt.Color(255, 51, 0));
        jButtonDelete.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonDelete.setForeground(new java.awt.Color(255, 255, 255));
        jButtonDelete.setText("Delete");
        jButtonDelete.addActionListener(this::jButtonDeleteActionPerformed);

        jButtonCancel.setBackground(new java.awt.Color(255, 204, 51));
        jButtonCancel.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonCancel.setForeground(new java.awt.Color(255, 255, 255));
        jButtonCancel.setText("Cancel");
        jButtonCancel.addActionListener(this::jButtonCancelActionPerformed);

        jButtonRefresh.setBackground(new java.awt.Color(153, 153, 153));
        jButtonRefresh.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jButtonRefresh.setText("Refresh");
        jButtonRefresh.addActionListener(this::jButtonRefreshActionPerformed);

        jLabel9.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel9.setText("TOTAL COST : ");

        jLabelTotal.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTotal.setText("-");

        jLabel13.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LogoReceip.png"))); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTotal)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButtonFinal, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(23, 23, 23)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(62, 62, 62)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jButtonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButtonRefresh, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel1)))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel13))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonFinal)
                    .addComponent(jLabel9)
                    .addComponent(jLabelTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(188, 188, 188)
                .addComponent(jButtonSave)
                .addGap(29, 29, 29)
                .addComponent(jButtonDelete)
                .addGap(28, 28, 28)
                .addComponent(jButtonCancel)
                .addGap(31, 31, 31)
                .addComponent(jButtonRefresh)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put( "Button.arc", 999 );
            UIManager.put( "Component.arc", 999 );
            UIManager.put( "ProgressBar.arc", 999 );
            UIManager.put( "TextComponent.arc", 999 );
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonCancel;
    private javax.swing.JButton jButtonDelete;
    private javax.swing.JButton jButtonFinal;
    private javax.swing.JButton jButtonRefresh;
    private javax.swing.JButton jButtonSave;
    private javax.swing.JComboBox<String> jComboBoxDoctor;
    private javax.swing.JComboBox<String> jComboBoxMedicine;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabelTotal;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextFieldAnimalID;
    private javax.swing.JTextField jTextFieldClientID;
    private javax.swing.JTextField jTextFieldClientName;
    private javax.swing.JTextField jTextFieldDiagnosis;
    private javax.swing.JTextField jTextFieldSuggest;
    private javax.swing.JTextField jTextFieldType;
    // End of variables declaration//GEN-END:variables
}
