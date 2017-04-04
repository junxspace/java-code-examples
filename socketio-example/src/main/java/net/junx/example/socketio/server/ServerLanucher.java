  
package net.junx.example.socketio.server;  

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.listener.DataListener;

import net.junx.example.socketio.Message;
 
/** 
 * @author	junx
 * @date	2015年11月18日上午11:52:56 
 * @since 	JDK 1.8
 */

public class ServerLanucher {
    public static void main(String[] args) throws IOException, InterruptedException {
        Configuration config = new Configuration();
        config.setHostname("localhost");
        config.setPort(9092);
        
        final SocketIOServer server  = new SocketIOServer(config);
        server.addEventListener("messageEvent", Message.class, new DataListener<Message>(){
            @Override
            public void onData(SocketIOClient sioClient, Message message,
                    AckRequest ack) throws Exception {
                System.out.println(">>server received:"+message);
                Message msg = new Message();
                msg.setCode("0000");
                msg.setMessage(DateFormat.getDateInstance().format(new Date()));
                server.getBroadcastOperations().sendEvent("chatevent", msg);
            }
        });
        server.start();
        
        Message msg;
        for (;;) {
            msg = new Message();
            msg.setCode("0000");
            msg.setMessage(Long.toHexString(System.currentTimeMillis()));
            server.getBroadcastOperations().sendEvent("chatevent", msg);
            TimeUnit.SECONDS.sleep(1);
        }
    }
}
  