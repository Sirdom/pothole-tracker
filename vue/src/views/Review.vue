<template>
  <div class="review">
    <div id="potholesReview" v-for="potholes in $store.state.potholes" :key="potholes.id">
        <ul id="info">
            <li>Date Created: {{ potholes.dateCreated }}</li>
            <li>Latitude: {{ potholes.latitude }}</li>
            <li>Longitude: {{ potholes.longitude }}</li>
            <li>Pothole ID: {{ potholes.potholeId }}</li>
            <li>Severity: {{ potholes.severity }}</li>
            <li>Current Status: {{ potholes.status }}</li>
            <form>
                <select v-model="potholes.status">
                    <option v-show="potholes.status != 'Scheduled'">Scheduled</option>
                    <option v-show="potholes.status != 'Repaired'">Repaired</option>
                    <option v-show="potholes.status != 'Inspected'">Inspected</option>
                    <option v-show="potholes.status != 'Reported'">Reported</option>
                </select>
                <br>
                <p v-if="potholes.status == 'Inspected'"> Please rate level of severity </p>
                <select v-model="potholes.severity" v-if="potholes.status == 'Inspected'"> 
                    <option disabled value="">Please rate severity of pothole from 1 to 5</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                </select>
                <button v-on:click.prevent="updateSchedule(potholes.status, potholes.severity, potholes)" >Submit New Status</button>
                <button v-on:click.prevent="deletePothole(potholes.potholeId)" v-if="potholes.status == 'Repaired'">Delete Repaired Pothole?</button>
            </form>
            <form v-if="changeToInspected">
                
            </form> 
        </ul>
        <img class="potholePicture" v-if="potholes.picture" v-bind:src="potholes.picture"/>
    </div>
    
  </div>
</template>

<script>
import PotholeService from "../services/PotholeService" 
import moment from "moment";
export default {
    name: "review",
    components: {},
    data: () => ({
        potholes: [{dateCreated: null,
                    latitude: null,
                    longitude: null,
                    potholeId: "",
                    severity: "",
                    status: null,
                   }            
                ],
                message: "",
                changeToInspected: false,
    }),
    
    displayReviews: function() {
            PotholeService.list().then( (response) => {
                this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
        },
    methods: {
        updateSchedule(status, severity, pothole) {
                // console.log(status + ' ' + id);
                const schedule = {
                    potholeId: pothole.potholeId,
                    status: status,
                    dateInspected: null,
                    dateRepaired: null,
                    severity: severity,
                }

                // console.log(schedule);

                if(schedule.status === 'Inspected'){
                    schedule.dateInspected = moment().format('YYYY-MM-DD')
                } else if (schedule.status === 'Repaired'){
                    schedule.dateRepaired = moment().format('YYYY-MM-DD')
                }


				PotholeService.updateSchedule(schedule).then(response => {
                    if (response.status === 200) {
                        alert("Status Submitted");
                    }
				console.log(schedule)
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

            checkForInspection(status){
                if(status === 'Inspected'){
                    this.changeToInspected = true;
                }

            }
        
    },
    created() {
        PotholeService.list().then( (response) => {
				this.$store.commit("ADD_POTHOLES", response.data);
			}).catch(error => {
				if(error.response.status == 400) {
					console.log(error.response.status);
				}
			});
    }, 
    mounted() {
        
    }
}
</script>

<style>

#date {
    justify-items: end;
}
.review {
    position: absolute;
    display: grid;
    grid-template-columns: 1fr 1fr;
    align-items: center;
    top: 8vh;
    font-family: 'Raleway', sans-serif;
    background-color: #FDF5E6;
    width: 100%;
    min-height: 100vh; 
}


#info {
    margin-top: 5%;
}

@media only screen and (max-width: 3440px) {
    #date {
    justify-items: end;
}
.review {
    position: absolute;
    display: grid;
    grid-template-columns: 1fr 1fr;
    align-items: center;
    top: 8vh;
    font-family: 'Raleway', sans-serif;
    background-color: #FDF5E6;
    width: 100%;
    min-height: 100vh; 
}


#info {
    margin-top: 5%;
    width: 100%;
}



#potholesReview {
    display: flex;
    width: 100%; 
 }

.potholePicture {
    display: flex;
    justify-content: center;
    /* width: 50%; */
    height: 160px;
    padding-top: 6%;
    /* padding-right: 20%; 
    padding-left: 10%;  */
    
}

#potholesReview {
    display: flex;
    width: 100vh;
}

}








 @media  only screen and (max-width: 767px) {
     .review {
         display: grid;
         grid-template-columns: 1fr;
         align-items: center;
         top: 9vh;
         padding-top: 40px;
        width: 100vh;
     }
 
     #info {
        display: grid;
        grid-template-columns: 1fr;
        margin-top: 5%;
    }
 
    .potholePicture {
    display: flex;
    justify-content: center;
    width: 100%;
    height: 160px;
    padding-top: 6%;
    padding-right: 20px; 
    /* padding-left: 10%;  */
    
}
 
 }
 
 
 @media  only screen and (max-width: 320px) {
 
    .review {
        display: grid;
        grid-template-columns: 1fr ;
        align-items: center;
        top: 9vh;
        padding-top: 40px;
        width: 100vh;
        height: 175vh;
     }
 
.potholePicture{
    display: flex;
    width: 100%;
    height: 160px;
    padding-top: 6%;
    padding-right: 20%; 
    padding-left: 10%; 
    
 }
    #info {
        display: grid;
        grid-template-columns: 1fr;
        margin-top: 5%;
    }



 
 }



</style>