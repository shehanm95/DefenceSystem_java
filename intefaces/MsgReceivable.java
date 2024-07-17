package intefaces;



import javax.swing.JOptionPane;
import javax.swing.JTextArea;

import MainClass.DefenseSystem;
import UIWindows.MessageSender;
import enums.GreenUnitType;

public interface MsgReceivable {
    JTextArea getMsgDisplayArea();
    MessageSender getMessageSender();
    String getSenderName();
    GreenUnitType getGreenUnitType();

    default void sendMessage(MsgReceivable thisUnit,MsgReceivable[] selectedMsgReceivables ){
        MessageSender msgSender =  thisUnit.getMessageSender();
        msgSender.setMsgReceivables(this,selectedMsgReceivables);
        msgSender.setVisible(true);
    };
   
    default void receiveMessage(MsgReceivable sender, JTextArea text){
       if( text.getText().isEmpty()){
        JOptionPane.showMessageDialog(null, "Message Cannot Be Empty !", "Empty Message !", JOptionPane.ERROR_MESSAGE);       
       }
       JTextArea msgDisplayArea = getMsgDisplayArea();
       //msgDisplayArea.setFont(DefenseSystem.monoDefenseFont);
       msgDisplayArea.setText(msgDisplayArea.getText() + "\n" + String.format("%-17s :  %s",sender.getSenderName(), text.getText()));

       text.setText("");
       JOptionPane.showMessageDialog(null, "Your Message Successfully Sent !", "Message sent !", JOptionPane.INFORMATION_MESSAGE);
                
    };




}
