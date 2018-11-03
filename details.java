Skip to content
 
Search or jump to…

Pull requests
Issues
Marketplace
Explore
 @kangatta Sign out
1
1 0 EdwinWalela/startup-managment
 Code  Issues 0  Pull requests 0  Projects 0  Wiki  Insights
startup-managment/details.java
4172b69  22 hours ago
@EdwinWalela EdwinWalela admin logout admindashboard fullscreen
@EdwinWalela @kangatta
     
391 lines (341 sloc)  21 KB

import javax.swing.JOptionPane;
import java.awt.*;
import java.sql.Connection;

public class details extends javax.swing.JFrame {
    Query query;
    public details(Query newQuery) {
        initComponents();
        query= newQuery;
        // Ensure application is centered on the screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }
    @SuppressWarnings("unchecked")

    private void initComponents() {
        panel1 = new javax.swing.JPanel();
        login = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        loginUsername = new javax.swing.JTextField();
        loginPassword = new javax.swing.JPasswordField();
        jButton7 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        signup = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        user_id = new javax.swing.JTextField();
        startup_id = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        admin = new javax.swing.JTextField();
        userName = new javax.swing.JTextField();
        addUser = new javax.swing.JButton();
        back = new javax.swing.JButton();
        cancel = new javax.swing.JButton();
        password = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        panel1.setLayout(new java.awt.CardLayout());

        jLabel13.setText("user ID");

        jLabel14.setText("password");

        jButton6.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton6.setText("SUBMIT");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton7.setText("EXIT");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("LOGIN");

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton1.setText("LOGIN");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jButton5.setText("REGISTER");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel11.setText("STARTUP MANAGEMENT SYSTEM");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(86, 86, 86)
                                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 133, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(63, 63, 63)
                                                .addComponent(jButton5))
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addGap(49, 49, 49)
                                                .addComponent(jLabel11)))
                                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel11)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton1)
                                        .addComponent(jButton5))
                                .addGap(37, 37, 37))
        );

        javax.swing.GroupLayout loginLayout = new javax.swing.GroupLayout(login);
        login.setLayout(loginLayout);
        loginLayout.setHorizontalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, loginLayout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(loginLayout.createSequentialGroup()
                                .addGap(115, 115, 115)
                                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(loginLayout.createSequentialGroup()
                                                .addGap(104, 104, 104)
                                                .addComponent(jLabel12))
                                        .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(loginLayout.createSequentialGroup()
                                                        .addComponent(jButton6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                                                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addComponent(loginPassword, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(loginUsername, javax.swing.GroupLayout.Alignment.LEADING)))
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        loginLayout.setVerticalGroup(
                loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(loginLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel13)
                                .addGap(27, 27, 27)
                                .addComponent(loginUsername, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(jLabel14)
                                .addGap(36, 36, 36)
                                .addComponent(loginPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addGroup(loginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jButton6)
                                        .addComponent(jButton7))
                                .addGap(32, 32, 32))
        );

        panel1.add(login, "card4");

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel15.setText(" USER SIGNUP");

        jLabel16.setText("PASSWORD");

        jLabel1.setText("USER ID");


        jLabel2.setText("STARTUP ID");

        jLabel3.setText("EMAIL");

        jLabel4.setText("ADMIN");

        jLabel6.setText("USERNAME");

        addUser.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        addUser.setText("ADD USER");

        back.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        back.setText("BACK");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        cancel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        cancel.setText("CANCEL");
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout signupLayout = new javax.swing.GroupLayout(signup);
        signup.setLayout(signupLayout);
        signupLayout.setHorizontalGroup(
                signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(signupLayout.createSequentialGroup()
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(signupLayout.createSequentialGroup()
                                                .addGap(175, 175, 175)
                                                .addComponent(jLabel15))
                                        .addGroup(signupLayout.createSequentialGroup()
                                                .addGap(44, 44, 44)
                                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2)
                                                        .addComponent(jLabel1)
                                                        .addComponent(jLabel3)
                                                        .addComponent(jLabel4)
                                                        .addComponent(jLabel6)
                                                        .addComponent(jLabel16))
                                                .addGap(18, 18, 18)
                                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(user_id, javax.swing.GroupLayout.DEFAULT_SIZE, 174, Short.MAX_VALUE)
                                                        .addComponent(email, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(admin)
                                                        .addComponent(userName)
                                                        .addComponent(password)
                                                        .addComponent(startup_id, javax.swing.GroupLayout.Alignment.TRAILING))
                                                .addGap(22, 22, 22)
                                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addComponent(addUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(back, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(cancel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                .addGap(28, 28, 28))
        );
        signupLayout.setVerticalGroup(
                signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, signupLayout.createSequentialGroup()
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(signupLayout.createSequentialGroup()
                                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addGroup(signupLayout.createSequentialGroup()
                                                                .addComponent(jLabel15)
                                                                .addGap(131, 131, 131))
                                                        .addGroup(signupLayout.createSequentialGroup()
                                                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                                                        .addComponent(user_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel1))
                                                                .addGap(35, 35, 35)
                                                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                                        .addComponent(startup_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                        .addComponent(jLabel2))))
                                                .addGap(41, 41, 41))
                                        .addGroup(signupLayout.createSequentialGroup()
                                                .addComponent(addUser)
                                                .addGap(30, 30, 30)
                                                .addComponent(back)
                                                .addGap(30, 30, 30)))
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel3)
                                        .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(cancel))
                                .addGap(33, 33, 33)
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(admin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(43, 43, 43)
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(userName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                                .addGroup(signupLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel16)
                                        .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(75, 75, 75))
        );

        panel1.add(signup, "card2");

        getContentPane().add(panel1, java.awt.BorderLayout.CENTER);

        pack();


    }

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {

        panel1.removeAll();
        panel1.add(login);
        panel1.repaint();
        panel1.revalidate();
    }

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {

        panel1.removeAll();
        panel1.add(signup);
        panel1.repaint();
        panel1.revalidate();
    }

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {
        System.exit(0);
    }
    
    private void addUserActionPerformed(java.awt.event.ActionEvent evt) 
    {
      String usid =user_id.getText();
        String stid=startup_id.getText();
        String mail =email.getText();
        String adm =admin.getText();
        String user =userName.getText();
        String pass =password.getText();
        if(usid.equals("")||stid.equals("")||mail.equals("")|| adm.equals("")|| user.equals("") || pass.equals(""))
        {
            JOptionPane.showMessageDialog(null, "Missing fields");
        }
        else
        {
                boolean success= query.registerUser(new String[]{usid, stid, mail, adm, user, pass});
                if
                (success)
                {
                     JOptionPane.showMessageDialog(null, "successfully added");
                }
        else
        {
             JOptionPane.showMessageDialog(null, "please try again");
        }
         }
       
    }

    private void backActionPerformed(java.awt.event.ActionEvent evt) {
        signup.removeAll();
        signup.add(login);
        signup.repaint();
        signup.revalidate();
    }

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }

    public static void main(String args[]) {
        Connection conn = new Configuration().newConnection();
        Query query = new Query(conn);
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new details(query).setVisible(true);
            }
        });
    }


    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {

        String userName=loginUsername.getText();
        String password=loginPassword.getText();
        if(userName.equals("") || password.equals("")){
            JOptionPane.showMessageDialog(null, "Missing fields");
        }else {
            boolean[] result = query.userLogin(new String[]{userName, password});
            if (result[0]) {
                if (result[1]) {
                    new AdminDashboardFrame(query);
                    setVisible(false);
                } else {
                    //NORMAL USER DASHBOARD
                    setVisible(false);

                }
            } else {
                JOptionPane.showMessageDialog(null, "Invalid Credentials!!!!");
            }
        }

    }



    private javax.swing.JButton addUser;
    private javax.swing.JTextField admin;
    private javax.swing.JButton back;
    private javax.swing.JButton cancel;
    private javax.swing.JTextField email;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel login;
    private javax.swing.JPasswordField loginPassword;
    private javax.swing.JTextField loginUsername;
    private javax.swing.JPanel panel1;
    private javax.swing.JPasswordField password;
    private javax.swing.JPanel signup;
    private javax.swing.JTextField startup_id;
    private javax.swing.JTextField userName;
    private javax.swing.JTextField user_id;
    // End of variables declaration//GEN-END:variables
}
