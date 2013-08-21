package x10dt.ui.contentProposer;

import org.eclipse.jface.internal.text.html.BrowserInformationControlInput;

public class X10BrowserInformationControlInput extends BrowserInformationControlInput {
	public static String PATTERN = "PATTERN"; //placeholder for pattern template (hack)
	String fHtml;
	String fName;
	
	public X10BrowserInformationControlInput(X10BrowserInformationControlInput previous, String html, String name) {
		super(previous);
		fHtml = html;
		fName = name;
	}

	@Override
	public String getHtml() {
		return fHtml;
	}
	
	@Override
	public Object getInputElement() {
		return fHtml;
	}

	@Override
	public String getInputName() {
		return fName;
	}
	
	public void addToHtml(String info) {
		int patIdx = fHtml.indexOf(PATTERN);
		if (patIdx == -1) return; //placeholder has already been replaced
		fHtml = fHtml.substring(0, patIdx) +info + "\n\n\n" + fHtml.substring(patIdx + PATTERN.length() + 1);
		
	}
}
