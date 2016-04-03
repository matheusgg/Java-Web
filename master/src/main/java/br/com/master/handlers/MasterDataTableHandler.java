package br.com.master.handlers;

import javax.faces.view.facelets.ComponentConfig;
import javax.faces.view.facelets.ComponentHandler;
import javax.faces.view.facelets.MetaRuleset;

import com.sun.faces.facelets.tag.MethodRule;

public class MasterDataTableHandler extends ComponentHandler {

	public MasterDataTableHandler(ComponentConfig config) {
		super(config);
	}

	@SuppressWarnings("rawtypes")
	@Override
	protected MetaRuleset createMetaRuleset(Class type) {
		MethodRule methodRule = new MethodRule("paginatorMethod", long.class, new Class[] { long.class, int.class });
		return super.createMetaRuleset(type).addRule(methodRule);
	}

}
