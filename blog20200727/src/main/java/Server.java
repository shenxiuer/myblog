import javax.imageio.IIOException;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Server {
    public static void main(String[] args) {

    try{
//        Socket s = new Socket("127.0.0.1",1234)
        ServerSocket server = new ServerSocket(); //一个socket
        Socket s = server.accept();
        InputStream in = s.getInputStream();  //输入
        OutputStream out =s.getOutputStream();  //输出
        BufferedReader netin = new BufferedReader(new InputStreamReader(in));
        PrintWriter netout = new PrintWriter(out);
        String msg;
        msg=netin.readLine();
        System.out.println(msg);

        netout.println("Server"+msg);
        netout.flush();

    }
      catch (IOException ex){
          Logger.getLogger(Server.class.getName()).log(Level.SEVERE,null,ex);
      }
    }
}
