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
            <h1>Welcome to Your Doctor Portal</h1>
          </div>
          <ul class="nav-links"><li>
              <router-link v-bind:to="{ name: 'upcoming-appointment' }"
                >Home</router-link
              >
            </li>
            <li>
              <router-link v-bind:to="{ name: 'schedule-availability' }"
                >Update My Availability</router-link
              >
            </li>
            <li>
              <router-link to="/update-office-info"
                >Update Office Info</router-link
              >
            </li>
            <li>
              <router-link v-bind:to="{ name: 'doctor-profile' }"
                >My Doctor Profile</router-link
              >
            </li>
            <li><router-link to="/reviews">My Reviews</router-link></li>
          </ul>
        </div>
      </nav>
    </div>
    <div class="content">
      <h2 class="page-title">Upcoming Appointments</h2>
      <ul class="appointment-list">
        <br>
        <li
          v-for="appointment in upcomingAppointments"
          :key="appointment.appointmentId"
          class="appointment-item"
        >
          <div class="appointment-details">
            <div class="patient-name">
              {{ appointment.patientFirstName }}
              {{ appointment.patientLastName }}
            </div>
            <br>
            <div class="appointment-date">
              {{ formatDate(appointment.dateSelected) }}
            </div>
            <div class="appointment-time">{{ appointment.timeSlot }}</div>
          </div>
          <button class="cancel-button" @click="cancelAppointment(appointment.id)">Cancel</button>
        </li>
      </ul>
    </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";

export default {
  name: "UpcomingAppointments",
  data() {
    return {
      upcomingAppointments: [
        {
          appointmentId: 0,
          patientFirstName: "",
          patientLastName: "",
          dateSelected: "",
          timeSlot: "",
          doctorId: 0,
        },
      ],
    };
  },
  methods: {
    getappointmentList() {
      authService.getUpcomingAppointmentList().then((response) => {
        if (response.status === 200) {
          this.upcomingAppointments = response.data;
        }
      });
    },

    formatDate(date) {
      const options = { year: "numeric", month: "long", day: "numeric" };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    // cancelAppointment(appointmentId) {
    //   // Simulate appointment cancellation logic
    //   // Make API request
    //   console.log("Canceling appointment ID:", appointmentId);
    // },
    created() {
      window.addEventListener("scroll", this.handleScroll);
    },
    beforeDestroy() {
      window.removeEventListener("scroll", this.handleScroll);
    },
  },

  created() {
    this.getappointmentList();
  },
};
</script>


<style scoped>
.container {
  display: flex;
  flex-direction: column;
  align-items: center;
  height: 150vh;
}

.header {
  background-color: #a1de81;
  width: 100%;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  /* border: 1px solid white */
}

.content {
  background-color: #ffffff;
  padding: 10px 20px;
  width: 100%;
  max-width: 1500px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-top: 20px;
  border: 1px solid white;
}

.nav-links {
  text-decoration: none;
  color: #0e2431;
  font-size: 18px;
  font-weight: 500;
  padding: 10px 4px;
  transition: all 0.3s ease;
  /* border: 1px solid white */
}

.nav-links a:hover {
  color: #4070f4;
}

.nav-links a.router-link-active {
  color: #4070f4;
}

.upcoming-appointments {
  max-width: 600px;
  margin: auto;
  padding: 10px;
  margin-top: 100px;
}

.appointment-list {
  list-style: none;
  padding: 0;
}

.appointment-list li {
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 10px;
  border: 1px solid #ccc;
  margin-bottom: 10px;
}

.appointment-details {
  display: flex;
  flex-direction: column;
}

.patient-name {
  font-weight: bold;
}

.cancel-button {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 1rem;
  margin-right: 5vh;
}

.cancel-button hover {
  background-color: #0056b3;
}
.welcome-title {
  color: #0d1fe0;
}

</style>
