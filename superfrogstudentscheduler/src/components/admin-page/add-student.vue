<template>
  <form @submit.prevent="addStudent"> <!-- First name last name, id-->
        <label for="fname" >First name:</label><br>
        <input type="text" id="fname" name="fname" v-model="fname"><br>
        <label for="lname">Last name:</label><br>
        <input type="text" id="lname" name="lname" v-model="lname"><br>
        <label for="phoneNumber">Phone Number:</label><br>
        <input type="text" id="phoneNumber" name="phoneNumber" v-model="phoneNumber" pattern="^[0-9]{10}$"><br>
        <label for="email">email:</label><br>
        <input type="email" id="email" name="email" v-model="email"><br>
        <label for="address">Physical Address:</label><br>
        <input type="text" id="address" name="address" v-model="address"><br>
        <input type="submit" value="Submit">
    </form>

    <p v-if="successMessage">{{ successMessage }}</p>
</template>

<script>
import axios from 'axios';


export default {
    name: "AddStudent",
    data() {
        return {
            fname: "",
            lname: "",
            phoneNumber: "",
            email: "",
            address: "",
            successMessage: null,
        };
    },
    methods: {
      addStudent() {
        const token=localStorage.getItem('token');
        const headers = {
          'Authorization': `Bearer ${token}`,
          'Content-Type': 'application/json'
        }

        let isDuplicate = false;

        // Check if the email already exists
        axios
          .get(`http://localhost:8080/api/superfrogstudents/email/${this.email}`, {headers})
          .then((response) => {
            isDuplicate = true;
            this.successMessage = 'Add Student Failed! Email already in use';
          })
          .catch(error => {
            console.log(error);
          })
          .finally(() => {
            // If the email doesn't exist, create a new student
            if (!isDuplicate) {
              axios.post("http://localhost:8080/api/superfrogstudents", {
                firstName: this.fname,
                lastName: this.lname,
                phoneNumber: this.phoneNumber,
                email: this.email,
                address: this.address,
                }, {headers})
                .then(response=>{
                  console.log(response.data);
                  this.successMessage = 'Student added successfully! Default password is superfrogstudent';
                })
                .catch(error=>{
                  console.log(error);
                  this.successMessage = 'Student not added successfully- please try again';
                })
            }
          })
      },

  },
}

</script>