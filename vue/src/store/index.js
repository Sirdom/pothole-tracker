import Vue from 'vue'
import Vuex from 'vuex'
import axios from 'axios'

Vue.use(Vuex)

/*
 * The authorization header is set for axios when you login but what happens when you come back or
 * the page is refreshed. When that happens you need to check for the token in local storage and if it
 * exists you should set the header so that it will be attached to each request
 */
const currentToken = localStorage.getItem('token')
const currentUser = JSON.parse(localStorage.getItem('user'));

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    userIsAuthorized:false,
    token: currentToken || '',
    user: currentUser || {},
    potholes: [],
    newReport: {},
    images: [],
    linkIsActive: {
      home: true,
      create: false,
      review: false,
      login: false,
      register: false
    }
  },    
  mutations: {
    LINK_ACTIVE(state, activePage) {
        state.linkIsActive[activePage] = true;
        console.log('in link active');
        for(const page in state.linkIsActive) {
          if (state.linkIsActive[page] != state.linkIsActive[activePage]) {
              state.linkIsActive[page] = false
          }
        }
    },
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      state.userIsAuthorized = true;
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      state.userIsAuthorized = true;
      localStorage.setItem('user',JSON.stringify(user));
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      state.token = '';
      state.user = {};
      state.userIsAuthorized = false;
      axios.defaults.headers.common = {};
    },
    ADD_POTHOLES(state, data) {
      state.potholes = data;
    },
    DELETE_POTHOLE(state, data) {
      state.potholes = state.potholes.filter((pothole) => {
        return pothole.id !== data;
      })
    },
    ADD_REPORT(state, data) {
      state.newReport = data;
    },
    SET_AUTH(state, data) {
      state.userIsAuthorized = true;
      console.log(data);
    },
    SET_POTHOLES(state, potholes) {
      state.potholes = potholes
    },
    LIST_IMAGES(state, images) {
      state.images = images
    }
  }
})
