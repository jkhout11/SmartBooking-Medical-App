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
            <h1></h1>
          </div>
          <ul class="nav-links">
            <li><router-link to="/patient-portal">Home</router-link></li>
            <li><router-link to="/book-appointment">Book An Appointment</router-link></li>
            <li><router-link v-bind:to="{name:'patient-profile'}">My Patient Profile</router-link></li>
          </ul>
        </div>
      </nav>
    </div>
    <div class="office-info">
      <h2>Office Details</h2>
      <div class="facility-names">
      <label id="facility-name">{{ facilityInfo.officeName }}</label>
      </div>
      <br />
      <div class="address-info">
      <label id="address">{{ facilityInfo.streetAddress }}</label>
      </div>
      <label id="city">{{ facilityInfo.city }}, </label>
      <label id="state">{{ facilityInfo.state }} </label>
      <label id="Zipcode">{{ facilityInfo.zipCode }}</label>
      <br />
      <a href="#">
        <img src="../img/offices.png" alt="officespic">
      </a>
      <br />
      <label id="phoneNumber">Phone: {{ facilityInfo.phone }}</label>
      <br />
      <br />
      <span>Hours of Operation:</span>
      <br>
      <label id="opening-days">{{ facilityInfo.openDays }}</label>
      <br />
      <label id="opening-hours">{{ facilityInfo.openTime }} - </label>
      <label id="closing-hours">{{ facilityInfo.closeTime }}</label>
    </div>
    <review-list v-bind:facilityId="facilityId" /> 
  </div>
</template>

<script>
import authService from "../services/AuthService";
import ReviewList from "./ReviewList.vue";

export default {
  name: "office-info",
  components: {
    ReviewList
  },
  props: ["facilityId"],
  doctorFacilityInfo: {
    type: Object
  },
  data() {
    return {
      facilityInfo: {
        id: 0,
        officeName: "",
        streetAddress: "",
        city: "",
        state: "",
        zipCode: "",
        phone: "",
        openTime: "",
        closeTime: "",
        openDays: "",
      },
    };
  },
  methods: {
    getFacilityInfo() {
      authService.getOfficeDetail(this.facilityId).then((response) => {
        if (response.status == 200) {
          this.facilityInfo = response.data;
        }
      });
    },
  },
  created() {
    this.getFacilityInfo();
  },
};
</script>

<style scoped>

.container {
  display: flex;
  flex-direction: column;
  justify-content: top;
  align-items: center;
  height: 250vh;
}

.header {
  background-color: #a1de81;
  width: 100%;
  padding: 0px 0;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}

.office-info {
  background-color: #ffffff;
  border: 1px;
  padding: 10px 20px;
  width: 100%;
  max-width: 1500px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-top: 20px;
  text-align: center;
}

.office-info img {
  max-width: 25%; /* Adjust this value as needed */
  height: auto;
}

.facility-names {
  font-size: 20px;
  font-weight: bold;
}
</style>