package wicket.contrib.mootools.plugins;

import org.apache.wicket.ajax.AjaxRequestTarget;
import org.apache.wicket.markup.ComponentTag;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.apache.wicket.markup.html.basic.Label;
import org.apache.wicket.markup.html.form.Button;
import org.apache.wicket.markup.html.form.TextArea;
import org.apache.wicket.markup.html.panel.Panel;
import org.apache.wicket.model.IModel;
import org.apache.wicket.util.time.Duration;

import wicket.contrib.mootools.behaviors.MFXFadeOutBehavior;

public class MFXEditableMultiLineLabel extends Panel {
	private static final long serialVersionUID = 1L;
	private WebMarkupContainer container;
	private Label label;
	private EditPanel editPanel;

	protected boolean getEscapeStrings() {
		return true;
	}

	protected void onEdit(final AjaxRequestTarget target) {
	}

	protected void onSubmit(final AjaxRequestTarget target) {
	}

	public MFXEditableMultiLineLabel(final String id, final IModel model) {
		super(id, model);

		add(container = new WebMarkupContainer("container"));
		container.setOutputMarkupId(true);

		container.add(label = new Label("label", getModel()));
		label.setEscapeModelStrings(MFXEditableMultiLineLabel.this.getEscapeStrings());
		label.setOutputMarkupId(true);

		label.add(new MFXFadeOutBehavior("ondblclick", Duration.milliseconds(500)) {
			@Override
			protected void onEvent(final AjaxRequestTarget arg0) {
				onEdit(arg0);
				editPanel.setVisible(true);
				label.setVisible(false);
				arg0.addComponent(container);
			}
		});

		container.add(editPanel = new EditPanel("editPanel"));
		editPanel.setVisible(false);
	}

	public class EditPanel extends WebMarkupContainer {
		private TextArea txt;

		public EditPanel(final String id) {
			super(id);

			setOutputMarkupId(true);
			add(txt = new TextArea("editor", new IModel() {

				public Object getObject() {
					return MFXEditableMultiLineLabel.this.getModelObject();
				}

				public void setObject(final Object arg0) {
					MFXEditableMultiLineLabel.this.setModelObject(arg0);
				}

				public void detach() {
				}
			}));
			txt.setOutputMarkupId(true);

			add(new Button("cancel").add(new MFXFadeOutBehavior("onclick", Duration.milliseconds(500), EditPanel.this) {
				@Override
				protected void onEvent(final AjaxRequestTarget arg0) {
					EditPanel.this.setVisible(false);
					label.setVisible(true);
					arg0.addComponent(container);
				}
			}));

			add(new Button("save").add(new MFXFadeOutBehavior("onclick", Duration.milliseconds(500), EditPanel.this) {
				@Override
				protected void onEvent(final AjaxRequestTarget arg0) {
					txt.processInput();
					MFXEditableMultiLineLabel.this.onSubmit(arg0);
					EditPanel.this.setVisible(false);
					label.setVisible(true);
					arg0.addComponent(container);
				}

				@Override
				protected void onComponentTag(final ComponentTag tag) {
					super.onComponentTag(tag);

					String callback = "wicketAjaxPost('" + getCallbackUrl() + "',wicketSerialize(Wicket.$('"
							+ txt.getMarkupId() + "'))";

					tag.put("onclick", "javascript:" + generateCallbackScript(callback));

				}
			}));
		}
	}
}
