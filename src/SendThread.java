import javax.imageio.IIOException;
import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.Date;

/**
 * Created by Natalia on 12.04.16.
 */
public class SendThread implements Runnable {
    private  LinkedList<String> history;
    private String name;
    private Scanner sc  = new Scanner(System.in);
    private DatagramSocket socket;
    private String address;

    public SendThread(String myName, String address, LinkedList<String> history){
        this.name = myName;
        this.address = address;
        this.history = history;
        try{
            socket = new DatagramSocket(8090);

        }catch(SocketException e){
            System.out.print("some fatal error");
        }
    }



    public void run(){
        while(true){
            InetAddress adr = null;
            try{
                 adr = InetAddress.getByName(this.address);
                System.out.print("Send text: ");
                String message = sc.nextLine();

                Date d = new Date();
                String sendMessage = name+ "[" + d.toString() + "]:" + message;
                synchronized (history){
                    history.add(sendMessage);
                }
                byte[] mBytes = sendMessage.getBytes();
                DatagramPacket packet = new DatagramPacket(mBytes, mBytes.length, adr, 8091);
                socket.send(packet);

            }catch (IIOException e ){
                System.out.print("error");
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
