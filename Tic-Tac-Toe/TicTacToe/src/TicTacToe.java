 import java.awt.*;
 import java.awt.event.*;
import javax.swing.*;

public class TicTacToe {
    int boardWidth=600;
    int boardHeight=650;

    JFrame frame=new JFrame("Tic-Tac-Toe");
    JLabel textLabel=new JLabel();
    JPanel textPanel=new JPanel();
    JPanel boardPannel=new JPanel();

    JButton[][] board=new JButton[3][3];
    String playerX="X";
    String playerO="O";
    String currentPlayer=playerX;

    boolean gameOver=false;
    int turns =0;


    TicTacToe(){
        frame.setVisible(true);
        frame.setSize(boardWidth,boardHeight);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());
        textLabel.setBackground(Color.DARK_GRAY);
        textLabel.setForeground(Color.WHITE);
        textLabel.setFont(new Font("Arial",Font.BOLD,50));
        textLabel.setHorizontalAlignment(JLabel.CENTER);
        textLabel.setText("Tic-Tac-Toe");
        textLabel.setOpaque(true);

        textPanel.setLayout(new BorderLayout());
        textPanel.add(textLabel);
        frame.add(textPanel,BorderLayout.NORTH);

        boardPannel.setLayout(new GridLayout(3,3));
        boardPannel.setBackground(Color.BLACK);
        frame.add(boardPannel);

        for(int r=0;r<3;r++)
        {
            for(int c=0;c<3;c++)
            {
                JButton tile =new JButton();
                board[r][c]=tile;
                boardPannel.add(tile);

                tile.setBackground(Color.darkGray);
                tile.setForeground(Color.white);
                tile.setFont(new Font("Arial",Font.BOLD,120));
                //tile.setText(currentPlayer);

                tile.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e){
                        if(gameOver) return;
                    JButton tile =(JButton) e.getSource();
                    if(tile.getText()==""){
                    tile.setText(currentPlayer);
                    turns++;
                    checkWinner();
                    if(!gameOver){
                    currentPlayer=currentPlayer==playerX?playerO:playerX;
                    textLabel.setText(currentPlayer+"'s turn.");}}
                }
                });
            }
        }
    }
    void checkWinner()
    {
        for(int r=0;r<3;r++)
        {
            if(board[r][0].getText()=="")continue;
            if(board[r][0].getText()==board[r][1].getText()&&
            board[r][1].getText()==board[r][2].getText()){
                for(int i=0;i<3;i++)
                {
                    setWinner(board[r][i]);
                }
                gameOver=true;
                return;
            }
        }

        //vertical
        for(int c=0;c<3;c++)
        {
            if(board[0][c].getText()=="")continue;
            if(board[0][c].getText()==board[1][c].getText()&&
            board[1][c].getText()==board[2][c].getText()){
                for(int i=0;i<3;i++)
                {
                    setWinner(board[i][c]);
                }
                gameOver=true;
                return;
            }
        }
        //diagnoal
        if(board[0][0].getText()==board[1][1].getText()&&
        board[1][1].getText()==board[2][2].getText()&&
        board[0][0].getText()!=""){
            for(int i=0;i<3;i++)
            {
                setWinner(board[i][i]);
            }
            gameOver=true;
            return;
    }

        // anidiagnol
        if(board[0][2].getText()==board[1][1].getText()&&
        board[1][1].getText()==board[2][0].getText()&&
        board[0][2].getText()!=""){
            setWinner(board[0][2]);
            setWinner(board[1][1]);
            setWinner(board[2][0]);
            gameOver=true;
            return;
    }
    if(turns==9)
    {
        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                setTie(board[i][j]);
            }
        }
        gameOver=true;
    }
    }
    void setWinner(JButton tile)
    {
        tile.setForeground(Color.green);
        tile.setBackground(Color.gray);
        textLabel.setText (currentPlayer+" is the winner" );
    }
    void setTie(JButton tile)
    {
        tile.setForeground(Color.orange);
        tile.setBackground(Color.gray);
        textLabel.setText("Tie!");
    }
}
