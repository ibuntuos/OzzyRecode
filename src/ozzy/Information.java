/*   1:    */ package ozzy;
/*   2:    */ 
/*   3:    */ import java.io.Serializable;
/*   4:    */ import java.util.HashSet;
/*   5:    */ 
/*   6:    */ class Information
/*   7:    */   implements Serializable
/*   8:    */ {
/*   9:    */   private static final long serialVersionUID = -123456788L;
/*  10:    */   private final String info;
/*  11:    */   protected int connectionCount;
/*  12:    */   private HashSet<Connection> Connections;
/*  13:    */   
/*  14:    */   protected Information(String info)
/*  15:    */   {
/*  16:924 */     this.info = info;
/*  17:925 */     this.connectionCount = 0;
/*  18:926 */     this.Connections = new HashSet();
/*  19:    */   }
/*  20:    */   
/*  21:    */   public String toString()
/*  22:    */   {
/*  23:933 */     return this.info;
/*  24:    */   }
/*  25:    */   
/*  26:    */   public boolean equals(Object obj)
/*  27:    */   {
/*  28:939 */     if (!(obj instanceof Information)) {
/*  29:940 */       return false;
/*  30:    */     }
/*  31:941 */     if (obj == this) {
/*  32:942 */       return true;
/*  33:    */     }
/*  34:943 */     return this.info.equals(((Information)obj).info);
/*  35:    */   }
/*  36:    */   
/*  37:    */   public int hashCode()
/*  38:    */   {
/*  39:948 */     return this.info.hashCode();
/*  40:    */   }
/*  41:    */   
/*  42:    */   protected boolean addConnection(Connection newConnection)
/*  43:    */   {
/*  44:953 */     if (!this.Connections.add(newConnection)) {
/*  45:954 */       return false;
/*  46:    */     }
/*  47:955 */     this.connectionCount += 1;
/*  48:956 */     return true;
/*  49:    */   }
/*  50:    */   
/*  51:    */   protected boolean subConnection(Connection oldConnection)
/*  52:    */   {
/*  53:961 */     if (!this.Connections.contains(oldConnection)) {
/*  54:962 */       return false;
/*  55:    */     }
/*  56:964 */     this.Connections.remove(oldConnection);
/*  57:965 */     this.connectionCount -= 1;
/*  58:966 */     return true;
/*  59:    */   }
/*  60:    */   
/*  61:    */   protected HashSet getConnections()
/*  62:    */   {
/*  63:971 */     return this.Connections;
/*  64:    */   }
/*  65:    */   
/*  66:    */   protected Connection[] getConnectionsAsArray()
/*  67:    */   {
/*  68:976 */     return (Connection[])this.Connections.toArray(new Connection[this.connectionCount]);
/*  69:    */   }
/*  70:    */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.Information
 * JD-Core Version:    0.7.0.1
 */