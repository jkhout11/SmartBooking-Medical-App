import axios from 'axios';

export default {
  login(user) {
    return axios.post('/login', user);
  },

  register(user) {
    return axios.post('/register', user);
  },

  getUserProfile() {
    return axios.get('/profile');
  },

  getDoctorOfficeInfo() {
    return axios.get('/doctors/view-doctor');
  },

  getOfficeDetail(officeId) {
    return axios.get(`/office-info/${officeId}`);
  },

  getOfficeList() {
    return axios.get('/office-info/all');
  },

  updateDoctorProfile(profile) {
    return axios.post('/save-profile', profile);
  },

  updatePatientProfile(profile) {
    return axios.post('/save-profile', profile);
  },

  getDoctorScheduleByDay(day) {    
    return axios.get(`/update-availability/schedule?dayOfTheWeek=${day}`);
  },

  getAllReviews() {
    return axios.get('/reviews/all');
  },

  getReviewsByReviewedOffice(officeId) {
    return axios.get(`/reviews/all/${officeId}`);
  },

  getListBookAppointments() {
    return axios.get('/agenda/listbookappointments');
  },

  postBookAnAppointment(appointment) {
    return axios.post('/agenda/bookanappointment', appointment);
  },

  getDoctorList() {
    return axios.get('/doctors/all');
  },

  getReviewById(reviewId) {
    return axios.get(`/reviews/${reviewId}`);
  },

  updateOffice(doctorFacilityInfo) {
    alert(doctorFacilityInfo.id)
    return axios.put(`/office-info/update/${doctorFacilityInfo.id}`, doctorFacilityInfo);
  },
  getDoctorSchedule(day){    
    return axios.get(`/update-availability/schedule?dayOfTheWeek=${day}`)
  },
  updateUnavailability(doctorScheduleIdList){      
    return axios.post('/update-availability', doctorScheduleIdList)
  },
  getUpcomingAppointmentList(){
    return axios.get('/agenda/view-appointments')
  },
  // getDoctorSchedule(doctorId) {
  //   return axios.get(`/update-availability/${doctorId}`);
  // }
  getOfficeInfoById(id)  {
    return axios.get(`/office-info/${id}`);
  }
}
