/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ 
/*   6:    */ public class OzPsych
/*   7:    */ {
/*   8:    */   private int angry;
/*   9:    */   private int happy;
/*  10:    */   private ArrayList<String> shortmemory;
/*  11:    */   private int askMeCounter;
/*  12:    */   
/*  13:    */   public OzPsych()
/*  14:    */   {
/*  15: 28 */     this.shortmemory = new ArrayList();
/*  16: 29 */     this.askMeCounter = 0;
/*  17:    */     
/*  18: 31 */     this.angry = OzLogic.myRandom(0, 100);
/*  19: 32 */     this.happy = (100 - this.angry);
/*  20: 36 */     if (this.angry > 50) {
/*  21: 37 */       System.out.println("SYS-Psyche: Ozzy ist heute verärgert -> Werte: Angry(" + this.angry + "), Happy(" + this.happy + ")");
/*  22: 39 */     } else if (this.angry < 50) {
/*  23: 40 */       System.out.println("SYS-Psyche: Ozzy ist heute glücklich -> Werte: Angry(" + this.angry + "), Happy(" + this.happy + ")");
/*  24:    */     } else {
/*  25: 43 */       System.out.println("SYS-Psyche: Ozzy ist heute neutral -> Werte: Angry(" + this.angry + "), Happy(" + this.happy + ")");
/*  26:    */     }
/*  27: 47 */     System.out.println("SYS-Psyche: Ozzy's Kurzzeitgedächtnis:" + this.shortmemory);
/*  28:    */   }
/*  29:    */   
/*  30:    */   public int getAngryValue()
/*  31:    */   {
/*  32: 53 */     return this.angry;
/*  33:    */   }
/*  34:    */   
/*  35:    */   public int getHappyValue()
/*  36:    */   {
/*  37: 57 */     return this.happy;
/*  38:    */   }
/*  39:    */   
/*  40:    */   public void addAngry(double weight)
/*  41:    */   {
/*  42: 62 */     double a = this.angry;
/*  43: 65 */     if (weight < 0.1D) {
/*  44: 65 */       weight = 0.1D;
/*  45:    */     }
/*  46: 66 */     if (weight > 0.9D) {
/*  47: 66 */       weight = 0.9D;
/*  48:    */     }
/*  49: 69 */     a += a / 2.0D * weight;
/*  50: 72 */     if (a > 100.0D) {
/*  51: 72 */       a = 100.0D;
/*  52:    */     }
/*  53: 75 */     this.angry = ((int)Math.ceil(a));
/*  54:    */     
/*  55:    */ 
/*  56: 78 */     updateHappy();
/*  57:    */     
/*  58:    */ 
/*  59: 81 */     System.out.println("SYS-Psyche: Du hast Ozzy verärgert");
/*  60:    */   }
/*  61:    */   
/*  62:    */   public void addHappy(double weight)
/*  63:    */   {
/*  64: 86 */     double a = this.happy;
/*  65: 89 */     if (weight < 0.1D) {
/*  66: 89 */       weight = 0.1D;
/*  67:    */     }
/*  68: 90 */     if (weight > 0.9D) {
/*  69: 90 */       weight = 0.9D;
/*  70:    */     }
/*  71: 93 */     a += a / 2.0D * weight;
/*  72: 96 */     if (a > 100.0D) {
/*  73: 96 */       a = 100.0D;
/*  74:    */     }
/*  75: 99 */     this.happy = ((int)Math.ceil(a));
/*  76:    */   }
/*  77:    */   
/*  78:    */   private void updateHappy()
/*  79:    */   {
/*  80:104 */     this.happy = (100 - this.angry);
/*  81:    */   }
/*  82:    */   
/*  83:    */   public int countAskMe()
/*  84:    */   {
/*  85:110 */     this.askMeCounter += 1;
/*  86:111 */     return this.askMeCounter;
/*  87:    */   }
/*  88:    */   
/*  89:    */   public void addMeme(String meme)
/*  90:    */   {
/*  91:115 */     this.shortmemory.add(meme);
/*  92:    */   }
/*  93:    */   
/*  94:    */   public ArrayList getMemory()
/*  95:    */   {
/*  96:119 */     return this.shortmemory;
/*  97:    */   }
/*  98:    */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.OzPsych
 * JD-Core Version:    0.7.0.1
 */