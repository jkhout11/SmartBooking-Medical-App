<template>
  <div id="app">
    <header class="header-content">
      <div id="nav">
        <div v-if="$store.state.token !== '' && !['login', 'registration'].includes($route.name)">
          <span>Logged in as: {{ $store.state.user.username }} |   </span>
          <router-link v-bind:to="{ name: 'logout' }" class="logout-link">Logout</router-link>
        </div>
        <router-link v-if="!['login', 'registration'].includes($route.name)" v-bind:to="{ name: 'login' }"></router-link>
      </div>
    </header>
    <main class="main-view">
      <router-view />
    </main>
  </div>
</template>

<script>
export default {
  created() {
    if (this.$route.name === 'login') {
      this.logout();
    }
  },
  methods: {

  },
};
</script>

<style scoped>
#app {
  height: auto;
  width: 100vw;
  background-color: rgb(46, 51, 107);
  background-size: cover;
  background-attachment: fixed;
  background-position: center;
}

#nav {
  text-align: right;
  padding: 20px 30px;
}

.logout-link {
  color: white;
  text-decoration: none;
  transition: color 0.3s;
}

.logout-link:hover {
  color: lightblue;
}
.header-content {
  color: white;
}
</style>
