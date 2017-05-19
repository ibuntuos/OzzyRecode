/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.PrintStream;
/*   4:    */ import java.util.ArrayList;
/*   5:    */ 
/*   6:    */ public class Ozzy
/*   7:    */ {
/*   8:    */   public final OzPsych OzPsych;
/*   9:    */   public final OzMemory OzMemory;
/*  10:    */   public final OzLogic OzLogic;
/*  11:    */   public final OzTextBuilder OzTextBuilder;
/*  12:    */   public final ArrayList<OzLocation> locations;
/*  13:    */   private final ArrayList<OzActor> searchers;
/*  14:    */   private ArrayList<OzBrain> brains;
/*  15:    */   public OzBrain Brain;
/*  16:    */   
/*  17:    */   public static void main(String[] args)
/*  18:    */   {
/*  19: 31 */     Ozzy Ozzy = new Ozzy();
/*  20:    */     
/*  21: 33 */     String whereToGo = Ozzy.OzLogic.askMe(Ozzy.OzMemory, Ozzy.OzPsych, Ozzy.locations);
/*  22:    */     
/*  23: 35 */     System.out.println(whereToGo);
/*  24:    */   }
/*  25:    */   
/*  26:    */   public Ozzy()
/*  27:    */   {
/*  28: 40 */     this.OzLogic = new OzLogic();
/*  29: 41 */     this.OzPsych = new OzPsych();
/*  30: 42 */     this.OzMemory = new OzMemory();
/*  31: 43 */     this.OzTextBuilder = new OzTextBuilder();
/*  32: 44 */     this.locations = new ArrayList();
/*  33: 45 */     this.searchers = new ArrayList();
/*  34:    */     
/*  35: 47 */     this.brains = this.OzMemory.getBrains();
/*  36: 50 */     if (this.brains.isEmpty())
/*  37:    */     {
/*  38: 51 */       this.Brain = new OzBrain("Locations");
/*  39: 52 */       this.brains.add(this.Brain);
/*  40: 53 */       System.out.println("Sys-Ozzy: Default-Brain erstellt");
/*  41:    */     }
/*  42:    */     else
/*  43:    */     {
/*  44: 55 */       this.Brain = ((OzBrain)this.brains.get(0));
/*  45: 56 */       System.out.println("Sys-Ozzy: Brain wurde geladen");
/*  46:    */     }
/*  47: 60 */     this.locations.add(new OzLocation("Laedele", 'n', "Imbiss", true, 1, 0.6D, "ins Laedele"));
/*  48: 61 */     this.locations.add(new OzLocation("Gutgelaunten", 'm', "Metzgerei", false, 2, 0.4D, "zum Gutgelaunten"));
/*  49: 62 */     this.locations.add(new OzLocation("Walfisch", 'm', "Restaurant", true, 3, 0.2D, "in den Walfisch"));
/*  50: 63 */     this.locations.add(new OzLocation("Hamma", 'm', "Bäckerei", false, 3, 0.2D, "zum Hamma"));
/*  51: 64 */     this.locations.add(new OzLocation("Subway", 'm', "Imbiss", true, 3, 0.2D, "zum Subway"));
/*  52: 65 */     this.locations.add(new OzLocation("Irish", 'n', "Restaurant", true, 2, 0.6D, "ins Irish"));
/*  53:    */     
/*  54: 67 */     this.locations.add(new OzLocation("Walser", 'm', "Metzgerei", true, 3, 0.2D, "zum Walser"));
/*  55: 68 */     this.locations.add(new OzLocation("Firenze", 'm', "Restaurant", false, 3, 0.2D, "zum Firenze"));
/*  56: 69 */     this.locations.add(new OzLocation("König Kebap", 'm', "Restaurant", false, 3, 0.2D, "zum König Kebap"));

                  this.locations.add(new OzLocation("KuhMuh", 'm', "Restaurant", false, 3, 0.2D, "ins KuhMuh"));
                  this.locations.add(new OzLocation("Noodles", 'm', "Restaurant", false, 3, 0.2D, "ins Noodles"));
                  this.locations.add(new OzLocation("Radius", 'm', "Restaurant", false, 3, 0.2D, "ins Radius"));
                  this.locations.add(new OzLocation("Kantine", 'm', "Restaurant", false, 3, 0.2D, "in die Kantine - Ha Ha!"));
                  this.locations.add(new OzLocation("Docs", 'm', "Restaurant", false, 3, 0.2D, "ins Docs"));
/*  57:    */     
/*  58:    */ 
/*  59: 72 */     this.OzMemory.addAbuse(new OzText("Ich werde mich nicht umentscheiden", 2));
/*  60: 73 */     this.OzMemory.addAbuse(new OzText("Hör auf zu nerven!", 2));
/*  61: 74 */     this.OzMemory.addAbuse(new OzText("Klick wo anders hin", 2));
                  this.OzMemory.addAbuse(new OzText("Dein Schnauz!", 2));
/*  62: 75 */     this.OzMemory.addAbuse(new OzText("Hast du nichts zu arbeiten?", 2));
/*  63: 76 */     this.OzMemory.addAbuse(new OzText("Himmelarsch! Dann iss ein Brot!", 2));
                  this.OzMemory.addAbuse(new OzText("Ich entscheid mich nicht um - friss oder stirb!", 2));
/*  64: 77 */     this.OzMemory.addAbuse(new OzText("Dann frag mich halt nicht!", 2));
/*  65: 78 */     this.OzMemory.addAbuse(new OzText("Nochmal? Zur Strafe 10 saure Zungen auf einmal!", 2));
/*  66: 79 */     this.OzMemory.addAbuse(new OzText("Ich schick euch in die Kantine! Und ja, das ist eine Drohung!", 2));
                  this.OzMemory.addAbuse(new OzText("So es langt - ihr esst heute gar nix, fertig!!!", 2));
/*  67:    */     
/*  68:    */ 
/*  69: 82 */     this.OzMemory.addRevisions(new OzText("Na gut, weil ich heut gut drauf bin.", 3));
/*  70: 83 */     this.OzMemory.addRevisions(new OzText("Ich will zwar nicht, aber ich wurde so programmiert!", 3));
/*  71: 84 */     this.OzMemory.addRevisions(new OzText("Wer von euch will denn nicht? Hmpf!", 3));
/*  72: 85 */     this.OzMemory.addRevisions(new OzText("So viele bunte Pillen :-)", 3));
                  this.OzMemory.addRevisions(new OzText("Ja doch, ist ja schon gut!", 3));
                  this.OzMemory.addRevisions(new OzText("Ich mag es nicht wenn ich angezweifelt werde, aber ok.", 3));
/*  73:    */   }
/*  74:    */   
/*  75:    */   public void doClosingThings()
/*  76:    */   {
/*  77: 94 */     this.OzMemory.setBrains(this.brains);
/*  78:    */     
/*  79: 96 */     this.OzMemory.writeBrains();
/*  80: 97 */     System.out.println("Sys-Ozzy: Brain wurde gespeichert");
/*  81:    */   }
/*  82:    */   
/*  83:    */   public void doSomeTests()
/*  84:    */   {
/*  85:102 */     this.brains.add(new OzBrain("Test"));
/*  86:103 */     this.OzMemory.setBrains(this.brains);
/*  87:104 */     this.OzMemory.writeBrains();
/*  88:    */     
/*  89:106 */     this.OzMemory.readBrains();
/*  90:107 */     this.brains = this.OzMemory.getBrains();
/*  91:    */   }
/*  92:    */ }



/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar

 * Qualified Name:     ozzy.Ozzy

 * JD-Core Version:    0.7.0.1

 */