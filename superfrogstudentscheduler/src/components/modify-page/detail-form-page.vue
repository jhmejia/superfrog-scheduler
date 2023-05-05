<template>
  <div class="container" v-show="requests">
    <div class="date-1">
      <table class="date-table">
        <tr>
          <td>Date</td>
          <td><VueDatePicker
            
            :enable-time-picker="false"
            v-model="requests.eventDate"
            :model-value="requests.eventDate"
            model-type="yyyy-MM-dd"
            


          /></td>
        </tr>
        <tr>
          <td>Start Time</td>
          <td><VueDatePicker
            :max-time="{ hours: 11, minutes: 0, a: 'PM' }"
            :min-time="{ hours: 7, minutes: 0 }"
              :model-value="requests.startTime"
              model-type="HH:mm:ss"
              time-picker
              :is-24="false"
              :start-time="requests.startTime"

    
    /></td>
        </tr>
        <tr>
          <td>End Time</td>
          <td><VueDatePicker
            :max-time="{ hours: 11, minutes: 0, a: 'PM' }"
            :min-time="{ hours: 7, minutes: 0,  }"
                v-model="requests.endTime"
                model-type="HH:mm:ss"
                time-picker
                :is-24="false"
                :start-time="requests.endTime"

    
    /></td>
        </tr>
      </table>
      <div class="dropdown-box">
      <label for="dropdown">Event Type</label>
        <select id="dropdown" v-model="requests.eventType" >
          <option value="TCU">TCU</option>
          <option value="PUBLIC">Public/Non-profit</option>
          <option value="PRIVATE">Private/Residential</option>
        </select>
        </div>
      </div>
      <div class="title-container">
            <span class="title-1">Personal Contact</span>
        </div>
        <div class="contact-info">
          
            <div>
              <span>Contact First Name</span>
              <input type="text" class="normal-text" v-model="requests.contactFirstName" >
            </div>
            <div>
              <span>Contact Last Name</span>
              <input type="text" class="normal-text" v-model="requests.contactLastName">
            </div>
            <div>
              <span>Email</span>
              <input type="text" class="normal-text" v-model="requests.email">
            </div>
            <div>
              <span>Phone Number</span>
              <input placeholder="(xxx)-xxx-xxxx" type="text" class="normal-text" v-model="requests.phoneNumber" >
            </div>
          
        </div>
  <div class="title-container">
  <span class="title-1">Event Information</span>
  </div>
  <div class="event-1">
    <div>
      <span>Event Titie</span>
      <span><input type="text" class="normal-text" v-model="requests.title" ></span>
    </div>
    <div>
      <span>Name of Organization</span>
      <span><input type="text" class="normal-text" v-model="requests.nameOfOrg" ></span>
    </div>
    <div>
      <span style="display: inline;">Address of Appearance</span>
      <span><input placeholder="(street, suite/room/floor, city, state, postal code" type="text" class="normal-text" v-model="requests.address" ></span>
    </div>
  </div>
    
  
  <div class="event-2">
    <div>
      <span>Event Description</span>
      <span><input type="text" class="large-text" v-model="requests.description" ></span>
    </div>
  <div>
      <span>Special Instructions</span>
      <span><input type="text" class="large-text" v-model="requests.specialInstructions"></span>
  </div>
  <div>
      <span>List any other outside organizations involved in sponsoring the event</span>
      <span><input type="text" class="large-text" v-model="requests.outsideOrgs" ></span>
  </div>
  <div>
      <span>Describe any expensive or benefits to the spirit team members</span>
      <span><input type="text" class="large-text" v-model="requests.expenses" ></span>
  </div>
  </div>
</div>
<div v-if="responseStatus == 200">{{ returnMsg }}</div>
    <!--For the other css components when you add the input boxes just follow the two as above-->
<div class="button-container">
  <button class="button is-primary submit-button" @click="submit">Submit</button>
  <button class="button is-primary submit-button" @click="cancel">Cancel</button>

</div>
</template>

<script>
import VueDatePicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'
import axios from 'axios';
import { isProxy, reactive, toRaw } from "vue";

export default {
  name: 'DetailFormPage',
  props: {
    requestId: String,
  },
  data(){
    return{
      requests: Object,
      responseStatus: undefined,
      ogReq: Object
        
    }
    

  },
  components: {
    VueDatePicker

  },
  methods: {
    findDifferentVars(obj1, obj2) {
      let differentVars = [];

      const rawObj1 = isProxy(obj1) ? toRaw(obj1) : obj1;
      const rawObj2 = isProxy(obj2) ? toRaw(obj2) : obj2;

      const obj1Keys = Object.keys(rawObj1);
      const obj2Keys = Object.keys(rawObj2);

      obj1Keys.forEach((key) => {
        if (rawObj1[key] !== rawObj2[key]) {
          differentVars.push(key);
        }
      });

return differentVars;
    },
    cancel() {

      const headers = {
                'Content-Type': 'application/json'
             }
              axios.put(`http://localhost:8080/api/superfrogappearancerequests/${this.requestId}`, {
                    contactFirstName: this.requests.contactFirstName,
                    contactLastName: this.requests.contactLastName,
                    email: this.requests.email,
                    phoneNumber: this.requests.phoneNumber,
                    address: this.requests.address,
                    nameOfOrg: this.requests.nameOfOrg,
                    title: this.requests.title,
                    description: this.requests.description,
                    specialInstructions: this.requests.specialInstructions,
                    outsideOrgs: this.requests.outsideOrgs,
                    expenses: this.requests.expenses,
                    eventDate: this.requests.eventDate,
                    startTime: this.requests.startTime,
                    endTime: this.requests.endTime,
                    eventType: this.requests.eventType,
                    totalCost: this.requests.totalCost,
                    status: "CANCELED"
               }, {headers})
               .then(response =>{
                    const data = (response.data);
                    console.log(data.data.requestId);
                    this.requests.requestId = data.data.requestId;
                    this.returnMsg ="Request has been canceled"
                    this.responseStatus = response.status;
                  }).catch(error=>{
                    console.log(error);
            })

    },
    
    submit() {
      let containsCommonElement = false
      let differentVars = this.findDifferentVars(this.$data.requests, this.$data.ogReq)
      const pendingVars = ['startTime', 'endTime', 'eventDate', 'eventType', 'title', 'address', 'description', 'outsideOrgs', 'expenses', 'nameOfOrg', ]
      pendingVars.forEach((key) => {
        if (differentVars.includes(key)) {
          containsCommonElement = true
        }
      });

      console.log(differentVars);

      const headers = {
                'Content-Type': 'application/json'
             }
              axios.put(`http://localhost:8080/api/superfrogappearancerequests/${this.requestId}`, {
                    contactFirstName: this.requests.contactFirstName,
                    contactLastName: this.requests.contactLastName,
                    email: this.requests.email,
                    phoneNumber: this.requests.phoneNumber,
                    address: this.requests.address,
                    nameOfOrg: this.requests.nameOfOrg,
                    title: this.requests.title,
                    description: this.requests.description,
                    specialInstructions: this.requests.specialInstructions,
                    outsideOrgs: this.requests.outsideOrgs,
                    expenses: this.requests.expenses,
                    eventDate: this.requests.eventDate,
                    startTime: this.requests.startTime,
                    endTime: this.requests.endTime,
                    eventType: this.requests.eventType,
                    totalCost: this.requests.totalCost,
                    status: this.requests.status
               }, {headers})
               .then(response =>{
                    const data = (response.data);
                    console.log(data.data.requestId);
                    this.requests.requestId = data.data.requestId;
                    this.responseStatus = response.status;
                    this.returnMsg = "Request Updated"

                  }).catch(error=>{
                    console.log(error);
            })
          },
  
},
beforeMount(){
   axios.get(`http://localhost:8080/api/superfrogappearancerequests/${this.requestId}`)
           .then((response)  => {
            
              this.requests = response.data.data;
              this.ogReq = {...response.data.data};
              console.log(this.ogReq)
              console.log(this.requests)
            
            }).catch((error) => {
              console.log(error)

            })
}
}

</script>

<style scoped>
.container {

width: 90vw;
margin: 0 auto;
padding: 20px;
font-family: sans-serif;
}

.date-1 {
font-size: 1.2em;
font-weight: bold;
margin-bottom: 10px;
display: flex;
justify-content: space-evenly;
}
.date-1 > div {
flex-basis: 40%;
position: relative;
align-self: center;
text-align: center;
}

.date-1 > table{
flex-basis: content;
}
.date-table{
background-color: white;
color:lightslategray;
padding-left: 5%;
padding-right: 5%;
border: 1px solid black;
}

.event-1 {
font-size: 1.2em;
font-weight: bold;
margin-bottom: 10px;
display: flex;
flex-direction: row;
flex-wrap: wrap;
text-align: center;
margin-bottom: 1vh;
}

.event-1 > div > span{
text-align: left;
}
.event-1 > div{

flex-basis: 50%;
display: grid;
justify-content: space-around;
align-content: stretch;
align-items: center;
margin-bottom: 2vh;


}
.event-1 > div:nth-child(3)
{
flex-basis: 100%;
justify-content: center;
}


.contact-info {
font-size: 1.2em;
font-weight: bold;
margin-bottom: 2vh;
display: flex;
flex-direction: row;
flex-wrap: wrap;
justify-content: space-between;


}

.title-container {
display: flex;
justify-content: center;
margin-bottom: 1vh;
}


.contact-info > div{
flex-basis: 50%;
width: 22rem;
display: flex;
align-items: center;
flex-wrap: wrap;
flex-direction: column;
}

.contact-info > div > span{
width: 22rem;
position: relative;
}


.title-1 {
font-size: 1.5em;
font-weight: bold;
margin-bottom: 10px;
display: block;
align-items: center;
}



.normal-text{
width: 21rem;
height: 1rem;
padding: 5px;
border: 1px solid #ccc;
position: relative;

}

select{
height: 20px;
}
.large-text{
height: 8rem;
width: 22rem;
}

.event-2{
font-size: 1.2em;
font-weight: bold;
margin-bottom: 10px;
display: flex;
flex-direction: row;
flex-wrap: wrap;
justify-content: space-between;
}

.event-2 > div{
  
flex-basis: 50%;
width: 22rem;
display: flex;
align-items: center;
flex-wrap: wrap;
flex-direction: column;
margin-bottom: 2vh;
}

.event-2 > div > span{
width: 22rem;
position: relative;
}



.button-container {
    display: flex;
    justify-content: center;
    align-items: center;

  }
  

</style>