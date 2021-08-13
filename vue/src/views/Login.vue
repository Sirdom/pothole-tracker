<template>
  <div id="login" class="loginPage">
    <form class="form-signin" @submit.prevent="login">
      <h1 class="h3 mb-3 font-weight-normal">Please Sign In</h1>
      <div
        class="alert alert-danger"
        role="alert"
        v-if="invalidCredentials"
      >Invalid username and password!</div>
      <div
        class="alert alert-success"
        role="alert"
        v-if="this.$route.query.registration"
      >Thank you for registering, please sign in.</div>
      <label for="username" class="sr-only">Username</label>
      <input
        type="text"
        id="username"
        class="form-control"
        placeholder="Username"
        v-model="user.username"
        required
        autofocus
      />
      <label for="password" class="sr-only">Password</label>
      <input
        type="password"
        id="password"
        class="form-control"
        placeholder="Password"
        v-model="user.password"
        required
      />

      <div class="forgotPw">

        <button type="button" class="btn" @click="showModal">
          Forgot password?
        </button>

        <forgot-pw-modal v-show="isModalVisible" @close="closeModal"/>
      
      </div>

      <!-- forgot pw component here
      <div class= "forgotPw">
      </div>
      -->

      <div class = "needAccount">
        <router-link :to="{ name: 'register' }" id="needAccountBtn">Need an account?</router-link>
      </div>
      <button class="sign-in" type="submit">Sign in</button>
    </form>
  </div>
</template>

<script scope>
import authService from "../services/AuthService";
import ForgotPwModal from '../components/ForgotPwModal.vue';

export default {
  name: "login",
  components: {
    ForgotPwModal
  },
  data() {
    return {
      user: {
        username: "",
        password: ""
      },
      invalidCredentials: false,
      isModalVisible: false
    };
  },
  methods: {
    login() {
      authService
        .login(this.user)
        .then(response => {
          if (response.status == 200) {
            this.$store.commit("SET_AUTH_TOKEN", response.data.token);
            this.$store.commit("SET_USER", response.data.user);
            this.$router.push("/");
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            this.invalidCredentials = true;
          }
        });
    },
    showModal() {
      this.isModalVisible = true;
    },
    closeModal() {
      this.isModalVisible = false;
    }
  }
};
</script>

<style scoped>


#login {
  margin: 0 auto;
  
}
.loginPage {
  font-family: 'Raleway', sans-serif;

}

@media  only screen and (max-width: 3444px) {

  .loginPage {
  display: flex; 
  justify-content: center ;
  background-color: #FDF5E6;
  border-radius: 1.5em;
  z-index: 0;
  height: 120vh;
}

h1 {
  display: flex;
  justify-content: center;
  padding-top: 40px;
  color: #8C55AA;
  font-weight: bold;
  font-size: 35;
  margin-bottom: 5% ;
  margin-top: 30%;


}

.form-control {
  /* width: 93%; */
    color: rgb(38, 50, 56);
    font-weight: 700;
    font-size: 14px;
    letter-spacing: 1px;
    background: rgba(136, 126, 126, 0.04);
    /* padding: 10px 20px; */
    border: none;
    border-radius: 10px;
    outline: none;
    box-sizing: border-box;
    height: 1px;
    border: 5px solid rgba(0, 0, 0, 0.02);
    margin-bottom: 50px;
    display: flex;
  justify-content: center;
  padding: 30px;
  padding-top: 20px;
  text-align: center;
  margin-top: 30%;

}

.form-control:focus {
  border: 2px solid rgba(0, 0, 0, 0.18) !important;
  

}

.sign-in {

  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9C27B0, #E040FB);
  border: 0;
  font-size: 18px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
  box-sizing: border-box;
  width: 100%;
  height: 7%;
  padding-bottom: 10%;
  padding-top: 10%;
  

}

.needAccount {
  display: flex;
  justify-content: center;
  padding: 50px;
  text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
  color: #DDA0DD;
  padding-top: 15px;
    }

#needAccountBtn {
  text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
  color: #DDA0DD;
  text-decoration: none;
}


}
@media  only screen and (max-width: 766px) {


  .loginPage {
  display: flex;
  justify-content: center;
  background-color: #FDF5E6;
  box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
  z-index: 0;
  padding-bottom: 20px;
  overflow: hidden;
  height: 100vh;
}
        
h1 {
  display: flex;
  justify-content: center;
  padding-top: 40px;
  color: #8C55AA;
  font-weight: bold;
  font-size: 23px;
  margin-top: 35%;

}

.form-control {
  color: rgb(38, 50, 56);
    font-weight: 700;
    font-size: 14px;
    letter-spacing: 1px;
    background: rgba(136, 126, 126, 0.04);
    border: none;
    border-radius: 10px;
    outline: none;
    box-sizing: border-box;
    height: 1px;
    border: 5px solid rgba(0, 0, 0, 0.02);
    margin-top: 15%;
    margin-bottom: 50px;
    display: flex;
  justify-content: center;
  padding: 30px;
  padding-top: 30px;
  text-align: center;

}

.form-control:focus {
  border: 2px solid rgba(0, 0, 0, 0.18) !important;

}

.sign-in {
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
  padding-top: 20px;
  width: 100%;
  height: 6%;


}

.needAccount {
  display: flex;
  justify-content: center;
  padding: 50px;
  text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
  color: #DDA0DD;
  padding-top: 15px;
    }

#needAccountBtn {
  text-shadow: 0px 0px 3px rgba(117, 117, 117, 0.12);
  color: #DDA0DD;
  text-decoration: none;
}

}

@media  only screen and (max-width: 330px) {

  .loginPage {
  display: flex;
  justify-content: center;
  background-color: #FDF5E6;
  box-shadow: 0px 11px 35px 2px rgba(0, 0, 0, 0.14);
  z-index: 0;
  padding-bottom: 20px;
  overflow: hidden;
  height: 100vh;
}

.needAccount {
  margin-bottom: -20px;
    }




  .sign-in {
  display: flex;
  justify-content: center;
  cursor: pointer;
  border-radius: 5em;
  color: #fff;
  background: linear-gradient(to right, #9C27B0, #E040FB);
  border: 0;
  font-family: 'Ubuntu', sans-serif;
  font-size: 15px;
  box-shadow: 0 0 20px 1px rgba(0, 0, 0, 0.04);
  /* margin-bottom: 50%; */
  box-sizing: border-box;
  padding-top: 12px;
  padding-bottom: 15px;
  width: 100%;
  height: 5%;

}

  .loginPage {
  display: flex; 
  justify-content: center ;
  background-color: #FDF5E6;
  border-radius: 1.5em;
  z-index: 0;
  height: 100vh;
}


}


    

</style>