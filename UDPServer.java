import java.net.*;

public class UDPServer {
    public static void main(String[] args) {
        try {
            DatagramSocket serverSocket = new DatagramSocket(9876);

            byte[] receiveData = new byte[1024];

            while (true) {
                DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
                serverSocket.receive(receivePacket);

                String sentence = new String(receivePacket.getData(), 0, receivePacket.getLength());

                InetAddress IPAddress = receivePacket.getAddress();
                int port = receivePacket.getPort();

                System.out.println("Message reçu du client : " + sentence);

                String capitalizedSentence = sentence.toUpperCase();
                byte[] sendData = capitalizedSentence.getBytes();

                DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
                serverSocket.send(sendPacket);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
