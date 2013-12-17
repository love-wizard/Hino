package com.hino.page.internal;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.click.Context;
import org.apache.click.control.ActionLink;
import org.apache.click.control.Checkbox;
import org.apache.click.control.Column;
import org.apache.click.control.Decorator;
import org.apache.click.control.Form;
import org.apache.click.control.HiddenField;
import org.apache.click.control.Option;
import org.apache.click.control.Select;
import org.apache.click.control.Submit;
import org.apache.click.control.Table;
import org.apache.click.control.TextArea;
import org.apache.click.control.TextField;
import org.apache.click.dataprovider.DataProvider;
import org.apache.click.dataprovider.PagingDataProvider;
import org.apache.click.element.JsScript;
import org.apache.click.extras.control.LinkDecorator;
import org.apache.click.util.Bindable;

import com.hino.dto.GeneralGuideAssignDto;
import com.hino.model.Group;
import com.hino.model.Staff;
import com.hino.util.EscapeHtml;
import com.hino.util.Info;
import com.hino.util.TimeFormater;

/**
 * Create and manage external pages' menu items
 */
public class GeneralGuideAssign extends BasePage {
	private static final long serialVersionUID = -5569311868983581758L;
	
	@Bindable private String groupid;
	@Bindable private Form guideAssignForm = new Form("guideassignform");
    @Bindable private Table guideAssignableTable = new Table("guideassignabletable");
    @Bindable private ActionLink editLink = new ActionLink("edit", "编辑", this, "onEditClick");

    @Override
    public void onInit() {
		
		// ----- Display guide assign form -----
    	final Group targetGroup;
    	
    	if(groupid == null) {
    		targetGroup = new Group();
    		targetGroup.setStatus(Info.GS_OPENNING);
    	}
    	else
    		targetGroup = getGuideService().getGroupById(groupid);
    	
    	// Check if target group is in right status
    	if(targetGroup.getStatus() == Info.GS_OPENNING) {
    		
    		TextField groupName = new TextField("groupname", "团名称", 50, false);
    		groupName.setValue(targetGroup.getName());
    		groupName.setDisabled(true);
    		guideAssignForm.add(groupName);
    		
    		TextField groupTime = new TextField("grouptime", "出团时间", 50, false);
    		groupTime.setValue(TimeFormater.format2(targetGroup.getDepart_date()));
    		groupTime.setDisabled(true);
    		guideAssignForm.add(groupTime);
    		
    		TextField groupSeatTaken = new TextField("groupSeatTaken", "确认人数", 50, false);
    		groupSeatTaken.setValue(String.valueOf(targetGroup.getSeats_taken()));
    		groupSeatTaken.setDisabled(true);
    		guideAssignForm.add(groupSeatTaken);
    		
			Select groupGuide1 = new Select("groupGuide1", "导游一", false);
			groupGuide1.setDefaultOption(new Option("-1", "未分配"));
	        DataProvider<Option> guide1DataPrvd = new DataProvider<Option>() {
				private static final long serialVersionUID = 5483961988201550807L;

				public List<Option> getData() {
					Set<Staff> allGuide = targetGroup.getApplying_guide();
					List<Option> Options = new ArrayList<Option>();
					if(allGuide != null)
						for(Staff curGuide : allGuide) {
							Options.add(new Option(curGuide.getId(), EscapeHtml.htmlDecode(curGuide.genFullName())));
						}
	                return Options;
	            }
	        };
	        groupGuide1.setDataProvider(guide1DataPrvd);
	        if(targetGroup.getGuide1() != null) {
		        groupGuide1.setValue(String.valueOf(targetGroup.getGuide1().getId()));
	        }
	        guideAssignForm.add(groupGuide1);
    		
			Select groupGuide2 = new Select("groupGuide2", "导游二", false);
			groupGuide2.setDefaultOption(new Option("-1", "未分配"));
	        DataProvider<Option> guide2DataPrvd = new DataProvider<Option>() {
				private static final long serialVersionUID = 6995231596521477612L;

				public List<Option> getData() {
					Set<Staff> allGuide = targetGroup.getApplying_guide();
					List<Option> Options = new ArrayList<Option>();
					if(allGuide != null)
						for(Staff curGuide : allGuide) {
							Options.add(new Option(curGuide.getId(), EscapeHtml.htmlDecode(curGuide.genFullName())));
						}
	                return Options;
	            }
	        };
	        groupGuide2.setDataProvider(guide2DataPrvd);
	        if(targetGroup.getGuide2() != null) {
	        	groupGuide2.setValue(String.valueOf(targetGroup.getGuide2().getId()));
	        }
	        guideAssignForm.add(groupGuide2);
			
	        Checkbox guideReadyCheck = new Checkbox("guidereadycheck", "确认导游分配");
	        guideReadyCheck.setChecked(targetGroup.getGuide_ready());
			guideAssignForm.add(guideReadyCheck);

			TextArea caution = new TextArea("caution", "出团注意事项", 50, 10, false);
			caution.setValue(EscapeHtml.htmlDecode(targetGroup.getCaution()));
			guideAssignForm.add(caution);
			
	        guideAssignForm.add(new Submit("save", "更新团信息", this, "onSaveClick"));
	        guideAssignForm.add(new Submit("cancel", "取消编辑", this, "onCancelClick"));
			HiddenField groupIdFld = new HiddenField("id", String.class);
			groupIdFld.setValue(groupid);
			guideAssignForm.add(groupIdFld);
    	}
    	else {
    		setRedirect(GeneralGuideAssign.class);
            return;
    	}

		// ----- Display assignable groups table -----
		guideAssignableTable.setClass(Table.CLASS_SIMPLE);
		guideAssignableTable.setWidth("100%");
		guideAssignableTable.setPageSize(10);
		guideAssignableTable.setShowBanner(true);

		Column assignableColumn = new Column("id");
        assignableColumn.setWidth("5%");
        guideAssignableTable.addColumn(assignableColumn);
        
        // Use setDecorator to show Chinese characters and special symbos like < and >
        assignableColumn = new Column("name", "团名称");
        assignableColumn.setWidth("15%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return curGroup.getName(); 
			}
        });
        guideAssignableTable.addColumn(assignableColumn);
        
        assignableColumn = new Column("departdate","出团时间");
        assignableColumn.setWidth("10%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return TimeFormater.format2(curGroup.getDepart_date());
			}
        });
        guideAssignableTable.addColumn(assignableColumn);
        
        assignableColumn = new Column("seatstaken","确认人数");
        assignableColumn.setWidth("10%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return String.valueOf(curGroup.getSeats_taken());
			}
        });
        guideAssignableTable.addColumn(assignableColumn);
        
        assignableColumn = new Column("guideone","导游一");
        assignableColumn.setWidth("10%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				Staff guide1 = curGroup.getGuide1(); 
				return (guide1 == null)?"未分配":guide1.genFullName();
			}
        });
        guideAssignableTable.addColumn(assignableColumn);
        
        assignableColumn = new Column("guidetwo","导游二");
        assignableColumn.setWidth("10%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				Staff guide2 = curGroup.getGuide2(); 
				return (guide2 == null)?"未分配":guide2.genFullName();
			}
        });
        guideAssignableTable.addColumn(assignableColumn);

        assignableColumn = new Column("caution","出团注意事项");
        assignableColumn.setWidth("20%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				String caution = curGroup.getCaution();
				if(caution != null) {
					if(caution.length() > 50) {
						caution = caution.substring(0, 50)+"...";
					}
				} else {
					caution = "";
				}
				return caution;
			}
        });
        guideAssignableTable.addColumn(assignableColumn);
        
        assignableColumn = new Column("guideready","导游状态");
        assignableColumn.setWidth("10%");
        assignableColumn.setDecorator(new Decorator() {
			@Override
			public String render(Object object, Context context) {
				Group curGroup = (Group) object;
				return (curGroup.getGuide_ready())?"YES":"NO";
			}
        });
        guideAssignableTable.addColumn(assignableColumn);
        
        assignableColumn = new Column("action", "操作");
        assignableColumn.setWidth("10%");
        ActionLink[] links = new ActionLink[]{editLink};
        assignableColumn.setDecorator(new LinkDecorator(guideAssignableTable, links, "id"));
        guideAssignableTable.addColumn(assignableColumn);

        // Get groups from database as data provider
        guideAssignableTable.setDataProvider(new PagingDataProvider<Group>() {
			private static final long serialVersionUID = -5763834379543399717L;

			// group data List
			public List<Group> getData() { 
            	int page = guideAssignableTable.getPageNumber();
                int size = guideAssignableTable.getPageSize(); 
                List<Group> pagedList = getGuideService().getPagedAssignableGroupList(page, size);
                return pagedList;
            }

			// Group item data size
			@Override
			public int size() {
				return getGuideService().getAssignableGroupCount();
			} 
        });

    }

	/**
	 * Get redirect to self with group id
	 * @return
	 */
	public boolean onEditClick() {
        Integer id = editLink.getValueInteger();
		getHeadElements().add(
				new JsScript("window.location = './general_guide_assign.htm?groupid="+id+"'"));
        return true;
    }

    /**
     * Update group guide information
     * @return
     */
    public boolean onSaveClick() {
        if (guideAssignForm.isValid()) {
        	GeneralGuideAssignDto guideassigndto = new GeneralGuideAssignDto(); 
        	guideAssignForm.copyTo(guideassigndto);

        	guideassigndto.setCaution(EscapeHtml.htmlEncode(guideassigndto.getCaution()));

        	getGuideService().updateGuideAssign(guideassigndto);
    		
    		// Show javascript message window and redirect the page 
			getHeadElements().add(new JsScript("alert('分派导游操作成功')"));
			getHeadElements().add(
					new JsScript("window.location = './general_guide_assign.htm'"));
        }
        return true;
    }

    /**
     * Redirect to general_guide_assign.htm
     * @return
     */
    public boolean onCancelClick() {
    	setRedirect(GeneralGuideAssign.class);
        return true;
    }

}
