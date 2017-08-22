package pe.com.pps.ui;

import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class MiFeedbackPanel extends FeedbackPanel {

	public MiFeedbackPanel(String id) {
		super(id);
	}

	public MiFeedbackPanel(String id, IFeedbackMessageFilter filter) {
		super(id, filter);
	}

}
