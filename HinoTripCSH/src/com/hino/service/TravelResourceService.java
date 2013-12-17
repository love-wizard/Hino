package com.hino.service;

import java.util.List;

import com.hino.dto.ServiceOptionCAMDto;
import com.hino.dto.DistinguishedGroupCAMDto;
import com.hino.dto.RouteCAMDto;
import com.hino.dto.DiyRouteDto;
import com.hino.dto.ScheduleDto;
import com.hino.dto.SiteCAMDto;
import com.hino.dto.VehicleCAMDto;
import com.hino.model.GroupTag;
import com.hino.model.ServiceOption;
import com.hino.model.DistinguishedGroup;
import com.hino.model.DiyRoute;
import com.hino.model.Route;
import com.hino.model.Schedule;
import com.hino.model.Site;
import com.hino.model.Vehicle;

public interface TravelResourceService {
	public void create_site(SiteCAMDto scamdto);
	public void create_route(RouteCAMDto rcamdto);
	public void create_vehicle(VehicleCAMDto vcamdto);
	public void delete_site(long id);
	public void delete_route(long id);
	public void delete_vehicle(long id);
	public void modify_site(SiteCAMDto scamdto);
	public void modify_route(RouteCAMDto rcamdto);
	public void modify_vehicle(VehicleCAMDto vcamdto);
	public Site getSiteById(long id);
	public Route getRouteById(long id);
	public Vehicle getVehicleById(long id);
	public List<Site> getAllSite();
	public List<Route> getAllRoute();
	public List<Vehicle> getAllVehicle();
	public List<Site> getPagedSiteList(int start, int count);
	public List<Route> getPagedRouteList(int start, int count);
	public List<Vehicle> getPagedVehicleList(int start, int count);
	
	public int getSiteCount();
	public int getRouteCount();
	public int getVehicleCount();
	public void create_dir_route(DiyRouteDto diydto);
	public DiyRoute getDiyRouteById(long id);
	public List<DiyRoute> getPagedDiyRouteList(int page, int size);
	public int getDiyRouteCount();
	
	// Ken Chen 2012/11/01 页面新需求 begin
	public void create_schedule(ScheduleDto scheduleDto);
	public void delete_schedule(long routeId, long scheduleId, long sequenceId);
	public void delete_schedule(long id);
	public void modify_schedule(ScheduleDto scheduleDto);
	public Schedule getScheduleById(long routeId, long scheduleId, long sequenceId);
	public Schedule getScheduleById(long id);
	public List<Schedule> getRouteScheduleList(long routeId);
	public int list_route_schedule_count(long routeId);
	public int getNextScheduleSequenceId(long routeId);
	// Ken Chen 2012/11/01 页面新需求 end
	
	public DistinguishedGroup getDistinguishedGroupById(long id);
	public void deleteDistinguishedGroup(long id);
	public List<DistinguishedGroup> getPagedDistinguishedGroupList(int start, int count);
	
	public void createDistinguishedGroup(DistinguishedGroupCAMDto dgcamdto);
	public void modifyDistinguishedGroup(DistinguishedGroupCAMDto dgcamdto);
	public int getDistinguishedGroupCount();
	public List<ServiceOption> getServiceOptions();
	public int getServiceOptionCount();
	public ServiceOption getServiceOptionById(Integer id);
	public void deleteServiceOption(Integer id);
	public void modifyServiceOption(ServiceOptionCAMDto distGroupOptionDto);
	public void createServiceOption(ServiceOptionCAMDto distGroupOptionDto);
	public List<ServiceOption> getDGServiceOptions(long dgid);
	public int getDGServiceOptionCount(long dgid);
}
