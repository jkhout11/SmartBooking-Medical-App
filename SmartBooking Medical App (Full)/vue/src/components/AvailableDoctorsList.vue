<template>
  <div class="container">
    <div class="header">
      <nav>
        <div class="nav-content">
          <div class="logo">
            <a href="#">
              <img src="../img/NavBarLogo.png" alt="Logo">
            </a>
          </div>
          <div class="welcome-title">
            <h1>Welcome to Your Patient Portal</h1>
          </div>
          <ul class="nav-links">
          <li><router-link to="/patient-portal">Home</router-link></li>
          <li><router-link to="/book-appointment">Book An Appointment</router-link></li>
          <li><router-link v-bind:to="{name:'patient-profile'}">My Patient Profile</router-link></li>
          <!-- <li><router-link to="/reviews">Reviews</router-link></li> -->
          </ul>
        </div>
      </nav>
   </div>
    <div class="content">
      <h1>Available Doctors</h1>
      <div v-for="facility in doctorFacilityInfo" :key="facility.id" class="profile-card">
        <div class="profile-image">
          <img src="../img/doctorImage.png" alt="Doctor Image" class="doctor-image">
        </div>
        <div class="profile-details">
          <h2>{{ facility.doctorFirstName }} {{ facility.doctorLastName }}</h2>
          <p>{{ facility.streetAddress }}</p>
          <p>{{ facility.city }}, {{ facility.state }}   {{ facility.zipCode }}</p>
          <router-link :to="{ name: 'office-info', params: { id: facility.officeId } }">
            <button class="view-profile-button">View Office Details</button>
          </router-link>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import authService from '../services/AuthService';

export default {
  name: 'doctor-info',
  data() {
    return {
      doctorFacilityInfo: []
    }
  },
  methods: {
    getDoctorOfficeInfo() {
      authService.getDoctorOfficeInfo()
        .then((response) => {
          if (response.status === 200) {
            this.doctorFacilityInfo = response.data;
          }
        });
    }
  },
  created() {
    this.getDoctorOfficeInfo();
  }
}
</script>

<style scoped>
.container {
  display: flex;
  flex-direction: column;
  justify-content: top;
  align-items: center;
  height: 150vh;
}

.header {
  background-color: #A1DE81;
  width: 100%;
  padding: 0px 0;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.content {
  background-color: #ffffff;
  padding: 10px 20px;
  width: 100%;
  max-width: 1500px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-top: 20px;
}

.doctor-info {
  border: 1px solid #e3e3e3;
  height: 200px;
  display: flex;
  flex-direction: row;
  margin: 10px;
}

.address,
.doctor-details {
  text-align: left;
  flex-grow: 1;
  margin: 10px 20px;
}

.address {
  text-align: right;
}

h1 {
  text-align: center;
  margin: 0 0 10px;
  padding: 50px;
}

.button {
  text-align: center;
  margin-left: 1px;
  display: flex;
  flex-direction: row;
}

.button button {
  background-color: #007bff;
  color: white;
  padding: 10px 30px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 15px;
}

.doctor-details {
  display: flex;
  flex-direction: column;
  align-items: left;
}

.doctor-image {
  width: 100px;
  height: auto;
  margin: 10px;
  padding: 10px;
}

/* .nav-content { */
  /* border: 1px solid #e3e3e3; */
/* } */
.profile-card {
  display: flex;
  justify-content: center;
  align-items: flex-start;
  border: 1px solid #e3e3e3;
  padding: 10px;
  border-radius: 10px;
  margin-bottom: 20px;
  background-color: #ffffff;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
}

.profile-image {
  width: 130px;
  height: 150px;
  margin-right: 10px;
  }

.doctor-image {
  width: 100%;
  height: 100%;
  object-fit: cover;
}

.profile-details {
  flex-grow: 1;
}

.profile-details h2 {
  margin: 0;
}

.view-profile-button {
  background-color: #007bff;
  color: white;
  padding: 10px 20px;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 18px;
  margin-top: 10px;
}
.welcome-title {
  color: #0D1FE0;
}

</style>
