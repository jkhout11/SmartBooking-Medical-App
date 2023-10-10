<template>
  <div class="container">
     <div class="header">
      <nav>
        <div class="nav-content">
          <div class="logo">
            <a href="#">
              <img src="../img/NavBarLogo.png" alt="Logo" />
            </a>
          </div>
          <div class="welcome-title">
            <h1>Manage Your Profile</h1>
          </div>
          <ul class="nav-links">
            <li><router-link v-bind:to="{name:'view-available-doctors'}">Home</router-link></li>            
            <li>
              <router-link v-bind:to="{name:'book-appointment'}"
                >Book An Appointment</router-link
              >
            </li>           
            <!-- <li><router-link to="/reviews">My Reviews</router-link></li> -->
          </ul> 
        </div>
      </nav>
    </div>
    <form id="patient-profile-form" @submit.prevent="savePatientProfile" class ="patient-profile-form">
      <h1>Patient Profile</h1>
      <br>
      <div class="form-element">
        <label for="firstName">First Name:</label>
        <input
          id="firstName"
          type="text" required
          v-model="patient.patientProfile.patientFirstName"
        />
      </div>
      <div class="form-element">
        <label for="lastName">Last Name:</label>
        <input
          id="lastName"
          type="text" required
          v-model="patient.patientProfile.patientLastName"
        />
      </div>
      <div class="form-element">
        <label for="streetAddress">Street Address:</label>
        <input
          id="streetAddress"
          type="text" required
          v-model="patient.patientProfile.patientStreetAddress"
        />
      </div>
      <div class="form-element">
        <label for="city">City:</label>
        <input id="city" type="text" required v-model="patient.patientProfile.patientCity" />
      </div>
      <div class="form-element">
        <label for="state">State:</label>
        <input id="state" type="text" required v-model="patient.patientProfile.patientState" />
      </div>
      <div class="form-element">
        <label for="zipcode">Zip Code:</label>
        <input
          id="zipcode"
          type="number" required
          v-model="patient.patientProfile.patientZipCode"
        />
      </div>
      <div class="form-element">
        <label for="phoneNumber">Phone Number:</label>
        <input
          id="phoneNumber"
          type="number" required
          v-model="patient.patientProfile.patientPhoneNumber"
        />
      </div>
      <div class="form-element">
        <label for="email">Email Address:</label>
        <input id="email" type="email" required v-model="patient.patientProfile.patientEmail" />
      </div>
      <div class="form-element">
        <label for="date-of-birth">Date Of Birth:</label>
        <input
          id="date-of-birth" name="date"
          type="date" pattern="\d{4}-\d{2}-\d{2}" required min="1900-01-01"
          v-model="patient.patientProfile.patientDob"
        />
      </div>

      <div class="row button">
        <button type="submit">Save</button>
      </div>
    </form>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "manage-patient-profile",

  data() {
    return {
      
      //currentDate: new Date().toJSON().slice(0,10).replace(/-/g,'/'),
      currentDate: new Date().toJSON().slice(0,10),
      patient: {
        isDoctor: false,
        patientProfile: {
          patientFirstName: "",
          patientLastName: "",
          patientStreetAddress: "",
          patientCity: "",
          patientState: "",
          patientZipCode: "",
          patientPhoneNumber: "",
          patientEmail: "",
          patientDob: ""
        }
      }
    };
  },

  methods:{
    setMaxLimitDOB(){
      
    },

    savePatientProfile(){    
     
      this.patient.isDoctor = this.$store.state.isDoctor;
      authService.updatePatientProfile(this.patient)
       .then((response) => {
          if (response.status === 200) {
            (this.errors = false), (this.message = "Successfully Updated.");
          }
          this.clear();
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.message = "Bad Request: Validation Errors";
          }
        });     
    },

    clear(){
      this.patient.patientProfile.patientFirstName = "";
      this.patient.patientProfile.patientLastName = "";
      this.patient.patientProfile.patientStreetAddress = "";
      this.patient.patientProfile.patientCity = "";
      this.patient.patientProfile.patientState = "";
      this.patient.patientProfile.patientZipCode = "";
      this.patient.patientProfile.patientPhoneNumber = "";
      this.patient.patientProfile.patientEmail = "";
      this.patient.patientProfile.patientDob = "";         
    }
  },

  created(){
     //alert(this.currentDate);
  }

};
</script>

<style scoped>

  .patient-profile-form {
  background-color: #ffffff;
  border: 1px solid #ccc;
  padding: 20px;
  width: 100%;
  max-width: 800px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-top: 20px;
}
.form-element {
  margin-bottom: 1rem;
  display: flex;
  align-items: center;
}

.form-element label {
  margin-right: 0.5rem;
  font-weight: 600;
}

.form-element input {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

button[type="submit"] {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 1rem;
}

button[type="submit"]:hover {
  background-color: #0056b3;
}
.container {
  display: flex;
  flex-direction: column;
  justify-content: start;
  align-items: center;
  height: 150vh;
}

.header {
  background-color: #a1de81;
  width: 100%;
  padding: 0px 0;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  border: 1px solid white
}

.nav-content {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 15px;
}

.logo img {
  display: block;
}

.welcome-title {
  color: #0d1fe0;
}

.nav-links {
  list-style: none;
  display: flex;
  align-items: center;
}
</style>