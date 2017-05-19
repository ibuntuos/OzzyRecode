/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.FileInputStream;
/*   4:    */ import java.io.FileOutputStream;
/*   5:    */ import java.io.IOException;
/*   6:    */ import java.io.ObjectInputStream;
/*   7:    */ import java.io.ObjectOutputStream;
/*   8:    */ import java.io.PrintStream;
/*   9:    */ import java.util.ArrayList;
/*  10:    */ import java.util.Date;
/*  11:    */ import java.util.GregorianCalendar;
/*  12:    */ 
/*  13:    */ public class OzMemory
/*  14:    */ {
/*  15:    */   private ArrayList<OzLocation> locations;
/*  16:    */   private ArrayList<OzBrain> brains;
/*  17:    */   private ArrayList<OzText> recommendLocations;
/*  18:    */   private ArrayList<OzText> abuses;
/*  19:    */   private ArrayList<OzText> revision;
/*  20:    */   private ArrayList<OzText> talk;
/*  21: 29 */   private final String brainfile = "OzMemBrain.ser";
/*  22: 30 */   private final String locationfile = "OzMemLoc.ser";
/*  23: 31 */   private final String recommendfile = "OzMemRLoc.ser";
/*  24: 32 */   private final String talkfile = "OzMemTalk.ser";
/*  25: 33 */   private final String memorypath = System.getProperty("user.dir") + System.getProperty("file.separator");
/*  26:    */   
/*  27:    */   public OzMemory()
/*  28:    */   {
/*  29: 39 */     this.locations = new ArrayList();
/*  30: 40 */     this.brains = new ArrayList();
/*  31: 41 */     this.recommendLocations = new ArrayList();
/*  32: 42 */     this.abuses = new ArrayList();
/*  33: 43 */     this.revision = new ArrayList();
/*  34: 44 */     this.talk = new ArrayList();
/*  35:    */     
/*  36: 46 */     readMemory("brain");
/*  37: 47 */     readMemory("locations");
/*  38: 48 */     readMemory("recommendLocations");
/*  39: 49 */     readMemory("talk");
/*  40: 50 */     System.out.println(this.memorypath);
/*  41:    */   }
/*  42:    */   
/*  43:    */   public ArrayList<OzLocation> getLocations()
/*  44:    */   {
/*  45: 58 */     return this.locations;
/*  46:    */   }
/*  47:    */   
/*  48:    */   public ArrayList<OzText> getAbuses()
/*  49:    */   {
/*  50: 67 */     return this.abuses;
/*  51:    */   }
/*  52:    */   
/*  53:    */   public ArrayList<OzText> getRevisions()
/*  54:    */   {
/*  55: 76 */     return this.revision;
/*  56:    */   }
/*  57:    */   
/*  58:    */   public ArrayList<OzText> getTalk()
/*  59:    */   {
/*  60: 85 */     return this.talk;
/*  61:    */   }
/*  62:    */   
/*  63:    */   public ArrayList<OzBrain> getBrains()
/*  64:    */   {
/*  65: 93 */     return this.brains;
/*  66:    */   }
/*  67:    */   
/*  68:    */   public void addLocation(OzLocation Location)
/*  69:    */   {
/*  70:101 */     this.locations.add(Location);
/*  71:102 */     writeMemory("locations");
/*  72:    */   }
/*  73:    */   
/*  74:    */   public void addAbuse(OzText Text)
/*  75:    */   {
/*  76:112 */     this.abuses.add(Text);
/*  77:    */   }
/*  78:    */   
/*  79:    */   public void addRevisions(OzText Text)
/*  80:    */   {
/*  81:121 */     this.revision.add(Text);
/*  82:    */   }
/*  83:    */   
/*  84:    */   public void addTalk(OzText Text)
/*  85:    */   {
/*  86:130 */     this.talk.add(Text);
/*  87:131 */     writeMemory("talk");
/*  88:    */   }
/*  89:    */   
/*  90:    */   public void addBrain(OzBrain Brain)
/*  91:    */   {
/*  92:139 */     this.brains.add(Brain);
/*  93:    */   }
/*  94:    */   
/*  95:    */   public void setBrains(ArrayList<OzBrain> brains)
/*  96:    */   {
/*  97:147 */     this.brains = brains;
/*  98:    */   }
/*  99:    */   
/* 100:    */   public void addMemeRecommendLocations(OzText Text)
/* 101:    */   {
/* 102:156 */     this.recommendLocations.add(Text);
/* 103:157 */     writeMemory("recommendLocations");
/* 104:    */   }
/* 105:    */   
/* 106:    */   public OzText getRecommendLocationOfToday()
/* 107:    */   {
/* 108:165 */     GregorianCalendar cal = new GregorianCalendar();
/* 109:166 */     Date today = new Date();
/* 110:167 */     cal.setTime(today);
/* 111:168 */     cal.set(11, 0);
/* 112:169 */     cal.set(12, 0);
/* 113:170 */     cal.set(13, 0);
/* 114:171 */     cal.set(14, 0);
/* 115:    */     
/* 116:173 */     Date todayStart = cal.getTime();
/* 117:    */     
/* 118:175 */     System.out.println("SYS-Memory: Heutiges Datum " + todayStart);
/* 119:176 */     for (int i = this.recommendLocations.size() - 1; i > 0; i--) {
/* 120:177 */       if (((OzText)this.recommendLocations.get(i)).getTimestamp().after(todayStart)) {
/* 121:178 */         return (OzText)this.recommendLocations.get(i);
/* 122:    */       }
/* 123:    */     }
/* 124:181 */     return null;
/* 125:    */   }
/* 126:    */   
/* 127:    */   public OzText getLastRecommendLocationOf(Date D)
/* 128:    */   {
/* 129:185 */     GregorianCalendar cal = new GregorianCalendar();
/* 130:186 */     cal.setTime(D);
/* 131:187 */     cal.set(11, 0);
/* 132:188 */     cal.set(12, 0);
/* 133:189 */     cal.set(13, 0);
/* 134:190 */     cal.set(14, 0);
/* 135:    */     
/* 136:192 */     Date todayStart = cal.getTime();
/* 137:    */     
/* 138:194 */     System.out.println("SYS-Memory: Empfehlung von " + todayStart);
/* 139:196 */     for (int i = this.recommendLocations.size() - 1; i == 0; i--) {
/* 140:197 */       if (((OzText)this.recommendLocations.get(i)).getTimestamp().after(todayStart)) {
/* 141:198 */         return (OzText)this.recommendLocations.get(i);
/* 142:    */       }
/* 143:    */     }
/* 144:201 */     return null;
/* 145:    */   }
/* 146:    */   
/* 147:    */   public ArrayList<OzText> getRecommendLocations()
/* 148:    */   {
/* 149:210 */     return this.recommendLocations;
/* 150:    */   }
/* 151:    */   
/* 152:    */   public void readBrains()
/* 153:    */   {
/* 154:217 */     readMemory("brain");
/* 155:    */   }
/* 156:    */   
/* 157:    */   public void writeBrains()
/* 158:    */   {
/* 159:224 */     writeMemory("brain");
/* 160:    */   }
/* 161:    */   
/* 162:    */   private void writeMemory(String memName)
/* 163:    */   {
/* 164:228 */     ObjectOutputStream oos = null;
/* 165:229 */     FileOutputStream fos = null;
/* 166:    */     try
/* 167:    */     {
/* 168:231 */       switch (memName)
/* 169:    */       {
/* 170:    */       case "locations": 
/* 171:233 */         fos = new FileOutputStream(this.memorypath + "OzMemLoc.ser");
/* 172:234 */         oos = new ObjectOutputStream(fos);
/* 173:235 */         oos.writeObject(this.locations);
/* 174:236 */         break;
/* 175:    */       case "recommendLocations": 
/* 176:238 */         fos = new FileOutputStream(this.memorypath + "OzMemRLoc.ser");
/* 177:239 */         oos = new ObjectOutputStream(fos);
/* 178:240 */         oos.writeObject(this.recommendLocations);
/* 179:241 */         break;
/* 180:    */       case "talk": 
/* 181:243 */         fos = new FileOutputStream(this.memorypath + "OzMemTalk.ser");
/* 182:244 */         oos = new ObjectOutputStream(fos);
/* 183:245 */         oos.writeObject(this.talk);
/* 184:246 */         break;
/* 185:    */       case "brain": 
/* 186:248 */         fos = new FileOutputStream(this.memorypath + "OzMemBrain.ser");
/* 187:249 */         oos = new ObjectOutputStream(fos);
/* 188:250 */         oos.writeObject(this.brains);
/* 189:251 */         break;
/* 190:    */       default: 
/* 191:253 */         System.out.println("SYS-Gedächtnis: Da ist nichts zum abspeichern");
/* 192:    */       }
/* 193:    */       return;
/* 194:    */     }
/* 195:    */     catch (IOException e)
/* 196:    */     {
/* 197:259 */       System.out.println("Fehler beim Speichern: " + e);
/* 198:    */     }
/* 199:    */     finally
/* 200:    */     {
/* 201:262 */       if (oos != null) {
/* 202:    */         try
/* 203:    */         {
/* 204:262 */           oos.close();
/* 205:    */         }
/* 206:    */         catch (IOException e)
/* 207:    */         {
/* 208:262 */           System.out.println("Fehler beim Speichern: " + e);
/* 209:    */         }
/* 210:    */       }
/* 211:263 */       if (fos != null) {
/* 212:    */         try
/* 213:    */         {
/* 214:263 */           fos.close();
/* 215:    */         }
/* 216:    */         catch (IOException e)
/* 217:    */         {
/* 218:263 */           System.out.println("Fehler beim Speichern: " + e);
/* 219:    */         }
/* 220:    */       }
/* 221:    */     }
/* 222:    */   }
/* 223:    */   
/* 224:    */   private void readMemory(String memName)
/* 225:    */   {
/* 226:269 */     ObjectInputStream ois = null;
/* 227:270 */     FileInputStream fis = null;
/* 228:    */     try
/* 229:    */     {
/* 230:272 */       switch (memName)
/* 231:    */       {
/* 232:    */       case "locations": 
/* 233:274 */         fis = new FileInputStream(this.memorypath + "OzMemLoc.ser");
/* 234:275 */         ois = new ObjectInputStream(fis);
/* 235:276 */         this.locations = ((ArrayList)ois.readObject());
/* 236:277 */         break;
/* 237:    */       case "recommendLocations": 
/* 238:279 */         fis = new FileInputStream(this.memorypath + "OzMemRLoc.ser");
/* 239:280 */         ois = new ObjectInputStream(fis);
/* 240:281 */         this.recommendLocations = ((ArrayList)ois.readObject());
/* 241:282 */         break;
/* 242:    */       case "talk": 
/* 243:284 */         fis = new FileInputStream(this.memorypath + "OzMemTalk.ser");
/* 244:285 */         ois = new ObjectInputStream(fis);
/* 245:286 */         this.talk = ((ArrayList)ois.readObject());
/* 246:287 */         break;
/* 247:    */       case "brain": 
/* 248:289 */         fis = new FileInputStream(this.memorypath + "OzMemBrain.ser");
/* 249:290 */         ois = new ObjectInputStream(fis);
/* 250:291 */         this.brains = ((ArrayList)ois.readObject());
/* 251:292 */         break;
/* 252:    */       default: 
/* 253:294 */         System.out.println("SYS-Gedächtnis: Gedächtnis ist leer");
/* 254:    */       }
/* 255:    */       return;
/* 256:    */     }
/* 257:    */     catch (IOException|ClassNotFoundException e)
/* 258:    */     {
/* 259:299 */       System.out.println("Fehler beim Lesen: " + e);
/* 260:    */     }
/* 261:    */     finally
/* 262:    */     {
/* 263:302 */       if (ois != null) {
/* 264:    */         try
/* 265:    */         {
/* 266:302 */           ois.close();
/* 267:    */         }
/* 268:    */         catch (IOException e)
/* 269:    */         {
/* 270:302 */           System.out.println("Fehler beim Lesen: " + e);
/* 271:    */         }
/* 272:    */       }
/* 273:303 */       if (fis != null) {
/* 274:    */         try
/* 275:    */         {
/* 276:303 */           fis.close();
/* 277:    */         }
/* 278:    */         catch (IOException e)
/* 279:    */         {
/* 280:303 */           System.out.println("Fehler beim Lesen: " + e);
/* 281:    */         }
/* 282:    */       }
/* 283:    */     }
/* 284:    */   }
/* 285:    */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.OzMemory
 * JD-Core Version:    0.7.0.1
 */