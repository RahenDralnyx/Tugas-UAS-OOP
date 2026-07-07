/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import com.formdev.flatlaf.FlatLightLaf;
import java.awt.Color;
import java.sql.SQLException;
import java.time.LocalTime;
import javax.swing.UIManager;

/**
 *
 * @author user
 */
public class Dashboard extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Dashboard.class.getName());

    /**
     * Creates new form Dashboard
     */
    public Dashboard() {
        initComponents();
        greeting();
        // Ukuran minimum supaya sidebar & panel utama tidak "gepeng"/berantakan
        // ketika window di-resize lebih kecil dari ukuran desain.
        setMinimumSize(new java.awt.Dimension(1050, 700));
        setLocationRelativeTo(null);
        // Tampilkan Dashboard secara default supaya panel utama tidak kosong
        // saat aplikasi pertama kali dibuka.
        showPanel(new DashboardPanel());
        setActiveMenu(jPanelDashboard, jPanelDashboardSmall);
    }

    // Menu sidebar yang sedang aktif (dipilih terakhir kali)
    private javax.swing.JPanel activeMenuPanel = null;
    private javax.swing.JPanel activeMenuStrip = null;

    private static final Color COLOR_NORMAL = new Color(255, 153, 0);
    private static final Color COLOR_HOVER = new Color(205, 153, 0);
    private static final Color COLOR_HOVER_STRIP = new Color(190, 180, 0);
    private static final Color COLOR_ACTIVE = new Color(204, 112, 0);
    private static final Color COLOR_ACTIVE_STRIP = new Color(255, 255, 255);

    /**
     * Efek hover: hanya mengubah warna, tidak menggeser posisi panel.
     * Menggeser posisi (setLocation) pada komponen yang diatur GroupLayout
     * itulah yang menyebabkan sidebar "berantakan" saat berpindah panel.
     */
    private void hoverPanel(javax.swing.JPanel panel, javax.swing.JPanel stripPanel) {
        if (panel == activeMenuPanel) {
            return; // menu yang sedang aktif tidak perlu berubah warna saat hover
        }
        panel.setBackground(COLOR_HOVER);
        if (stripPanel != null) {
            stripPanel.setBackground(COLOR_HOVER_STRIP);
        }
    }

    private void exitHover(javax.swing.JPanel panel, javax.swing.JPanel stripPanel) {
        if (panel == activeMenuPanel) {
            return; // biarkan tetap dengan warna aktif
        }
        panel.setBackground(COLOR_NORMAL);
        if (stripPanel != null) {
            stripPanel.setBackground(COLOR_NORMAL);
        }
    }

    /**
     * Menandai menu sidebar sebagai aktif (sedang dibuka) sehingga warnanya
     * tetap terlihat "terklik" walaupun mouse sudah menjauh, sampai menu
     * lain dipilih.
     */
    private void setActiveMenu(javax.swing.JPanel panel, javax.swing.JPanel stripPanel) {
        if (activeMenuPanel != null) {
            activeMenuPanel.setBackground(COLOR_NORMAL);
            if (activeMenuStrip != null) {
                activeMenuStrip.setBackground(COLOR_NORMAL);
            }
        }
        activeMenuPanel = panel;
        activeMenuStrip = stripPanel;
        panel.setBackground(COLOR_ACTIVE);
        if (stripPanel != null) {
            stripPanel.setBackground(COLOR_ACTIVE_STRIP);
        }
    }
    
    private void greeting(){
    
        LocalTime localTime = LocalTime.now();
        
        int jamSekarang = localTime.getHour();
        
        if (jamSekarang < 12){
            jLabelWaktu.setText("Selamat Pagi");
        }
        else if (jamSekarang < 15 ){
            jLabelWaktu.setText("Selamat Siang");
        }
        else if (jamSekarang < 18){
            jLabelWaktu.setText("Selamat Sore");
        }
        else {
            jLabelWaktu.setText("Selamat Malam");
        }
    }
    
    private void showPanel(javax.swing.JPanel panel) {
        jPanelUtama.removeAll();

        // Panel-panel (Animal, Medicine, Doctor, dll) dibuat oleh NetBeans Form
        // Editor dengan GroupLayout yang mempunyai ukuran (preferred size) tetap
        // sesuai desain masing-masing. Jika langsung ditempel ke jPanelUtama
        // (BorderLayout.CENTER) dan ternyata ruang yang tersedia lebih kecil,
        // Swing akan "memaksa" ukurannya mengecil sehingga komponen di dalamnya
        // saling tumpang tindih / terpotong (inilah yang terlihat "berantakan").
        //
        // Membungkusnya dengan JScrollPane membuat panel selalu tampil dengan
        // ukuran aslinya (rapi, tidak terdistorsi) dan scrollbar hanya muncul
        // bila memang diperlukan, alih-alih memaksakan komponen mengecil.
        javax.swing.JScrollPane scrollPane = new javax.swing.JScrollPane(panel);
        scrollPane.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        scrollPane.setBackground(panel.getBackground());
        scrollPane.getViewport().setBackground(panel.getBackground());
        scrollPane.getVerticalScrollBar().setUnitIncrement(16);
        scrollPane.getHorizontalScrollBar().setUnitIncrement(16);

        jPanelUtama.add(scrollPane, java.awt.BorderLayout.CENTER);
        jPanelUtama.revalidate();
        jPanelUtama.repaint();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanelBackground = new javax.swing.JPanel();
        jPanelSidebar = new javax.swing.JPanel();
        jLabelNamaRS = new javax.swing.JLabel();
        jLabelLogoRS = new javax.swing.JLabel();
        jPanelDashboard = new javax.swing.JPanel();
        jLabelClientIcon = new javax.swing.JLabel();
        jLabelDashboard = new javax.swing.JLabel();
        jPanelDashboardSmall = new javax.swing.JPanel();
        jPanelAnimal = new javax.swing.JPanel();
        jLabelClientIcon1 = new javax.swing.JLabel();
        jLabelAnimal = new javax.swing.JLabel();
        jPanelAnimalSmall = new javax.swing.JPanel();
        jPanelDoctor = new javax.swing.JPanel();
        jLabelClientIcon2 = new javax.swing.JLabel();
        jLabelDoctor = new javax.swing.JLabel();
        jPanelDoctorSmall = new javax.swing.JPanel();
        jPanelMedicine = new javax.swing.JPanel();
        jLabelClientIcon3 = new javax.swing.JLabel();
        jLabelMedicine = new javax.swing.JLabel();
        jPanelMedicineSmall = new javax.swing.JPanel();
        jPanelReceipt = new javax.swing.JPanel();
        jLabelClientIcon4 = new javax.swing.JLabel();
        jLabelReceipt = new javax.swing.JLabel();
        jPanelReceiptSmall = new javax.swing.JPanel();
        jPanelLogout = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jPanelClient = new javax.swing.JPanel();
        jLabelClientIcon5 = new javax.swing.JLabel();
        jLabelClient = new javax.swing.JLabel();
        jPanelClientSmall = new javax.swing.JPanel();
        kGradientPanel1 = new keeptoo.KGradientPanel();
        jLabelWaktu = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jPanelUtama = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanelSidebar.setBackground(new java.awt.Color(255, 153, 0));

        jLabelNamaRS.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelNamaRS.setForeground(new java.awt.Color(51, 51, 51));
        jLabelNamaRS.setText("NawaSatwaHospital");

        jLabelLogoRS.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/vet-icon.png"))); // NOI18N
        jLabelLogoRS.setText("jLabel2");

        jPanelDashboard.setBackground(new java.awt.Color(255, 153, 0));
        jPanelDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelDashboardMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelDashboardMouseExited(evt);
            }
        });

        jLabelClientIcon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home-icon.png"))); // NOI18N
        jLabelClientIcon.setText("jLabel4");

        jLabelDashboard.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelDashboard.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDashboard.setText("Dashboard");
        jLabelDashboard.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelDashboardMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelDashboardMouseExited(evt);
            }
        });

        jPanelDashboardSmall.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelDashboardSmallLayout = new javax.swing.GroupLayout(jPanelDashboardSmall);
        jPanelDashboardSmall.setLayout(jPanelDashboardSmallLayout);
        jPanelDashboardSmallLayout.setHorizontalGroup(
            jPanelDashboardSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanelDashboardSmallLayout.setVerticalGroup(
            jPanelDashboardSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelDashboardLayout = new javax.swing.GroupLayout(jPanelDashboard);
        jPanelDashboard.setLayout(jPanelDashboardLayout);
        jPanelDashboardLayout.setHorizontalGroup(
            jPanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDashboardSmall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelClientIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDashboard)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDashboardLayout.setVerticalGroup(
            jPanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDashboardLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelDashboardLayout.createSequentialGroup()
                        .addGroup(jPanelDashboardLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelClientIcon)
                            .addComponent(jLabelDashboard))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelDashboardSmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanelAnimal.setBackground(new java.awt.Color(255, 153, 0));
        jPanelAnimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelAnimalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAnimalMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAnimalMouseClicked(evt);
            }
        });

        jLabelClientIcon1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/paw-icon.png"))); // NOI18N

        jLabelAnimal.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelAnimal.setForeground(new java.awt.Color(51, 51, 51));
        jLabelAnimal.setText("Animal");
        jLabelAnimal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelAnimalMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelAnimalMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelAnimalMouseExited(evt);
            }
        });

        jPanelAnimalSmall.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelAnimalSmallLayout = new javax.swing.GroupLayout(jPanelAnimalSmall);
        jPanelAnimalSmall.setLayout(jPanelAnimalSmallLayout);
        jPanelAnimalSmallLayout.setHorizontalGroup(
            jPanelAnimalSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanelAnimalSmallLayout.setVerticalGroup(
            jPanelAnimalSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelAnimalLayout = new javax.swing.GroupLayout(jPanelAnimal);
        jPanelAnimal.setLayout(jPanelAnimalLayout);
        jPanelAnimalLayout.setHorizontalGroup(
            jPanelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAnimalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelAnimalSmall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelClientIcon1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelAnimalLayout.setVerticalGroup(
            jPanelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAnimalLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelAnimalSmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelAnimalLayout.createSequentialGroup()
                        .addGroup(jPanelAnimalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelClientIcon1)
                            .addComponent(jLabelAnimal))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelDoctor.setBackground(new java.awt.Color(255, 153, 0));
        jPanelDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelDoctorMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDoctorMouseClicked(evt);
            }
        });

        jLabelClientIcon2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/doctor-icon.png"))); // NOI18N

        jLabelDoctor.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelDoctor.setForeground(new java.awt.Color(51, 51, 51));
        jLabelDoctor.setText("Doctor");
        jLabelDoctor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelDoctorMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelDoctorMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelDoctorMouseExited(evt);
            }
        });

        jPanelDoctorSmall.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelDoctorSmallLayout = new javax.swing.GroupLayout(jPanelDoctorSmall);
        jPanelDoctorSmall.setLayout(jPanelDoctorSmallLayout);
        jPanelDoctorSmallLayout.setHorizontalGroup(
            jPanelDoctorSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanelDoctorSmallLayout.setVerticalGroup(
            jPanelDoctorSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelDoctorLayout = new javax.swing.GroupLayout(jPanelDoctor);
        jPanelDoctor.setLayout(jPanelDoctorLayout);
        jPanelDoctorLayout.setHorizontalGroup(
            jPanelDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDoctorLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelDoctorSmall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelClientIcon2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelDoctorLayout.setVerticalGroup(
            jPanelDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelDoctorLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelDoctorSmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelDoctorLayout.createSequentialGroup()
                        .addGroup(jPanelDoctorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelClientIcon2)
                            .addComponent(jLabelDoctor))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelMedicine.setBackground(new java.awt.Color(255, 153, 0));
        jPanelMedicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelMedicineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMedicineMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMedicineMouseClicked(evt);
            }
        });

        jLabelClientIcon3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LogoMedicin.png"))); // NOI18N

        jLabelMedicine.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelMedicine.setForeground(new java.awt.Color(51, 51, 51));
        jLabelMedicine.setText("Medicine");
        jLabelMedicine.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelMedicineMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelMedicineMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelMedicineMouseExited(evt);
            }
        });

        jPanelMedicineSmall.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelMedicineSmallLayout = new javax.swing.GroupLayout(jPanelMedicineSmall);
        jPanelMedicineSmall.setLayout(jPanelMedicineSmallLayout);
        jPanelMedicineSmallLayout.setHorizontalGroup(
            jPanelMedicineSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanelMedicineSmallLayout.setVerticalGroup(
            jPanelMedicineSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelMedicineLayout = new javax.swing.GroupLayout(jPanelMedicine);
        jPanelMedicine.setLayout(jPanelMedicineLayout);
        jPanelMedicineLayout.setHorizontalGroup(
            jPanelMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelMedicineSmall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelClientIcon3, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelMedicineLayout.setVerticalGroup(
            jPanelMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelMedicineLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelMedicineSmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelMedicineLayout.createSequentialGroup()
                        .addGroup(jPanelMedicineLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelClientIcon3)
                            .addComponent(jLabelMedicine))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelReceipt.setBackground(new java.awt.Color(255, 153, 0));
        jPanelReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelReceiptMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelReceiptMouseExited(evt);
            }
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelReceiptMouseClicked(evt);
            }
        });

        jLabelClientIcon4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LogoReceip.png"))); // NOI18N

        jLabelReceipt.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelReceipt.setForeground(new java.awt.Color(51, 51, 51));
        jLabelReceipt.setText("Receipt");
        jLabelReceipt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelReceiptMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelReceiptMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelReceiptMouseExited(evt);
            }
        });

        jPanelReceiptSmall.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelReceiptSmallLayout = new javax.swing.GroupLayout(jPanelReceiptSmall);
        jPanelReceiptSmall.setLayout(jPanelReceiptSmallLayout);
        jPanelReceiptSmallLayout.setHorizontalGroup(
            jPanelReceiptSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanelReceiptSmallLayout.setVerticalGroup(
            jPanelReceiptSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelReceiptLayout = new javax.swing.GroupLayout(jPanelReceipt);
        jPanelReceipt.setLayout(jPanelReceiptLayout);
        jPanelReceiptLayout.setHorizontalGroup(
            jPanelReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReceiptLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelReceiptSmall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelClientIcon4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelReceiptLayout.setVerticalGroup(
            jPanelReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelReceiptLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanelReceiptSmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelReceiptLayout.createSequentialGroup()
                        .addGroup(jPanelReceiptLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabelClientIcon4)
                            .addComponent(jLabelReceipt))
                        .addGap(0, 7, Short.MAX_VALUE)))
                .addContainerGap())
        );

        jPanelLogout.setBackground(new java.awt.Color(255, 153, 0));
        jPanelLogout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelLogoutMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Calibri", 1, 17)); // NOI18N
        jLabel1.setText("LogOut");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/LogoLogout.png"))); // NOI18N
        jLabel2.setText("jLabel2");

        javax.swing.GroupLayout jPanelLogoutLayout = new javax.swing.GroupLayout(jPanelLogout);
        jPanelLogout.setLayout(jPanelLogoutLayout);
        jPanelLogoutLayout.setHorizontalGroup(
            jPanelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLogoutLayout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(14, 14, 14))
        );
        jPanelLogoutLayout.setVerticalGroup(
            jPanelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelLogoutLayout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addGroup(jPanelLogoutLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(11, 11, 11))
        );

        jPanelClient.setBackground(new java.awt.Color(255, 153, 0));
        jPanelClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelClientMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelClientMouseExited(evt);
            }
        });

        jLabelClientIcon5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/user (1).png"))); // NOI18N
        jLabelClientIcon5.setText("jLabel4");

        jLabelClient.setFont(new java.awt.Font("Calibri", 1, 18)); // NOI18N
        jLabelClient.setForeground(new java.awt.Color(51, 51, 51));
        jLabelClient.setText("Client");
        jLabelClient.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabelClientMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabelClientMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabelClientMouseExited(evt);
            }
        });

        jPanelClientSmall.setBackground(new java.awt.Color(255, 153, 0));

        javax.swing.GroupLayout jPanelClientSmallLayout = new javax.swing.GroupLayout(jPanelClientSmall);
        jPanelClientSmall.setLayout(jPanelClientSmallLayout);
        jPanelClientSmallLayout.setHorizontalGroup(
            jPanelClientSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 8, Short.MAX_VALUE)
        );
        jPanelClientSmallLayout.setVerticalGroup(
            jPanelClientSmallLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanelClientLayout = new javax.swing.GroupLayout(jPanelClient);
        jPanelClient.setLayout(jPanelClientLayout);
        jPanelClientLayout.setHorizontalGroup(
            jPanelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanelClientSmall, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20)
                .addComponent(jLabelClientIcon5, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabelClient, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanelClientLayout.setVerticalGroup(
            jPanelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelClientLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanelClientLayout.createSequentialGroup()
                        .addGroup(jPanelClientLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabelClientIcon5)
                            .addComponent(jLabelClient))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanelClientSmall, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanelSidebarLayout = new javax.swing.GroupLayout(jPanelSidebar);
        jPanelSidebar.setLayout(jPanelSidebarLayout);
        jPanelSidebarLayout.setHorizontalGroup(
            jPanelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSidebarLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanelDashboard, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanelSidebarLayout.createSequentialGroup()
                        .addComponent(jLabelLogoRS, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabelNamaRS))
                    .addComponent(jPanelAnimal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelDoctor, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelMedicine, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelReceipt, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelClient, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(14, 14, 14))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanelSidebarLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanelSidebarLayout.setVerticalGroup(
            jPanelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelSidebarLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanelSidebarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNamaRS)
                    .addComponent(jLabelLogoRS))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 33, Short.MAX_VALUE)
                .addComponent(jPanelDashboard, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelAnimal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanelDoctor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanelMedicine, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanelReceipt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(105, 105, 105)
                .addComponent(jPanelLogout, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        kGradientPanel1.setkEndColor(new java.awt.Color(255, 204, 102));
        kGradientPanel1.setkStartColor(new java.awt.Color(255, 153, 0));

        jLabelWaktu.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabelWaktu.setText("-");

        jLabel4.setBackground(new java.awt.Color(204, 204, 204));
        jLabel4.setFont(new java.awt.Font("Calibri", 1, 22)); // NOI18N
        jLabel4.setText(", User");

        javax.swing.GroupLayout kGradientPanel1Layout = new javax.swing.GroupLayout(kGradientPanel1);
        kGradientPanel1.setLayout(kGradientPanel1Layout);
        kGradientPanel1Layout.setHorizontalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(kGradientPanel1Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addComponent(jLabelWaktu)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(582, Short.MAX_VALUE))
        );
        kGradientPanel1Layout.setVerticalGroup(
            kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, kGradientPanel1Layout.createSequentialGroup()
                .addContainerGap(18, Short.MAX_VALUE)
                .addGroup(kGradientPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelWaktu)
                    .addComponent(jLabel4))
                .addGap(17, 17, 17))
        );

        jPanelUtama.setBackground(new java.awt.Color(255, 255, 255));
        jPanelUtama.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout jPanelBackgroundLayout = new javax.swing.GroupLayout(jPanelBackground);
        jPanelBackground.setLayout(jPanelBackgroundLayout);
        jPanelBackgroundLayout.setHorizontalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addComponent(jPanelSidebar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kGradientPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanelBackgroundLayout.setVerticalGroup(
            jPanelBackgroundLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelSidebar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanelBackgroundLayout.createSequentialGroup()
                .addComponent(kGradientPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jPanelUtama, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabelDashboardMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDashboardMouseEntered
       hoverPanel(jPanelDashboard, jPanelDashboardSmall);
    }//GEN-LAST:event_jLabelDashboardMouseEntered

    private void jLabelDashboardMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDashboardMouseExited
       exitHover(jPanelDashboard, jPanelDashboardSmall);
    }//GEN-LAST:event_jLabelDashboardMouseExited

    private void jLabelAnimalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAnimalMouseEntered
       hoverPanel(jPanelAnimal, jPanelAnimalSmall);
    }//GEN-LAST:event_jLabelAnimalMouseEntered

    private void jLabelAnimalMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAnimalMouseExited
       exitHover(jPanelAnimal, jPanelAnimalSmall);
    }//GEN-LAST:event_jLabelAnimalMouseExited

    private void jPanelAnimalMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelAnimalMouseEntered
        hoverPanel(jPanelAnimal, jPanelAnimalSmall);
    }//GEN-LAST:event_jPanelAnimalMouseEntered

    private void jLabelDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDoctorMouseEntered
       hoverPanel(jPanelDoctor, jPanelDoctorSmall);
    }//GEN-LAST:event_jLabelDoctorMouseEntered

    private void jLabelDoctorMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDoctorMouseExited
       exitHover(jPanelDoctor, jPanelDoctorSmall);
    }//GEN-LAST:event_jLabelDoctorMouseExited

    private void jPanelDoctorMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDoctorMouseEntered
        hoverPanel(jPanelDoctor, jPanelDoctorSmall);
    }//GEN-LAST:event_jPanelDoctorMouseEntered

    private void jLabelMedicineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMedicineMouseEntered
       hoverPanel(jPanelMedicine, jPanelMedicineSmall);
    }//GEN-LAST:event_jLabelMedicineMouseEntered

    private void jLabelMedicineMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMedicineMouseExited
       exitHover(jPanelMedicine, jPanelMedicineSmall);
    }//GEN-LAST:event_jLabelMedicineMouseExited

    private void jPanelMedicineMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelMedicineMouseEntered
        hoverPanel(jPanelMedicine, jPanelMedicineSmall);
    }//GEN-LAST:event_jPanelMedicineMouseEntered

    private void jLabelReceiptMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReceiptMouseEntered
        hoverPanel(jPanelReceipt, jPanelReceiptSmall);
    }//GEN-LAST:event_jLabelReceiptMouseEntered

    private void jLabelReceiptMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReceiptMouseExited
        exitHover(jPanelReceipt, jPanelReceiptSmall);
    }//GEN-LAST:event_jLabelReceiptMouseExited

    private void jPanelReceiptMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelReceiptMouseEntered
       hoverPanel(jPanelReceipt, jPanelReceiptSmall);
    }//GEN-LAST:event_jPanelReceiptMouseEntered

    private void jPanelLogoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelLogoutMouseClicked
        
        LoginForm loginForm = new LoginForm();
        loginForm.setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_jPanelLogoutMouseClicked

    private void jPanelDashboardMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelDashboardMouseClicked
        showPanel(new DashboardPanel());
        setActiveMenu(jPanelDashboard, jPanelDashboardSmall);
    }//GEN-LAST:event_jPanelDashboardMouseClicked

    private void jLabelClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClientMouseEntered
       hoverPanel(jPanelClient, jPanelClientSmall);
    }//GEN-LAST:event_jLabelClientMouseEntered

    private void jLabelClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClientMouseExited
       exitHover(jPanelClient, jPanelClientSmall);
    }//GEN-LAST:event_jLabelClientMouseExited

    private void jPanelClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelClientMouseClicked
        try {
            showPanel(new ClientPanel());
        } catch (SQLException ex) {
            System.getLogger(Dashboard.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        setActiveMenu(jPanelClient, jPanelClientSmall);
    }//GEN-LAST:event_jPanelClientMouseClicked

    private void jPanelClientMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelClientMouseEntered
       hoverPanel(jPanelClient, jPanelClientSmall);
    }//GEN-LAST:event_jPanelClientMouseEntered

    private void jPanelClientMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelClientMouseExited
        exitHover(jPanelClient, jPanelClientSmall);
    }//GEN-LAST:event_jPanelClientMouseExited

    private void jPanelReceiptMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelReceiptMouseExited
       exitHover(jPanelReceipt, jPanelReceiptSmall);
    }//GEN-LAST:event_jPanelReceiptMouseExited

    private void jLabelAnimalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelAnimalMouseClicked
        try {
            showPanel(new AnimalPanel());
        } catch (SQLException ex) {
            System.getLogger(Dashboard.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        setActiveMenu(jPanelAnimal, jPanelAnimalSmall);
    }//GEN-LAST:event_jLabelAnimalMouseClicked

    private void jLabelClientMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelClientMouseClicked
        try {
            showPanel(new ClientPanel());
        } catch (SQLException ex) {
            System.getLogger(Dashboard.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        setActiveMenu(jPanelClient, jPanelClientSmall);
    }//GEN-LAST:event_jLabelClientMouseClicked

    private void jLabelDoctorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelDoctorMouseClicked
        try {
            showPanel(new DoctorPanel());
        } catch (SQLException ex) {
            System.getLogger(Dashboard.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        setActiveMenu(jPanelDoctor, jPanelDoctorSmall);
    }//GEN-LAST:event_jLabelDoctorMouseClicked

    private void jLabelMedicineMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelMedicineMouseClicked
        showPanel(new MedicinePanel());
        setActiveMenu(jPanelMedicine, jPanelMedicineSmall);
    }//GEN-LAST:event_jLabelMedicineMouseClicked

    private void jLabelReceiptMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabelReceiptMouseClicked
        showPanel(new ReceiptPanel());
        setActiveMenu(jPanelReceipt, jPanelReceiptSmall);
    }//GEN-LAST:event_jLabelReceiptMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        try{
            UIManager.setLookAndFeel(new FlatLightLaf());
            UIManager.put( "TextComponent.arc", 999 );
            UIManager.put( "Component.arc", 999 );
        } catch (Exception e){
            e.printStackTrace();
        }

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Dashboard().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabelAnimal;
    private javax.swing.JLabel jLabelClient;
    private javax.swing.JLabel jLabelClientIcon;
    private javax.swing.JLabel jLabelClientIcon1;
    private javax.swing.JLabel jLabelClientIcon2;
    private javax.swing.JLabel jLabelClientIcon3;
    private javax.swing.JLabel jLabelClientIcon4;
    private javax.swing.JLabel jLabelClientIcon5;
    private javax.swing.JLabel jLabelDashboard;
    private javax.swing.JLabel jLabelDoctor;
    private javax.swing.JLabel jLabelLogoRS;
    private javax.swing.JLabel jLabelMedicine;
    private javax.swing.JLabel jLabelNamaRS;
    private javax.swing.JLabel jLabelReceipt;
    private javax.swing.JLabel jLabelWaktu;
    private javax.swing.JPanel jPanelAnimal;
    private javax.swing.JPanel jPanelAnimalSmall;
    private javax.swing.JPanel jPanelBackground;
    private javax.swing.JPanel jPanelClient;
    private javax.swing.JPanel jPanelClientSmall;
    private javax.swing.JPanel jPanelDashboard;
    private javax.swing.JPanel jPanelDashboardSmall;
    private javax.swing.JPanel jPanelDoctor;
    private javax.swing.JPanel jPanelDoctorSmall;
    private javax.swing.JPanel jPanelLogout;
    private javax.swing.JPanel jPanelMedicine;
    private javax.swing.JPanel jPanelMedicineSmall;
    private javax.swing.JPanel jPanelReceipt;
    private javax.swing.JPanel jPanelReceiptSmall;
    private javax.swing.JPanel jPanelSidebar;
    private javax.swing.JPanel jPanelUtama;
    private keeptoo.KGradientPanel kGradientPanel1;
    // End of variables declaration//GEN-END:variables
}
