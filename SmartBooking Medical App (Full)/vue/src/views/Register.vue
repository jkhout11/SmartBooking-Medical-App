<template>
  <div class="container">
    <div class="wrapper">
      <div class="sblogo">
        <img src="../img/NavBarLogo.png" alt="sblogo">
      </div> 
      <div class="title"><span>Registration Form</span></div>
      <form @submit.prevent="register">
        <div class="row">
          <i class="fas fa-user"></i>
          <input type="text" placeholder="Username" v-model="user.username" required>
        </div>
        <div class="row">
          <i class="fas fa-lock"></i>
          <input type="password" placeholder="Password" v-model="user.password" required>
        </div>
        <div class="row">
          <i class="fas fa-lock"></i>
          <input type="password" placeholder="Confirm Password" v-model="user.confirmPassword" required>
        </div>
          <div class="row">
          <input type="checkbox" id="isDoctor" v-model="user.isDoctor" v-on:change="updateIsDoctor">
          <label for="isDoctor">Are you a doctor?</label>
        </div>
        
         <div class="row" v-show="user.isDoctor">
          <i class="fas fa-id-card"></i>
          <input type="text" placeholder="NPI Number" v-model="user.npiNumber" v-bind:required="user.isDoctor"  >
          
          
          
        </div>
      
        <div class="row button">
          <button type="submit">Register</button>
        </div>
        <div class="signup-link">Already have an account? <router-link to="/login">Login now</router-link></div>
      </form>
    </div>
  </div>
</template>


<script>
import authService from '../services/AuthService';

export default {
  name: 'register',
  data() {
    return {
      doctor:[],

      user: {
        username: '',
        password: '',
        confirmPassword: '',
        role: 'user',
        isDoctor: false,
        npiNumber:''
      },
      registrationErrors: false,
      registrationErrorMsg: 'There were problems registering this user.',
      
    };
  },
  
  methods: {
    updateIsDoctor(){
      if(this.doctor.length != 0){
        this.user.isDoctor = true;
      }
    },

    register() {
      if (this.user.password != this.user.confirmPassword) {
        this.registrationErrors = true;
        this.registrationErrorMsg = 'Password & Confirm Password do not match.';
      }  
      else {
        
        authService
          .register(this.user)
          .then((response) => {
            if (response.status == 201) {
              this.$router.push({
                path: '/login',
                query: { registration: 'success' },
              });
            }
          })
          .catch((error) => {
            const response = error.response;
            this.registrationErrors = true;
            if (response.status === 400) {
              this.registrationErrorMsg = 'Bad Request: Validation Errors';
            }
          });          
      }
    },
    clearErrors() {
      this.registrationErrors = false;
      this.registrationErrorMsg = 'There were problems registering this user.';
    },
  },
};
</script>

<style scoped>
.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.wrapper {
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 400px;
}

.title {
  font-size: 30px;
  text-align: center;
  margin-bottom: 20px;
}

.row {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.row i {
  font-size: 18px;
  margin-right: 10px;
  color: #333;
}

.row input {
  flex-grow: 1;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.row label {
  margin-left: 10px;
  color: #333;
}

.button {
  text-align: center;
}

.button button {
  background-color: #007bff;
  color: white;
  padding: 10px 30px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 15px;
  display: flex;
  margin-left: 10px;
}

.sblogo {
  text-align: center;
  margin-bottom: 20px;
}

.sblogo img {
  width: 360px;
  height: auto;
}
</style>