<template>
    <div class="container">
      <div class="date"></div>
      <div class="contact-info">
        <span class="title">Personal Contact</span>
        <div>
          <span>Contact First Name</span>
          <span><input type="text" v-model="eventInfo.contactFirstName" @input="updateParent"></span>
        </div>
      <div>
        <span>Contact Last Name</span>
        <span><input type="text" v-model="eventInfo.contactLastName" @input="updateParent"></span> <!--For the other css components when you -->
      </div>
    </div>
      <!--For the other css components when you add the input boxes just follow the two as above-->
      
    </div>
  </template>
  
  <script>
  export default {
    name: 'DetailFormPage',
    props: {
      eventInfo: {
        type: Object
      }
    },
    data(){
      return{
          contactFirstName: this.eventInfo.contactFirstName,
          contactLastName: this.eventInfo.contactLastName,
          email: this.eventInfo.email,
          phoneNumber: this.eventInfo.phoneNumber,
          addressOfAppearance: this.eventInfo.addressOfAppearance,
          nameOfOrganization: this.eventInfo.nameOfOrganization,
          eventTitle: this.eventInfo.eventTitle,
          eventDescription: this.eventInfo.eventDescription,
          specialInstructions: this.eventInfo.specialInstructions,
          outsideOrganizations: this.eventInfo.outsideOrganizations,
          expensesBenefits: this.eventInfo.expensesBenefits
      }
      

    },
    methods: {
      updateParent() {
      this.$emit('update', {
        eventInfo: this.eventInfo,
          
        });
    }
  }
  }
  </script>