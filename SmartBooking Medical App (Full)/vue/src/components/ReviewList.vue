<template>
  <div class="container">
    <div class="header">
      <nav v-show= isDoctor>
        <div class="nav-content">
          <div class="logo">
            <a href="#">
              <img src="../img/NavBarLogo.png" alt="Logo" />
            </a>
          </div>
          <div class="welcome-title">
            <h1>Reviews</h1>
          </div>
          <ul class="nav-links">
            <li>
              <router-link to="/doctor-portal">Home</router-link>
            </li>
            <li>
              <router-link v-bind:to="{ name: 'schedule-availability' }"
                >Update My Availability</router-link
              >
            </li>
            <li>
              <router-link to="/update-office-info"
                >Update Office Info</router-link
              >
            </li>
            <li>
              <router-link v-bind:to="{ name: 'doctor-profile' }"
                >My Doctor Profile</router-link
              >
            </li>
            <li><router-link to="/reviews">My Reviews</router-link></li>
          </ul>
        </div>
      </nav>
    </div>
    <div class="comment-wrapper pt--40">
      <div class="section-title">
        <h5 class="mb--25"></h5>
      </div>
      <div
        class="edu-comment"
        v-for="review in reviewList"
        :key="review.reviewId"
      >
        <div class="thumbnail">
          <img
            src="../img/patient.png"
            alt="Comment Images"
            class="patient-image"
          />
        </div>
        <div class="comment-content">
          <div class="comment-top">
            <h6 class="title">@username</h6>
            <div class="rating">
              Star Rating: {{ generateStarRating(review.reviewRating) }}
            </div>
          </div>
          
          <span class="subtitle">{{ review.reviewTitle }}</span>
            <p>{{ review.reviewContent }}</p>
                  <br>
          <button class="submit-button">Respond To Review</button>
        </div>
      </div>

<div v-show="isDoctor === false" >
  <div class="review-form">
        <h5 class="mb--25">Write a Review:</h5>
        <br>
        <form @submit.prevent="submitReview">
          <div class="form-group">
            <label>Rating:</label>
            <star-rating
              v-model="newReview.reviewRating"
              :star-size="20"
              :read-only="false"
            ></star-rating>
          </div>
          <div class="form-group">
            <label>Title:</label>
            <input v-model="newReview.reviewTitle" class="form-control" />
          </div>
          <div class="form-group">
            <label>Content:</label>
            <textarea
              v-model="newReview.reviewContent"
              class="form-control"
            ></textarea>
          </div>
          <button type="submit" class="btn btn-primary">Submit Review</button>
        </form>
  </div>
      </div>
    </div>
  </div>
</template>


<script>
import authService from "../services/AuthService";
import StarRating from "vue-star-rating"; // Import the vue-star-rating component

export default {
  name: "Reviews",
  components: {
    StarRating,
  },
  props: ["facilityId"],
  data() {
    return {
      newReview: {
        reviewRating: 0,
        reviewTitle: "",
        reviewContent: "",
      },

      reviewList: [],
      isDoctor: false,
      officeId: 0,
      reviewInfo: {
        reviewId: 0,
        reviewer: "",
        officeId: "",
        officeName: "",
        username: "",
        reviewRating: "",
        reviewTitle: "",
        reviewContent: "",
        reviewDate: "",
      },
    };
  },
  methods: {
    generateStarRating(rating) {
      const fullStars = Math.floor(rating);
      const halfStar = rating - fullStars >= 0.5;

      let starRating = "";
      for (let i = 0; i < fullStars; i++) {
        starRating += "★"; // Unicode star character
      }
      if (halfStar) {
        starRating += "½"; // Unicode half star character
      }
      return starRating;
    },

    submitReview() {
      console.log("Submitting review:", this.newReview);

      this.newReview = {
        reviewRating: 0,
        reviewTitle: "",
        reviewContent: "",
      };
    },
    getReviewList() {
      console.log("Is the user a doctor?", this.$store.state.isDoctor);
      if (this.$store.state.isDoctor === true) {
        console.log("doctor hit");
        authService.getOfficeInfoById(this.officeId).then((response) => {
          console.log("response info", response.data);
          this.officeId = response.data.id;
          authService.getOfficeDetail(this.officeId).then((response) => {
            if (response.status == 200) {
              authService
                .getReviewsByReviewedOffice(response.data.id)
                .then((response) => {
                  if (response.status == 200) {
                    this.reviewList = response.data;
                  }
                });
            }
          });
        });
      } else {
        console.log("not a doctor hit");
        console.log("isprops working: ", this.facilityId);
        this.officeId = this.facilityId;
        authService.getOfficeDetail(this.officeId).then((response) => {
          if (response.status == 200) {
            console.log(response.data);
            console.log("office Id is: ", response.data.id);
            this.officeId = response.data.id;
            authService
              .getReviewsByReviewedOffice(this.officeId)
              .then((response) => {
                this.reviewList = response.data;
              });
          }
        });
      }
    },
  },
  created() {
    this.isDoctor = this.$store.state.isDoctor;
    this.getReviewList();
  },
};
</script>

<style>
.container {
  display: flex;
  justify-content: start;
  flex-direction: column;
  align-items: center;
  height: 150vh;
}
.header {
  background-color: #a1de81;
  width: 100%;
  padding: 0px 0;
  box-shadow: 0px 2px 10px rgba(0, 0, 0, 0.1);
  border-radius: 5px;
}
.comment-wrapper {
  display: flex;
  flex-direction: column;
  align-items: center; /* Center horizontally */
  justify-content: center; /* Center vertically */
  margin-top: 30px;
  margin-right: 70vh;
  min-width: 100vh; /* Set a minimum height to fill the screen */

}

.edu-comment {
  display: flex;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  margin-bottom: 20px;
  width: 50vh;
  margin-left: 70vh;
}

.edu-comment .thumbnail {
  min-width: 70px;
  width: 70px;
  height: 10px;
  max-height: 90px;
  border-radius: 100%;
  margin-right: 25px;
  margin-top: 40px;
}

.edu-comment .thumbnail img {
  border-radius: 100%;
  width: 100%;
}

.edu-comment .comment-content {
  flex: 1;
}

.edu-comment .comment-content .comment-top {
  display: flex;
  align-items: center;
  margin-bottom: 10px;
}

.edu-comment .comment-content .title {
  font-weight: 700;
  font-size: 20px;
  line-height: 32px;
  margin-right: 15px;
}


.edu-comment .comment-content .subtitle {
  font-weight: 700;
  font-size: 16px;
  line-height: 26px;
  display: block;
  margin-bottom: 10px;
  color: #231f40;
}

.edu-comment .comment-content p {
  margin-top: 0;
  font-style: italic;
}


.review-form {
  margin-top: 30px;
  padding: 20px;
  background-color: #f5f5f5;
  border-radius: 8px;
  width: 50vh;
  margin-left: 70vh;
}

.review-form form {
  max-width: 400px;
  margin: 0 auto;
}

.review-form .form-group {
  margin-bottom: 15px;
}

.review-form label {
  font-weight: 600;
}

.review-form input,
.review-form textarea {
  width: 100%;
  padding: 10px;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.review-form button {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.review-form button:hover {
  background-color: #2980b9;
}
.submit-button {
  padding: 10px 20px;
  background-color: #3498db;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
}

.submit-button:hover {
  background-color: #2980b9;
}

.edu-comment .comment-content {
  flex: 1;
  display: flex;
  flex-direction: column;
}

.edu-comment .comment-content .comment-top {
  display: flex;
  align-items: center;
  justify-content: space-between;
  margin-bottom: 10px;
}
</style>
