/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.io.Serializable;
/*   5:    */ import java.util.ArrayList;
/*   6:    */ import java.util.Collections;
/*   7:    */ import java.util.HashMap;
/*   8:    */ import java.util.HashSet;
/*   9:    */ import java.util.Iterator;
/*  10:    */ import java.util.LinkedList;
/*  11:    */ import java.util.List;
/*  12:    */ import java.util.Map;
/*  13:    */ import java.util.Random;
/*  14:    */ import java.util.Set;
/*  15:    */ 
/*  16:    */ public class OzBrain
/*  17:    */   implements Serializable
/*  18:    */ {
/*  19:    */   private static final long serialVersionUID = -123456789L;
/*  20: 49 */   private int searchMaxInformation = 20;
/*  21: 50 */   private boolean allowZeroWeight = false;
/*  22: 51 */   private double defaultConnectionWeight = 1.0D;
/*  23: 52 */   private double maxConnectionWeight = 2.0D;
/*  24: 53 */   private double minConnectionWeight = 0.1D;
/*  25: 54 */   private double changeConnectionWeight = 0.1D;
/*  26:    */   private Information ActiveInformation;
/*  27:    */   private Information ActiveInformationAssociation;
/*  28:    */   private HashSet<Connection> allConnectionSet;
/*  29:    */   private HashSet<Information> allInformationSet;
/*  30:    */   private HashSet<Information> EntryPointInformations;
/*  31:    */   private int bigInformationWeight;
/*  32:    */   private LinkedList<Information> levelOneInformations;
/*  33:    */   private LinkedList<Information> levelTwoInformations;
/*  34:    */   private LinkedList<Information> levelThreeInformations;
/*  35:    */   private Set<Information> settledNodes;
/*  36:    */   private Set<Information> unSettledNodes;
/*  37:    */   private Map<Information, Information> predecessors;
/*  38:    */   private Map<Information, Integer> distance;
/*  39:    */   
/*  40:    */   public OzBrain(String EntryPointInformation)
/*  41:    */   {
/*  42: 79 */     initalize();
/*  43: 80 */     Information EntryPoint = new Information(EntryPointInformation);
/*  44: 81 */     this.EntryPointInformations.add(EntryPoint);
/*  45: 82 */     this.allInformationSet.add(EntryPoint);
/*  46:    */   }
/*  47:    */   
/*  48:    */   public OzBrain(String[] EntryPointInformation)
/*  49:    */   {
/*  50: 91 */     initalize();
/*  51: 93 */     for (String loopString : EntryPointInformation)
/*  52:    */     {
/*  53: 94 */       Information EntryPoint = new Information(loopString);
/*  54: 95 */       this.EntryPointInformations.add(EntryPoint);
/*  55: 96 */       this.allInformationSet.add(EntryPoint);
/*  56:    */     }
/*  57:    */   }
/*  58:    */   
/*  59:    */   public OzBrain(String[] EntryPointInformation, int searchMaxInformation)
/*  60:    */   {
/*  61:108 */     this(EntryPointInformation);
/*  62:109 */     this.searchMaxInformation = searchMaxInformation;
/*  63:    */   }
/*  64:    */   
/*  65:    */   public OzBrain(String[] EntryPointInformation, int searchMaxInformation, boolean allowZeroWeight, double defaultConnectionWeight, double maxConnectionWeight, double minConnectionWeight, double changeConnectionWeight)
/*  66:    */   {
/*  67:124 */     this(EntryPointInformation);
/*  68:125 */     this.searchMaxInformation = searchMaxInformation;
/*  69:126 */     this.allowZeroWeight = allowZeroWeight;
/*  70:127 */     this.defaultConnectionWeight = defaultConnectionWeight;
/*  71:128 */     this.maxConnectionWeight = maxConnectionWeight;
/*  72:129 */     this.minConnectionWeight = minConnectionWeight;
/*  73:130 */     this.changeConnectionWeight = changeConnectionWeight;
/*  74:    */   }
/*  75:    */   
/*  76:    */   private void initalize()
/*  77:    */   {
/*  78:136 */     this.EntryPointInformations = new HashSet();
/*  79:137 */     this.allInformationSet = new HashSet();
/*  80:138 */     this.allConnectionSet = new HashSet();
/*  81:    */     
/*  82:140 */     this.bigInformationWeight = 0;
/*  83:141 */     this.levelOneInformations = new LinkedList();
/*  84:142 */     this.levelTwoInformations = new LinkedList();
/*  85:143 */     this.levelThreeInformations = new LinkedList();
/*  86:    */     
/*  87:145 */     this.ActiveInformation = null;
/*  88:146 */     this.ActiveInformationAssociation = null;
/*  89:    */   }
/*  90:    */   
/*  91:    */   public void setSearchMaxInformation(int searchMaxInformation)
/*  92:    */     throws OzException
/*  93:    */   {
/*  94:156 */     if (searchMaxInformation > 1) {
/*  95:157 */       this.searchMaxInformation = searchMaxInformation;
/*  96:    */     } else {
/*  97:159 */       throw new OzException("searchMaxInformation muss größer als 1 sein");
/*  98:    */     }
/*  99:    */   }
/* 100:    */   
/* 101:    */   public String getActiveInformation()
/* 102:    */   {
/* 103:168 */     return this.ActiveInformation.toString();
/* 104:    */   }
/* 105:    */   
/* 106:    */   public String getActiveInformationAssociation()
/* 107:    */   {
/* 108:177 */     return this.ActiveInformationAssociation.toString();
/* 109:    */   }
/* 110:    */   
/* 111:    */   public String getActiveConnection()
/* 112:    */     throws OzException
/* 113:    */   {
/* 114:187 */     if ((this.ActiveInformation != null) && (this.ActiveInformationAssociation != null))
/* 115:    */     {
/* 116:188 */       StringBuilder Builder = new StringBuilder();
/* 117:    */       
/* 118:190 */       double weight = getConnection(this.ActiveInformation, this.ActiveInformationAssociation).getWeight();
/* 119:191 */       Builder.append(this.ActiveInformation).append(",");
/* 120:192 */       Builder.append(this.ActiveInformationAssociation).append(":");
/* 121:193 */       Builder.append("Weight ").append(weight);
/* 122:194 */       return Builder.toString();
/* 123:    */     }
/* 124:196 */     throw new OzException("Keine verknüpfte Verbindung im aktiven Speicher");
/* 125:    */   }
/* 126:    */   
/* 127:    */   public List<String> getEntryPoints()
/* 128:    */   {
/* 129:205 */     List<Information> list = new ArrayList(this.EntryPointInformations);
/* 130:206 */     return getList(list);
/* 131:    */   }
/* 132:    */   
/* 133:    */   public void newEntryPoint(String newEntryPoint)
/* 134:    */     throws OzException
/* 135:    */   {
/* 136:215 */     Information NewEntryPoint = new Information(newEntryPoint);
/* 137:217 */     if (this.EntryPointInformations.contains(NewEntryPoint)) {
/* 138:218 */       throw new OzException("EntryPoint bereits vorhanden");
/* 139:    */     }
/* 140:220 */     if (!this.allInformationSet.add(NewEntryPoint)) {
/* 141:221 */       throw new OzException("Information bereits vorhanden und kann nicht zusätzlich als EntryPoint angelegt werden");
/* 142:    */     }
/* 143:224 */     this.EntryPointInformations.add(NewEntryPoint);
/* 144:    */   }
/* 145:    */   
/* 146:    */   public void newInformation(String newInformation, String connectToInformation)
/* 147:    */     throws OzException
/* 148:    */   {
/* 149:239 */     Information NewInformation = new Information(newInformation);
/* 150:240 */     if (this.allInformationSet.contains(NewInformation)) {
/* 151:241 */       throw new OzException("Information bereits vorhanden");
/* 152:    */     }
/* 153:243 */     Information ConnectToInformation = searchInAllInformations(connectToInformation);
/* 154:244 */     if (ConnectToInformation == null) {
/* 155:245 */       throw new OzException("connectToInformation nicht vorhanden");
/* 156:    */     }
/* 157:248 */     createInformation(newInformation, ConnectToInformation);
/* 158:    */   }
/* 159:    */   
/* 160:    */   private void createInformation(String Information, Information connectToInfo)
/* 161:    */     throws OzException
/* 162:    */   {
/* 163:253 */     Information NewInfo = new Information(Information);
/* 164:254 */     this.allInformationSet.add(NewInfo);
/* 165:255 */     Connection NewConnection = new Connection(NewInfo, connectToInfo, this.defaultConnectionWeight);
/* 166:256 */     NewInfo.addConnection(NewConnection);
/* 167:257 */     connectToInfo.addConnection(NewConnection);
/* 168:    */   }
/* 169:    */   
/* 170:    */   public void newConnection(String information, String connectToInformation)
/* 171:    */     throws OzException
/* 172:    */   {
/* 173:270 */     Information Information = searchInAllInformations(information);
/* 174:271 */     if (Information == null) {
/* 175:272 */       throw new OzException("Information nicht vorhanden");
/* 176:    */     }
/* 177:274 */     Information ConnectToInfo = searchInAllInformations(connectToInformation);
/* 178:275 */     if (ConnectToInfo == null) {
/* 179:276 */       throw new OzException("connectToInformation nicht vorhanden");
/* 180:    */     }
/* 181:279 */     if (Information.getConnections().contains(new Connection(Information, ConnectToInfo, this.defaultConnectionWeight))) {
/* 182:280 */       throw new OzException("Connection existiert schon");
/* 183:    */     }
/* 184:282 */     createConnection(Information, ConnectToInfo, this.defaultConnectionWeight);
/* 185:    */   }
/* 186:    */   
/* 187:    */   private void createConnection(Information Information, Information ConnectToInformation, double weight)
/* 188:    */   {
/* 189:288 */     Connection NewConnection = new Connection(Information, ConnectToInformation, weight);
/* 190:289 */     Information.addConnection(NewConnection);
/* 191:290 */     ConnectToInformation.addConnection(NewConnection);
/* 192:    */     
/* 193:292 */     setInformationImportance(Information);
/* 194:293 */     setInformationImportance(ConnectToInformation);
/* 195:    */     
/* 196:295 */     this.allConnectionSet.add(NewConnection);
/* 197:    */   }
/* 198:    */   
/* 199:    */   public void deleteConnection(String information, String connectToInformation)
/* 200:    */     throws OzException
/* 201:    */   {
/* 202:305 */     Information Information = searchInAllInformations(information);
/* 203:306 */     if (Information == null) {
/* 204:307 */       throw new OzException("Information nicht vorhanden");
/* 205:    */     }
/* 206:309 */     Information ConnectToInfo = searchInAllInformations(connectToInformation);
/* 207:310 */     if (ConnectToInfo == null) {
/* 208:311 */       throw new OzException("connectToInformation nicht vorhanden");
/* 209:    */     }
/* 210:314 */     Connection DelConnection = getConnection(Information, ConnectToInfo);
/* 211:315 */     Information.subConnection(DelConnection);
/* 212:316 */     ConnectToInfo.subConnection(DelConnection);
/* 213:    */     
/* 214:318 */     this.allConnectionSet.remove(DelConnection);
/* 215:    */   }
/* 216:    */   
/* 217:    */   public void deleteInformation(String information)
/* 218:    */     throws OzException
/* 219:    */   {
/* 220:330 */     Information Information = searchInAllInformations(information);
/* 221:331 */     if (Information == null) {
/* 222:332 */       throw new OzException("Information nicht vorhanden");
/* 223:    */     }
/* 224:336 */     Connection[] Connections = Information.getConnectionsAsArray();
/* 225:337 */     for (Connection c : Connections)
/* 226:    */     {
/* 227:338 */       Information Other = c.getOtherInformation(Information);
/* 228:339 */       Other.subConnection(c);
/* 229:340 */       Information.subConnection(c);
/* 230:341 */       setInformationImportance(Information);
/* 231:342 */       setInformationImportance(Other);
/* 232:    */     }
/* 233:345 */     this.allInformationSet.remove(Information);
/* 234:347 */     if (this.EntryPointInformations.contains(Information)) {
/* 235:348 */       this.EntryPointInformations.remove(Information);
/* 236:    */     }
/* 237:    */   }
/* 238:    */   
/* 239:    */   public List<String> pathSearch(String information, String startPoint)
/* 240:    */     throws OzException
/* 241:    */   {
/* 242:361 */     Information Target = null;
/* 243:362 */     Information Source = null;
/* 244:    */     
/* 245:364 */     Iterator<Information> Iter = this.allInformationSet.iterator();
/* 246:365 */     List<String> Path = new LinkedList();
/* 247:367 */     while (Iter.hasNext())
/* 248:    */     {
/* 249:368 */       Information TempInfo = (Information)Iter.next();
/* 250:369 */       if (TempInfo.toString().equals(startPoint)) {
/* 251:370 */         Source = TempInfo;
/* 252:371 */       } else if (TempInfo.toString().equals(information)) {
/* 253:372 */         Target = TempInfo;
/* 254:    */       }
/* 255:374 */       if ((Target != null) && (Source != null)) {
/* 256:    */         break;
/* 257:    */       }
/* 258:    */     }
/* 259:379 */     if (Source == null) {
/* 260:380 */       throw new OzException("Startpunkt nicht gefunden");
/* 261:    */     }
/* 262:381 */     if (Target == null) {
/* 263:382 */       throw new OzException("Ziel nicht gefunden");
/* 264:    */     }
/* 265:384 */     execute(Source);
/* 266:385 */     LinkedList<Information> path = getPath(Target);
/* 267:386 */     Iterator<Information> IterPath = path.iterator();
/* 268:387 */     while (IterPath.hasNext()) {
/* 269:388 */       Path.add(((Information)IterPath.next()).toString());
/* 270:    */     }
/* 271:391 */     return Path;
/* 272:    */   }
/* 273:    */   
/* 274:    */   private void execute(Information source)
/* 275:    */   {
/* 276:396 */     this.settledNodes = new HashSet();
/* 277:397 */     this.unSettledNodes = new HashSet();
/* 278:398 */     this.distance = new HashMap();
/* 279:399 */     this.predecessors = new HashMap();
/* 280:400 */     this.distance.put(source, Integer.valueOf(0));
/* 281:401 */     this.unSettledNodes.add(source);
/* 282:402 */     while (this.unSettledNodes.size() > 0)
/* 283:    */     {
/* 284:403 */       Information node = getMinimum(this.unSettledNodes);
/* 285:404 */       this.settledNodes.add(node);
/* 286:405 */       this.unSettledNodes.remove(node);
/* 287:406 */       findMinimalDistances(node);
/* 288:    */     }
/* 289:    */   }
/* 290:    */   
/* 291:    */   private void findMinimalDistances(Information node)
/* 292:    */   {
/* 293:412 */     List<Information> adjacentNodes = getNeighbors(node);
/* 294:413 */     for (Information target : adjacentNodes) {
/* 295:414 */       if (getShortestDistance(target) > getShortestDistance(node) + getConnection(node, target).toDistance(this.searchMaxInformation))
/* 296:    */       {
/* 297:416 */         this.distance.put(target, Integer.valueOf(getShortestDistance(node) + getConnection(node, target).toDistance(this.searchMaxInformation)));
/* 298:    */         
/* 299:418 */         this.predecessors.put(target, node);
/* 300:419 */         this.unSettledNodes.add(target);
/* 301:    */       }
/* 302:    */     }
/* 303:    */   }
/* 304:    */   
/* 305:    */   private List<Information> getNeighbors(Information node)
/* 306:    */   {
/* 307:427 */     List<Information> neighbors = new ArrayList();
/* 308:428 */     for (Connection edge : node.getConnectionsAsArray()) {
/* 309:429 */       if (!isSettled(edge.getOtherInformation(node))) {
/* 310:430 */         neighbors.add(edge.getOtherInformation(node));
/* 311:    */       }
/* 312:    */     }
/* 313:433 */     return neighbors;
/* 314:    */   }
/* 315:    */   
/* 316:    */   private Information getMinimum(Set<Information> vertexes)
/* 317:    */   {
/* 318:438 */     Information minimum = null;
/* 319:439 */     for (Information vertex : vertexes) {
/* 320:440 */       if (minimum == null) {
/* 321:441 */         minimum = vertex;
/* 322:443 */       } else if (getShortestDistance(vertex) < getShortestDistance(minimum)) {
/* 323:444 */         minimum = vertex;
/* 324:    */       }
/* 325:    */     }
/* 326:448 */     return minimum;
/* 327:    */   }
/* 328:    */   
/* 329:    */   private boolean isSettled(Information vertex)
/* 330:    */   {
/* 331:453 */     return this.settledNodes.contains(vertex);
/* 332:    */   }
/* 333:    */   
/* 334:    */   private int getShortestDistance(Information destination)
/* 335:    */   {
/* 336:458 */     Integer d = (Integer)this.distance.get(destination);
/* 337:459 */     if (d == null) {
/* 338:460 */       return 2147483647;
/* 339:    */     }
/* 340:462 */     return d.intValue();
/* 341:    */   }
/* 342:    */   
/* 343:    */   private LinkedList<Information> getPath(Information target)
/* 344:    */   {
/* 345:468 */     LinkedList<Information> path = new LinkedList();
/* 346:469 */     Information step = target;
/* 347:471 */     if (this.predecessors.get(step) == null) {
/* 348:472 */       return null;
/* 349:    */     }
/* 350:474 */     path.add(step);
/* 351:475 */     while (this.predecessors.get(step) != null)
/* 352:    */     {
/* 353:476 */       step = (Information)this.predecessors.get(step);
/* 354:477 */       path.add(step);
/* 355:    */     }
/* 356:480 */     Collections.reverse(path);
/* 357:481 */     return path;
/* 358:    */   }
/* 359:    */   
/* 360:    */   public String[] search(String information, String startPoint, boolean overEntryPoints)
/* 361:    */     throws OzException
/* 362:    */   {
/* 363:497 */     Information SourceInfo = null;
/* 364:    */     
/* 365:499 */     String searchFor = information;
/* 366:500 */     int resultSteps = this.searchMaxInformation;
/* 367:    */     
/* 368:502 */     LinkedList<Information> VisitedInformations = new LinkedList();
/* 369:503 */     Iterator<Information> IterEntry = this.EntryPointInformations.iterator();
/* 370:504 */     Iterator<Information> IterAll = this.allInformationSet.iterator();
/* 371:    */     
/* 372:    */ 
/* 373:    */ 
/* 374:    */ 
/* 375:509 */     long zstVorher = System.currentTimeMillis();
/* 376:511 */     if (overEntryPoints)
/* 377:    */     {
/* 378:    */       Information TempInfo;
/* 379:    */       do
/* 380:    */       {
/* 381:513 */         if (!IterEntry.hasNext()) {
/* 382:    */           break;
/* 383:    */         }
/* 384:514 */         TempInfo = (Information)IterEntry.next();
/* 385:515 */       } while (!TempInfo.toString().equals(startPoint));
/* 386:516 */       SourceInfo = null;
/* 387:517 */       System.out.println("Suche startet über EntryPoint " + SourceInfo.toString());
/* 388:    */     }
/* 389:    */     else
/* 390:    */     {
/* 391:523 */       while (IterAll.hasNext())
/* 392:    */       {
/* 393:524 */         Information TempInfo = (Information)IterAll.next();
/* 394:525 */         if (TempInfo.toString().equals(startPoint))
/* 395:    */         {
/* 396:526 */           SourceInfo = TempInfo;
/* 397:527 */           System.out.println("Suche startet über Information " + SourceInfo.toString());
/* 398:    */         }
/* 399:    */       }
/* 400:    */     }
/* 401:533 */     if (SourceInfo == null) {
/* 402:534 */       throw new OzException("Startpunkt nicht gefunden");
/* 403:    */     }
/* 404:537 */     for (int i = 0; i < this.searchMaxInformation; i++)
/* 405:    */     {
/* 406:539 */       if (i == 0)
/* 407:    */       {
/* 408:540 */         VisitedInformations.add(SourceInfo);
/* 409:541 */         VisitedInformations.add(searchBiggestNeighbour(SourceInfo, searchFor, null));
/* 410:    */       }
/* 411:    */       else
/* 412:    */       {
/* 413:543 */         VisitedInformations.add(searchBiggestNeighbour((Information)VisitedInformations.get(i), searchFor, (Information)VisitedInformations.get(i - 1)));
/* 414:    */       }
/* 415:546 */       if (((Information)VisitedInformations.get(i + 1)).toString().equals(information))
/* 416:    */       {
/* 417:548 */         resultSteps = i;
/* 418:    */         
/* 419:550 */         break;
/* 420:    */       }
/* 421:    */     }
/* 422:554 */     String[] result = new String[VisitedInformations.size()];
/* 423:555 */     for (int j = 0; j < VisitedInformations.size(); j++) {
/* 424:556 */       result[j] = ((Information)VisitedInformations.get(j)).toString();
/* 425:    */     }
/* 426:560 */     long zstNachher = System.currentTimeMillis();
/* 427:561 */     System.out.println("System.Brain.search: Suche benötigte " + (zstNachher - zstVorher) / 1000L + " sec für das Ergebnis");
/* 428:565 */     if (resultSteps == this.searchMaxInformation) {
/* 429:566 */       throw new OzException("Kein Ergebnis innerhalb der maximalen Knoten " + resultSteps + " erreicht");
/* 430:    */     }
/* 431:569 */     return result;
/* 432:    */   }
/* 433:    */   
/* 434:    */   private Information searchBiggestNeighbour(Information SourceInfo, String searchFor, Information PreInfo)
/* 435:    */   {
/* 436:574 */     ArrayList<Connection> heaviestConnections = new ArrayList();
/* 437:575 */     ArrayList<Information> biggestInformations = new ArrayList();
/* 438:576 */     double heaviestConWeight = 0.0D;
/* 439:577 */     int heaviestInfo = 0;
/* 440:    */     
/* 441:579 */     Information Neighbour = null;
/* 442:584 */     for (int i = 0; i < SourceInfo.connectionCount; i++)
/* 443:    */     {
/* 444:586 */       if (SourceInfo.getConnectionsAsArray()[i].getOtherInformation(SourceInfo).toString().equals(searchFor)) {
/* 445:587 */         return SourceInfo.getConnectionsAsArray()[i].getOtherInformation(SourceInfo);
/* 446:    */       }
/* 447:589 */       if ((PreInfo == null) || (!SourceInfo.getConnectionsAsArray()[i].getOtherInformation(SourceInfo).equals(PreInfo))) {
/* 448:590 */         if (SourceInfo.getConnectionsAsArray()[i].getWeight() > heaviestConWeight)
/* 449:    */         {
/* 450:591 */           heaviestConWeight = SourceInfo.getConnectionsAsArray()[i].getWeight();
/* 451:592 */           heaviestConnections.clear();
/* 452:593 */           heaviestConnections.add(SourceInfo.getConnectionsAsArray()[i]);
/* 453:    */         }
/* 454:594 */         else if (SourceInfo.getConnectionsAsArray()[i].getWeight() == heaviestConWeight)
/* 455:    */         {
/* 456:595 */           heaviestConnections.add(SourceInfo.getConnectionsAsArray()[i]);
/* 457:    */         }
/* 458:    */       }
/* 459:    */     }
/* 460:599 */     System.out.println("System.Brain: ^" + heaviestConnections.size());
/* 461:602 */     if (heaviestConnections.size() > 1)
/* 462:    */     {
/* 463:603 */       Iterator<Connection> Iter = heaviestConnections.iterator();
/* 464:604 */       while (Iter.hasNext())
/* 465:    */       {
/* 466:605 */         Connection TempConnection = (Connection)Iter.next();
/* 467:606 */         if (TempConnection.getOtherInformation(SourceInfo).connectionCount > heaviestInfo)
/* 468:    */         {
/* 469:607 */           heaviestInfo = TempConnection.getOtherInformation(SourceInfo).connectionCount;
/* 470:608 */           biggestInformations.clear();
/* 471:609 */           biggestInformations.add(TempConnection.getOtherInformation(SourceInfo));
/* 472:    */         }
/* 473:610 */         else if (TempConnection.getOtherInformation(SourceInfo).connectionCount == heaviestInfo)
/* 474:    */         {
/* 475:611 */           biggestInformations.add(TempConnection.getOtherInformation(SourceInfo));
/* 476:    */         }
/* 477:    */       }
/* 478:615 */       int rnd = new Random().nextInt(biggestInformations.size());
/* 479:616 */       Neighbour = (Information)biggestInformations.get(rnd);
/* 480:    */     }
/* 481:617 */     else if (heaviestConnections.size() == 1)
/* 482:    */     {
/* 483:618 */       Neighbour = ((Connection)heaviestConnections.get(0)).getOtherInformation(SourceInfo);
/* 484:    */     }
/* 485:620 */     return Neighbour;
/* 486:    */   }
/* 487:    */   
/* 488:    */   public void linearSearch(String information)
/* 489:    */   {
/* 490:629 */     this.ActiveInformation = searchInAllInformations(information);
/* 491:630 */     this.ActiveInformationAssociation = null;
/* 492:    */   }
/* 493:    */   
/* 494:    */   private Information searchInAllInformations(String information)
/* 495:    */   {
/* 496:636 */     Information result = null;
/* 497:    */     
/* 498:    */ 
/* 499:639 */     long zstVorher = System.currentTimeMillis();
/* 500:    */     
/* 501:641 */     Iterator<Information> Iter = this.allInformationSet.iterator();
/* 502:642 */     while (Iter.hasNext())
/* 503:    */     {
/* 504:643 */       Information TempInformation = (Information)Iter.next();
/* 505:644 */       if (TempInformation.toString().equals(information)) {
/* 506:645 */         result = TempInformation;
/* 507:    */       }
/* 508:    */     }
/* 509:651 */     long zstNachher = System.currentTimeMillis();
/* 510:652 */     if (result != null) {
/* 511:653 */       System.out.println("System.Brain: FastSearch erlolgreich in " + (zstNachher - zstVorher) / 1000L + " sec");
/* 512:    */     } else {
/* 513:656 */       System.out.println("System.Brain: FastSearch hat Info nicht gefunden - " + (zstNachher - zstVorher) / 1000L + " sec");
/* 514:    */     }
/* 515:660 */     return result;
/* 516:    */   }
/* 517:    */   
/* 518:    */   private boolean existInfo(String information)
/* 519:    */   {
/* 520:665 */     return this.allInformationSet.contains(new Information(information));
/* 521:    */   }
/* 522:    */   
/* 523:    */   public void enhanceActiveAssociation()
/* 524:    */     throws OzException
/* 525:    */   {
/* 526:675 */     if ((this.ActiveInformation != null) && (this.ActiveInformationAssociation != null))
/* 527:    */     {
/* 528:677 */       Connection[] Connections = this.ActiveInformation.getConnectionsAsArray();
/* 529:678 */       for (Connection c : Connections) {
/* 530:679 */         if (c.getOtherInformation(this.ActiveInformation).equals(this.ActiveInformationAssociation)) {
/* 531:680 */           c.enhanceWeight(this.maxConnectionWeight, this.changeConnectionWeight);
/* 532:    */         }
/* 533:    */       }
/* 534:    */     }
/* 535:    */     else
/* 536:    */     {
/* 537:683 */       throw new OzException("Aktive Informationen sind nicht ausreichend gefüllt");
/* 538:    */     }
/* 539:    */   }
/* 540:    */   
/* 541:    */   public void decreaseActiveAssociation()
/* 542:    */     throws OzException
/* 543:    */   {
/* 544:693 */     if ((this.ActiveInformation != null) && (this.ActiveInformationAssociation != null))
/* 545:    */     {
/* 546:695 */       Connection[] Connections = this.ActiveInformation.getConnectionsAsArray();
/* 547:696 */       for (Connection c : Connections) {
/* 548:697 */         if (c.getOtherInformation(this.ActiveInformation).equals(this.ActiveInformationAssociation)) {
/* 549:698 */           c.decreaseWeight(this.allowZeroWeight, this.minConnectionWeight, this.changeConnectionWeight);
/* 550:    */         }
/* 551:    */       }
/* 552:    */     }
/* 553:    */     else
/* 554:    */     {
/* 555:701 */       throw new OzException("Aktive Informationen sind nicht ausreichend gefüllt");
/* 556:    */     }
/* 557:    */   }
/* 558:    */   
/* 559:    */   public void setActiveAssociation(String information, String association)
/* 560:    */     throws OzException
/* 561:    */   {
/* 562:713 */     if (existInfo(information))
/* 563:    */     {
/* 564:716 */       this.ActiveInformation = searchInAllInformations(information);
/* 565:717 */       Connection[] Connections = this.ActiveInformation.getConnectionsAsArray();
/* 566:718 */       for (int i = 0; i < Connections.length; i++)
/* 567:    */       {
/* 568:719 */         Information Association = Connections[i].getOtherInformation(this.ActiveInformation);
/* 569:720 */         if (Association.toString().equals(association))
/* 570:    */         {
/* 571:721 */           this.ActiveInformationAssociation = Association;
/* 572:722 */           break;
/* 573:    */         }
/* 574:725 */         if (i == Connections.length - 1)
/* 575:    */         {
/* 576:726 */           this.ActiveInformation = null;
/* 577:727 */           throw new OzException("Assoziation ist nicht mit Information verknüpft");
/* 578:    */         }
/* 579:    */       }
/* 580:    */     }
/* 581:    */     else
/* 582:    */     {
/* 583:731 */       throw new OzException("Information existiert nicht");
/* 584:    */     }
/* 585:    */   }
/* 586:    */   
/* 587:    */   public void setNewRandomActiveAssoziation()
/* 588:    */   {
/* 589:740 */     this.ActiveInformation = getRandomInformation();
/* 590:741 */     this.ActiveInformationAssociation = getRandomAssoziatedInformation(this.ActiveInformation);
/* 591:    */   }
/* 592:    */   
/* 593:    */   public void setNewrandomActiveAssoziation(String information)
/* 594:    */     throws OzException
/* 595:    */   {
/* 596:750 */     if (existInfo(information))
/* 597:    */     {
/* 598:751 */       this.ActiveInformation = searchInAllInformations(information);
/* 599:752 */       this.ActiveInformationAssociation = getRandomAssoziatedInformation(this.ActiveInformation);
/* 600:    */     }
/* 601:    */     else
/* 602:    */     {
/* 603:754 */       throw new OzException("Information existiert nicht");
/* 604:    */     }
/* 605:    */   }
/* 606:    */   
/* 607:    */   public String[] getAllPossibleAssoziations(String information)
/* 608:    */     throws OzException
/* 609:    */   {
/* 610:765 */     String[] result = null;
/* 611:766 */     if (existInfo(information))
/* 612:    */     {
/* 613:767 */       this.ActiveInformation = searchInAllInformations(information);
/* 614:768 */       this.ActiveInformationAssociation = null;
/* 615:769 */       result = new String[this.ActiveInformation.connectionCount];
/* 616:770 */       for (int i = 0; i < this.ActiveInformation.connectionCount; i++) {
/* 617:771 */         result[i] = this.ActiveInformation.getConnectionsAsArray()[i].getOtherInformation(this.ActiveInformation).toString();
/* 618:    */       }
/* 619:    */     }
/* 620:    */     else
/* 621:    */     {
/* 622:774 */       throw new OzException("Information existiert nicht");
/* 623:    */     }
/* 624:776 */     return result;
/* 625:    */   }
/* 626:    */   
/* 627:    */   private Information getRandomAssoziatedInformation(Information Information)
/* 628:    */   {
/* 629:781 */     Random Random = new Random();
/* 630:    */     
/* 631:    */ 
/* 632:784 */     int rndCon = Random.nextInt(Information.connectionCount);
/* 633:785 */     return Information.getConnectionsAsArray()[rndCon].getOtherInformation(Information);
/* 634:    */   }
/* 635:    */   
/* 636:    */   private Information getRandomInformation()
/* 637:    */   {
/* 638:790 */     Random Random = new Random();
/* 639:    */     
/* 640:792 */     int rnd = Random.nextInt(this.allInformationSet.size());
/* 641:793 */     Iterator<Information> Iter = this.allInformationSet.iterator();
/* 642:794 */     int i = 0;
/* 643:795 */     while ((Iter.hasNext()) && 
/* 644:796 */       (i != rnd)) {
/* 645:797 */       i++;
/* 646:    */     }
/* 647:799 */     return (Information)Iter.next();
/* 648:    */   }
/* 649:    */   
/* 650:    */   private Connection getConnection(Information Info, Information Association)
/* 651:    */   {
/* 652:804 */     for (int i = 0; i < Info.connectionCount; i++) {
/* 653:805 */       if (Info.getConnectionsAsArray()[i].getOtherInformation(Info).equals(Association)) {
/* 654:806 */         return Info.getConnectionsAsArray()[i];
/* 655:    */       }
/* 656:    */     }
/* 657:809 */     return null;
/* 658:    */   }
/* 659:    */   
/* 660:    */   public List<String> getLevelList(int level)
/* 661:    */     throws OzException
/* 662:    */   {
/* 663:    */     List<Information> list;
/* 664:825 */     switch (level)
/* 665:    */     {
/* 666:    */     case 1: 
/* 667:827 */       if (this.levelOneInformations.isEmpty()) {
/* 668:828 */         throw new OzException("LevelOne-Liste ist leer");
/* 669:    */       }
/* 670:829 */       list = new ArrayList(this.levelOneInformations);
/* 671:830 */       return getList(list);
/* 672:    */     case 2: 
/* 673:832 */       if (this.levelTwoInformations.isEmpty()) {
/* 674:833 */         throw new OzException("LevelTwo-Liste ist leer");
/* 675:    */       }
/* 676:834 */       list = new ArrayList(this.levelOneInformations);
/* 677:835 */       return getList(list);
/* 678:    */     case 3: 
/* 679:837 */       if (this.levelThreeInformations.isEmpty()) {
/* 680:838 */         throw new OzException("LevelThree-Liste ist leer");
/* 681:    */       }
/* 682:839 */       list = new ArrayList(this.levelOneInformations);
/* 683:840 */       return getList(list);
/* 684:    */     }
/* 685:842 */     throw new OzException("Parameter Level existiert nicht");
/* 686:    */   }
/* 687:    */   
/* 688:    */   private List<String> getList(List<Information> List)
/* 689:    */   {
/* 690:848 */     List<String> StringList = new ArrayList();
/* 691:849 */     Iterator<Information> Iter = List.iterator();
/* 692:850 */     while (Iter.hasNext()) {
/* 693:851 */       StringList.add(((Information)Iter.next()).toString());
/* 694:    */     }
/* 695:852 */     return StringList;
/* 696:    */   }
/* 697:    */   
/* 698:    */   private void setInformationImportance(Information Information)
/* 699:    */   {
/* 700:860 */     if (Information.connectionCount > this.bigInformationWeight)
/* 701:    */     {
/* 702:862 */       enhanceBigInformationCount();
/* 703:863 */       System.out.println("System.Brain: Wichtigkeitsprüfung->Erhöhung der Stufe");
/* 704:    */     }
/* 705:866 */     if (Information.connectionCount == this.bigInformationWeight)
/* 706:    */     {
/* 707:868 */       this.levelOneInformations.addLast(Information);
/* 708:869 */       if (this.levelTwoInformations.contains(Information))
/* 709:    */       {
/* 710:870 */         this.levelTwoInformations.remove(this.levelTwoInformations.indexOf(Information));
/* 711:871 */         System.out.println("System.Brain: Information aus Level 2 entfernt");
/* 712:    */       }
/* 713:873 */       System.out.println("System.Brain: Wichtigkeitsprüfung->Level 1");
/* 714:    */     }
/* 715:874 */     else if (Information.connectionCount == this.bigInformationWeight - 1)
/* 716:    */     {
/* 717:876 */       this.levelTwoInformations.addLast(Information);
/* 718:877 */       if (this.levelOneInformations.contains(Information))
/* 719:    */       {
/* 720:878 */         this.levelOneInformations.remove(this.levelOneInformations.indexOf(Information));
/* 721:879 */         System.out.println("System.Brain: Information aus Level 1 entfernt");
/* 722:    */       }
/* 723:880 */       else if (this.levelThreeInformations.contains(Information))
/* 724:    */       {
/* 725:881 */         this.levelThreeInformations.remove(this.levelThreeInformations.indexOf(Information));
/* 726:882 */         System.out.println("System.Brain: Information aus Level 3 entfernt");
/* 727:    */       }
/* 728:884 */       System.out.println("System.Brain: Wichtigkeitsprüfung->Level 2");
/* 729:    */     }
/* 730:885 */     else if (Information.connectionCount == this.bigInformationWeight - 2)
/* 731:    */     {
/* 732:887 */       this.levelThreeInformations.addLast(Information);
/* 733:888 */       if (this.levelTwoInformations.contains(Information))
/* 734:    */       {
/* 735:889 */         this.levelTwoInformations.remove(this.levelTwoInformations.indexOf(Information));
/* 736:890 */         System.out.println("System.Brain: Information aus Level 2 entfernt");
/* 737:    */       }
/* 738:892 */       System.out.println("System.Brain: Wichtigkeitsprüfung->Level 3");
/* 739:    */     }
/* 740:    */     else
/* 741:    */     {
/* 742:895 */       System.out.println("System.Brain: Wichtigkeitsprüfung->unwichtig");
/* 743:    */     }
/* 744:    */   }
/* 745:    */   
/* 746:    */   private void enhanceBigInformationCount()
/* 747:    */   {
/* 748:903 */     this.bigInformationWeight += 1;
/* 749:    */     
/* 750:905 */     this.levelThreeInformations.clear();
/* 751:906 */     this.levelThreeInformations.addAll(this.levelTwoInformations);
/* 752:907 */     this.levelTwoInformations.clear();
/* 753:908 */     this.levelTwoInformations.addAll(this.levelOneInformations);
/* 754:909 */     this.levelOneInformations.clear();
/* 755:    */   }
/* 756:    */ }



/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar

 * Qualified Name:     ozzy.OzBrain

 * JD-Core Version:    0.7.0.1

 */