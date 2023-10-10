<template>
  <div>
    <available-doctor-list />    
  </div>
</template>

<script>

import AvailableDoctorList from '../components/AvailableDoctorsList.vue'

export default {
    name:'view-available-doctor',
    components:{        
        AvailableDoctorList
    } 
}
</script>

<style>

</style>   