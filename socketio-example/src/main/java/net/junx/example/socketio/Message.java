  
package net.junx.example.socketio;  
 
/** 
 * @author	junx
 * @date	2015年11月18日上午11:48:42 
 * @since 	JDK 1.8
 */

public class Message {
    private String code;
    private String message;
    
    
    public Message(){
        
    }
    
    /** 
     * Creates a new instance of Message. 
     * 
     * @param code
     * @param message 
     */  
    public Message(String code, String message) {
        super();
        this.code = code;
        this.message = message;
    }
    public String getCode() {
        return code;
    }
    public void setCode(String code) {
        this.code = code;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    @Override
    public String toString() {
        return "Message [code=" + code + ", message=" + message + "]";
    }
}
  