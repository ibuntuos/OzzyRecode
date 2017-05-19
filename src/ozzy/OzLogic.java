/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.Date;
/*   6:    */ 
/*   7:    */ public class OzLogic
/*   8:    */ {
/*   9:    */   public String askMe(OzMemory M, OzPsych P, ArrayList<OzLocation> locations)
/*  10:    */   {
/*  11: 39 */     String additionalText = "";
/*  12:    */     
/*  13:    */ 
/*  14:    */ 
/*  15:    */ 
/*  16:    */ 
/*  17:    */ 
/*  18:    */ 
/*  19:    */ 
/*  20:    */ 
/*  21:    */ 
/*  22:    */ 
/*  23:    */ 
/*  24:    */ 
/*  25:    */ 
/*  26:    */ 
/*  27: 55 */     OzText ActualRecommendation = M.getRecommendLocationOfToday();
/*  28:    */     String myRecommendation;
/*  29: 57 */     if (ActualRecommendation == null)
/*  30:    */     {
/*  31: 59 */       int rnd = myRandom(0, OzLocation.getLocationCounter());
/*  32: 60 */        myRecommendation = "Geht heute " + ((OzLocation)locations.get(rnd)).getOzzysaid() + " !";
/*  33:    */       
/*  34: 62 */       M.addMemeRecommendLocations(new OzText(((OzLocation)locations.get(rnd)).getName(), 1));
/*  35:    */     }
/*  36:    */     else
/*  37:    */     {
/*  38: 68 */       double weight = 0.1D;
/*  39: 69 */       boolean newRecom = false;
/*  40:    */       
/*  41: 71 */       int countAskMe = P.countAskMe();
/*  42: 72 */       int angry = P.getAngryValue();
/*  43: 76 */       if (angry < 30)
/*  44:    */       {
/*  45: 77 */         if (myRandom() < 0.4D)
/*  46:    */         {
/*  47: 78 */           ArrayList<OzText> SaySomething = M.getRevisions();
/*  48: 79 */           OzText SayThis = (OzText)SaySomething.get(myRandom(0, SaySomething.size()));
/*  49: 80 */           additionalText = SayThis.getMyText();
/*  50: 81 */           newRecom = true;
/*  51:    */         }
/*  52:    */       }
/*  53: 83 */       else if (angry < 50)
/*  54:    */       {
/*  55: 84 */         if (myRandom() < 0.2D)
/*  56:    */         {
/*  57: 85 */           ArrayList<OzText> SaySomething = M.getRevisions();
/*  58: 86 */           OzText SayThis = (OzText)SaySomething.get(myRandom(0, SaySomething.size()));
/*  59: 87 */           additionalText = SayThis.getMyText();
/*  60: 88 */           newRecom = true;
/*  61:    */         }
/*  62:    */       }
/*  63: 91 */       else if (angry < 70)
/*  64:    */       {
/*  65: 92 */         if (myRandom() < 0.3D)
/*  66:    */         {
/*  67: 93 */           ArrayList<OzText> SaySomething = M.getAbuses();
/*  68: 94 */           OzText SayThis = (OzText)SaySomething.get(myRandom(0, SaySomething.size()));
/*  69: 95 */           additionalText = SayThis.getMyText();
/*  70: 96 */           newRecom = false;
/*  71:    */         }
/*  72:    */       }
/*  73: 99 */       else if (myRandom() < 0.6D)
/*  74:    */       {
/*  75:100 */         ArrayList<OzText> SaySomething = M.getAbuses();
/*  76:101 */         OzText SayThis = (OzText)SaySomething.get(myRandom(0, SaySomething.size()));
/*  77:102 */         additionalText = SayThis.getMyText();
/*  78:103 */         newRecom = false;
/*  79:    */       }
/*  80:107 */       P.addAngry(weight);
/*  81:108 */       angry = P.getAngryValue();
/*  82:109 */       System.out.println("SYS-Logic: Schon " + countAskMe + "x gefragt. Angry-Wert: " + angry);
/*  83:    */       
/*  84:112 */       if (!newRecom)
/*  85:    */       {
/*  86:113 */         myRecommendation = "Meine Empfehlung heute war " + ActualRecommendation.getMyText();
/*  87:    */       }
/*  88:    */       else
/*  89:    */       {
/*  90:    */         int rnd;
/*  91:    */         String tmpString;
/*  92:    */         do
/*  93:    */         {
/*  94:120 */           rnd = myRandom(0, OzLocation.getLocationCounter());
/*  95:121 */           tmpString = ((OzLocation)locations.get(rnd)).getName();
/*  96:122 */         } while (tmpString.equals(ActualRecommendation.getMyText()));
/*  97:123 */         myRecommendation = "Dann empfehle ich euch, geht " + ((OzLocation)locations.get(rnd)).getOzzysaid() + " !";
/*  98:    */         
/*  99:    */ 
/* 100:126 */         M.addMemeRecommendLocations(new OzText(((OzLocation)locations.get(rnd)).getName(), 1));
/* 101:    */       }
/* 102:    */     }
/* 103:129 */     if (!"".equals(additionalText)) {
/* 104:129 */       myRecommendation = additionalText + "\n" + myRecommendation;
/* 105:    */     }
/* 106:130 */     System.out.println(additionalText);
/* 107:131 */     return myRecommendation;
/* 108:    */   }
/* 109:    */   
/* 110:    */   private OzLocation newRecommendation(int days, OzMemory M)
/* 111:    */   {
/* 112:143 */     Date today = new Date();
/* 113:    */     String OldRecom;
/* 114:149 */     switch (days)
/* 115:    */     {
/* 116:    */     case 0: 
/* 117:    */     case 1: 
/* 118:153 */       OldRecom = M.getRecommendLocationOfToday().getMyText();
/* 119:154 */       break;
/* 120:    */     case 2: 
/* 121:    */       break;
/* 122:    */     case 3: 
/* 123:    */       break;
/* 124:    */     }
/* 125:162 */     return null;
/* 126:    */   }
/* 127:    */   
/* 128:    */   public static int myRandom(int low, int high)
/* 129:    */   {
/* 130:168 */     return (int)(Math.random() * (high - low) + low);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public static double myRandom()
/* 134:    */   {
/* 135:172 */     return Math.random();
/* 136:    */   }
/* 137:    */ }



/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar

 * Qualified Name:     ozzy.OzLogic

 * JD-Core Version:    0.7.0.1

 */