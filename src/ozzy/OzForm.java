/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.awt.Color;
/*   4:    */ import java.awt.Container;
/*   5:    */ import java.awt.EventQueue;
/*   6:    */ import java.awt.Font;
/*   7:    */ import java.awt.event.ActionEvent;
/*   8:    */ import java.awt.event.ActionListener;
/*   9:    */ import java.util.logging.Level;
/*  10:    */ import java.util.logging.Logger;
/*  11:    */ import javax.swing.GroupLayout;
/*  12:    */ import javax.swing.GroupLayout.Alignment;
/*  13:    */ import javax.swing.GroupLayout.ParallelGroup;
/*  14:    */ import javax.swing.GroupLayout.SequentialGroup;
/*  15:    */ import javax.swing.ImageIcon;
/*  16:    */ import javax.swing.JButton;
/*  17:    */ import javax.swing.JFrame;
/*  18:    */ import javax.swing.JLabel;
/*  19:    */ import javax.swing.JPanel;
/*  20:    */ import javax.swing.JProgressBar;
/*  21:    */ import javax.swing.JScrollPane;
/*  22:    */ import javax.swing.JTextArea;
/*  23:    */ import javax.swing.JTextField;
/*  24:    */ import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
/*  25:    */ import javax.swing.LayoutStyle.ComponentPlacement;
/*  26:    */ import javax.swing.UIManager;
/*  27:    */ import javax.swing.UIManager.LookAndFeelInfo;
/*  28:    */ import javax.swing.UnsupportedLookAndFeelException;
/*  29:    */ 
/*  30:    */ public class OzForm
/*  31:    */   extends JFrame
/*  32:    */ {
/*  33: 15 */   Ozzy Ozzy = new Ozzy();
/*  34:    */   private JButton jButtonDown;
/*  35:    */   private JButton jButtonOK;
/*  36:    */   private JButton jButtonUp;
/*  37:    */   private JButton jButtontest;
/*  38:    */   private JLabel jLabelOzzy;
/*  39:    */   private JLabel jLabelTitle;
/*  40:    */   private JPanel jPanel;
/*  41:    */   private JProgressBar jProgressBarAngryValue;
/*  42:    */   private JScrollPane jScrollPane1;
/*  43:    */   private JTextArea jTextAreaAnswer;
/*  44:    */   private JTextField jTextFieldInput;
/*  45:    */   private JToggleButton jToggleButton1;
/*  46:    */   private JToggleButton jToggleButton2;
/*  47:    */   
/*  48:    */   public OzForm()
/*  49:    */   {
/*  50: 22 */     initComponents();
/*  51:    */     
/*  52: 24 */     this.jButtontest.setVisible(false);
/*  53:    */     
/*  54: 26 */     this.jProgressBarAngryValue.setValue(this.Ozzy.OzPsych.getAngryValue());
/*  55:    */   }
/*  56:    */   
/*  57:    */   public void dispose()
/*  58:    */   {
/*  59: 31 */     this.Ozzy.doClosingThings();
/*  60: 32 */     super.dispose();
/*  61:    */   }
/*  62:    */   
/*  63:    */   private void initComponents()
/*  64:    */   {
/*  65: 44 */     this.jPanel = new JPanel();
/*  66: 45 */     this.jLabelOzzy = new JLabel();
/*  67: 46 */     this.jButtonOK = new JButton();
/*  68: 47 */     this.jLabelTitle = new JLabel();
/*  69: 48 */     this.jScrollPane1 = new JScrollPane();
/*  70: 49 */     this.jTextAreaAnswer = new JTextArea();
/*  71: 50 */     this.jButtonUp = new JButton();
/*  72: 51 */     this.jButtonDown = new JButton();
/*  73: 52 */     this.jTextFieldInput = new JTextField();
/*  74: 53 */     this.jProgressBarAngryValue = new JProgressBar();
/*  75: 54 */     this.jToggleButton1 = new JToggleButton();
/*  76: 55 */     this.jToggleButton2 = new JToggleButton();
/*  77: 56 */     this.jButtontest = new JButton();
/*  78:    */     
/*  79: 58 */     setDefaultCloseOperation(2);
/*  80: 59 */     setTitle("Ozzy V1.2.3");
/*  81:    */     
/*  82: 61 */     this.jLabelOzzy.setIcon(new ImageIcon(getClass().getResource("/ozzy/images/Ozzy.jpg")));
/*  83: 62 */     this.jLabelOzzy.setText("jLabel2");
/*  84:    */     
/*  85: 64 */     this.jButtonOK.setFont(new Font("Tahoma", 3, 11));
/*  86: 65 */     this.jButtonOK.setText("OK");
/*  87: 66 */     this.jButtonOK.addActionListener(new ActionListener()
/*  88:    */     {
/*  89:    */       public void actionPerformed(ActionEvent evt)
/*  90:    */       {
/*  91: 68 */         OzForm.this.jButtonOKActionPerformed(evt);
/*  92:    */       }
/*  93: 71 */     });
/*  94: 72 */     this.jLabelTitle.setFont(new Font("Tahoma", 1, 24));
/*  95: 73 */     this.jLabelTitle.setText("Frag Ozzy!");
/*  96: 74 */     this.jLabelTitle.setToolTipText("");
/*  97:    */     
/*  98: 76 */     this.jTextAreaAnswer.setEditable(false);
/*  99: 77 */     this.jTextAreaAnswer.setColumns(20);
/* 100: 78 */     this.jTextAreaAnswer.setLineWrap(true);
/* 101: 79 */     this.jTextAreaAnswer.setRows(5);
/* 102: 80 */     this.jTextAreaAnswer.setWrapStyleWord(true);
/* 103: 81 */     this.jScrollPane1.setViewportView(this.jTextAreaAnswer);
/* 104:    */     
/* 105: 83 */     this.jButtonUp.setIcon(new ImageIcon(getClass().getResource("/ozzy/images/thumbup.png")));
/* 106:    */     
/* 107: 85 */     this.jButtonDown.setIcon(new ImageIcon(getClass().getResource("/ozzy/images/thumbdown.png")));
/* 108:    */     
/* 109: 87 */     this.jTextFieldInput.setText("Wohin sollen wir heute Mittag zum Essen gehn?");
/* 110:    */     
/* 111: 89 */     this.jProgressBarAngryValue.setBackground(new Color(255, 0, 0));
/* 112: 90 */     this.jProgressBarAngryValue.setForeground(new Color(51, 255, 51));
/* 113:    */     
/* 114: 92 */     this.jToggleButton1.setText("chat");
/* 115: 93 */     this.jToggleButton1.addActionListener(new ActionListener()
/* 116:    */     {
/* 117:    */       public void actionPerformed(ActionEvent evt)
/* 118:    */       {
/* 119: 95 */         OzForm.this.jToggleButton1ActionPerformed(evt);
/* 120:    */       }
/* 121: 98 */     });
/* 122: 99 */     this.jToggleButton2.setText("OzBrain");
/* 123:100 */     this.jToggleButton2.addActionListener(new ActionListener()
/* 124:    */     {
/* 125:    */       public void actionPerformed(ActionEvent evt)
/* 126:    */       {
/* 127:102 */         OzForm.this.jToggleButton2ActionPerformed(evt);
/* 128:    */       }
/* 129:105 */     });
/* 130:106 */     this.jButtontest.setText("test");
/* 131:107 */     this.jButtontest.addActionListener(new ActionListener()
/* 132:    */     {
/* 133:    */       public void actionPerformed(ActionEvent evt)
/* 134:    */       {
/* 135:109 */         OzForm.this.jButtontestActionPerformed(evt);
/* 136:    */       }
/* 137:112 */     });
/* 138:113 */     GroupLayout jPanelLayout = new GroupLayout(this.jPanel);
/* 139:114 */     this.jPanel.setLayout(jPanelLayout);
/* 140:115 */     jPanelLayout.setHorizontalGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jLabelOzzy, -2, 235, -2).addComponent(this.jProgressBarAngryValue, -1, -1, 32767)).addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addGap(8, 8, 8).addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addComponent(this.jLabelTitle, -2, 136, -2).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, -1, 32767).addComponent(this.jButtontest).addGap(18, 18, 18)).addComponent(this.jTextFieldInput, GroupLayout.Alignment.TRAILING).addComponent(this.jScrollPane1))).addGroup(jPanelLayout.createSequentialGroup().addGap(10, 10, 10).addComponent(this.jButtonUp, -2, 44, -2).addGap(18, 18, 18).addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addComponent(this.jToggleButton1).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 34, 32767).addComponent(this.jToggleButton2)).addComponent(this.jButtonOK, -1, -1, 32767)).addGap(18, 18, 18).addComponent(this.jButtonDown, -2, 47, -2))).addContainerGap()));
/* 141:    */     
/* 142:    */ 
/* 143:    */ 
/* 144:    */ 
/* 145:    */ 
/* 146:    */ 
/* 147:    */ 
/* 148:    */ 
/* 149:    */ 
/* 150:    */ 
/* 151:    */ 
/* 152:    */ 
/* 153:    */ 
/* 154:    */ 
/* 155:    */ 
/* 156:    */ 
/* 157:    */ 
/* 158:    */ 
/* 159:    */ 
/* 160:    */ 
/* 161:    */ 
/* 162:    */ 
/* 163:    */ 
/* 164:    */ 
/* 165:    */ 
/* 166:    */ 
/* 167:    */ 
/* 168:    */ 
/* 169:    */ 
/* 170:    */ 
/* 171:    */ 
/* 172:147 */     jPanelLayout.setVerticalGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addContainerGap().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(jPanelLayout.createSequentialGroup().addComponent(this.jLabelOzzy).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addComponent(this.jProgressBarAngryValue, -2, -1, -2)).addGroup(jPanelLayout.createSequentialGroup().addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING).addComponent(this.jLabelTitle).addComponent(this.jButtontest)).addGap(18, 18, 18).addComponent(this.jTextFieldInput, -2, -1, -2).addGap(18, 18, 18).addComponent(this.jScrollPane1, -2, 154, -2).addGap(9, 9, 9).addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.BASELINE).addComponent(this.jToggleButton1).addComponent(this.jToggleButton2)).addPreferredGap(LayoutStyle.ComponentPlacement.RELATED).addGroup(jPanelLayout.createParallelGroup(GroupLayout.Alignment.LEADING, false).addComponent(this.jButtonUp, GroupLayout.Alignment.TRAILING, -1, 41, 32767).addComponent(this.jButtonDown).addComponent(this.jButtonOK, GroupLayout.Alignment.TRAILING, -1, -1, 32767)))).addContainerGap(-1, 32767)));
/* 173:    */     
/* 174:    */ 
/* 175:    */ 
/* 176:    */ 
/* 177:    */ 
/* 178:    */ 
/* 179:    */ 
/* 180:    */ 
/* 181:    */ 
/* 182:    */ 
/* 183:    */ 
/* 184:    */ 
/* 185:    */ 
/* 186:    */ 
/* 187:    */ 
/* 188:    */ 
/* 189:    */ 
/* 190:    */ 
/* 191:    */ 
/* 192:    */ 
/* 193:    */ 
/* 194:    */ 
/* 195:    */ 
/* 196:    */ 
/* 197:    */ 
/* 198:    */ 
/* 199:    */ 
/* 200:    */ 
/* 201:176 */     GroupLayout layout = new GroupLayout(getContentPane());
/* 202:177 */     getContentPane().setLayout(layout);
/* 203:178 */     layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel, -1, -1, 32767)));
/* 204:    */     
/* 205:    */ 
/* 206:    */ 
/* 207:    */ 
/* 208:    */ 
/* 209:184 */     layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap().addComponent(this.jPanel, -1, -1, 32767)));
/* 210:    */     
/* 211:    */ 
/* 212:    */ 
/* 213:    */ 
/* 214:    */ 
/* 215:    */ 
/* 216:191 */     pack();
/* 217:    */   }
/* 218:    */   
/* 219:    */   private void jButtonOKActionPerformed(ActionEvent evt)
/* 220:    */   {
/* 221:195 */     if (this.jToggleButton1.isSelected())
/* 222:    */     {
/* 223:196 */       this.jTextAreaAnswer.setText(this.Ozzy.OzTextBuilder.answerMe(this.jTextFieldInput.getText(), this.Ozzy.Brain));
/* 224:    */     }
/* 225:197 */     else if (this.jToggleButton2.isSelected())
/* 226:    */     {
/* 227:198 */       this.jTextAreaAnswer.setText(this.Ozzy.OzTextBuilder.parser(this.jTextFieldInput.getText(), this.Ozzy.Brain));
/* 228:    */     }
/* 229:    */     else
/* 230:    */     {
/* 231:200 */       this.jTextAreaAnswer.setText(this.Ozzy.OzLogic.askMe(this.Ozzy.OzMemory, this.Ozzy.OzPsych, this.Ozzy.locations));
/* 232:201 */       this.jProgressBarAngryValue.setValue(this.Ozzy.OzPsych.getAngryValue());
/* 233:    */     }
/* 234:    */   }
/* 235:    */   
/* 236:    */   private void jToggleButton1ActionPerformed(ActionEvent evt)
/* 237:    */   {
/* 238:206 */     this.jTextFieldInput.setText("Was willst du wissen?");
/* 239:    */   }
/* 240:    */   
/* 241:    */   private void jToggleButton2ActionPerformed(ActionEvent evt)
/* 242:    */   {
/* 243:210 */     this.jTextFieldInput.setText(" - Befehl eingeben -");
/* 244:    */   }
/* 245:    */   
/* 246:    */   private void jButtontestActionPerformed(ActionEvent evt)
/* 247:    */   {
/* 248:215 */     this.Ozzy.doSomeTests();
/* 249:    */   }
/* 250:    */   
/* 251:    */   public static void main(String[] args)
/* 252:    */   {
/* 253:    */     
/* 279:247 */     EventQueue.invokeLater(new Runnable()
/* 280:    */     {
/* 281:    */       public void run()
/* 282:    */       {
/* 283:249 */         new OzForm().setVisible(true);
/* 284:    */       }
/* 285:    */     });
/* 286:    */   }
/* 287:    */ }



/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar

 * Qualified Name:     ozzy.OzForm

 * JD-Core Version:    0.7.0.1

 */