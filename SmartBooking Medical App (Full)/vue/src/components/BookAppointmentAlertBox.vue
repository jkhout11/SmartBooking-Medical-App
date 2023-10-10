<template>
  <div class="alertbox-overlay" v-if="showAlertAppt">
    <div class="alert">
      <h2>Appointment Confirmed</h2>
      <p>Your appointment has been successfully confirmed and your doctor has been notified.</p>
      <button @click="closeAlertAppt">Close</button>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    showAlertAppt: Boolean
  },
  methods: {
    closeAlertAppt() {
      this.$emit('close');
    }
  }
}
</script>