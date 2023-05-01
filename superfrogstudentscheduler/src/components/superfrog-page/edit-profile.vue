<!-- <template>
    <div>
        <form @submit.prevent="saveChanges">
            <div>
                <label for="firstName">First Name</label>
                <input type="text" v-model="superFrog.firstName" id="firstName" required />
            </div>
            <div>
                <label for="lastName">Last Name</label>
                <input type="text" v-model="superFrog.lastName" id="lastName" required />
            </div>
            <div>
                <label for="email">Email</label>
                <input type="email" v-model="superFrog.email" id="email" required />
            </div>
            <div>
                <label for="phoneNumber">Phone Number</label>
                <input type="text" v-model="superFrog.phoneNumber" id="phoneNumber" />
            </div>
            <div>
                <label for="status">Active Status:</label>
                <select id="status" v-model="superFrog.active">
                    <option value="true">Active</option>
                    <option value="false">Inactive</option>
                </select>
            </div>
            <button type="submit">Save Changes</button>
        </form>
    </div>
</template> -->
<template>
    <div>
      <form @submit.prevent="saveChanges">
        <div>
          <label for="firstName">First Name</label>
          <input type="text" v-model="superFrog.firstName" id="firstName" required />
        </div>
        <div>
          <label for="lastName">Last Name</label>
          <input type="text" v-model="superFrog.lastName" id="lastName" required />
        </div>
        <div>
          <label for="email">Email</label>
          <input type="email" v-model="superFrog.email" id="email" required />
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
    </div>
  </template>
  
<script>
import axios from "axios";

export default {
    name: "EditProfile",
    data() {
        return {
            superFrog: {},
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
                    // Redirect to profile page or show success message
                })
                .catch((error) => {
                    console.log(error);
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
input[type="email"],
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