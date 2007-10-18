package org.wicketstuff.yui.examples.pages;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebMarkupContainer;
import org.wicketstuff.yui.behavior.Animation;
import org.wicketstuff.yui.behavior.Attributes;
import org.wicketstuff.yui.behavior.Easing;
import org.wicketstuff.yui.behavior.Effect;
import org.wicketstuff.yui.behavior.OnEvent;
import org.wicketstuff.yui.examples.WicketExamplePage;

/**
 * Example on using Animation...
 * 
 * @author josh
 *
 */
public class AnimationPage extends WicketExamplePage 
{

	public AnimationPage()
	{
		// Shrink Grow Fold UnFold Pulse
		newAnimatedComponent("shrink", 	OnEvent.click, Effect.Type.Shrink, true);
		newAnimatedComponent("grow", 	OnEvent.click, Effect.Type.Grow,   true);
		newAnimatedComponent("fold", 	OnEvent.click, Effect.Type.Fold,   true);
		newAnimatedComponent("unfold", 	OnEvent.click, Effect.Type.UnFold, true);
		newAnimatedComponent("pulse", 	OnEvent.click, Effect.Type.Pulse,  true);

		// Shadow Fade Appear ShakeLR ShakeTB
		newAnimatedComponent("shadow", 	OnEvent.click, Effect.Type.Shadow, true);  // needs position:relative 
		newAnimatedComponent("appear", 	OnEvent.click, Effect.Type.Appear, false);
		newAnimatedComponent("shakelr", OnEvent.click, Effect.Type.ShakeLR, true); // needs position:relative 
		newAnimatedComponent("shaketb", OnEvent.click, Effect.Type.ShakeTB, true); // needs position:relative 
		
		// Attributes
		Attributes delay = new Attributes("delay", "true");
		
		// fade followed by appear
		WebMarkupContainer fade;
		add(fade = new WebMarkupContainer("fade"));
		fade.setOutputMarkupId(true);
		fade.add(new Animation(OnEvent.click)
						.add(new Effect(Effect.Type.Fade, delay))
						.add(new Effect(Effect.Type.Appear, delay)));
		
		// Drop 
		WebMarkupContainer drop;
		add(drop = new WebMarkupContainer("drop"));
		drop.setOutputMarkupId(true);
		drop.add(new Animation(OnEvent.click)
						.add(new Effect(Effect.Type.Drop, delay))
						.add(new Effect(Effect.Type.Appear, delay)));
		
		// TV 
		newAnimatedComponent("tv", OnEvent.click, Effect.Type.TV, true);
		newAnimatedComponent("fade_mouseover", OnEvent.mouseover, Effect.Type.Fade, true);
		
		
		// Blind Up / Down + attributes...
		Attributes ghost_attr = delay.add(new Attributes("ghost", "true"));
		Attributes easing = new Attributes("ease", Easing.elasticBoth.function());
		
		WebMarkupContainer ghost;
		add(ghost = new WebMarkupContainer("ghost"));
		ghost.setOutputMarkupId(true);
		ghost.add(new Animation(OnEvent.click)
						.add(new Effect(Effect.Type.BlindUp,  ghost_attr))
						.add(new Effect(Effect.Type.BlindDown, easing)));
		
		
		// Batch Blind Up / Down / Right / Drop / Appear 
		WebMarkupContainer batch;
		add(batch = new WebMarkupContainer("batch"));
		batch.setOutputMarkupId(true);
		batch.add(new Animation(OnEvent.click)
						.add(new Effect(Effect.Type.BlindUp, delay))
						.add(new Effect(Effect.Type.BlindDown))
						.add(new Effect(Effect.Type.BlindRight, delay))
						.add(new Effect(Effect.Type.Drop))
						.add(new Effect(Effect.Type.Appear, delay)));
		
		// Blind
		newAnimatedComponent("blindup", OnEvent.click, Effect.Type.BlindUp, true);
		newAnimatedComponent("blinddown", OnEvent.click, Effect.Type.BlindDown, true);
		newAnimatedComponent("blindleft", OnEvent.click, Effect.Type.BlindLeft, true);
		newAnimatedComponent("blindright", OnEvent.click, Effect.Type.BlindRight, true);
		
		// Blind Right + with attributes
		WebMarkupContainer blindright;
		add(blindright = new WebMarkupContainer("blindright_binded"));
		blindright.setOutputMarkupId(true);
		
		// set up attributes
		Attributes blindrightAttributes = new Attributes("bind", "'right'");
		blindrightAttributes.add(delay);
		blindright.add(new Animation(OnEvent.click)
						.add(new Effect(Effect.Type.BlindRight, blindrightAttributes))
						.add(new Effect(Effect.Type.Appear, delay)));
		
		// Click for Info ... 
		WebMarkupContainer info;
		add(info = new WebMarkupContainer("info"));
		info.setOutputMarkupId(true);
		

		info.add(new ClickInfoAnimation("clickformore",OnEvent.click).add(new Effect(Effect.Type.BlindDown, delay)));
		info.add(new ClickInfoAnimation("clickformore",OnEvent.click).add(new Effect(Effect.Type.BlindUp, delay)));
//		info.add(new ClickInfoAnimation("clickformore",OnEvent.mouseover).add(new Effect(Effect.Type.BlindDown, ghost_attr)));
//		info.add(new ClickInfoAnimation("clickformore",OnEvent.mouseout).add(new Effect(Effect.Type.BlindUp, ghost_attr)));
	}
	
	/**
	 * create an Animated Component
	 * @param id
	 * @param onEvent
	 * @param effectType
	 * @param delay
	 * @return
	 */
	private Component newAnimatedComponent(String id, OnEvent onEvent, Effect.Type effectType, boolean delay)
	{
		WebMarkupContainer comp;
		comp = new WebMarkupContainer(id);
		Attributes attributes = new Attributes();
		
		if (delay)
		{
			attributes.add("delay", "true");
		}
		
		add(comp);
		comp.setOutputMarkupId(true);
		comp.add(new Animation(onEvent).add(new Effect(effectType, attributes)));
		return comp;
	}

	/**
	 * my Animation Class for displaying the Info Div
	 * @author josh
	 *
	 */
	private class ClickInfoAnimation extends Animation
	{
		private String triggerId;
		
		public ClickInfoAnimation(String trigger_id, OnEvent onEvent)
		{
			super(onEvent);
			this.triggerId = trigger_id;
		}
		
		@Override
		public String getTriggerId()
		{
			return this.triggerId;
		}
		
	}
}
