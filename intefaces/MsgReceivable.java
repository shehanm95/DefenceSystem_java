package intefaces;



import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import MainClass.DefenseSystem;
import UIWindows.MessageSender;
import enums.GreenUnitType;
import support.DefenseLabel;

public interface MsgReceivable {
    JTextArea getMsgDisplayArea();
    MessageSender getMessageSender();
    String getSenderName();
    GreenUnitType getGreenUnitType();
    DefenseLabel getNoMessageLabel();

    default void sendMessage(MsgReceivable thisUnit,MsgReceivable[] selectedMsgReceivables ){
        MessageSender msgSender =  thisUnit.getMessageSender();
        msgSender.setMsgReceivables(this,selectedMsgReceivables);
        msgSender.setVisible(true);
    };
   
    default void receiveMessage(MsgReceivable sender, JTextArea text){
       
       JTextArea msgDisplayArea = getMsgDisplayArea();
       getNoMessageLabel().setVisible(false);
       //msgDisplayArea.setFont(DefenseSystem.monoDefenseFont);
       if(msgDisplayArea.getText().length() == 0) msgDisplayArea.setText(String.format("%-19s :  %s",sender.getSenderName(), text.getText()));
       else msgDisplayArea.setText(msgDisplayArea.getText() + "\n" + String.format("%-19s :  %s",sender.getSenderName(), text.getText()));

              
    };







}
