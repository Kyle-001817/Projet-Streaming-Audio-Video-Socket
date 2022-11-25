package client;

import java.io.*;
import java.net.*;
import javax.sound.sampled.*;
import javazoom.jl.player.Player;


public class AudioClient {
    public static void main(String[] args) throws Exception {
        if (args.length > 0) {
            
            File soundFile = AudioUtil.getSoundFile(args[0]);
            Player jlPlayer;
            System.out.println("Client: " + soundFile);

            try {
                FileInputStream fileInputStream = new FileInputStream(soundFile);
                BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
                jlPlayer = new Player(bufferedInputStream);
                jlPlayer.play();
            }
            catch(Exception e){
                System.out.println(e.getMessage()); 
            }
        }
        else {
                System.out.println("Client: reading from localhost:6666");
                    try
                    {
                        Socket socket = new Socket("localhost", 6666);
                        if (socket.isConnected()) 
                        {
                            BufferedInputStream bufferedInputStream = new BufferedInputStream(socket.getInputStream());
                            Player jlPlayer = new Player(bufferedInputStream);
                            jlPlayer.play();
                        }
                    }
                    catch(Exception e)
                    {
                        System.out.println(e.getMessage()); 
                    }
        }
        System.out.println("Client: end");
    }
}