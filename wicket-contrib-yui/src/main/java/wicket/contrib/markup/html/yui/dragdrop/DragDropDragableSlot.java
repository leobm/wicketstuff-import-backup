package wicket.contrib.markup.html.yui.dragdrop;

import java.util.HashMap;
import java.util.Map;

import wicket.Component;
import wicket.contrib.YuiImage;
import wicket.contrib.markup.html.yui.AbstractYuiPanel;
import wicket.extensions.util.resource.PackagedTextTemplate;
import wicket.markup.html.basic.Label;
import wicket.model.AbstractReadOnlyModel;

public class DragDropDragableSlot extends AbstractYuiPanel{

	private static final long serialVersionUID = 1L;
	private int index;
	private YuiImage slot;
	private DragDropSettings settings;
	
	private String javaScriptId;
	
	public DragDropDragableSlot(final String id, final int index, YuiImage slot , DragDropSettings settings){
		super(id);
		this.index=index;
		this.slot= slot;
		this.settings= settings;
		
		Label slotLabel = new Label("dragDropDragableScript", new AbstractReadOnlyModel() {
			private static final long serialVersionUID = 1L;
			public Object getObject(Component component) {
				return getDragDropSlotInitializationScript(id+""+index);
			}
		});
		slotLabel.setEscapeModelStrings(false);
		add(slotLabel);
	}
	
	//Need to add the slot image
	
	protected String getDragDropSlotInitializationScript(String slotId) {
		PackagedTextTemplate template = new PackagedTextTemplate(DragDropDragableSlot.class, "dragdropSlot.js");
		Map<String, Object> variables = new HashMap<String, Object>(3);
		variables.put("javaScriptId", javaScriptId);
		variables.put("id", slotId);
		variables.put("dragableSlot", settings.getDragableSlotList().getId());
		template.interpolate(variables);
		return template.getString();
	}
	
	protected void onAttach() {
		super.onAttach();
		javaScriptId = findParent(DragDropGroup.class).getMarkupId();
	}

	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}

	/**
	 * @return the settings
	 */
	public DragDropSettings getSettings() {
		return settings;
	}

	/**
	 * @param settings the settings to set
	 */
	public void setSettings(DragDropSettings settings) {
		this.settings = settings;
	}

	/**
	 * @return the slot
	 */
	public YuiImage getSlot() {
		return slot;
	}

	/**
	 * @param slot the slot to set
	 */
	public void setSlot(YuiImage slot) {
		this.slot = slot;
	}
	
}
