/**
 * Created by Natalia on 12.04.16.
 */
import javafx.scene.image.Image;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.Scanner;

public class NetSend {
    public static void main(String[] args) throws IOException {

        boolean iamsender = args.length > 0 && args[0].equals("sender");
        DatagramSocket socket = new DatagramSocket(8090);
        Scanner sc = new Scanner(System.in);
        while (true) {
            if (iamsender) {
                InetAddress address = InetAddress.getByName("10.17.2.163");
                String message = sc.nextLine();
                byte[] helloString = message.getBytes();
                DatagramPacket packet = new DatagramPacket(helloString, helloString.length, address, 8090);
                socket.send(packet);
                iamsender = false;
            } else {
                byte[] receiveBytes = new byte[1024];
                DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
                socket.receive(receivePacket);
                String text = new String(receivePacket.getData(), 0, receivePacket.getLength());
                System.out.print("Recieved  text:" + text);
                iamsender = true;
            }

        }
///*
//DatagramSocket secondSocket = new DatagramSocket(8091);
//byte[] receiveBytes = new byte[1024];
//DatagramPacket receivePacket = new DatagramPacket(receiveBytes, receiveBytes.length);
//secondSocket.receive(receivePacket);
//String text = new String( receivePacket.getData(), 0, receivePacket.getLength());
//System.out.printf("receive text: " + text );
//*/
    }
}
