package com.hino.click.extension;

import java.util.Collections;
import java.util.List;

import org.apache.click.control.Label;

import com.hino.model.Route;
import com.hino.model.WebMenuRoute;
import com.hino.util.Info;

/**
 * This container lists route thumb image and description by 3*n matrix
 */
public class RouteListDiv extends Div{
	private static final long serialVersionUID = 8590325807067167053L;

	public RouteListDiv(String name, List<WebMenuRoute> webMenuRouteList, boolean isEn)
	{
		super(name);
		int count = 1;
		
		// Sort routes according to their prioritys
		Collections.sort(webMenuRouteList);
		
		for(WebMenuRoute curMenuRoute : webMenuRouteList) {
			Route curRoute = curMenuRoute.getRoute();
			
			// Create div for single route thumb
			Div divRouteThumb = new Div("divRouteThumb"+count);
			divRouteThumb.setCss("route_thumb");
			
			Link aProduct = new Link("aProduct"+count);
			aProduct.setCss("product");
			aProduct.setHref("route_detail.htm?routeid="+curRoute.getId());
			
			Image imgRouteThumb = new Image();
			
			if(isEn)
				imgRouteThumb.setSrc("../" + Info.EXTERNAL_PATH_PREFIX + curRoute.getThumbUrl());
			else
				imgRouteThumb.setSrc(Info.EXTERNAL_PATH_PREFIX + curRoute.getThumbUrl());
			
			Div divProductTitle = new Div("divProductTitle"+count);
			divProductTitle.setCss("product_title");
			if(isEn)
				divProductTitle.add(new Label("product_title"+count, "&bull;&nbsp;"+curRoute.getName_en()));
			else
				divProductTitle.add(new Label("product_title"+count, "&bull;&nbsp;"+curRoute.getName()));
			
			Div divProductDetail = new Div("divProductDetail"+count);
			divProductDetail.setCss("product_detail");
			if(isEn)
				divProductDetail.add(new Label("product_detail"+count, (""+curRoute.getThumbDesc_en()).replace("\n","<br />")));
			else
				divProductDetail.add(new Label("product_detail"+count, (""+curRoute.getThumbDesc()).replace("\n","<br />")));

			/*
			 * example HTML:
			 *
			 * <div class="route_thumb">
			 * <a class="product" href="#">
			 * <img src="images/thumb.png" />
			 * <div class="product_title">&bull;&nbsp;TITLE</div>
			 * <div class="product_detail">DESC</div>
			 * </a>
			 * </div>
			 */
			aProduct.add(imgRouteThumb);
			aProduct.add(divProductTitle);
			aProduct.add(divProductDetail);
			divRouteThumb.add(aProduct);
			this.add(divRouteThumb);
			
			// Create clear div if current is the third route in this row
			if(count%3 == 0) {
				Div clearDiv = new Div("clearDiv"+count);
				clearDiv.setStyle("clear", "both");
				/*
				 * example HTML:
				 *
				 * <div style="clear: both"></div>
				 */
				this.add(clearDiv);
			}
			
			count++;
		}
		
		// Create dummy route thumb if this row is not full
		while(count%3 != 1) {
			Div divDummyRouteThumb = new Div("divDummyRouteThumb"+count);
			divDummyRouteThumb.setCss("route_thumb");
			
			Div divProductEmpty = new Div("divProductEmpty"+count);
			divProductEmpty.setCss("product_empty");

			/*
			 * example HTML:
			 *
			 * <div class="route_thumb">
			 * <div class="product_empty">
			 * <a class="product" href="#">
			 * </div>
			 * </div>
			 */
			divDummyRouteThumb.add(divProductEmpty);
			this.add(divDummyRouteThumb);
			count++;
		}
		
		// Create clear div again, otherwise parent div will not be long enough
		Div clearDiv = new Div("clearDiv"+count);
		clearDiv.setStyle("clear", "both");
		/*
		 * example HTML:
		 *
		 * <div style="clear: both"></div>
		 */
		this.add(clearDiv);
	}
	
}
