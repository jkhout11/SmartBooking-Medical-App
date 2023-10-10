<template>


  <div class="appointment-form">
    <div class="sblogo">
      <img src="../img/NavBarLogo.png" alt="sblogo" />
    </div>
    <h2>Book an Appointment</h2>
    <form @submit.prevent="submitAppointment">
      
      <div class="profile-card"></div>
      <div class="form-group">
        <label for="doctor">Choose a Doctor:</label>
        <select id="doctor" v-model="selectedDoctor" class="form-control">
          <option v-for="doctor in doctorList" :key="doctor.id" :value="doctor.id">
            {{ doctor.doctorFirstName }} {{ doctor.doctorLastName }}
          </option>
        </select>
      </div>

      <div class="form-group">
        <label for="date">Choose a Date:</label>
        <input
          type="date"
          id="date"
          v-model="selectedDate"
          class="form-control"
          required
        />
      </div>

      <div class="form-group">
        <label for="time">Choose a Time:</label>
        <select id="time" v-model="scheduleId" :disabled="!selectedDate">
          <option
            v-for="schedule in doctorSchedule"
            v-bind:key="schedule.scheduleId"
            v-bind:value="schedule.scheduleId"
          >
            {{ schedule.timeSlot }}
            
          </option>
        </select>
        <div v-if="selectedTime">
          <label for="reason">Reason for Visit:</label>
          <textarea
            id="reason"
            v-model="reasonForVisit"
            rows="4"
            required
          ></textarea>
        </div>
      </div>

      <button type="submit" class="btn btn-primary">Book Appointment</button>
    </form>
    <!--<BookAppointmentAlertBox :showAlertAppt="showAlertAppt" @close="closeAlertAppt" />-->

  </div>
</template>

<script>

import authService from "../services/AuthService";
import BookAppointmentAlertBox from "../components/BookAppointmentAlertBox.vue";
//import Swal from 'sweetalert2';
export default {
  name: 'book-appointment', 
  component: BookAppointmentAlertBox,
  props: ["appointmentList"],
  data() {
    
    return {
      showAlertAppt: false,

      doctorList:[],
      doctorSchedule:[],
      selectedDate:null,
      scheduleId: null
     }
  },
  methods: {
    getAvailableAppointments() {
      authService.getListBookAppointments()
        .then((response) => {
          if (response.status === 200) {
            //alert('getAvailableAppointments');
            this.doctors = response.data;
          }
        });
    },
  submitAppointment() {
  const appointmentDetails = { doctorScheduleId: this.scheduleId, dateSelected:this.selectedDate};
  authService.postBookAnAppointment(appointmentDetails).then((response) => {
        if (response.status == 202) {
        alert('Your appointment has been successfully confirmed and your doctor has been notified. Your appointment Id is: ' + response.data);    
        /*Swal.fire({
          title: 'Smart Booking',
          text: 'Your appointment has been successfully confirmed and your doctor has been notified.Your appointment Id is: ' + response.data,
          icon: 'info',
          confirmButtonText: 'OK',
        });*/
          this.$router.push({name:'view-available-doctors'});
        }
      })
        .catch((error) => {
          alert('Your appointment has not been booked, please try later.');
          /*Swal.fire({
              title: 'Smart Booking',
              text: 'Your appointment has not been booked, please try later.',
              icon: 'info',
              confirmButtonText: 'OK',
            });*/
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
  }
},

  showAppointmentConfirmation(){ 
    this.showAlertAppt = true;

  },

  closeAlertAppt(){
    this.showAlertAppt = false;
  },


  created(){
  //alert('created');
  
      authService.getDoctorList().then((response) => {
        if (response.status == 200) {
          //alert(response);
          this.doctorList = response.data;
        }
      })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
      authService.getListBookAppointments().then((response) => {
        if (response.status == 200) {
          //alert(response);
          this.doctorSchedule = response.data;
        }
      })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    //this.getAvailableAppointments();
    }
};
</script>

<style scoped>
/* Google Font Link */
@import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700&family=Ubuntu:wght@400;500;700&display=swap");
.appointment-form {
  max-width: 400px;
  margin: 0 auto;
  padding: 20px;
}

.form-group {
  margin-top: 10px;
}

.appointment-form label {
  display: block;
  font-weight: bold;
}

.appointment-form select,
.appointment-form input {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
}

.appointment-form button {
  display: block;
  width: 100%;
  padding: 10px;
  margin-top: 15px;
  background-color: #007bff;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.sblogo {
  text-align: center;
  margin-bottom: 20px;
}

.sblogo img {
  width: 300px;
  height: auto;
}
.appointment-form label {
  display: block;
  margin-top: 10px;
  font-weight: bold;
}

.appointment-form select,
.appointment-form input,
.appointment-form textarea {
  width: 100%;
  padding: 10px;
  margin-top: 5px;
  border: 1px solid #ccc;
  border-radius: 5px;
}
</style>
