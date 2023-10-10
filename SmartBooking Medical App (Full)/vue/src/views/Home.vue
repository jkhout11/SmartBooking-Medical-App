<template>
  <div>
    <nav :class="{'sticky': isSticky}">
      <div class="nav-content">
        <div class="logo">
          <a href="#">
            <img src="../img/NavBarLogo.png" alt="Logo">
          </a>
        </div>
        <ul class="nav-links">
          <li><a href="#">Home</a></li>
          <li><a href="#">Find a doctor</a></li>
          <li><a href="#">About Us</a></li>
          <li><a href="#">Services</a></li>
          <li><a href="/login">Login</a></li>
        </ul>
      </div>
    </nav>
    <section class="home"></section>
    <div class="text">
      <!-- ... your content ... -->
    </div>
  </div>
</template>

<script>
export default {
  name: "home",
  data() {
    return {
      isSticky: false
    };
  },
  mounted() {
    window.addEventListener('scroll', this.handleScroll);
  },
  beforeDestroy() {
    window.removeEventListener('scroll', this.handleScroll);
  },
  methods: {
    handleScroll() {
      this.isSticky = document.documentElement.scrollTop > 20;
    }
  }
};
</script>

<style scoped>


</style>
