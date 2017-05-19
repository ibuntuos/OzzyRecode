/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ import java.util.List;
/*   6:    */ 
/*   7:    */ public class OzTextBuilder
/*   8:    */ {
/*   9:    */   public String Sentence;
/*  10:    */   private ArrayList<String> keywords;
/*  11:    */   private final String[] commands;
/*  12:    */   private ArrayList<OzText> Beleidigungen;
/*  13:    */   
/*  14:    */   public OzTextBuilder()
/*  15:    */   {
/*  16: 27 */     this.commands = new String[20];
/*  17: 28 */     this.commands[0] = "<help>";
/*  18: 29 */     this.commands[1] = "<OzBrain.newEntryPoint>";
/*  19: 30 */     this.commands[2] = "<OzBrain.newInformation>";
/*  20: 31 */     this.commands[3] = "<OzBrain.newConnection>";
/*  21: 32 */     this.commands[4] = "<OzBrain.setActiveAssociation>";
/*  22: 33 */     this.commands[5] = "<OzBrain.setNewRandomActive>";
/*  23: 34 */     this.commands[6] = "<OzBrain.setNewrandomActiveAssoziation>";
/*  24: 35 */     this.commands[7] = "<OzBrain.enhanceActiveAssociation>";
/*  25: 36 */     this.commands[8] = "<OzBrain.decreaseActiveAssociation>";
/*  26: 37 */     this.commands[9] = "<OzBrain.getActiveInformation>";
/*  27: 38 */     this.commands[10] = "<OzBrain.getActiveInformationAssociation>";
/*  28: 39 */     this.commands[11] = "<OzBrain.getAllPossibleAssoziations>";
/*  29: 40 */     this.commands[12] = "<OzBrain.getActiveConnection>";
/*  30: 41 */     this.commands[13] = "<OzBrain.getEntryPoints>";
/*  31: 42 */     this.commands[14] = "<OzBrain.search>";
/*  32: 43 */     this.commands[15] = "<OzBrain.pathSearch>";
/*  33: 44 */     this.commands[16] = "<OzBrain.linearSearch>";
/*  34: 45 */     this.commands[17] = "<OzBrain.getLevelList>";
/*  35: 46 */     this.commands[18] = "<OzBrain.deleteConnection>";
/*  36: 47 */     this.commands[19] = "<OzBrain.deleteInformation>";
/*  37:    */   }
/*  38:    */   
/*  39:    */   public void initBeleidigungen()
/*  40:    */   {
/*  41: 52 */     this.Beleidigungen.add(new OzText("Hör auf zu nerven!", 2));
/*  42: 53 */     this.Beleidigungen.add(new OzText("Klick wo anders hin", 2));
/*  43: 54 */     this.Beleidigungen.add(new OzText("Hast du nichts zu arbeiten?", 2));
/*  44: 55 */     this.Beleidigungen.add(new OzText("Himmelarsch! Dann iss ein Brot!", 2));
/*  45: 56 */     this.Beleidigungen.add(new OzText("Dann frag mich halt nicht!", 2));
/*  46: 57 */     this.Beleidigungen.add(new OzText("Nochmal? Zur Strafe 10 saure Zungen auf einmal!", 2));
/*  47: 58 */     this.Beleidigungen.add(new OzText("Ich schick euch in die Kantine! Und ja, das ist eine Drohung!", 2));
/*  48:    */   }
/*  49:    */   
/*  50:    */   public String answerMe(String saySomething)
/*  51:    */   {
/*  52: 62 */     return answerMe(saySomething, null);
/*  53:    */   }
/*  54:    */   
/*  55:    */   public String answerMe(String saySomething, OzBrain brain)
/*  56:    */   {
/*  57: 66 */     String answer = "";
/*  58: 67 */     System.out.println("SYS-TextBuilder: Input '" + saySomething + "'");
/*  59: 68 */     char c = saySomething.charAt(saySomething.length() - 1);
/*  60: 69 */     if (c == '?')
/*  61:    */     {
/*  62: 70 */       System.out.println("SYS-TextBuilder: Frage wurde erkannt");
/*  63: 71 */       if (question(saySomething) != "") {
/*  64: 72 */         answer = question(saySomething);
/*  65:    */       } else {
/*  66: 74 */         answer = "Frag mich nicht so einen Unsinn!";
/*  67:    */       }
/*  68:    */     }
/*  69: 76 */     else if (c == '!')
/*  70:    */     {
/*  71: 77 */       System.out.println("SYS-TextBuilder: Kommando wurde erkannt");
/*  72: 78 */       answer = "Von dir lass ich mir gar nix sagen!";
/*  73:    */     }
/*  74:    */     else
/*  75:    */     {
/*  76: 80 */       answer = "Was soll das denn heißen?";
/*  77:    */     }
/*  78: 82 */     System.out.println("SYS-TextBuilder: Antwort '" + answer + "'");
/*  79: 83 */     return answer;
/*  80:    */   }
/*  81:    */   
/*  82:    */   public String parser(String command, OzBrain brain)
/*  83:    */   {
/*  84: 87 */     String answer = "";
/*  85: 88 */     System.out.println("SYS-TextBuilder: Input '" + command + "'");
/*  86: 89 */     char c = command.charAt(command.length() - 1);
/*  87: 90 */     if (c == '!')
/*  88:    */     {
/*  89: 91 */       System.out.println("SYS-TextBuilder: Kommando wurde erkannt");
/*  90:    */       try
/*  91:    */       {
/*  92: 93 */         answer = command(command, brain);
/*  93:    */       }
/*  94:    */       catch (OzException ex)
/*  95:    */       {
/*  96: 95 */         System.out.println(ex);
/*  97: 96 */         return ex.toString();
/*  98:    */       }
/*  99:    */     }
/* 100:    */     else
/* 101:    */     {
/* 102: 99 */       answer = "Dieser Befehl ist nicht bekannt. Für Hilfe '<help>!' eingeben";
/* 103:    */     }
/* 104:101 */     System.out.println("SYS-TextBuilder: Antwort '" + answer + "'");
/* 105:102 */     return answer;
/* 106:    */   }
/* 107:    */   
/* 108:    */   private String question(String question)
/* 109:    */   {
/* 110:106 */     question = question.toLowerCase();
/* 111:108 */     if ((question.contains("mittag")) && (question.contains("essen"))) {
/* 112:109 */       return "Woher soll ich wissen, wo du heute essen willst?";
/* 113:    */     }
/* 114:111 */     if ((question.startsWith("wer")) && 
/* 115:112 */       (question.contains("ist")) && (question.contains("oli"))) {
/* 116:113 */       return "Vermutlich der mürrische, seelisch sehr alte Mann, der rechts neben Pascal sitzt.";
/* 117:    */     }
/* 118:118 */     return "";
/* 119:    */   }
/* 120:    */   
/* 121:    */   private String command(String command, OzBrain Brain)
/* 122:    */     throws OzException
/* 123:    */   {
/* 124:123 */     command = command.trim();
/* 125:124 */     command = command.substring(0, command.length() - 1);
/* 126:    */     
/* 127:    */ 
/* 128:127 */     String answer = "";
/* 129:130 */     if (command.startsWith(this.commands[0])) {
/* 130:131 */       return help();
/* 131:    */     }
/* 132:133 */     if (Brain != null)
/* 133:    */     {
/* 134:135 */       if (command.startsWith(this.commands[1]))
/* 135:    */       {
/* 136:136 */         String content = command.substring(this.commands[1].length(), command.length());
/* 137:137 */         String[] contentAr = content.split(",");
/* 138:138 */         Brain.newEntryPoint(contentAr[0]);
/* 139:139 */         return "EntryPoint " + contentAr[0] + " wurde angelegt";
/* 140:    */       }
/* 141:142 */       if (command.startsWith(this.commands[2]))
/* 142:    */       {
/* 143:143 */         String content = command.substring(this.commands[2].length(), command.length());
/* 144:144 */         String[] contentAr = content.split(",");
/* 145:145 */         Brain.newInformation(contentAr[0], contentAr[1]);
/* 146:146 */         return "Information " + contentAr[0] + " wurde angelegt und mit " + contentAr[1] + " verknüpft";
/* 147:    */       }
/* 148:150 */       if (command.startsWith(this.commands[3]))
/* 149:    */       {
/* 150:151 */         String content = command.substring(this.commands[3].length(), command.length());
/* 151:152 */         String[] contentAr = content.split(",");
/* 152:153 */         Brain.newConnection(contentAr[0], contentAr[1]);
/* 153:154 */         return "Information " + contentAr[0] + " wurde mit Information " + contentAr[1] + " verknüpft";
/* 154:    */       }
/* 155:158 */       if (command.startsWith(this.commands[4]))
/* 156:    */       {
/* 157:159 */         String content = command.substring(this.commands[4].length(), command.length());
/* 158:160 */         String[] contentAr = content.split(",");
/* 159:161 */         Brain.setActiveAssociation(contentAr[0], contentAr[1]);
/* 160:162 */         return "Information " + contentAr[0] + " und Verknüpfung " + contentAr[1] + " wurden in den aktiven Speicher gesetzt";
/* 161:    */       }
/* 162:166 */       if (command.startsWith(this.commands[5]))
/* 163:    */       {
/* 164:167 */         Brain.setNewRandomActiveAssoziation();
/* 165:168 */         return "Eine zufälliges Informationspaar wurde in den aktiven Speicher gesetzt";
/* 166:    */       }
/* 167:171 */       if (command.startsWith(this.commands[6]))
/* 168:    */       {
/* 169:172 */         String content = command.substring(this.commands[6].length(), command.length());
/* 170:173 */         String[] contentAr = content.split(",");
/* 171:174 */         Brain.setNewrandomActiveAssoziation(contentAr[0]);
/* 172:175 */         return "Information " + contentAr[0] + " wurde mit einer zufälligen Verbindung" + " in den aktiven Speicher gesetzt";
/* 173:    */       }
/* 174:179 */       if (command.startsWith(this.commands[7]))
/* 175:    */       {
/* 176:180 */         Brain.enhanceActiveAssociation();
/* 177:181 */         return "Die Verbindung des aktiven Informationspaars wurde verstärkt";
/* 178:    */       }
/* 179:184 */       if (command.startsWith(this.commands[8]))
/* 180:    */       {
/* 181:185 */         Brain.decreaseActiveAssociation();
/* 182:186 */         return "Die Verbindung des aktiven Informationspaars wurde geschwächt";
/* 183:    */       }
/* 184:189 */       if (command.startsWith(this.commands[9])) {
/* 185:190 */         return Brain.getActiveInformation();
/* 186:    */       }
/* 187:193 */       if (command.startsWith(this.commands[10])) {
/* 188:194 */         return Brain.getActiveInformationAssociation();
/* 189:    */       }
/* 190:197 */       if (command.startsWith(this.commands[11]))
/* 191:    */       {
/* 192:198 */         String content = command.substring(this.commands[11].length(), command.length());
/* 193:199 */         String[] contentAr = content.split(",");
/* 194:200 */         String[] list = Brain.getAllPossibleAssoziations(contentAr[0]);
/* 195:201 */         //for (??? = 0; ??? < list.length; ???++) 
/* 196:202 */          // answer = answer + list[???] + ","; 
/* 197:    */         }
/* 198:204 */         return answer;
/* 199:    */       }
/* 200:207 */       if (command.startsWith(this.commands[12])) {
/* 201:208 */         return Brain.getActiveConnection();
/* 202:    */       }
/* 203:211 */       if (command.startsWith(this.commands[13]))
/* 204:    */       {
/* 205:212 */         List list = Brain.getEntryPoints();
/* 206:213 */         return list.toString();
/* 207:    */       }
/* 208:216 */       if (command.startsWith(this.commands[14]))
/* 209:    */       {
/* 210:217 */         String content = command.substring(this.commands[14].length(), command.length());
/* 211:218 */         String[] contentAr = content.split(",");
/* 212:    */         boolean overEntryPoints;
/* 213:220 */         switch (contentAr[2])
/* 214:    */         {
/* 215:    */         case "true": 
/* 216:222 */           overEntryPoints = true;
/* 217:223 */           break;
/* 218:    */         case "false": 
/* 219:225 */           overEntryPoints = false;
/* 220:226 */           break;
/* 221:    */         default: 
/* 222:228 */           return "OzBrain.search: letztes Argument muss 'true' oder 'false' sein";
/* 223:    */         }
/* 224:230 */         String[] list = Brain.search(contentAr[0], contentAr[1], overEntryPoints);
/* 225:231 */         StringBuilder Builder = new StringBuilder();
/* 226:232 */         for (String s : list) {
/* 227:233 */           Builder.append(s).append(",");
/* 228:    */         }
/* 229:235 */         answer = contentAr[0] + " in " + list.length + " Schritten gefunden:\n" + Builder.toString();
/* 230:236 */         return answer;
/* 231:    */       }
/* 232:239 */       if (command.startsWith(this.commands[15]))
/* 233:    */       {
/* 234:240 */         String content = command.substring(this.commands[15].length(), command.length());
/* 235:241 */         String[] contentAr = content.split(",");
/* 236:242 */         List<String> list = Brain.pathSearch(contentAr[0], contentAr[1]);
/* 237:243 */         return list.toString();
/* 238:    */       }
/* 239:246 */       if (command.startsWith(this.commands[16]))
/* 240:    */       {
/* 241:247 */         String content = command.substring(this.commands[15].length(), command.length());
/* 242:248 */         String[] contentAr = content.split(",");
/* 243:249 */         Brain.newEntryPoint(contentAr[0]);
/* 244:250 */         return "Gefundene Information " + contentAr[0] + " wurde im aktiven Speicher abgelegt";
/* 245:    */       }
/* 246:253 */       if (command.startsWith(this.commands[17]))
/* 247:    */       {
/* 248:254 */         String content = command.substring(this.commands[16].length(), command.length());
/* 249:255 */         String[] contentAr = content.split(",");
/* 250:256 */         int level = Integer.parseInt(contentAr[0]);
/* 251:257 */         List list = Brain.getLevelList(level);
/* 252:258 */         return list.toString();
/* 253:    */       }
/* 254:261 */       if (command.startsWith(this.commands[18]))
/* 255:    */       {
/* 256:262 */         String content = command.substring(this.commands[18].length(), command.length());
/* 257:263 */         String[] contentAr = content.split(",");
/* 258:264 */         Brain.deleteConnection(contentAr[0], contentAr[1]);
/* 259:265 */         return "Verbindung zwischen " + contentAr[0] + " und " + contentAr[1] + " wurde gelöscht";
/* 260:    */       }
/* 261:269 */       if (command.startsWith(this.commands[19]))
/* 262:    */       {
/* 263:270 */         String content = command.substring(this.commands[19].length(), command.length());
/* 264:271 */         String[] contentAr = content.split(",");
/* 265:272 */         Brain.deleteInformation(contentAr[0]);
/* 266:273 */         return "Information " + contentAr[0] + " wurde gelöscht";
/* 267:    */       }
/* 268:    */     
/* 269:    */     else
/* 270:    */     {
/* 271:277 */       return "kein OzBrain initialisiert!";
/* 272:    */     }

/* 274:    */   }
/* 275:    */   
/* 276:    */   private String help()
/* 277:    */   {
/* 278:285 */     String help = "Mögliche Kommandos:\n\n<help>\n*Ruft diese Hilfe auf\n<OzBrain.newEntryPoint>Information!\n*legt einen neuen Einstiegspunkt an\n<OzBrain.newInformation>Information,Information!\n*legt eine neue Information an. Die zweite Information muss schon existieren.\n<OzBrain.newConnection>Information,Information!\n*legt eine neue Verbindung zwischen zwei existiernden Informationen an.\n<OzBrain.setActiveAssociation>Information,Information!\n*Setzt ein bestehendes Informationspaar in den aktiven Speicher\n<OzBrain.setNewRandomActive>!\n*Setzt ein zufälliges Informationspaar in den aktiven Speicher\n<OzBrain.setNewrandomActiveAssoziation>Information!\n*Setzt eine Information mit zufälliger Verbindung in den aktiven Speicher\n<OzBrain.enhanceActiveAssociation>!\n*Verstärkt die Verbindung des Informationspaars im aktiven Speicher\n<OzBrain.decreaseActiveAssociation>!\n*Schwächt die Verbindung des Informationspaars im aktiven Speicher\n<OzBrain.getActiveInformation>!\n*Gibt die Information aus dem aktiven Speicher zurück\n<OzBrain.getActiveInformationAssociation>!\n*Gibt die verbundene Information aus dem aktiven Speicher zurück\n<OzBrain.getAllPossibleAssoziations>!\n*Gibt alle möglichen verbundenen Informationen der Information aus dem aktiven Speicher zurück\n<OzBrain.getActiveConnection>!\n*Gibt die Information, ihre Verknüpfung und das Gewicht der Verbindung aus dem aktiven Speicher zurück\n<OzBrain.getEntryPoints>!\n*Gibt alle Informationen die EntryPoints sind zurück\n<OzBrain.search>Information,Information,boolean!\n*Sucht nach der ersten Information über eine zweite Information und gibt den gefundenen Weg zurück.Über den Boolean steuert man den StartPunkt (true=EntryPoint, false=normale Information)<OzBrain.linearSearch>Information!\n*Setzt die Information in den aktiven Speicher\n<OzBrain.getLevelList>int!\n*Gibt die Informationen aus der Levelliste zurück (int=1,2 oder 3)\n<OzBrain.deleteConnection>Information,Information!\n*löscht eine Verbindung zwischen zwei Informationen.\n<OzBrain.deleteInformation>Information!\n*löscht eine Information (auch EntryPoints). Dabei werden alle Verbindungen gelöscht.\n\nLeerzeichen und das Kommandoausrufezeichen werden entfernt. Informationen werden \nautomatisch auf lowercase gesetzt. Der Aufruf wird nach dem Gleichzeichen beschrieben.";
/* 279:    */     
/* 280:    */ 
/* 281:    */ 
/* 282:    */ 
/* 283:    */ 
/* 284:    */ 
/* 285:    */ 
/* 286:    */ 
/* 287:    */ 
/* 288:    */ 
/* 289:    */ 
/* 290:    */ 
/* 291:    */ 
/* 292:    */ 
/* 293:    */ 
/* 294:    */ 
/* 295:    */ 
/* 296:    */ 
/* 297:    */ 
/* 298:    */ 
/* 299:    */ 
/* 300:    */ 
/* 301:    */ 
/* 302:    */ 
/* 303:    */ 
/* 304:    */ 
/* 305:    */ 
/* 306:    */ 
/* 307:    */ 
/* 308:    */ 
/* 309:    */ 
/* 310:    */ 
/* 311:    */ 
/* 312:    */ 
/* 313:    */ 
/* 314:    */ 
/* 315:    */ 
/* 316:    */ 
/* 317:    */ 
/* 318:    */ 
/* 319:    */ 
/* 320:    */ 
/* 321:    */ 
/* 322:329 */     return help;
/* 323:    */   }
/* 324:    */ }



/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar

 * Qualified Name:     ozzy.OzTextBuilder

 * JD-Core Version:    0.7.0.1

 */