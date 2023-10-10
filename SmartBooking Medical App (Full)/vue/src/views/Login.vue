<template>
  <div class="container">
    <div class="wrapper1">
      <div class="sblogo">
          <img src="../img/NavBarLogo.png" alt="sblogo">
        </div> 
      <h2>Please Login</h2>
      <form @submit.prevent="login">
        
        <div class="row">
          <i class="fas fa-user"></i>
          <input type="text" placeholder="Username" v-model="user.username" required>
        </div>
        <div class="row">
          <i class="fas fa-lock"></i>
          <input type="password" placeholder="Password" v-model="user.password" required>
        </div>
        <div class="row button">
          <button type="submit">Login</button>
        </div>
        <div class="signup-link">Don't have an account? <router-link to="/register">Signup now</router-link></div>
      </form>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "login",
  data() {
    return {
      user: {
        username: "",
        password: ""
      }
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
            // this.$store.commit("SET_IS_DOCTOR", response.data.isDoctor);
            // this.$store.commit("SET_IS_VALID_DOCTOR", response.data.isValidDoctor);
            this.$router.push('/processing');
          }
        })
        .catch(error => {
          const response = error.response;

          if (response.status === 401) {
            // Handle invalid credentials
          }
        });
    }
  }
};
</script>

<style scoped>

.container {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
}

.wrapper1 {
  background-color: #ffffff;
  border-radius: 5px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 400px;
}

.h2 {
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
  margin-left: 10px;
}

.sblogo {
  text-align: center;
  margin-bottom: 20px;
}

.sblogo img {
  width: 300px;
  height: auto;
}
</style>