<template>
  <div>
    <header>
        <img id="mainLogo" src="../img/SmartBookingLogo.png" alt="SmartBookingLogo" />      
    </header>
  <main>
     <router-view />
   </main>
   <footer>
    </footer>
  </div>
</template>
<script>
import authService from '../services/AuthService';

export default {
  name: "home",
  data() {
    return {
       isDoctor: '',
      isValidDoctor: ''     
    };
  },
  methods:{         
    getUserProfile(){
      authService
      .getUserProfile()
      .then((response) => {         
          if (response.status == 200) {
            this.$store.commit("SET_IS_DOCTOR", response.data.isDoctor);
            this.$store.commit("SET_IS_VALID_DOCTOR", response.data.isVerifiedDoctor);
            
            if(response.data.isDoctor){
               this.$router.push({name:'upcoming-appointment'});
            } 
            else{
              this.$router.push({name:'view-available-doctors'});
            }
          }
        });
    }
  },
  created() {  
    this.getUserProfile();   
    
  }
};
</script>

<style>
#mainLogo{
  height: 150px;
  width: 150px;
  padding-right: 20px;
  
}
.columns {
  display: flex;
  justify-content: space-between;
}
.column {
  flex: 1;
  margin: 10px;
  padding: 10px;
  border: 1px solid #ccc;
}
header #mainLogo{
  text-align: start;
}
</style>