

<template>
    <div class="input-id" v-if="!requests">
        <span>Enter the ID of the request you wish to modify</span>
        <input v-model.lazy="requestId" v-bind:placeholder="message" >
        <button class="button is-primary submit-button"  @click="getrequests(requestId)">Submit</button>
        <span v-if=message>{{ message }}</span>

    </div>
    <div class="request-page" v-if="requests">
      <div class="content-container">
        <!-- <h3>Select Date and Time:</h3> -->
        <component :requestId="requestId" :is="currentComponent !== '' ? currentComponent : defaultComponent"></component>
        
        <router-link to="/">
          <button class="button is-primary home-button">Home</button>
        </router-link>
        
      </div>
    </div>
  </template>
  
        
  <script>
     import DetailFormPage from './detail-form-page.vue'
     import axios from 'axios'
        
    export default {
      name: 'ModifyPage',
      components: {
       
        DetailFormPage,
      
          },
          data() {
            return {
              components: [
              //  { name: 'Date Time', component: DateTimePage },
                { name: 'Detail Form', component: DetailFormPage },
              ],
              selectedComponentIndex: 0,
              currentStep: 0,
              currentComponent: '',
              defaultComponent: DetailFormPage,
              requests: undefined,
              requestId: undefined ,
              message: undefined,
              }
                
            },
        
        
          computed: {
                selectedComponent() {
                    if (this.selectedComponentIndex !== null) {
                      return this.components[this.selectedComponentIndex].component
                    } else {
                    return null
                  }
                },
          },
          
          methods: {
            selectComponent(index) {
                this.selectedComponentIndex = index;
                
            },
            
            sendData(data) {
              this.requests = data;
            
            },
            submit() {
              if (this.selectedComponentIndex < this.components.length - 1) {
                this.selectedComponentIndex++;
                this.currentStep++;
                this.currentComponent = this.components[this.selectedComponentIndex].component;
                
              }
              const token=localStorage.getItem('token');
              const headers = {
                'Content-Type': 'application/json'
             }
              axios.put(`http://api.superfrogscheduler.xyz:8080/api/superfrogappearancerequests/${this.requestId}`, {
                    contactFirstName: this.requests.contactFirstName,
                    contactLastName: this.requests.contactLastName,
                    email: this.requests.email,
                    phoneNumber: this.requests.phoneNumber,
                    address: this.requests.addressOfAppearance,
                    nameOfOrg: this.requests.nameOfOrg,
                    title: this.requests.title,
                    description: this.requests.description,
                    specialInstructions: this.requests.specialInstructions,
                    outsideOrgs: this.requests.outsideOrgs,
                    expenses: this.requests.expenses,
                    eventDate: this.requests.scheduleDate,
                    startTime: this.requests.startTime,
                    endTime: this.requests.endTime,
                    eventType: this.requests.eventType,
                    totalCost: this.requests.totalCost,
               }, {headers})
               .then(response =>{
                    const data = (response.data);
                    console.log(data.data.requestId);
                    this.successMessage = 'Student added successfully!';
                    this.requests.reciptId = data.data.requestId;
                  }).catch(error=>{
                    console.log(error);
            })
          },
          getrequests(requestId)
          {
            console.log(requestId)
            axios.get(`http://api.superfrogscheduler.xyz:8080/api/superfrogappearancerequests/${requestId}`)
           .then((response)  => {
              this.requests = response.data.data;
              console.log(this.requests)
            }).catch((error) => {
                console.log(error.response.status)
                if (error.response.status === 404) {
                    this.message = "the request id does not exist"
                }
            })
          }
        },
          
  
   }
    
  
  </script>
  
  <style scoped>
  .input-id {
    display: flex;
    flex-direction: column;
    align-items: center;
    justify-content: center;
    margin-top: 10vh;

    color: white;
  }
  .input-id > * {
    padding: 2vh;
    font-size: 1.2em;
  }
 
  .request-page {
    background-color: #4d2279;
    color: white;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  
  .content-container {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .home-button {
    font-size: 20px;
    padding: 10px 0px;
  }
  
  button.button.is-primary {
    width: 120px;
  }
  

  

  .submit-button {
    font-size: 20px;
    padding: 10px 0px;
  }
  
  .button-container {
    display: flex;
    justify-content: center;
    align-items: center;
    margin-top: 30px;
  }
  </style>