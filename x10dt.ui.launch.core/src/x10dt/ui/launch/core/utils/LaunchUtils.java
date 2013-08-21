package x10dt.ui.launch.core.utils;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.ui.IWorkbench;

import x10dt.ui.launch.core.LaunchCore;
import x10dt.ui.launch.core.Messages;

public class LaunchUtils {
    public static boolean queryUserToLaunchWithErrors(String message) {
        final String boxMessage = message;
        final IWorkbench workbench = LaunchCore.getInstance().getWorkbench();
        final boolean[] result = new boolean[1];
        workbench.getDisplay().syncExec(new Runnable() {

          public void run() {
            final MessageBox msgBox = new MessageBox(workbench.getActiveWorkbenchWindow().getShell(), SWT.ICON_QUESTION |
                                                                                                      SWT.YES | SWT.NO);
            msgBox.setMessage(boxMessage);
            msgBox.setText(Messages.LCD_LaunchWithErrorsCheck);
            result[0] = (msgBox.open() == SWT.YES);
          }

        });
        return result[0];
      }


}
