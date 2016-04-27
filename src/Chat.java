import java.util.LinkedList;

/**
 * Created by Natalia on 12.04.16.
 */
public class Chat {
    public static void main(String[] args){
        LinkedList<String> history = new LinkedList<String>();
        Thread sendThread = new Thread(new SendThread("Natasha","10.17.4.100",history));
        Thread receiveThread = new Thread(new ReceiveThread(history));
        sendThread.start();
        receiveThread.start();
    }
}
