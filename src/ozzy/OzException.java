/*    1:     */ package ozzy;
/*    2:     */ 
/*    3:     */ import java.io.Serializable;
/*    4:     */ 
/*    5:     */ class OzException
/*    6:     */   extends Exception
/*    7:     */   implements Serializable
/*    8:     */ {
/*    9:     */   private static final long serialVersionUID = -123456789L;
/*   10:     */   
/*   11:     */   protected OzException() {}
/*   12:     */   
/*   13:     */   protected OzException(String msg)
/*   14:     */   {
/*   15:1075 */     super(msg);
/*   16:     */   }
/*   17:     */ }


/* Location:           C:\Users\jungoliver\Desktop\Ozzy.jar
 * Qualified Name:     ozzy.OzException
 * JD-Core Version:    0.7.0.1
 */