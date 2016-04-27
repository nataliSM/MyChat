import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.LinkedList;

/**
 * Created by Natalia on 12.04.16.
 */
public class ReceiveThread implements Runnable {
    private LinkedList<String> history = new LinkedList<String>();

    private DatagramSocket socket;
    public ReceiveThread(LinkedList<String> history){
        try{
            socket = new DatagramSocket(8091);

        }catch(SocketException e){
            System.out.print("some fatal error");
        }
    }
    @Override
    public void run() {

        while(true){
            try {
                byte[] receiveBytes = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);

                socket.receive(receivePacket);

                String text = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.print("Recieved  text:" + text);
                synchronized (history){
                    history.add(text);
                }


            }catch(IOException e){
                System.out.println("some error");
            }
        }

    }
}
