/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import GraphAlgo.DFS;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jayad
 */
public class View extends JFrame implements ActionListener,MouseListener{
    //0-not viisted 1-wall 2-visited 9-target position
    public int[][] maze ={ //0-path 1-walls 9-end point
        {1,1,1,1,1,1,1,1,1,1,1,1,1},
        {1,0,1,0,1,0,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,1,0,1,1,1,0,1},
        {1,0,0,0,1,1,1,0,0,0,0,0,1},
        {1,0,1,0,0,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,0,0,1},
        {1,0,1,0,1,0,0,0,1,1,1,0,1},
        {1,0,1,0,1,1,1,0,1,0,1,0,1},
        {1,0,0,0,0,0,0,0,0,0,1,9,1},
        {1,1,1,1,1,1,1,1,1,1,1,1,1},

    };
    
    private int[] target = {8,11};
    private List<Integer> path = new ArrayList<Integer>();
    
    JButton submit,clear,cancel;
    
    public View(){
    this.setTitle("Maze Solver");
    this.setSize(520,500);
    this.setLayout(null);//absolute layout(null) we can put components freely
    this.setLocationRelativeTo(null);//loaction comes in the middle of the screen
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);//closes the process when coss is clicked if it not present closes gui not hte process
    
    submit = new JButton("Show path");
    submit.addActionListener(this);
    submit.setBounds(95,400,100,30);
    
    clear = new JButton("Clear path");
    clear.addActionListener(this);
    clear.setBounds(200,400,100,30);
    
    cancel = new JButton("Cancel");
    cancel.addActionListener(this);
    cancel.setBounds(305,400,100,30);
    
    this.addMouseListener(this);
    this.add(submit);
    this.add(cancel);
    this.add(clear);
    
    }
    //whenever the jframe intitialized paint() method is called inorder to make jframe visisble 
    @Override
    public void paint(Graphics g){ //making the maze visible
        System.out.println("PAINT HERE");
     super.paint(g);//calling paint function in jframe(parent class of view class)
     for(int row=0;row<maze.length;row++){
       for(int col=0;col<maze[row].length;col++){
         Color color;
         switch(maze[row][col]){
             case 1: color = Color.BLACK; break;
             case 9: color = Color.RED; break;
             default : color = Color.WHITE;
         }
         g.setColor(color);
         g.fillRect(40*col, 40*row, 40, 40);
         g.setColor(Color.BLACK);
         g.drawRect(40*col, 40*row, 40, 40);
       }
     }
     
     for(int i=0;i<path.size();i+=2){
         int pathx=path.get(i);
         int pathy=path.get(i+1);
         g.setColor(Color.GREEN);
         g.fillRect(40*pathy, 40*pathx, 40, 40);
         
     }
    }
    
   @Override
   public void actionPerformed(ActionEvent e){
       if(e.getSource() == submit){
           try {
               boolean result = DFS.searchPath(maze,1,1,path);
               System.out.println(result);
               this.repaint();
           } catch (Exception ex) {
               JOptionPane.showMessageDialog(null,ex.toString());
           }
       }
       
       
       if(e.getSource()==cancel){
       int response = JOptionPane.showConfirmDialog(rootPane, "Are you sure you want to exit", "Submit", JOptionPane.YES_NO_OPTION);
       if(response==0){System.exit(0);}//0 yes 1 no
       }
       
       if(e.getSource()==clear){
        for(int row =0;row<maze.length;row++){
            for(int col=0;col<maze[0].length;col++){
                if(maze[row][col] == 2){
                   maze[row][col]=0;
                }
            }
             path.clear();
             this.repaint();
        }
       }
    
   }
   
    @Override
   public void mouseClicked(MouseEvent e){ //1:33:00
        for(int row =0;row<maze.length;row++){
            for(int col=0;col<maze[0].length;col++){
                if(maze[row][col] == 2){
                   maze[row][col]=0;
                }
            }
             path.clear();
        this.repaint();
        }
     if(e.getX()>=0 && e.getX()<=maze[0].length*40 && e.getY()>=0 && e.getY()<=maze.length*40 ){
         int x = e.getY()/40;
         int y = e.getX()/40;
         if(maze[x][y]==1){return;}
         Graphics g = getGraphics();
         g.setColor(Color.WHITE);
         g.fillRect(40*target[1], 40*target[0], 40, 40);
         g.setColor(Color.red);
         g.fillRect(40*y, 40*x, 40, 40);
        for(int row=0;row<maze.length;row++){
       for(int col=0;col<maze[row].length;col++){
         g.setColor(Color.BLACK);
         g.drawRect(40*col, 40*row, 40, 40);}}
         //this.repaint();
         maze[target[0]][target[1]]=0;
         maze[x][y]=9;
         target[0]=x;
         target[1]=y;
     }
   }
    @Override
   public void mousePressed(MouseEvent e){}
    @Override
   public void mouseReleased(MouseEvent e){}
    @Override
   public void mouseEntered(MouseEvent e){}
    @Override
   public void mouseExited(MouseEvent e){}
   
   public static void main(String[] args){
       View view = new View();
       view.setVisible(true);
   }
}
