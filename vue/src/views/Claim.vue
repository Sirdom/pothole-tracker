<template>
  <div class = "page">
      <form id="case">
          <br>
          <br>
          <h1 class="claimHeader">Submit a Claim</h1>
          <br>
              <p> Describe the damage:</p><textarea v-model='description'></textarea><br><br>  
				<label for='name'>Name: </label><input type='text' class='claimName' v-model='name'/><br><br>
				<label for='number'>Phone Number: </label><input type='text' v-model='phoneNumber' /><br><br> 
				<label for='email'>Email: </label><input type='email' v-model='email'/><br><br> 
				<label for='date'>Date Occurred: </label> <input type='date' v-model='dateOccurred' /><br><br>
				<label for='year'> Vehicle Year: </label><input type='text' v-model='year'/><br><br>
				<label for='model'>Vehicle Model: </label><input type='text' v-model='vehicleModel'/><br><br> 
				<label for='make'>Vehicle Make: </label><input type='text' v-model='vehicleMake' /><br><br>
				<center><button type='submit' id='claimBtn'  v-on:click.prevent='submitClaim()'>Submit Claim</button></center>;
      </form>
  </div>
</template>

<script>
import PotholeService from '../services/PotholeService'
export default {
    name: 'claim',
    data() {
        return {  
                potholeId: this.$route.params.id,
                description: null,
                name: null,
                phoneNumber: '',
                email: '',
                dateOccurred: '',
                year: '',
                vehicleModel: '',
                vehicleMake:''         
               }

},
        methods: {
            submitClaim(){
                const newClaim = {
                potholeId: this.potholeId,
                description: this.description,
                name: this.name,
                phoneNumber: this.phoneNumber,
                email: this.email,
                dateOccurred: this.dateOccurred,
                year: this.year,
                vehicleModel: this.vehicleModel,
                vehicleMake: this.vehicleMake  
                }
                
               PotholeService.createClaim(newClaim).then(() => {
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
            }
        }
}
</script>

<style scoped>
    @import url('https://fonts.googleapis.com/css2?family=Raleway:wght@300&display=swap');

    *  {  font-family: 'Raleway', sans-serif; }

    .page {
        background-color: #FDF5E6;
        
    }


    #claimBtn {
        display: flex;
  justify-content: center;
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9C27B0, #E040FB);
  border: 0;
  font-size: 15px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
  box-sizing: border-box;
  padding-top: 10px;
  padding-bottom: 10px;
  margin-top: 3%;
  width: 20%;
  height: 6%;
    }


    form{
        padding-left: 70px;
        width: 90%;
        height: 130vh;
    }

    .claimHeader {
        display: flex;
        color: #8C55AA;
        margin-top: 10px;
        padding-top: 50px;
        justify-content: center;
    }

    form input { width: 100%; }

    textarea {
     width: 100%; 
     
    }

    form#case {
    }

    @media  only screen and (max-width: 766px) {

        #claimBtn {
    display: flex;
    font-size: 15px;
    justify-content: center;
    cursor: pointer;
    border-radius: 5em;
    color: #fff;
    background: linear-gradient(to right, #9C27B0, #E040FB);
    border: 0;
    box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
    box-sizing: border-box;
    padding-top: 12px;
    padding-bottom: 15px;
    width: 40%;
    height: 6%;
    }

    .claimHeader {
        margin-top: 10%;
        padding-top: 15%;
    }

    form{
        padding-left: 20px;
        width: 90%;
        /* height: 10vh; */
    }

        
    }


</style>