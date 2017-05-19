/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ 
/*   5:    */ public class OzLocation
/*   6:    */   implements Serializable
/*   7:    */ {
/*   8:    */   private static final long serialVersionUID = -123456789L;
/*   9: 18 */   private static int locationCounter = 0;
/*  10:    */   private int id;
/*  11:    */   private String name;
/*  12:    */   private char gender;
/*  13:    */   private String category;
/*  14:    */   private boolean hasDailymenu;
/*  15:    */   private int prio;
/*  16:    */   private double weight;
/*  17:    */   private String ozzysaid;
/*  18:    */   
/*  19:    */   public OzLocation(String name, char gender, String category, boolean hasDailymenu, int prio, double weight, String ozzysaid)
/*  20:    */   {
/*  21: 35 */     this.id = (locationCounter++);
/*  22: 36 */     this.name = name;
/*  23: 37 */     this.gender = gender;
/*  24: 38 */     this.category = category;
/*  25: 39 */     this.hasDailymenu = hasDailymenu;
/*  26: 40 */     this.prio = prio;
/*  27: 41 */     this.weight = weight;
/*  28: 42 */     this.ozzysaid = ozzysaid;
/*  29:    */   }
/*  30:    */   
/*  31:    */   public OzLocation(int id, String name, char gender, String category, boolean hasDailymenu, int prio, double weight, String ozzysaid)
/*  32:    */   {
/*  33: 46 */     this.id = id;
/*  34: 47 */     this.name = name;
/*  35: 48 */     this.gender = gender;
/*  36: 49 */     this.category = category;
/*  37: 50 */     this.hasDailymenu = hasDailymenu;
/*  38: 51 */     this.prio = prio;
/*  39: 52 */     this.weight = weight;
/*  40: 53 */     this.ozzysaid = ozzysaid;
/*  41:    */   }
/*  42:    */   
/*  43:    */   public String getName()
/*  44:    */   {
/*  45: 59 */     return this.name;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public String getOzzysaid()
/*  49:    */   {
/*  50: 63 */     return this.ozzysaid;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public int getId()
/*  54:    */   {
/*  55: 67 */     return this.id;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public String getCategory()
/*  59:    */   {
/*  60: 71 */     return this.category;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public boolean isHasDailymenu()
/*  64:    */   {
/*  65: 75 */     return this.hasDailymenu;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public int getPrio()
/*  69:    */   {
/*  70: 79 */     return this.prio;
/*  71:    */   }
/*  72:    */   
/*  73:    */   public double getWeight()
/*  74:    */   {
/*  75: 83 */     return this.weight;
/*  76:    */   }
/*  77:    */   
/*  78:    */   public static int getLocationCounter()
/*  79:    */   {
/*  80: 88 */     return locationCounter;
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void setId(int id)
/*  84:    */   {
/*  85: 94 */     this.id = id;
/*  86:    */   }
/*  87:    */   
/*  88:    */   public void setName(String name)
/*  89:    */   {
/*  90: 98 */     this.name = name;
/*  91:    */   }
/*  92:    */   
/*  93:    */   public void setCategory(String category)
/*  94:    */   {
/*  95:102 */     this.category = category;
/*  96:    */   }
/*  97:    */   
/*  98:    */   public void setHasDailymenu(boolean hasDailymenu)
/*  99:    */   {
/* 100:106 */     this.hasDailymenu = hasDailymenu;
/* 101:    */   }
/* 102:    */   
/* 103:    */   public void setPrio(int prio)
/* 104:    */   {
/* 105:110 */     this.prio = prio;
/* 106:    */   }
/* 107:    */   
/* 108:    */   public void setWeight(double weight)
/* 109:    */   {
/* 110:114 */     this.weight = weight;
/* 111:    */   }
/* 112:    */   
/* 113:    */   public void setOzzysaid(String ozzysaid)
/* 114:    */   {
/* 115:118 */     this.ozzysaid = ozzysaid;
/* 116:    */   }
/* 117:    */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.OzLocation
 * JD-Core Version:    0.7.0.1
 */