package UIWindows;

import java.awt.GridLayout;
import java.util.Arrays;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;

import MainClass.DefenseSystem;
import intefaces.MsgReceivable;
import support.BackgroundAdder;
import support.DefenseLabel;
import support.ImageButton;
import support.MyCheckBox;

public class MessageSender extends JFrame {
    MsgReceivable[]  msgRBlesToDisplay;
    MsgReceivable[]  msgRBlesToSelect;
    MainController mainController = MainController.getMainController();
    MsgReceivable sender;
    ImageButton sendMessageButton;
    JTextArea messageTextArea;
    Stack<DefenseCheckHolder> holders = new Stack<>();

   

    JPanel allCheckBoxPanel;
   
    public MessageSender(){
        ImageIcon img = new ImageIcon("./images/msg/msgBack.png ");
        BackgroundAdder.addBackground(this,img, img.getIconWidth(), img.getIconHeight());
        initComponents();
    }

    void initComponents(){
        setLayout(null);
        allCheckBoxPanel = new JPanel(new GridLayout(2, 2, 5, 0));
        getContentPane().add(allCheckBoxPanel);
        allCheckBoxPanel.setBounds(getWidth()/7, 65, getWidth()-getWidth()/5, 50);
        allCheckBoxPanel.setOpaque(false);


        sendMessageButton = new ImageButton("./images/msg/msgButton.png");
        getContentPane().add(sendMessageButton);
        sendMessageButton.setBounds(17, 265, 360, 28);
        sendMessageButton.addActionListener((e)->sendMessage());


        messageTextArea =  new JTextArea(20, 40);
        getContentPane().add(messageTextArea);
        messageTextArea.setBounds(32, 145 , 330, 90);
        messageTextArea.setFont(DefenseSystem.defenseFont);
        messageTextArea.setForeground(DefenseSystem.PrimaryfontColor);
        messageTextArea.setOpaque(false);
        messageTextArea.setLineWrap(true);
        

    }

    private void sendMessage() {
        if(messageTextArea.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Message Cannot Be Empty !", "Empty Message !", JOptionPane.ERROR_MESSAGE);       
           return;
        }
      Stack<DefenseCheckHolder> holders = new DefenseCheckHolder().getAllHolders();
    
      for (DefenseCheckHolder defenseCheckHolder : holders) {
        if(defenseCheckHolder.checkBox.isSelected() == true)
            defenseCheckHolder.msgReceivable.receiveMessage(sender,messageTextArea);
      }

       messageTextArea.setText("");
       JOptionPane.showMessageDialog(null, "Your Message Successfully Sent !", "Message sent !", JOptionPane.INFORMATION_MESSAGE);
        
    }

    public void setMsgReceivables(MsgReceivable sender, MsgReceivable[] selectedMsgReceivables) {
        this.sender = sender;
        new DefenseCheckHolder().clearHolder();
        allCheckBoxPanel.removeAll();
        msgRBlesToDisplay = mainController.getMsgReceivables();
        for (MsgReceivable mrb : msgRBlesToDisplay) {
            if(mrb != sender){
            DefenseCheckHolder holder  = new DefenseCheckHolder(new MyCheckBox(mrb.getSenderName(), Arrays.asList(selectedMsgReceivables).contains(mrb)), mrb);
            allCheckBoxPanel.add(holder.checkBox);}
        }

        // setting this message sender window header 
        DefenseLabel header = new DefenseLabel("sending Message From : " + sender.getSenderName(), 16);
        getContentPane().add(header);
        header.setBounds(0, 0, getWidth(), 48);
        header.setHorizontalAlignment(SwingConstants.CENTER);
        messageTextArea.requestFocusInWindow();
    }


     class DefenseCheckHolder{
        MyCheckBox checkBox;
        MsgReceivable msgReceivable;
        public DefenseCheckHolder(MyCheckBox checkBox, MsgReceivable msgReceivable) {
            this.checkBox = checkBox;
            this.msgReceivable = msgReceivable;
            holders.push(this);
            System.out.println("added to stack");
        }
        public DefenseCheckHolder() {}

        public void clearHolder(){
            holders.clear();
        }

        public Stack<DefenseCheckHolder> getAllHolders(){
            return holders;
        }
    }


    public static void autoMessage(MsgReceivable sender , String text){
        MainController.getMainController().receiveMessage(sender,new JTextArea(text));
    }



    


}
