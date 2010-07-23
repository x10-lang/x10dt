package org.eclipse.imp.x10dt.ui.parser;

import lpg.runtime.ParseErrorCodes;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.imp.parser.IMessageHandler;

public class MessageHandlerAdapterFilter implements lpg.runtime.IMessageHandler {
	private final IMessageHandler fIMPHandler;
	private final IPath fFilePath;
    
    public MessageHandlerAdapterFilter(IMessageHandler impHandler, IPath filePath) {
        fIMPHandler = impHandler;
        fFilePath = filePath;
    }
    

    public void handleMessage(int errorCode, int[] msgLocation, int[] errorLocation, String filename, String[] errorInfo) {
        if (fIMPHandler == null) // there might be no IMP msg handler if we're parsing on behalf of the structure compare view
            return;
        int startOffset= msgLocation[lpg.runtime.IMessageHandler.OFFSET_INDEX];
        int length= msgLocation[lpg.runtime.IMessageHandler.LENGTH_INDEX];
        int startLine= msgLocation[lpg.runtime.IMessageHandler.START_LINE_INDEX];
        int endLine= msgLocation[lpg.runtime.IMessageHandler.END_LINE_INDEX];
        int startCol= msgLocation[lpg.runtime.IMessageHandler.START_COLUMN_INDEX];
        int endCol= msgLocation[lpg.runtime.IMessageHandler.END_COLUMN_INDEX];
//      String message = MessageFormat.format(ParseErrorCodes.errorMsgText[errorCode], (Object[]) errorInfo);
        String message = ParseErrorCodes.errorMsgText[errorCode] + ":";

        for (int i = 0; i < errorInfo.length; i++)
            message += " " + errorInfo[i];
        if (fFilePath.equals(new Path(filename)))
        fIMPHandler.handleSimpleMessage(message, startOffset, startOffset + length - 1,
                startCol, endCol, startLine, endLine);
    }
}
