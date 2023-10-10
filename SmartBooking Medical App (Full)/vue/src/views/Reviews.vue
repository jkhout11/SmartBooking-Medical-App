<template>
  <div>
    <review-list v-bind:reviewId="parseInt($route.params.id)"/>
  </div>
</template>

<script>
import ReviewList from '../components/ReviewList.vue';

export default {
    name:'reviews',
    components:{
        ReviewList
    }
}
</script>
