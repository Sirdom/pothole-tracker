<template>
<div>
	<div id="map" ref="map">
		
			<div id="markers" v-for="marker in $store.state.potholes" :key="marker.id"> 
				<map-marker  :lat="marker.latitude" :lng="marker.longitude"></map-marker>

				<map-info-window v-if="marker.severity == 0" class='zero' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<!-- <img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/> -->
					<img v-if="marker.picture !==null" v-bind:src="marker.picture" width="100%" height="160" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity:<b> Not Inspected</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 5" class='five' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<!-- <img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/> -->
					<img v-if="marker.picture !==null" v-bind:src="marker.picture" width="100%" height="160" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 4" class='four' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<!-- <img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/> -->
					<img v-if="marker.picture !==null" v-bind:src="marker.picture" width="100%" height="160" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 3" class='three' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<!-- <img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/> -->
					<img v-if="marker.picture !==null" v-bind:src="marker.picture" width="100%" height="160" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 2" class='two' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<!-- <img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/> -->
					<img v-if="marker.picture !==null" v-bind:src="marker.picture" width="100%" height="160" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>

				<map-info-window v-if="marker.severity == 1" class='one' :lat="marker.latitude + .00001" :lng="marker.longitude">
					<!-- <img src="../../assets/pothole_sample.jpg" width="100%" height="160"/><br/> -->
					<img v-if="marker.picture !==null" v-bind:src="marker.picture" width="100%" height="160" /><br>
					Date Reported: {{marker.dateCreated}}<br>
					Pothole ID: {{marker.potholeId}}<br>
					Current Status: {{marker.status}}<br>
					Severity: <b>{{marker.severity}}</b><br>
					<button v-on:click="deletePothole(marker.potholeId)" id="deleteBtn" 
					v-show="checkUser()">Delete</button>
					<router-link v-bind:to="{ name: 'claim', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Claim</button>
                    </router-link>
					<router-link v-bind:to="{ name: 'report', params: { id: marker.potholeId }}">
						<button v-show="checkLoggedIn()">Submit Picture</button>
                     </router-link>
				</map-info-window>
			</div>
			<!-- <map-info-window :lat="-23.344" :lng="129.036">
				<span style="background:red;">Test 2</span>
			</map-info-window>
			<map-info-window :lat="-22.344" :lng="128.036">
				<img
					src="https://1.bp.blogspot.com/_S2Wkv5XI8JI/TUL5MbRnT-I/AAAAAAAAACA/g8V_yjojceA/s1600/woot.jpg"
					height="100px"
				/>
			</map-info-window> -->
		</div>
</div>
</template>

<script>
	import MapMarker from "../components/MapMarker"
	import MapInfoWindow from "../components/MapInfoWindow"
	import PotholeService from "../services/PotholeService" 
	import $ from 'jquery'

	window.createReport = function() {
		// console.log(report);
			
	
	}

	export default {
		components: {
			MapMarker,
			MapInfoWindow
		},
		data: () => ({
			map: null,
			markers: [],
			newReportMarker: {
				latitude: null,
				longitude: null,
				//img
			},
			claim:  {
				name: null
			},
			currentPosition: {
				lat: null,
				lng: null
			},
			lat: null,
			lng: null,
			potholeStatus: {
				status: 'unscheduled',
			}
		}),
		created: function() {
			PotholeService.list().then( (response) => {
				this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
			
			PotholeService.getImages().then( response => {
				this.$store.commit("LIST_IMAGES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});

			
		},
		methods: {
			getMap(callback) {
				let vm = this
				function checkForMap() {
					if (vm.map) callback(vm.map)
					else setTimeout(checkForMap, 200)
				}
				checkForMap()
			},
			submitClaim() {
			
			},
			checkUser() {
				if(Object.keys(this.$store.state.user).length == 0 || Object.values(this.$store.state.user.authorities[0]).includes('ROLE_USER')) {
					return false;
				}
				return true;
			},
			checkLoggedIn() {
					if(Object.keys(this.$store.state.user).length == 0) {
						return false;
					}
					return true;
			},
			deletePothole(id) {
				PotholeService.deletePothole(id).then(response => {
					this.$store.commit("DELETE_POTHOLE", response);
					location.reload();
				}).catch(error => {
					if(error.response.status == 400) {
						console.log(error.response.status);
					}
				});
			},
			placeMarker(location) {
				new window.google.maps.Marker({
					position: location, 
					map: this.map
				});
			},
			createReport() {
				PotholeService.createReport(this.newReportMarker).then(() => {
				location.reload();
			}).catch(error => {
				if(error.response) {
					console.log('Error submitting new report.');
				} else if (error.request) {
					console.log("Error submitting new board. Server could not be reached.");
				} else {
					console.log("Error submitting new board. Request could not be created.");
				}
			})
			},
			getLocation() {
				navigator.geolocation.getCurrentPosition(position => {
					console.log('test');
					this.currentPosition = {
						lat: position.coords.latitude,
						lng: position.coords.longitude
					};
				});
			},
			handleLocationError(browserHasGeolocation, infoWindow, pos) {
				infoWindow.setPosition(pos);
				infoWindow.setContent(
					browserHasGeolocation
					? "Error: The Geolocation service failed."
					: "Error: Your browser doesn't support geolocation."
				);
				infoWindow.open(this.map);
			},
			showPosition(position) {
                this.currentPosition = {
                    lat: position.coords.latitude,
                    lng: position.coords.longitude
                }
                this.setPosition();
			},
			setPosition() {
				this.map = new window.google.maps.Map(this.$refs["map"], {
                    center: { lat: this.currentPosition.lat, lng: this.currentPosition.lng },
                    zoom: 15
                });
			},
			simulateClick(el, etype){
				if (el.fireEvent) {
					el.fireEvent('on' + etype);
				}else {
					var evObj = document.createEvent('Events');
					evObj.initEvent(etype, true, false);
					el.dispatchEvent(evObj);
				}
			},
			openClaimForm(pothole) {
				
				
				console.log(pothole.potholeId);
				let cover = document.createElement('div');
				cover.style.height = '100vh';
				cover.style.width = '100vw';
				cover.style.backgroundColor = 'rgba(0,0,0,.5)';
				cover.style.position = 'relative';
				cover.style.zIndex = '9';

				document.getElementById('map').appendChild(cover);



				let form = document.createElement('div');
				form.style.width = '70%';
				form.style.height = '55%';
				form.style.backgroundColor = '#EFEFEF';
				form.style.position = 'fixed';
				form.style.left = '0';
				form.style.right = '0';
				form.style.top = '0';
				form.style.bottom = '0';
				form.style.margin = 'auto';
				form.style.zIndex = '10';
				form.style.padding = '10px';
				form.innerHTML =
				"<h2>Pothole: " + pothole.potholeId + "</h2>" +
				"<p>Describe the damage:</p><textarea style='width: 99.5%;  min-height: 25%;'></textarea><br><br>" + 
				"<label for='name'>Name: </label><input type='text' class='claimName' style='width: 94%;' v-model='claim.name'/><br><br>" +
				"<label for='number'>Phone Number: </label><input type='text' style='width:  88%;' /><br><br>" +
				"<label for='email'>Email: </label><input type='email' style='width:  94%;' /><br><br>" + 
				"<label for='date'>Date Occurred: </label> <input type='date' /><br><br>" + 
				"<label for='year'>Vehicle Year: </label><input type='text' style='width: 89.5%;' /><br><br>"  + 
				"<label for='model'>Vehicle Model: </label><input type='text' style='width: 88%;' /><br><br>" + 
				"<label for='year'>Vehicle Make: </label><input type='text' style='width: 88.5%;' /><br><br>" + 
				"<center><button type='submit' id='claimBtn'  style='font-size: 24px;' onclick='submitClaim()'>Submit Claim</button></center>";



				document.getElementById('map').appendChild(form);
			},
			schedule(id) {
				let schedule = {potholeId: id};
				PotholeService.schedule(schedule).then(response => {
				location.reload();
					console.log(response);
				})   
			},

			checkSeverity(status){
				if (status === 5){
					return 'red';
				} else if (status == 1){
					return 'blue';
				}
			}
			
			
		},

		mounted() {
			
			this.map = new window.google.maps.Map(this.$refs["map"], {
                center: { lat: 39.952465, lng: -75.164062 },
                zoom: 15
			})


			this.$nextTick( () => {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(
						(position) => {
							const pos = {
								lat: position.coords.latitude,
								lng: position.coords.longitude,
							};
							infoWindow.setPosition(pos);
							infoWindow.setContent("Location found.");
							infoWindow.open(this.map);
							this.map.setCenter(pos);
							},
							() => {
								this.handleLocationError(true, infoWindow, this.map.getCenter());
							}
						);
				} else {
					this.handleLocationError(false, infoWindow, this.map.getCenter());
				}
			});

		
				
			let infoWindow = new window.google.maps.InfoWindow();
			const locationButton = document.createElement("button");
			locationButton.id = "panButton";
			locationButton.textContent = "Pan to Current Location";
			locationButton.classList.add("custom-map-control-button");
			this.map.controls[window.google.maps.ControlPosition.TOP_CENTER].push(locationButton);
			locationButton.style.fontSize = "20px";
			locationButton.style.marginTop = "18vh";
			locationButton.style.padding = "5px";
			locationButton.style.boxShadow = "0 2px 6px rgba(0, 0, 0, .3)";
			locationButton.style.textAlign = "center";
			locationButton.style.fontWeight = "normal";
			


			locationButton.addEventListener("click", () => {
				if (navigator.geolocation) {
					navigator.geolocation.getCurrentPosition(
						(position) => {
							const pos = {
								lat: position.coords.latitude,
								lng: position.coords.longitude,
							};
							infoWindow.setPosition(pos);
							infoWindow.setContent("Location found.");
							infoWindow.open(this.map);
							this.map.setCenter(pos);
							},
							() => {
								this.handleLocationError(true, infoWindow, this.map.getCenter());
							}
						);
				} else {
					this.handleLocationError(false, infoWindow, this.map.getCenter());
				}
			});

			
			

				

			// if(navigator.geolocation) {
            //     navigator.geolocation.getCurrentPosition(this.showPosition);
			// } 
			// else {
            // //     this.map = new window.google.maps.Map(this.$refs["map"], {
            // //         center: { lat: 39.952465, lng: -75.164062 },
            // //         zoom: 15
            // //     });
            // //     console.log('not supported');
            // // }
			
			
			// let infoWindow = null;
			if(!Object.keys(this.$store.state.user).length == 0) {
				infoWindow = new window.google.maps.InfoWindow({
					content: "<span style='font-size: 24px;'>Click the map to report a pothole!</span>",
					position: this.map.center,
				});
				console.log(this.map.center);
			} else {
				infoWindow = new window.google.maps.InfoWindow({
					content: "<span style='font-size: 24px;'>Click the map to report a pothole! You must be logged in.</span>",
					position: this.map.center,
				});
				
			}
			infoWindow.open(this.map);
			this.map.addListener('click', (event) => {
				infoWindow.close();
				let loc = JSON.stringify(event.latLng.toJSON());
				//changes JSON to array to access and save in dummy var
				let locArray = JSON.parse(loc);
				this.newReportMarker = {};
				this.newReportMarker.latitude = locArray.lat;
				this.newReportMarker.longitude = locArray.lng;
				this.$store.commit('ADD_REPORT', this.newReportMarker);
				// alert(event.latLng);
				if(this.$store.state.user.authorities.some(name => name.name === 'ROLE_EMPLOYEE' || name.name === 'ROLE_USER' || name.name === 'ROLE_ADMIN')) {
					let contentString = "<span style='font-size: 24px;'>Would you like to submit this pothole report?</span><br>" + 
					"<br><center><input type='button' id='submit-report' value='Submit' onclick='createReport()'> </center>";
					infoWindow = new window.google.maps.InfoWindow({
						content: contentString,
						position: event.latLng,
						ZIndex: 99
					});
				}
				
				infoWindow.open(this.map);
				window.google.maps.event.addListener(infoWindow, 'domready', function() {
					let submitReportBtn = document.getElementById('submit-report');
	
					submitReportBtn.addEventListener('click', () => {
						PotholeService.createReport().then(() => {
							// let authorized = this.$store.state.userIsAuthorized;
							location.reload();
							this.$store.commit("SET_AUTH", true);
						}).catch(error => {
							if(error.response) {
								console.log('Error submitting new report.');
							} else if (error.request) {
								console.log("Error submitting new board. Server could not be reached.");
							} else {
								console.log("Error submitting new board. Request could not be created.");
							}
						})
					})
				})
			});
			$('panButton').click();
			document.querySelectorAll('button').forEach(item => {
				console.log(item);
			})
			document.querySelector('.custom-map-control-button').dispatchEvent(new Event('click'));
					// this.simulateClick(document.getElementById("panButton"), "click");
					// document.getElementById('panButton').click();
		}
	}
</script>

<style>
/* Always set the map height explicitly to define the size of the div
       * element that contains the map. */
#map {
		height: 100vh;
		background: gray; 
		position: relative;
		z-index: 1;
	}

#custom-map-control-button {
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9C27B0, #E040FB);
  border: 0;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
  box-sizing: border-box;
  /* padding-top: 1; */
  width: 100%;
  height: 7%;
}

/* .gm-style .gm-style-iw {
	background-color: #E040FB
} */

.pothole_status.inspected {background-color: aqua;}


.zero{
	border: white
}
.five{
	border: 10px solid #4B0082;
}

.four{
	border: 10px solid #8A2BE2	;
}

.three{
	border: 10px solid #FF00FF	;
}

.two{
	border: 10px solid #EE82EE	
}

.one{
	border: 10px solid #D8BFD8	;
}

</style>