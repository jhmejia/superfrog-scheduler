<template>
    <div class="container">
      <div class="date-1"></div>
      <div class="contact-info">
        <span class="title-1">Personal Contact</span>
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

<style scoped>
.container {
  max-width: 600px;
  margin: 0 auto;
  padding: 20px;
  font-family: sans-serif;
}

.date-1 {
  font-size: 1.2em;
  font-weight: bold;
  margin-bottom: 10px;
}

.contact-info {
  border: 1px solid #ccc;
  padding: 10px;
}

.title-1 {
  font-size: 1.2em;
  font-weight: bold;
  margin-bottom: 10px;
  display: block;
}

div > span {
  display: inline-block;
  width: 200px;
  margin-bottom: 10px;
  font-weight: bold;
}

div > span input[type="text"] {
  width: 185px;
  padding: 5px;
  border: 1px solid #ccc;
}

</style>