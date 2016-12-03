/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ifsp.dsi.janela.util;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MessageBox {

    /**
     * MessageBox Ok button option
     */
    public static final int OK_OPTION = JOptionPane.OK_OPTION;

    /**
     * MessageBox Yes button option
     */
    public static final int YES_OPTION = JOptionPane.YES_OPTION;

    /**
     * MessageBox No button option
     */
    public static final int NO_OPTION = JOptionPane.NO_OPTION;

    /**
     * MessageBox Cancel button option
     */
    public static final int CANCEL_OPTION = JOptionPane.CANCEL_OPTION;

    /**
     * MessageBox Closed button option
     */
    public static final int CLOSED_OPTION = JOptionPane.CLOSED_OPTION;

    /**
     * Creates a new instance of MessageBox
     */
    public MessageBox() {
    }

    /**
     * Info MessageBox default with OK button.
     *
     * @param message string for MessageBox
     */
    public static void showInfo(String message) {
        JOptionPane.showMessageDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Informação",
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Information ConfirmDialog with OK Cancel buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.OK_OPTION or JOptionPane.CANCEL_OPTION or
     * JOptionPane.CLOSED_OPTION
     */
    public static int showInfoOkCancel(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Informação",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Information ConfirmDialog with Yes No buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION or
     * JOptionPane.CLOSED_OPTION
     */
    public static int showInfoYesNo(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Informação",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Information ConfirmDialog with Yes No Cancel buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION or
     * JOptionPane.CANCELD_OPTION or JOptionPane.CLOSED_OPTION
     */
    public static int showInfoYesNoCancel(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Informação",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Warning MessageBox default with OK button.
     *
     * @param message string for MessageBox
     */
    public static void showWarning(String message) {
        JOptionPane.showMessageDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Atenção",
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Warning ConfirmDialog with Ok Cancel buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.OK_OPTION or JOptionPane.CANCEL_OPTION or
     * JOptionPane.CLOSED_OPTION
     */
    public static int showWarningOkCancel(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Atenção",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Warning ConfirmDialog with Yes No buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION or
     * JOptionPane.CLOSED_OPTION
     */
    public static int showWarningYesNo(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Atenção",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Warning ConfirmDialog with Yes No Cancel buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION or
     * JOptionPane.CANCEL_OPTION or JOptionPane.CLOSED_OPTION
     */
    public static int showWarningYesNoCancel(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Atenção",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE);
    }

    /**
     * Question ConfirmDialog with Ok Cancel buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.OK_OPTION or JOptionPane.CANCEL_OPTION or
     * JOptionPane.CLOSED_OPTION
     */
    public static int showAskOkCancel(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Atenção",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Question ConfirmDialog with Yes No buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION or
     * JOptionPane.CLOSED_OPTION
     */
    public static int showAskYesNo(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Comfirma ?",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Question ConfirmDialog with Yes No Cancel buttons.
     *
     * @param message string for MessageBox
     * @return JOptionPane.YES_OPTION or JOptionPane.NO_OPTION or
     * JOptionPane.CANCEL_OPTION or JOptionPane.CLOSED_OPTION
     */
    public static int showAskYesNoCancel(String message) {
        return JOptionPane.showConfirmDialog(new JFrame(),
                "<html><body>" + message + "</body></html>",
                "Comfirma ?",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Error MessageDialog default with OK button without details of the
     * exception.
     *
     * @param errorMessage string for Error MessageBox
     */
    public static void showError(String errorMessage) {
        JOptionPane.showMessageDialog(new JFrame(),
                "<html><body>" + errorMessage + "</body></html>",
                "Comfirma ?",
                JOptionPane.ERROR_MESSAGE);
    }

}
