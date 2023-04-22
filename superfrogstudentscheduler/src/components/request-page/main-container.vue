<template>
  <div class="request-page">
    
      <step-progress :steps="steps" :current-step="currentStep" icon-class="fa fa-check" active-color="#32CD32

" passive-color="white"></step-progress>

    <component  :eventInfo="eventInfo" :is="currentComponent !== '' ? currentComponent : defaultComponent"></component>
    <button @click="goBack" :disabled="selectedComponentIndex === 0">Back</button>
    <button v-if="selectedComponentIndex != components.length - 2" @click="goForward">Forward</button>
    <button v-if="selectedComponentIndex === components.length - 2" @click="submit">Submit</button>
    
    <router-link to="/">
        <button class="button is-primary home-button">Home</button>
      </router-link>
  </div>
</template>

      
<script>
   import StepProgress from 'vue-step-progress';
   import 'vue-step-progress/dist/main.css';
   import DateTimePage from './date-time-page.vue'
   import DetailFormPage from './detail-form-page.vue'
   import PolicyPage from './policy-page.vue'
   import ReviewSubPage from './review-sub-page.vue'
   import ReceiptPage from './receipt.vue'
      
  export default {
    name: 'RequestPage',
    components: {
      DateTimePage,
      DetailFormPage,
      PolicyPage,
      ReviewSubPage,
      ReceiptPage,
      'StepProgress': StepProgress
        },
        data() {
          return {
            components: [
              { name: 'Date Time', component: DateTimePage },
              { name: 'Detail Form', component: DetailFormPage },
              { name: 'Policy Page', component: PolicyPage },
              { name: 'Review Sub', component: ReviewSubPage },
              { name: 'Receipt', component: ReceiptPage}
            ],
            selectedComponentIndex: 0,
            currentStep: 0,
            currentComponent: '',
            defaultComponent: DateTimePage,
            eventInfo : {
              contactFirstName: "",
              contactLastName: "",
              email: "",
              phoneNumber: "",
              addressOfAppearance: "",
              nameOfOrganization: "",
              eventTitle: "",
              eventDescription: "",
              specialInstructions: "",
              outsideOrganizations:"",
              expensesBenefits: "",
              scheduleDate: "",
              startTime: "",
              endTime: "",
              eventType: "",
              totalCost: 0.00,
            },
            steps: [
              'Select Date and Time',
              'Complete Form',
              'Policy Agreement',
              'Review Submission',
            ],
            


           
            
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
          goForward() {
            if (this.selectedComponentIndex < this.components.length - 1) {
              this.selectedComponentIndex++;
              this.currentStep++;
              this.currentComponent = this.components[this.selectedComponentIndex].component;
              
            }
          },
          goBack() {
          if (this.selectedComponentIndex > 0) {
            this.selectedComponentIndex--;
            this.currentStep--;
            this.currentComponent = this.components[this.selectedComponentIndex].component;
            
            }
          },
          sendData(data) {
            this.eventInfo = data;
          
          },
          submit() {
            if (this.selectedComponentIndex < this.components.length - 1) {
              this.selectedComponentIndex++;
              this.currentStep++;
              this.currentComponent = this.components[this.selectedComponentIndex].component;
              
            }
            //Send data to backend/generate id and recipt
          }
        },
        

 }
  

</script>

<style scoped>
.request-page {
  background-color: #4d2279;
  color: white;
  width: 100vw;
  height: 100vh;
}
.home-button {
  font-size: 20px;
  padding: 10px 0px;
}
button.button.is-primary {
  width: 100px; /* adjust this value to your desired width */
}
</style>
