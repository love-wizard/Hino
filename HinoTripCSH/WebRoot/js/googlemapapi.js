/**
 * Google Map API V3
 * Customized for HinoTravel by Lilei Zhai 2011-02-11
 */

var hinoLatlng = null;
var hinoOptions = null;
var hinoMap = null;

function initialize() {

	//Default Lat
	hinoLatlng = new google.maps.LatLng(52.955093, -1.152868); //Hino Travel: Latitude, Longitude

	//Default map options
	hinoOptions = { 
		zoom: 15, 
		center: hinoLatlng, 
		mapTypeId: google.maps.MapTypeId.ROADMAP,
		scaleControl: true, //Control scale UI components
			
		navigationControlOptions: { 
			style: google.maps.NavigationControlStyle.DEFAULT,
			position: google.maps.ControlPosition.LEFT_TOP
		},
			
		mapTypeControlOptions: {
			style: google.maps.MapTypeControlStyle.DEFAULT,
			position: google.maps.ControlPosition.TOP_RIGHT
		},
					
		scaleControlOptions: {
			position: google.maps.ControlPosition.BOTTOM_CENTER
		}
	}; //hinoOptions

	//Create Map object
	hinoMap = new google.maps.Map(jQuery("#map_canvas")[0], hinoOptions); 

	//Create goHome button
	var mapHomeControlDiv = document.createElement('DIV');
	var hinoHomeController = new HinoHomeController(hinoMap, mapHomeControlDiv, hinoLatlng);
	hinoHomeController.index = 1;
	hinoMap.controls[google.maps.ControlPosition. BOTTOM_CENTER].push(mapHomeControlDiv);
  
	//Create customized marker for HinoTravel
	createMarker(hinoLatlng);
} //--The end of initialize()

//Create new Marker
function createMarker(TargetLat) { 
	var clickedLocation = new google.maps.LatLng(TargetLat); 
	var hinoImage = new google.maps.MarkerImage(
		'./images/googlemap.gif',
		new google.maps.Size(214, 131), //Image size
		new google.maps.Point(0, 0), //Original for image
		new google.maps.Point(36, 127)
	);
	/*var hinoShadow = new google.maps.MarkerImage(
		'./images/hinoshadow.png',
		new google.maps.Size(214, 131), //Image size
		new google.maps.Point(0, 0), //Original for image
		new google.maps.Point(36, 127)
	);*/
	var hinoShape = {
		coord: [33, 5, 33, 25, 85, 25, 85, 5],
	  	type: 'poly'
	};
	var newMarker = new google.maps.Marker({ 
		position: TargetLat,  
		title: '[HinoTravel]',
		icon: hinoImage,
		//shadow: hinoShadow,
		//shape: hinoShape,
		zIndex: 1
	}); 

	//Add Marker to Map
	newMarker.setMap(hinoMap);
}

function HinoHomeController(targetMap, hinoControllerDiv, homeLat) {
	hinoHomeControllerIns = this;
	hinoHomeControllerIns._curHomeLat = homeLat;//Initialize to York
	hinoControllerDiv.style.padding = '5px';
	
	var goHomeDiv = document.createElement('DIV');
	goHomeDiv.innerHTML = '&nbsp;Find Hino Travel&nbsp;';
	goHomeDiv.title = ' Find Hino Travel ';
	goHomeDiv.style.backgroundColor = 'white';
	goHomeDiv.style.borderStyle = 'solid';
	goHomeDiv.style.borderWidth = '2px';
	goHomeDiv.style.textAlign = 'center';
	goHomeDiv.style.cursor = 'pointer';
	hinoControllerDiv.appendChild(goHomeDiv);
	
	google.maps.event.addDomListener(goHomeDiv, 'click', function() {
		targetMap.setCenter(hinoHomeControllerIns.getHomeLat());
	});
	
	HinoHomeController.prototype._curHomeLat = null;
	HinoHomeController.prototype.getHomeLat = function() {
		return this._curHomeLat;
	};
}

jQuery(document).ready(function() {
	initialize();
});
