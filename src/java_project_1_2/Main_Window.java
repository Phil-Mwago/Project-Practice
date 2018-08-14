/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java_project_1_2;

import java.awt.Image;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author PHILA
 */
public class Main_Window extends javax.swing.JFrame {

    /**
     * Creates new form Main_Window
     */
    public Main_Window() {
        initComponents();
        Show_Products_In_JTable();
        //getConnection();
    }

    String ImgPath = null;
    int pos = 0;
    
    public Connection getConnection()
    {
     Connection con = null;
     
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/products_db2","root","");
            //JOptionPane.showMessageDialog(null, "Connected To DB");
            return con;
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        JOptionPane.showMessageDialog(null, "Not Connected to DB");
            return null;
        }
    }
    
    //Check Input Fields
    
    public boolean checkInputs()
    {
     if(
             txt_name.getText() == null
             || txt_price.getText() == null
             ||txt_AddDate.getDate() == null
             ) 
     {return false;
     }
     else{
         try{
         Float.parseFloat(txt_price.getText()); 
         return true; //(txt_price.getText());
         }catch(Exception ex)
         {
             return false;
     }
    }
    }
    
    
    //Resize Image
  public  ImageIcon ResizeImage(String imagePath, byte[] pic) 
  {
      ImageIcon myImage = null;
      
      if(imagePath !=null)
      {
          myImage = new ImageIcon(imagePath);
      }else{
          
        myImage = new ImageIcon(pic);
          
      }
      
      Image img = myImage.getImage();
      Image img2 = img.getScaledInstance(lbl_image.getWidth(), lbl_image.getHeight(), Image.SCALE_SMOOTH);
      ImageIcon image = new ImageIcon(img2);
      return image;
  }
    //Display Table Data
  // 1. Fill ArrayList With Data
  
  public ArrayList<Product> getProductList()
  {
                  ArrayList<Product>  productList = new ArrayList<Product>();
            Connection con =  getConnection();
            String querry = "SELECT * FROM products";
            
            Statement st;
            ResultSet rs;  
      try {

            
            st = con.createStatement();
            rs = st.executeQuery(querry);
            Product product;
            
            while(rs.next())
            {
            product = new Product(rs.getInt("id"),rs.getString("name"),Float.parseFloat(rs.getString("price")),rs.getString("add_date"),rs.getBytes("image"));
            productList.add(product);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
      return productList;
  }
  
  
  
  //2. Populate the JTable 
   
  public void Show_Products_In_JTable()
  {
  ArrayList<Product> list = getProductList();
  DefaultTableModel model = (DefaultTableModel)JTable_Products.getModel();
  
  //clear jtable content
  model.setRowCount(0);
 Object[] row = new Object[4];
 for(int i = 0; i< list.size(); i++)
 {
     row[0] = list.get(i).getId();
     row[1] = list.get(i).getName();
     row[2] = list.get(i).getPrice();
     row[3] = list.get(i).getAddDate();
     
     model.addRow(row);
 }
 }
  
  public void ShowItem(int index)
  {
      txt_id.setText(Integer.toString(getProductList().get(index).getId()));
            txt_name.setText(getProductList().get(index).getName());
            txt_price.setText(Float.toString(getProductList().get(index).getPrice()));
           
        try {
             Date addDate = null;
            addDate = new SimpleDateFormat("yyyy-MM-dd").parse((String)getProductList().get(index).getAddDate());
        txt_AddDate.setDate(addDate);
        } catch (ParseException ex) {
            Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
        }
        lbl_image.setIcon(ResizeImage(null, getProductList().get(index).getImage()));
  }
 
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_id = new javax.swing.JTextField();
        txt_price = new javax.swing.JTextField();
        txt_name = new javax.swing.JTextField();
        txt_AddDate = new com.toedter.calendar.JDateChooser();
        lbl_image = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        JTable_Products = new javax.swing.JTable();
        btn_ChooseImage = new javax.swing.JButton();
        Btn_Insert = new javax.swing.JButton();
        Btn_Previous = new javax.swing.JButton();
        Btn_Next = new javax.swing.JButton();
        Btn_First = new javax.swing.JButton();
        btn_update = new javax.swing.JButton();
        btn_Delete = new javax.swing.JButton();
        Btn_Last = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Get It Done");

        jPanel1.setBackground(new java.awt.Color(255, 204, 153));

        jLabel1.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel1.setText("ID:");

        jLabel2.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel2.setText("Name");

        jLabel3.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel3.setText("Price:");

        jLabel4.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel4.setText("Add Date:");

        jLabel5.setFont(new java.awt.Font("sansserif", 1, 18)); // NOI18N
        jLabel5.setText("Image:");

        txt_id.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N
        txt_id.setEnabled(false);

        txt_price.setFont(new java.awt.Font("Copperplate Gothic Light", 1, 14)); // NOI18N

        txt_name.setFont(new java.awt.Font("Lucida Sans Unicode", 1, 14)); // NOI18N

        txt_AddDate.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N

        lbl_image.setBackground(new java.awt.Color(204, 51, 255));
        lbl_image.setOpaque(true);

        JTable_Products.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Price", "Add Date"
            }
        ));
        JTable_Products.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                JTable_ProductsMouseClicked(evt);
            }
        });
        JTable_Products.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                JTable_ProductsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(JTable_Products);

        btn_ChooseImage.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        btn_ChooseImage.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Upload.png")); // NOI18N
        btn_ChooseImage.setText("Choose Image");
        btn_ChooseImage.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_ChooseImageActionPerformed(evt);
            }
        });

        Btn_Insert.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        Btn_Insert.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Add.png")); // NOI18N
        Btn_Insert.setText("Insert");
        Btn_Insert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_InsertActionPerformed(evt);
            }
        });

        Btn_Previous.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        Btn_Previous.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Left.png")); // NOI18N
        Btn_Previous.setText("Previous");
        Btn_Previous.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_PreviousActionPerformed(evt);
            }
        });

        Btn_Next.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        Btn_Next.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Forward.png")); // NOI18N
        Btn_Next.setText("Next");
        Btn_Next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_NextActionPerformed(evt);
            }
        });

        Btn_First.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        Btn_First.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Fast Rewind.png")); // NOI18N
        Btn_First.setText("First");
        Btn_First.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_FirstActionPerformed(evt);
            }
        });

        btn_update.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        btn_update.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\must_have_icons_icons_pack_120704\\must_have_icon_set\\Refresh\\Refresh_32x32.png")); // NOI18N
        btn_update.setText("Update");
        btn_update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_updateActionPerformed(evt);
            }
        });

        btn_Delete.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        btn_Delete.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Cancel.png")); // NOI18N
        btn_Delete.setText("Delete");
        btn_Delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_DeleteActionPerformed(evt);
            }
        });

        Btn_Last.setFont(new java.awt.Font("Perpetua Titling MT", 1, 14)); // NOI18N
        Btn_Last.setIcon(new javax.swing.ImageIcon("C:\\Users\\PHILA\\Desktop\\OrganiZen\\DICTM\\Java Works\\AddOns\\buttons_toolbar_icons_icons_pack_120592\\knobs\\PNG\\Knob Fast Forward.png")); // NOI18N
        Btn_Last.setText("Last");
        Btn_Last.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Btn_LastMouseClicked(evt);
            }
        });
        Btn_Last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Btn_LastActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("sansserif", 1, 14)); // NOI18N
        jLabel6.setText("Multi Use JFrame");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(Btn_Insert, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btn_update, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(17, 17, 17)
                .addComponent(btn_Delete, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(Btn_First, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Btn_Next)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Previous)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Btn_Last, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(246, 246, 246))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(54, 54, 54)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(24, 24, 24)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_ChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(32, 32, 32)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 545, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(210, 210, 210)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txt_id, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txt_name, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txt_price, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txt_AddDate, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbl_image, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(81, 81, 81)
                                .addComponent(jLabel5))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btn_ChooseImage, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Btn_Insert)
                    .addComponent(Btn_Last)
                    .addComponent(Btn_Previous)
                    .addComponent(Btn_Next)
                    .addComponent(Btn_First)
                    .addComponent(btn_Delete)
                    .addComponent(btn_update))
                .addContainerGap(18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 991, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_ChooseImageActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_ChooseImageActionPerformed

JFileChooser file = new JFileChooser();
file.setCurrentDirectory(new File(System.getProperty("user.home")));

        FileNameExtensionFilter filter = new FileNameExtensionFilter("*.image", "jpg", "png");
        file.addChoosableFileFilter(filter);
        int result = file.showSaveDialog(null);
        if (result == JFileChooser.APPROVE_OPTION)
        {
            File selectedFile = file.getSelectedFile();
            String path = selectedFile.getAbsolutePath();
            lbl_image.setIcon(ResizeImage(path, null));
            ImgPath = path;
        }else{
            System.out.println("No File Selected");
        }
        
        // TODO add your handling code he;re:
    }//GEN-LAST:event_btn_ChooseImageActionPerformed

    private void btn_updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_updateActionPerformed

        if(checkInputs() && txt_id.getText() != null)
{
String UpdateQuery = null;
        PreparedStatement ps = null;
        Connection con = getConnection();
       
        //update without image
        if(ImgPath == null)
        {
    try {
        UpdateQuery = "UPDATE products SET name = ?, price = ?" + ", add_date = ? WHERE id = ?";
        
        ps = con.prepareStatement(UpdateQuery);
        
        ps.setString(1, txt_name.getText());
        ps.setString(2, txt_price.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = dateFormat.format(txt_AddDate.getDate());
        
        ps.setString(3, addDate);
        
        ps.setInt(4, Integer.parseInt(txt_id.getText()));
        
         ps.executeUpdate();
         Show_Products_In_JTable();
          JOptionPane.showMessageDialog(null, "Item Updated on DB");
        
          
    } catch (SQLException ex) {
        Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
    }
        }
        //Update with Image
        else {
            try{
           InputStream img = new FileInputStream(new File(ImgPath));
            
           UpdateQuery = "UPDATE products SET name = ?, price = ?" + ", add_date = ?, image = ? WHERE id = ?";
         
           ps = con.prepareStatement(UpdateQuery);
        
        ps.setString(1, txt_name.getText());
        ps.setString(2, txt_price.getText());
        
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String addDate = dateFormat.format(txt_AddDate.getDate());
        
        ps.setString(3, addDate);
        
        ps.setBlob(4, img);  
        
        ps.setInt(5, Integer.parseInt(txt_id.getText()));
        
        ps.executeUpdate();
        Show_Products_In_JTable();
        JOptionPane.showMessageDialog(null, "Item Updated on DB");
        
         JOptionPane.showMessageDialog(null, "Item Updated on DB");
                
            }catch(Exception ex)
            {
                JOptionPane.showMessageDialog(null, ex.getMessage());
         
            } 
                    
        
        }
}else{ 
            JOptionPane.showMessageDialog(null, "One or More Feilds are Empty or Wrong");}
        
    }//GEN-LAST:event_btn_updateActionPerformed

    private void Btn_PreviousActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_PreviousActionPerformed

        pos--;
        if(pos<0)
        {
        pos = 0;
        }
        
        ShowItem(pos);
    }//GEN-LAST:event_Btn_PreviousActionPerformed

    private void Btn_InsertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_InsertActionPerformed

if(checkInputs() && ImgPath != null)
{
 
    try {
           Connection con = getConnection();
        PreparedStatement ps = con.prepareStatement("INSERT INTO products(name,price,add_date,image)" + "values(?,?,?,?)");
        ps.setString(1, txt_name.getText());
        ps.setString(2, txt_price.getText());
        SimpleDateFormat dateFormat =  new SimpleDateFormat("yyyy-MM-dd");
        String addDate = dateFormat.format(txt_AddDate.getDate());
        ps.setString(3, addDate);
        
        
           InputStream img = new FileInputStream(new File(ImgPath));
           ps.setBlob(4, img);
           ps.executeUpdate();
           Show_Products_In_JTable();
           
                   JOptionPane.showMessageDialog(null, "Data Inserted");
           
    } catch (Exception ex) {
    JOptionPane.showMessageDialog(null, ex.getMessage());
    }
}else{
    JOptionPane.showMessageDialog(null, "One Or More Feilds are Empty");
}
        System.out.println("Name => "+txt_name.getText());
         System.out.println("Price => "+txt_price.getText());
          System.out.println("Image => "+ImgPath);
// TODO add your handling code here:
    }//GEN-LAST:event_Btn_InsertActionPerformed

    private void btn_DeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_DeleteActionPerformed
 
        if(!txt_id.getText().equals(""))
        {
            try {
                Connection con = getConnection();
                PreparedStatement ps = con.prepareStatement("DELETE FROM products WHERE id = ?");
                int id = Integer.parseInt(txt_id.getText());
                ps.setInt(1, id);
                ps.executeUpdate();
                Show_Products_In_JTable();
                JOptionPane.showMessageDialog(null, "Product Deleted");
            } catch (SQLException ex) {
                Logger.getLogger(Main_Window.class.getName()).log(Level.SEVERE, null, ex);
                       JOptionPane.showMessageDialog(null, "Product Not Deleted");
            }
        }else{
           JOptionPane.showMessageDialog(null, "Product Not Deleted : Please \n Enter the Product \n ID to be Deleted");  
        }
        
    }//GEN-LAST:event_btn_DeleteActionPerformed

    private void JTable_ProductsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_JTable_ProductsMouseClicked
     
        int index = JTable_Products.getSelectedRow();
        ShowItem(index);
        
    }//GEN-LAST:event_JTable_ProductsMouseClicked

    private void Btn_FirstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_FirstActionPerformed
    
        pos = 0;
        ShowItem(pos);
              
        
    }//GEN-LAST:event_Btn_FirstActionPerformed

    private void Btn_LastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_LastActionPerformed

       
        pos = getProductList().size()-1;
           ShowItem(pos);
  
    }//GEN-LAST:event_Btn_LastActionPerformed

    private void Btn_NextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Btn_NextActionPerformed

pos++;

if(pos >= getProductList().size())
{
pos = getProductList().size()-1;
}
        ShowItem(pos);
    }//GEN-LAST:event_Btn_NextActionPerformed

    private void JTable_ProductsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_JTable_ProductsKeyReleased

 int index = JTable_Products.getSelectedRow();
        ShowItem(index);
        // TODO add your handling code here:
    }//GEN-LAST:event_JTable_ProductsKeyReleased

    private void Btn_LastMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Btn_LastMouseClicked
     
      //int index = JTable_Products.getSelectedRow();
        ShowItem(pos);          
        
    }//GEN-LAST:event_Btn_LastMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Main_Window.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Main_Window().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Btn_First;
    private javax.swing.JButton Btn_Insert;
    private javax.swing.JButton Btn_Last;
    private javax.swing.JButton Btn_Next;
    private javax.swing.JButton Btn_Previous;
    private javax.swing.JTable JTable_Products;
    private javax.swing.JButton btn_ChooseImage;
    private javax.swing.JButton btn_Delete;
    private javax.swing.JButton btn_update;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_image;
    private com.toedter.calendar.JDateChooser txt_AddDate;
    private javax.swing.JTextField txt_id;
    private javax.swing.JTextField txt_name;
    private javax.swing.JTextField txt_price;
    // End of variables declaration//GEN-END:variables
}
