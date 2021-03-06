/*
    PlayerThread class of Shiritori Framework
    Create by chi on 02/19/2017
    説明: Refereeクラスから生成され、
    各プレイヤが言い出した単語の受付、
    ゲームの判断などを行う。
*/

package codecheck;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;

public class PlayerThread extends Thread {

    private Socket player;
    private ArrayList<Socket> players;
    private int playerID;

    public PlayerThread (Socket player, ArrayList<Socket> players, int playerID) {
        this.player = player;
        this.players = players;
        this.playerID = playerID;
    }

    @Override
    public void run() {

        InputStream is = null;
        OutputStream os = null;

        try {

            String word = null, playerStr = null;
            byte[] b = new byte[1024];

            while(true){
                                
                if(Referee.isGameOver()) {
                    this.player.close();
                    Referee.gameOver();
                }   
                    synchronized(this) {
                        is = player.getInputStream();
                        word = new String(b, 0 , is.read(b));
                        for(Socket toPlayer: players){
                            if(this.player != toPlayer){  
                                os = toPlayer.getOutputStream();
                                os.write(word.getBytes());
                                playerStr = (this.playerID==0) ? "SECOND" : "FIRST";
                            }
                        }

                        Referee.showJudgementResult(word, playerStr);
                    }
            }            
        } catch (SocketException e) {
            try{
                this.player.close();
                is.close();
                os.close();
            }catch(Exception ee){}
            System.exit(0);
            // e.printStackTrace();
        } catch (IOException e) {
            System.out.println("IOException");
            e.printStackTrace();
        }
    }

}