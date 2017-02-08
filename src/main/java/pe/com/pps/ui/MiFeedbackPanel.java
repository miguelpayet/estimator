package pe.com.pps.ui;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.wicket.feedback.IFeedbackMessageFilter;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.MarkupStream;
import org.apache.wicket.markup.html.panel.FeedbackPanel;

public class MiFeedbackPanel extends FeedbackPanel {

	private static Logger log = LogManager.getLogger(MiFeedbackPanel.class);

	public MiFeedbackPanel(String id) {
		super(id);
	}

	public MiFeedbackPanel(String id, IFeedbackMessageFilter filter) {
		super(id, filter);
	}

	@Override
	protected void onBeforeRender() {
		super.onBeforeRender();
		log.info("onBeforeRender");
	}

	@Override
	public void onComponentTagBody(MarkupStream markupStream, ComponentTag openTag) {
		super.onComponentTagBody(markupStream, openTag);
		log.info("onComponentTagBody");
	}

}
