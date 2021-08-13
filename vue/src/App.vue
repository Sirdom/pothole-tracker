<template>
  <div id="app">
    <div class="nav">
      <navigation 
      name="Fixer"
      :navLinks="[

        {
          name: 'Home',
          link: '/',
          dropdown: false,
          

        },
          
        {

          name: 'Review',
          link: '/review',
          dropdown: false,
        },
        {
          name: 'Register',
          link: '/register',
          dropdown: false,
        },
        {
          name: 'Login',
          link: '/login',
          dropdown: false,
        },
        {
          name: 'Logout',
          link: '/logout',
          dropdown: false,
        }
          
       ]"
      />
    </div>
    <router-view />
  </div>
</template>

<script>
import Navigation from "./components/Navigation.vue";
export default {
  components: {
    Navigation
  }
  
}
</script>

<style>



