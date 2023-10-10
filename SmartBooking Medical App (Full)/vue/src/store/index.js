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
const currentIsDoctor = localStorage.getItem('isDoctor')
const currentIsValidDoctor = localStorage.getItem('isValidDoctor')

if(currentToken != null) {
  axios.defaults.headers.common['Authorization'] = `Bearer ${currentToken}`;
}

export default new Vuex.Store({
  state: {
    token: currentToken || '',
    user: currentUser || {},
    isDoctor: currentIsDoctor || '',
    isValidDoctor: currentIsValidDoctor || ''   
  },
  mutations: {
    SET_AUTH_TOKEN(state, token) {
      state.token = token;
      localStorage.setItem('token', token);
      axios.defaults.headers.common['Authorization'] = `Bearer ${token}`
    },
    SET_USER(state, user) {
      state.user = user;
      localStorage.setItem('user',JSON.stringify(user));
    },
    SET_IS_DOCTOR(state, isDoctor) {
      state.isDoctor = isDoctor;
      localStorage.setItem('isDoctor',isDoctor);
    },
    SET_IS_VALID_DOCTOR(state, isValidDoctor) {
      state.isValidDoctor = isValidDoctor;
      localStorage.setItem('isValidDoctor',isValidDoctor);
    },
    LOGOUT(state) {
      localStorage.removeItem('token');
      localStorage.removeItem('user');
      localStorage.removeItem('isDoctor');
      localStorage.removeItem('isValidDoctor');
      state.token = '';
      state.user = {};
      state.isDoctor = '',
      state.isValidDoctor = '',
      axios.defaults.headers.common = {};
    }
  }
})
