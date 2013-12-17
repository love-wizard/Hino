package com.hino.click.extension;

import java.util.List;

import com.hino.model.Site;
import com.hino.util.Info;

/**
 * This container lists route thumb image and description by 3*n matrix
 */
public class SiteListDiv extends Div{
	private static final long serialVersionUID = 3437232411853743727L;

	public SiteListDiv(String name, List<Site> siteList, boolean isEn)
	{
		super(name);
		this.setCss("row");
		
		int count = 1;
		for(Site curSite : siteList) {
			
			// Create div for single site thumb
			Div divSiteThumb = new Div("divSiteThumb"+count);
			divSiteThumb.setCss("box_img2");
			
			Link aPiroboxGal = new Link("aPiroboxGal"+count);
			aPiroboxGal.setCss("pirobox_gal");
			if(isEn == true)
				aPiroboxGal.setHref("../"+Info.EXTERNAL_PATH_PREFIX+curSite.getImageurl());
			else
				aPiroboxGal.setHref(Info.EXTERNAL_PATH_PREFIX+curSite.getImageurl());
			String desc = (isEn == true)?curSite.getDescription_en():curSite.getDescription();
			aPiroboxGal.setTitle((""+desc).replace("\n","<br />"));
			
			Image imgSiteThumb = new Image();
			if(isEn == true)
				imgSiteThumb.setSrc("../"+Info.EXTERNAL_PATH_PREFIX + curSite.getThumburl_en());
			else
				imgSiteThumb.setSrc(Info.EXTERNAL_PATH_PREFIX + curSite.getThumburl());

			/*
			 * example HTML:
			 *
			 * <div class="box_img2">
			 * <a href="images/site_01.jpg" class="pirobox_gal" title="DESC">
			 * <img src="images/site_thumb_01.png" alt="" title="" />
			 * </a>
			 * </div>
			 */
			aPiroboxGal.add(imgSiteThumb);
			divSiteThumb.add(aPiroboxGal);
			this.add(divSiteThumb);
			
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
			Div divDummySiteThumb = new Div("divDummySiteThumb"+count);
			divDummySiteThumb.setCss("box_img2");
			
			Image imgSiteEmpty = new Image("imgSiteEmpty"+count);
			imgSiteEmpty.setSrc("images/site_thumb_dummy.png");

			/*
			 * example HTML:
			 *
			 * <div class="box_img2">
			 * <img src="images/site_thumb_dummy.png" />
			 * </div>
			 */
			divDummySiteThumb.add(imgSiteEmpty);
			this.add(divDummySiteThumb);
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
