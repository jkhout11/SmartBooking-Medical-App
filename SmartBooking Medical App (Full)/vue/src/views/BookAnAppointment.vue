<template>
  <div class="container">
    <div class="wrapper">
      <AppointmentForm />
    </div>
  </div>
</template>

<script>
import AppointmentForm from "@/components/BookAppointmentForm.vue";

export default {
  name:'view-available-appointments',
  components: {
    AppointmentForm,
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
  background-color: #f5f5f5;
  border-radius: 5px;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  padding: 20px;
  width: 400px;
}
</style>
