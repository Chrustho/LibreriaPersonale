package GUI;

import command.CommandBase;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Creo un generico bottone che possiede un Command che una volta cliccato esguirà
 */
public class BottoneCommand extends JButton {
    private CommandBase command;

    public BottoneCommand(String nome, CommandBase command) {
        super(nome);
        this.command = command;
        this.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (command != null) {
                    command.execute();
                }
            }
        });
    }

    public void setCommand(CommandBase command) {
        this.command = command;
    }
}
