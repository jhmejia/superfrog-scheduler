<template>
  <div>
    <form @submit.prevent="saveChanges">
      <div>
        <label for="firstName">First Name</label>
        <input type="text" v-model="superFrog.firstName" id="firstName" />
      </div>
      <div>
        <label for="lastName">Last Name</label>
        <input type="text" v-model="superFrog.lastName" id="lastName" />
      </div>
      <div>
        <label for="address">Address</label>
        <input type="address" v-model="superFrog.address" id="address" />
      </div>
      <div>
        <label for="phoneNumber">Phone Number</label>
        <input type="text" v-model="superFrog.phoneNumber" id="phoneNumber" />
      </div>
      <!-- <div>
          <label for="status">Active Status:</label>
          <select id="status" v-model="superFrog.active">
            <option value="true">Active</option>
            <option value="false">Inactive</option>
          </select>
        </div> -->
      <button type="submit">Save Changes</button>
    </form>
    <p v-if="successMessage">{{ successMessage }}</p>
    <p v-if="superfrogSuccessMessage">{{ superfrogSuccessMessage }}</p>
  </div>
</template>
  
<script>
import axios from "axios";

export default {
  name: "EditProfile",
  data() {
    return {
      superFrog: {},
      successMessage: null,
    };
  },
  mounted() {
    this.getSuperFrog();
  },
  methods: {
    getSuperFrog() {
      const token = localStorage.getItem("token");
      const headers = {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      };
      const email = localStorage.getItem("superfrogEmail");

      axios
        .get(`http://api.superfrogscheduler.xyz:8080/api/superfrogstudents?email=${email}`, {
          headers,
        })
        .then((response) => {
          this.superFrog = response.data.data[0];
        })
        .catch((error) => {
          console.log(error);
        });
    },

    saveChanges() {
      const token = localStorage.getItem("token");
      const headers = {
        Authorization: `Bearer ${token}`,
        "Content-Type": "application/json",
      };
      const id = this.superFrog.id;
      axios
        .put(`http://api.superfrogscheduler.xyz:8080/api/superfrogstudents/${id}`, this.superFrog, {
          headers,
        })
        .then((response) => {
          console.log(response.data.data);
          this.successMessage = "Superfrog student information saved successfully!";
        })
        .catch((error) => {
          console.log(error);
          this.successMessage =
            "Failed to save Superfrog student information - please try again";
        });
    },
  },
};
</script>
<style scoped>
form {
  display: flex;
  flex-direction: column;
  align-items: left;
}

div {
  display: flex;
  flex-direction: column;
  margin: 10px;
}

label {
  margin-bottom: 5px;
}

input[type="text"],
input[type="address"],
select {
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  border: 1px solid #ccc;
  width: 100%;
  box-sizing: border-box;
}

button {
  padding: 10px;
  font-size: 16px;
  border-radius: 5px;
  background-color: #4d2279;
  color: #fff;
  border: none;
  width: 100%;
  margin-top: 10px;
  box-sizing: border-box;
}

@media only screen and (min-width: 600px) {
  form {
    max-width: 500px;
  }
}
</style>