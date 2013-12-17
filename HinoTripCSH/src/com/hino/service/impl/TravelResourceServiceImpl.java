package com.hino.service.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import com.hino.dao.ServiceOptionDAO;
import com.hino.dao.DistinguishedGroupDAO;
import com.hino.dao.DiyRouteDAO;
import com.hino.dao.RouteDAO;
import com.hino.dao.ScheduleDAO;
import com.hino.dao.SiteDAO;
import com.hino.dao.VehicleDAO;
import com.hino.dto.ServiceOptionCAMDto;
import com.hino.dto.DistinguishedGroupCAMDto;
import com.hino.dto.DiyRouteDto;
import com.hino.dto.RouteCAMDto;
import com.hino.dto.ScheduleDto;
import com.hino.dto.SiteCAMDto;
import com.hino.dto.VehicleCAMDto;
import com.hino.model.ServiceOption;
import com.hino.model.DistinguishedGroup;
import com.hino.model.DiyRoute;
import com.hino.model.Route;
import com.hino.model.Schedule;
import com.hino.model.Site;
import com.hino.model.Vehicle;
import com.hino.service.TravelResourceService;
import com.hino.util.FileCenter;

public class TravelResourceServiceImpl implements TravelResourceService {
	private SiteDAO siteDao;
	private RouteDAO routeDao;
	private DiyRouteDAO diyRouteDao;
	private VehicleDAO vehicleDao;
	private ScheduleDAO scheduleDao;
	private DistinguishedGroupDAO distinguishedGroupDao;
	private ServiceOptionDAO serviceOptionDao;
	
	public ServiceOptionDAO getServiceOptionDao() {
		return serviceOptionDao;
	}

	public void setServiceOptionDao(ServiceOptionDAO serviceOptionDao) {
		this.serviceOptionDao = serviceOptionDao;
	}

	public DistinguishedGroupDAO getDistinguishedGroupDao() {
		return distinguishedGroupDao;
	}

	public void setDistinguishedGroupDao(DistinguishedGroupDAO distinguishedGroupDao) {
		this.distinguishedGroupDao = distinguishedGroupDao;
	}

	public VehicleDAO getVehicleDao() {
		return vehicleDao;
	}

	public void setVehicleDao(VehicleDAO vehicleDao) {
		this.vehicleDao = vehicleDao;
	}

	private FileCenter fileCenter;// = new FileCenter();

	public DiyRouteDAO getDiyRouteDao() {
		return diyRouteDao;
	}

	public void setDiyRouteDao(DiyRouteDAO diyRouteDao) {
		this.diyRouteDao = diyRouteDao;
	}

	public FileCenter getFileCenter() {
		return fileCenter;
	}

	public void setFileCenter(FileCenter fileCenter) {
		this.fileCenter = fileCenter;
	}

	public SiteDAO getSiteDao() {
		return siteDao;
	}

	public void setSiteDao(SiteDAO siteDao) {
		this.siteDao = siteDao;
	}

	public RouteDAO getRouteDao() {
		return routeDao;
	}

	public void setRouteDao(RouteDAO routeDao) {
		this.routeDao = routeDao;
	}

	@Override
	public void create_site(SiteCAMDto scamdto) {
		Site s = new Site();
		s.setName(scamdto.getSitename());
		s.setName_en(scamdto.getSitename_en());
		s.setDescription_en(scamdto.getSitedesc_en());
		s.setDescription(scamdto.getSitedesc());

		if (scamdto.getFullimgfile() != null) {
			try {
				s.setImageurl(fileCenter.save_file(scamdto.getFullimgfile(),
						false));

			} catch (IOException e) {

				e.printStackTrace();
			}

		}

		if (scamdto.getCompimgfile() != null) {
			try {
				s.setThumburl(fileCenter.save_file(scamdto.getCompimgfile(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		if (scamdto.getCompimgfile_en() != null) {
			try {
				s.setThumburl_en(fileCenter.save_file(scamdto.getCompimgfile_en(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		siteDao.save(s);

	}

	@Override
	public void delete_site(long id) {
		siteDao.delete(id);
	}

	@Override
	public Route getRouteById(long id) {
		return routeDao.getRouteById(id);
	}

	@Override
	public Site getSiteById(long id) {
		return siteDao.getSiteById(id);
	}

	@Override
	public void modify_site(SiteCAMDto scamdto) {
		Site s = getSiteById(scamdto.getId());
		s.setName(scamdto.getSitename());
		s.setDescription(scamdto.getSitedesc());
		s.setName_en(scamdto.getSitename_en());
		s.setDescription_en(scamdto.getSitedesc_en());

		if (scamdto.getFullimgfile() != null) {
			try {
				s.setImageurl(fileCenter.save_file(scamdto.getFullimgfile(),
						false));

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		if (scamdto.getCompimgfile() != null) {
			try {
				s.setThumburl(fileCenter.save_file(scamdto.getCompimgfile(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		// System.out.println(scamdto.getCompimgfile()==null);

		if (scamdto.getCompimgfile_en() != null) {
			try {
				s.setThumburl_en(fileCenter.save_file(scamdto.getCompimgfile_en(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		siteDao.update(s);

	}

	@Override
	public List<Route> getAllRoute() {
		return routeDao.list_all_route();
	}

	@Override
	public List<Site> getAllSite() {
		return siteDao.list_all_site();
	}

	@Override
	public List<Site> getPagedSiteList(int start, int count) {
		return siteDao.list_site_by_paging(start, count);
	}

	@Override
	public int getRouteCount() {
		return routeDao.getRouteCount();
	}

	@Override
	public int getSiteCount() {
		return siteDao.getSiteCount();
	}

	@Override
	public void create_route(RouteCAMDto rcamdto) {
		Route r = new Route();
		r.setName(rcamdto.getRoutename());
		r.setSchedule(rcamdto.getRoutedesc());
		r.setService(rcamdto.getRouteservice());
		r.setThumbDesc(rcamdto.getCompdesc());
		r.setHint(rcamdto.getRoutetips());
		
		r.setName_en(rcamdto.getRoutename_en());
		r.setSchedule_en(rcamdto.getRoutedesc_en());
		r.setService_en(rcamdto.getRouteservice_en());
		r.setThumbDesc_en(rcamdto.getCompdesc_en());
		r.setHint_en(rcamdto.getRoutetips_en());
		if(null == rcamdto.getOriginalRoute()) {
			rcamdto.setOriginalRoute(r);
		}
		r.setOriginalRoute(rcamdto.getOriginalRoute());
		r.setPickup_info(rcamdto.getPickup_info());
		r.setCharacteristic(rcamdto.getCharacteristic());

		List<String> ls = rcamdto.getSitelist();
		List<Site> lst = new ArrayList<Site>();
		Site temp;
		for (int i = 0; i < ls.size(); i++) {
			temp = new Site();
			temp.setId(Integer.parseInt(ls.get(i)));
			lst.add(temp);
		}

		r.setSitelist(lst);
		if (rcamdto.getFullimgfile() != null) {
			try {
				r.setImageUrl(fileCenter.save_file(rcamdto.getFullimgfile(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		if (rcamdto.getCompimgfile() != null) {
			try {
				r.setThumbUrl(fileCenter.save_file(rcamdto.getCompimgfile(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Ken Chen 2012/11/01 页面新需求 begin
		r.setScatisfaction(rcamdto.getScatisfaction());
		if (rcamdto.getRouteMap() != null) {
			try {
				r.setRouteMap(fileCenter.save_file(rcamdto.getRouteMap(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		if (rcamdto.getRouteMapThumbl() != null) {
			try {
				r.setRouteMapThumbl(fileCenter.save_file(rcamdto.getRouteMapThumbl(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl1() != null) {
			try {
				r.setImageUrl1(fileCenter.save_file(rcamdto.getImageUrl1(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl2() != null) {
			try {
				r.setImageUrl2(fileCenter.save_file(rcamdto.getImageUrl2(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl3() != null) {
			try {
				r.setImageUrl3(fileCenter.save_file(rcamdto.getImageUrl3(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl4() != null) {
			try {
				r.setImageUrl4(fileCenter.save_file(rcamdto.getImageUrl4(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		// Ken Chen 2012/11/01 页面新需求 end
		
		routeDao.save(r);
	}

	@Override
	public void delete_route(long id) {
		routeDao.delete(id);

	}

	@Override
	public List<Route> getPagedRouteList(int start, int count) {
		return routeDao.list_route_by_paging(start, count);
	}

	@Override
	public void modify_route(RouteCAMDto rcamdto) {
		Route r = routeDao.getRouteById(rcamdto.getRouteid());
		r.setName(rcamdto.getRoutename());
		r.setSchedule(rcamdto.getRoutedesc());
		r.setService(rcamdto.getRouteservice());
		r.setThumbDesc(rcamdto.getCompdesc());
		r.setHint(rcamdto.getRoutetips());
		
		r.setName_en(rcamdto.getRoutename_en());
		r.setSchedule_en(rcamdto.getRoutedesc_en());
		r.setService_en(rcamdto.getRouteservice_en());
		r.setThumbDesc_en(rcamdto.getCompdesc_en());
		r.setHint_en(rcamdto.getRoutetips_en());
		if(null == rcamdto.getOriginalRoute()) {
			rcamdto.setOriginalRoute(r);
		}
		r.setOriginalRoute(rcamdto.getOriginalRoute());
		r.setPickup_info(rcamdto.getPickup_info());
		r.setCharacteristic(rcamdto.getCharacteristic());
		

		List<String> ls = rcamdto.getSitelist();
		List<Site> lst = new ArrayList<Site>();
		Site temp;
		for (int i = 0; i < ls.size(); i++) {
			temp = new Site();
			temp.setId(Integer.parseInt(ls.get(i)));
			lst.add(temp);
		}

		r.setSitelist(lst);
		if (rcamdto.getFullimgfile() != null) {
			try {
				r.setImageUrl(fileCenter.save_file(rcamdto.getFullimgfile(),
						false));

			} catch (IOException e) {

				e.printStackTrace();
			}
		}

		if (rcamdto.getCompimgfile() != null) {
			try {
				r.setThumbUrl(fileCenter.save_file(rcamdto.getCompimgfile(),
						false));
			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		// Ken Chen 2012/11/01 页面新需求 begin
		r.setScatisfaction(rcamdto.getScatisfaction());
		if (rcamdto.getRouteMap() != null) {
			try {
				r.setRouteMap(fileCenter.save_file(rcamdto.getRouteMap(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (rcamdto.getRouteMapThumbl() != null) {
			try {
				r.setRouteMapThumbl(fileCenter.save_file(rcamdto.getRouteMapThumbl(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl1() != null) {
			try {
				r.setImageUrl1(fileCenter.save_file(rcamdto.getImageUrl1(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl2() != null) {
			try {
				r.setImageUrl2(fileCenter.save_file(rcamdto.getImageUrl2(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl3() != null) {
			try {
				r.setImageUrl3(fileCenter.save_file(rcamdto.getImageUrl3(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		if (rcamdto.getImageUrl4() != null) {
			try {
				r.setImageUrl4(fileCenter.save_file(rcamdto.getImageUrl4(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Ken Chen 2012/11/01 页面新需求 end
		
		routeDao.update(r);

	}

	@Override
	public void create_dir_route(DiyRouteDto diydto) {
		DiyRoute diyRoute = new DiyRoute();
		diyRoute.setName(diydto.getName());
		diyRoute.setEmail(diydto.getEmail());
		diyRoute.setPhone(diydto.getPhone());
		diyRoute.setDescription(diydto.getDescription());
		diyRoute.setDiy_time(Calendar.getInstance());

		diyRouteDao.save(diyRoute);
	}

	@Override
	public DiyRoute getDiyRouteById(long id) {
		return diyRouteDao.getDiyRouteById(id);
	}

	@Override
	public List<DiyRoute> getPagedDiyRouteList(int page, int size) {
		return diyRouteDao.list_diy_route_by_paging(page, size);
	}

	@Override
	public int getDiyRouteCount() {
		return diyRouteDao.getDiyRouteCount();
	}

	@Override
	public void create_vehicle(VehicleCAMDto vcamdto) {
		Vehicle v = new Vehicle();
		v.setName(vcamdto.getVehiclename());
		v.setName_en(vcamdto.getVehicledesc_en());
		v.setDesci(vcamdto.getVehicledesc());
		v.setDesci_en(vcamdto.getVehicledesc_en());
		
		if (vcamdto.getFullimgfile() != null) {
			try {
				v.setImg_path(fileCenter.save_file(vcamdto.getFullimgfile(),
						false));

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		vehicleDao.save(v);
		
	}

	@Override
	public void delete_vehicle(long id) {
		vehicleDao.delete(id);
		
	}

	@Override
	public void modify_vehicle(VehicleCAMDto vcamdto) {
		Vehicle v = getVehicleById(vcamdto.getId());
		v.setName(vcamdto.getVehiclename());
		v.setName_en(vcamdto.getVehicledesc_en());
		v.setDesci(vcamdto.getVehicledesc());
		v.setDesci_en(vcamdto.getVehicledesc_en());
		
		if (vcamdto.getFullimgfile() != null) {
			try {
				v.setImg_path(fileCenter.save_file(vcamdto.getFullimgfile(),
						false));

			} catch (IOException e) {

				e.printStackTrace();
			}
		}
		
		vehicleDao.update(v);
		
	}

	@Override
	public List<Vehicle> getPagedVehicleList(int start, int count) {
		return vehicleDao.list_vehicle_by_paging(start, count);
	}

	@Override
	public Vehicle getVehicleById(long id) {
		return vehicleDao.getVehicleById(id);
	}

	@Override
	public int getVehicleCount() {
		return vehicleDao.getVehicleCount();
	}

	@Override
	public List<Vehicle> getAllVehicle() {
		return vehicleDao.list_all_vehicle();
	}

	@Override
	public void create_schedule(ScheduleDto scheduleDto) {
		Schedule schedule = new Schedule();
		schedule.setRoute_id(scheduleDto.getRoute_id());
		schedule.setSequence_id(scheduleDao.getNextScheduleSequenceId(scheduleDto.getRoute_id()));
		
		schedule.setTitle(scheduleDto.getTitle());
		schedule.setTitle_desc(scheduleDto.getTitle_desc());
		schedule.setSchedule_desc(scheduleDto.getSchedule_desc());
		schedule.setBreakfast(scheduleDto.getBreakfast());
		schedule.setLunch(scheduleDto.getLunch());
		schedule.setDinner(scheduleDto.getDinner());
		schedule.setTransport(scheduleDto.getTransport());
		schedule.setHotel(scheduleDto.getHotel());
		schedule.setFlight(scheduleDto.getFlight());


		if (scheduleDto.getMaterial_1() != null) {
			try {
				schedule.setMaterial_1(fileCenter.save_file(scheduleDto.getMaterial_1(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (scheduleDto.getMaterial_2() != null) {
			try {
				schedule.setMaterial_2(fileCenter.save_file(scheduleDto.getMaterial_2(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		if (scheduleDto.getMaterial_3() != null) {
			try {
				schedule.setMaterial_3(fileCenter.save_file(scheduleDto.getMaterial_3(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		schedule.setMaterial_1_type(scheduleDto.getMaterial_1_type());
		schedule.setMaterial_2_type(scheduleDto.getMaterial_2_type());
		schedule.setMaterial_3_type(scheduleDto.getMaterial_3_type());
		
		scheduleDao.save(schedule);
	}

	@Override
	public void delete_schedule(long routeId, long scheduleId, long sequenceId) {
		scheduleDao.delete(routeId, sequenceId);
	}

	@Override
	public void modify_schedule(ScheduleDto scheduleDto) {
		Schedule schedule = scheduleDao.getScheduleById(scheduleDto.getId());
		
		schedule.setId(scheduleDto.getId());
		schedule.setRoute_id(scheduleDto.getRoute_id());
		schedule.setSequence_id(scheduleDto.getSequence_id());
		
		schedule.setTitle(scheduleDto.getTitle());
		schedule.setTitle_desc(scheduleDto.getTitle_desc());
		schedule.setSchedule_desc(scheduleDto.getSchedule_desc());
		schedule.setBreakfast(scheduleDto.getBreakfast());
		schedule.setLunch(scheduleDto.getLunch());
		schedule.setDinner(scheduleDto.getDinner());
		schedule.setTransport(scheduleDto.getTransport());
		schedule.setHotel(scheduleDto.getHotel());
		schedule.setFlight(scheduleDto.getFlight());
		
		if (scheduleDto.getMaterial_1() != null) {
			try {
				schedule.setMaterial_1(fileCenter.save_file(scheduleDto.getMaterial_1(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		if (scheduleDto.getMaterial_2() != null) {
			try {
				schedule.setMaterial_2(fileCenter.save_file(scheduleDto.getMaterial_2(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		
		if (scheduleDto.getMaterial_3() != null) {
			try {
				schedule.setMaterial_3(fileCenter.save_file(scheduleDto.getMaterial_3(),
						false));

			} catch (IOException e) {
				e.printStackTrace();
			}

		}
		schedule.setMaterial_1_type(scheduleDto.getMaterial_1_type());
		schedule.setMaterial_2_type(scheduleDto.getMaterial_2_type());
		schedule.setMaterial_3_type(scheduleDto.getMaterial_3_type());
		
		scheduleDao.update(schedule);
	}

	@Override
	public Schedule getScheduleById(long routeId, long scheduleId,
			long sequenceId) {
		return scheduleDao.getScheduleById(routeId, sequenceId);
	}

	@Override
	public List<Schedule> getRouteScheduleList(long routeId) {
		return scheduleDao.list_route_schedule(routeId);
	}

	public ScheduleDAO getScheduleDao() {
		return scheduleDao;
	}

	public void setScheduleDao(ScheduleDAO scheduleDao) {
		this.scheduleDao = scheduleDao;
	}

	@Override
	public void delete_schedule(long id) {
		scheduleDao.delete(id);
	}

	@Override
	public Schedule getScheduleById(long id) {
		return scheduleDao.getScheduleById(id);
	}

	@Override
	public int list_route_schedule_count(long routeId) {
		return scheduleDao.list_route_schedule_count(routeId);
	}

	@Override
	public int getNextScheduleSequenceId(long routeId) {
		return scheduleDao.getNextScheduleSequenceId(routeId);
	}

	@Override
	public DistinguishedGroup getDistinguishedGroupById(long id) {		
		return distinguishedGroupDao.getDistinguishedGroupById(id);
	}

	@Override
	public void deleteDistinguishedGroup(long id) {
		distinguishedGroupDao.delete(id);
	}

	@Override
	public List<DistinguishedGroup> getPagedDistinguishedGroupList(int start,
			int count) {
		return distinguishedGroupDao.listDistinguishedGroupByPaging(start, count);
	}

	@Override
	public void createDistinguishedGroup(DistinguishedGroupCAMDto dgcamdto) {
		DistinguishedGroup dg = new DistinguishedGroup();
		dg.setTitle(dgcamdto.getTitle());
		dg.setDescription(dgcamdto.getDescription());
		dg.setPrice(dgcamdto.getPrice());
		dg.setRoute(dgcamdto.getRoute());
		dg.setVisible(dgcamdto.isVisible());
		if (dgcamdto.getImage() != null) {
			try {
				dg.setImage(fileCenter.save_file(dgcamdto.getImage(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		distinguishedGroupDao.save(dg);
	}

	@Override
	public void modifyDistinguishedGroup(DistinguishedGroupCAMDto dgcamdto) {
		DistinguishedGroup dg = distinguishedGroupDao.getDistinguishedGroupById(dgcamdto.getId());
		dg.setId(dgcamdto.getId());
		dg.setTitle(dgcamdto.getTitle());
		dg.setDescription(dgcamdto.getDescription());
		dg.setPrice(dgcamdto.getPrice());
		dg.setRoute(dgcamdto.getRoute());
		dg.setVisible(dgcamdto.isVisible());
		if (dgcamdto.getImage() != null) {
			try {
				dg.setImage(fileCenter.save_file(dgcamdto.getImage(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		distinguishedGroupDao.update(dg);
	}

	@Override
	public int getDistinguishedGroupCount() {	
		return distinguishedGroupDao.getDistinguishedGroupCount();
	}

	@Override
	public List<ServiceOption> getServiceOptions() {
		return serviceOptionDao.listServiceOptions();
	}
	
	@Override
	public List<ServiceOption> getDGServiceOptions(long dgid) {
		return serviceOptionDao.listDGServiceOptions(dgid);
	}

	@Override
	public int getServiceOptionCount() {
		return serviceOptionDao.getServiceOptionCount();
	}
	
	@Override
	public int getDGServiceOptionCount(long dgid) {
		return serviceOptionDao.getDGServiceOptionCount(dgid);
	}

	@Override
	public ServiceOption getServiceOptionById(Integer id) {
		return serviceOptionDao.getServiceOptionById(id);
	}

	@Override
	public void deleteServiceOption(Integer id) {
		serviceOptionDao.delete(id);
	}

	@Override
	public void modifyServiceOption(ServiceOptionCAMDto soDto) {
		ServiceOption so = serviceOptionDao.getServiceOptionById(soDto.getId());
		so.setId(soDto.getId());
		so.setTitle(soDto.getTitle());
		so.setDescription(soDto.getDescription());
		if (soDto.getImage() != null ) {
			try {
				so.setImage(fileCenter.save_file(soDto.getImage(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			
		}
		serviceOptionDao.update(so);
	}

	@Override
	public void createServiceOption(ServiceOptionCAMDto soDto) {
		ServiceOption so = new ServiceOption();
		so.setTitle(soDto.getTitle());
		so.setDescription(soDto.getDescription());
		if (soDto.getImage() != null ) {
			try {
				so.setImage(fileCenter.save_file(soDto.getImage(),
						false));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		serviceOptionDao.save(so);
	}
}
