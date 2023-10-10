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
            <h1></h1>
          </div>
          <ul class="nav-links">
            <li><router-link to="/doctor-portal">Home</router-link></li>
            <li>
              <router-link to="/schedule-availability"
                >My Availability</router-link
              >
            </li>
            <li>
              <router-link to="/update-office-info"
                >My Office Info</router-link
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
  <div id="main">
    <form v-on:submit.prevent="updateAvailability" class="availability-form">
      <h1>Update Schedule</h1>
      <br>
      <div class="select-container">
        <label for="selected-days">Select Day:</label>
        <select
          id="selected-days"
          v-model="selectedDay"
          v-on:change="getDoctorSchedule"
        >
          <option value="">Please select</option>
          <option value="Monday">Monday</option>
          <option value="Tuesday">Tuesday</option>
          <option value="Wednesday">Wednesday</option>
          <option value="Thursday">Thursday</option>
          <option value="Friday">Friday</option>
        </select>
      </div>
      <div class="schedule-table">
        <table>
          <thead>
            <tr>
              <th></th>
              <th>Timeslot</th>
            </tr>
          </thead>
          <tbody>
            <tr v-for="time in schedule" v-bind:key="time.doctorScheduleId">
              <td v-show="schedule[0].doctorScheduleId > 0">
                <input
                  type="checkbox"
                  v-model="selectedSlotId"
                  v-bind:id="time.doctorScheduleId"
                  v-bind:value="time.doctorScheduleId"
                  v-on:change="changeAvailableSlot"
                />
              </td>
              <td>{{ time.timeslot }}</td>
            </tr>
          </tbody>
        </table>
      </div>
      <button type="submit" class="btn save">Save</button>
    </form>
  </div>
  </div>
</template>

<script>
import authService from "../services/AuthService";
export default {
  name: "update-doctor-availability",

  data() {
    return {
      message: "",
      selectedDay: "",
      selectedSlotId: [],
      unSelectedTimeSlot: [],
      unselectedIdList: {
        doctorScheduleIdList: [],
      },

      schedule: [
       {
          scheduleId: 0,
          dayOfTheWeek: "",
          timeslot: "",
          doctorScheduleId: 0,
          doctorId: 0,
          slotAvailable: true,
       },
      ],
    };
  },
  methods: {
    getDoctorSchedule() {
      //alert(this.selectedDay);

      this.schedule = [];
      this.selectedSlotId = [];
      this.unSelectedTimeSlot = [];

      authService.getDoctorSchedule(this.selectedDay).then((response) => {
        if (response.status === 200) {
          //this.schedule = response.data;
          const availability = response.data;
          if (availability.length > 0) {
            for (let id in availability) {
              this.schedule.push(availability[id]);
              if (availability[id].slotAvailable) {                
                this.selectedSlotId.push(availability[id].doctorScheduleId);
              }
            }
          }
        }
      });
    },

    changeAvailableSlot(){
      
    },

    updateAvailability() {
      this.unSelectedTimeSlot = this.schedule.filter((time) => {
        return !this.selectedSlotId.includes(time.doctorScheduleId);
      });
     // let unselectedIdList = [];
      this.unSelectedTimeSlot.forEach((value) =>      
        this.unselectedIdList.doctorScheduleIdList.push(value.doctorScheduleId)
      );

      authService.updateUnavailability(this.unselectedIdList).then((response) => {
        if (response.status === 200) {
          this.message = "Successfully Updated";
           this.getDoctorSchedule();
        }
        
      });

     
    },
  },
};
</script>

<style>
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

.availability-form {
  background-color: #fff;
  border: 1px solid #ccc;
  padding: 20px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
  margin-top: 20px;
  width: 100vh;
}

.select-container {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.select-container label {
  margin-right: 10px;
}

.schedule-table {
  width: 100%;
  overflow-x: auto; /* Enable horizontal scrolling if needed */
}

.schedule-table table {
  width: 100%;
  border-collapse: collapse;
}

.schedule-table th,
.schedule-table td {
  padding: 8px 12px;
  border: 1px solid #ccc;
  text-align: center;
}

.btn.save {
  background-color: #007bff;
  color: #fff;
  border: none;
  padding: 10px 20px;
  border-radius: 5px;
  cursor: pointer;
  margin-top: 1rem;
}

.btn.save:hover {
  background-color: #0056b3;
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

.nav-links li {
  margin: 0 8px;
}

.nav-links a {
  text-decoration: none;
  color: #0e2431;
  font-size: 18px;
  font-weight: 500;
  padding: 10px 4px;
  transition: all 0.3s ease;
}

.nav-links a:hover {
  color: #4070f4;
}

</style>





