<template>
    <div id="report">
        <h1 class="potholePhotoUpload">Upload Pothole Photo</h1>
        <input class="pictureFile" type="file" id="file"  ref="fileInput" />
        <button class="pictureSubmit" type="submit" v-on:click="submitPicture()">Submit</button>
    </div>
</template>

<script>
import PotholeService from '../services/PotholeService'
export default {    
    data: () => ({
        potholeId: this.$route.params.id,
    }),
    methods: {
        submitPicture() {
            let formData  = new FormData();
            formData.append('file', this.$refs.fileInput.files[0]);


                const options = {
                    headers: {
                        'Content-Type': 'multipart/form-data'
                    }
                }



                // console.log(schedule);

        


				PotholeService.submitPicture(formData, options, this.$route.params.id).then((response) => {
                        if (response.status === 200) {
                            alert("Picture Submitted");
                        }
                    }).catch(error => {
                    if(error.response) {
                        console.log('Error submitting new report.');
                    } else if (error.request) {
                        console.log("Error submitting new board. Server could not be reached.");
                    } else {
                        console.log("Error submitting new board. Request could not be created.");
                    }
                })
                this.$router.push("/");
                location.reload();
            }

        },
                

}
</script>

<style>
#report {
    position: fixed;
    padding: 5%;
    background-color: #FDF5E6;
    height: 100%;
    width: 100%;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.pictureSubmit {
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9C27B0, #E040FB);
  border: 0;
  font-size: 18px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
  box-sizing: border-box;
  height: 6%;
  width: 25%;
  margin-top: 5%;
}

.pictureFile {
    display: flex;
    justify-content: center;
    margin-top: 5%;
}

.potholePhotoUpload {
    display: flex;
    color: #8C55AA;
    font-family: 'Raleway', sans-serif;
    justify-content: center;



}

@media  only screen and (max-width: 766px) {

.pictureFile {
    display: flex;
    justify-content: center;
    margin-top: 35%;
    font-style:
}


}

</style>