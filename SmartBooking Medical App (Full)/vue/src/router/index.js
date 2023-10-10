import Vue from 'vue'
import Router from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Logout from '../views/Logout.vue'
import Register from '../views/Register.vue'
import store from '../store/index'
import DoctorProfile from '../views/DoctorProfile.vue'
import PatientProfile from '../views/PatientProfile.vue'
import ViewOfficeInfo from '../views/ViewOfficeInfo.vue'
import UpdateOfficeInfo from '../views/UpdateOfficeInfo.vue'
import UpcomingAppointment from '../views/ViewUpcomingAppointment.vue'
import ViewAvailableDoctor from '../views/ViewAvailableDoctors.vue'
import Processing from '../views/Processing.vue'
import UpdateDocAvailabity from '../views/UpdateAvailability.vue'
import BookAppointmentForm from '../views/BookAnAppointment.vue';
import Reviews from '../views/Reviews.vue'

Vue.use(Router)

/**
 * The Vue Router is used to "direct" the browser to render a specific view component
 * inside of App.vue depending on the URL.
 *
 * It also is used to detect whether or not a route requires the user to have first authenticated.
 * If the user has not yet authenticated (and needs to) they are redirected to /login
 * If they have (or don't need to) they're allowed to go about their way.
 */

const router = new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/home',
      name: 'home',
      component: Home,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: '/',
      name: 'login',
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/login",
      name: "login",
      component: Login,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/logout",
      name: "logout",
      component: Logout,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/register",
      name: "register",
      component: Register,
      meta: {
        requiresAuth: false
      }
    },
    {
      path: "/processing",
      name: "processing",
      component: Processing,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/doctor-profile",
      name: "doctor-profile",
      component: DoctorProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/patient-profile",
      name: "patient-profile",
      component: PatientProfile,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/view-office-info/:id",
      name: "office-info",
      component: ViewOfficeInfo,
      meta: {
        requiresAuth: true
      }
    },
    
    {
      path: "/update-office-info",
      name: "update-office-info",
      component: UpdateOfficeInfo,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/doctor-portal",
      name: "upcoming-appointment",
      component: UpcomingAppointment,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/patient-portal",
      name: "view-available-doctors",
      component: ViewAvailableDoctor,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: "/schedule-availability",
      name: "schedule-availability",
      component: UpdateDocAvailabity,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/book-appointment',
      name: 'book-appointment',
      component: BookAppointmentForm,
      meta: {
        requiresAuth: true
      }
    },
    {
      path: '/reviews',
      name: 'reviews',
      component: Reviews,
      meta: {
        requiresAuth: true
      }
    }
  ]
})

router.beforeEach((to, from, next) => {
  // Determine if the route requires Authentication
  const requiresAuth = to.matched.some(x => x.meta.requiresAuth);

  // If it does and they are not logged in, send the user to "/login"
  if (requiresAuth && store.state.token === '') {
    next("/login");
  } else {
    // Else let them go to their next destination
    next();
  }
});

export default router;
