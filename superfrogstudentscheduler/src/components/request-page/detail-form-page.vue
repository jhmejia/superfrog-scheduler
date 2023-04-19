<template>
    <div class="container">
      <div class="date"></div>
      <div class="contact-info">
        <span class="title">Personal Contact</span>
        <div>
          <span>Contact First Name</span>
          <span><input type="text" v-model="firstName" @input="updateParent"></span>
        </div>
      <div>
        <span>Contact Last Name</span>
        <span><input type="text" v-model="lastName"></span>
      </div>
    </div>
      
      
    </div>
  </template>
  
  <script>
  export default {
    name: 'DetailFormPage',
    data(){
      return{
        firstName: "",
        lastName: "",
        email: "",
        phone: "",
        address: "",
        nameOfOrg: "",
        eventTitle: "",
        eventDescription: "",
        specialInstructions: "",
        outSideOrgs:"",
        expensesBenefits: "",

      }
      

    },
    methods: {
      updateParent() {
      this.$emit('update', {
        firstName: this.firstName,
          
        });
    }
  }
  }
  </script>