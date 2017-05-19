/*  1:   */ package ozzy;
/*  2:   */ 
/*  3:   */ import java.io.Serializable;
/*  4:   */ import java.util.Date;
/*  5:   */ 
/*  6:   */ public class OzText
/*  7:   */   implements Serializable
/*  8:   */ {
/*  9:   */   private static final long serialVersionUID = -123456789L;
/* 10:   */   private String myText;
/* 11:   */   private int category;
/* 12:   */   private Date timestamp;
/* 13:   */   
/* 14:   */   public OzText(String myText, int category)
/* 15:   */   {
/* 16:33 */     this.myText = myText;
/* 17:34 */     this.category = category;
/* 18:35 */     this.timestamp = setTimestamp();
/* 19:   */   }
/* 20:   */   
/* 21:   */   public String getMyText()
/* 22:   */   {
/* 23:39 */     return this.myText;
/* 24:   */   }
/* 25:   */   
/* 26:   */   public Date getTimestamp()
/* 27:   */   {
/* 28:43 */     return this.timestamp;
/* 29:   */   }
/* 30:   */   
/* 31:   */   public int getCategory()
/* 32:   */   {
/* 33:47 */     return this.category;
/* 34:   */   }
/* 35:   */   
/* 36:   */   private Date setTimestamp()
/* 37:   */   {
/* 38:51 */     return new Date(System.currentTimeMillis());
/* 39:   */   }
/* 40:   */   
/* 41:   */   private static enum Category
/* 42:   */   {
/* 43:55 */     Beleidigung(1),  Location(2);
/* 44:   */     
/* 45:   */     private final int CategoryCode;
/* 46:   */     
/* 47:   */     private Category(int CategoryCode)
/* 48:   */     {
/* 49:61 */       this.CategoryCode = CategoryCode;
/* 50:   */     }
/* 51:   */   }
/* 52:   */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.OzText
 * JD-Core Version:    0.7.0.1
 */