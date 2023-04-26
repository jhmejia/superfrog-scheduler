<template>
    <form> <!-- First name last name, id-->
        <label for="fname" >First name:</label><br>
        <input type="text" id="fname" name="fname" v-model="fname"><br>
        <label for="lname">Last name:</label><br>
        <input type="text" id="lname" name="lname" v-model="lname"><br>
        <label for="phoneNumber">Phone Number:</label><br>
        <input type="text" id="phoneNumber" name="phoneNumber" v-model="phoneNumber"><br>
        <label for="email">email:</label><br>
        <input type="text" id="email" name="email" v-model="email"><br>
        <label for="address">Physical Address:</label><br>
        <input type="text" id="address" name="address" v-model="address"><br>
        <input type="submit" value="Submit" @click="addStudent">
    </form>

</template>

<script>



export default {
    name: "AddStudent",
    data() {
        return {
            fname: "",
            lname: "",
            phoneNumber: "",
            email: "",
            address: "",
        };
    },
    methods: {
        addStudent() {
            const headers={
              'Authorization': 'Bearer ${token}',
              'Content-Type': 'application/json'
            }
            axios.post("localhost:8080/api/superfrogstudents", {
                 fname: this.fname,
                 lname: this.lname,
                 phoneNumber: this.phoneNumber,
                 email: this.email,
                 address: this.address,
             }, {headers})
                .then(response=>{
                  console.log(response.data());
                }).catch(error=>{
                  console.log(error);
          })

        },
    },
}

</script>