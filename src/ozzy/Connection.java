/*    1:     */ package ozzy;
/*    2:     */ 
/*    3:     */ import java.io.Serializable;
/*    4:     */ 
/*    5:     */ class Connection
/*    6:     */   implements Serializable
/*    7:     */ {
/*    8:     */   private static final long serialVersionUID = -123456789L;
/*    9:     */   private double weight;
/*   10:     */   private final Information in;
/*   11:     */   private final Information out;
/*   12:     */   
/*   13:     */   protected Connection(Information in, Information out, double weight)
/*   14:     */   {
/*   15: 991 */     this.weight = weight;
/*   16: 992 */     this.in = in;
/*   17: 993 */     this.out = out;
/*   18:     */   }
/*   19:     */   
/*   20:     */   public String toString()
/*   21:     */   {
/*   22: 998 */     return this.in + "," + this.out + ":" + this.weight;
/*   23:     */   }
/*   24:     */   
/*   25:     */   public boolean equals(Object obj)
/*   26:     */   {
/*   27:1004 */     if (!(obj instanceof Information)) {
/*   28:1005 */       return false;
/*   29:     */     }
/*   30:1006 */     if (obj == this) {
/*   31:1007 */       return true;
/*   32:     */     }
/*   33:1008 */     return (this.in.equals(((Connection)obj).in)) && (this.out.equals(((Connection)obj).out));
/*   34:     */   }
/*   35:     */   
/*   36:     */   public int hashCode()
/*   37:     */   {
/*   38:1013 */     if ((this.in != null) && (this.out != null))
/*   39:     */     {
/*   40:1014 */       String hashString = this.in.toString() + this.out.toString();
/*   41:1015 */       return hashString.hashCode();
/*   42:     */     }
/*   43:1017 */     return super.hashCode();
/*   44:     */   }
/*   45:     */   
/*   46:     */   protected double getWeight()
/*   47:     */   {
/*   48:1022 */     return this.weight;
/*   49:     */   }
/*   50:     */   
/*   51:     */   protected int toDistance(double searchMaxInformation)
/*   52:     */   {
/*   53:1026 */     double result = (searchMaxInformation - this.weight / 1.0D) * 100.0D;
/*   54:1027 */     return (int)result;
/*   55:     */   }
/*   56:     */   
/*   57:     */   protected Information getIn()
/*   58:     */   {
/*   59:1031 */     return this.in;
/*   60:     */   }
/*   61:     */   
/*   62:     */   protected Information getOut()
/*   63:     */   {
/*   64:1035 */     return this.out;
/*   65:     */   }
/*   66:     */   
/*   67:     */   protected Information getOtherInformation(Information Info)
/*   68:     */   {
/*   69:1040 */     if (Info.equals(this.in)) {
/*   70:1041 */       return this.out;
/*   71:     */     }
/*   72:1043 */     if (Info.equals(this.out)) {
/*   73:1044 */       return this.in;
/*   74:     */     }
/*   75:1046 */     return null;
/*   76:     */   }
/*   77:     */   
/*   78:     */   protected void enhanceWeight(double maxConnectionWeight, double changeConnectionWeight)
/*   79:     */   {
/*   80:1051 */     if (this.weight < maxConnectionWeight) {
/*   81:1051 */       this.weight += changeConnectionWeight;
/*   82:     */     }
/*   83:     */   }
/*   84:     */   
/*   85:     */   protected void decreaseWeight(boolean allowZeroWeight, double minConnectionWeight, double changeConnectionWeight)
/*   86:     */   {
/*   87:1056 */     if (!allowZeroWeight) {
/*   88:1059 */       if (this.weight > minConnectionWeight) {
/*   89:1059 */         this.weight -= changeConnectionWeight;
/*   90:     */       }
/*   91:     */     }
/*   92:     */   }
/*   93:     */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.Connection
 * JD-Core Version:    0.7.0.1
 */