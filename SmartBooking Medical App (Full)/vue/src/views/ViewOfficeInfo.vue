<template>
  <div>
    <office-info v-bind:facilityId="parseInt($route.params.id)"/>
  </div>
</template>

<script>
import OfficeInfo from '../components/OfficeInfo.vue'
export default {
  name:'view-office-info',
components:{
  OfficeInfo
}

}
</script>

<style>

</style>