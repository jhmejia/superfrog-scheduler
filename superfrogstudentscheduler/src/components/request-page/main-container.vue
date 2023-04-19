
<template>
  <div class="request-page">
    <component  @update="saveData" :is="currentComponent !== '' ? currentComponent : defaultComponent"></component>
    <button @click="goBack" :disabled="currentComponentIndex === 0">Back</button>
    <button @click="goForward" :disabled="currentComponentIndex === components.length - 1">Forward</button>
    
  </div>
</template>

      
<script>
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
      ReceiptPage
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
            currentComponent: '',
            defaultComponent: DateTimePage,
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
              this.currentComponent = this.components[this.selectedComponentIndex].component;
            }
          },
          goBack() {
          if (this.selectedComponentIndex > 0) {
            this.selectedComponentIndex--;
            this.currentComponent = this.components[this.selectedComponentIndex].component;
            }
          },
          
          saveData(data) {
            this.firstName = data.firstName;
            this.lastName = data.lastName;
            this.email = data.email;
            this.phone = data.phone;
            this.address = data.address;
            this.nameOfOrg = data.nameOfOrg;
            this.eventTitle = data.eventTitle;
            this.eventDescription = data.eventDescription;
            this.specialInstructions = data.specialInstructions;
            this.outSideOrgs = data.outSideOrgs;
            this.expensesBenefits = data.expensesBenefits;
          }
          ,
          watch: {
              DetailFormPage: function(newVal, oldVal) {
                this.firstName = newVal.firstName
                this.lastName = newVal.lastName
                this.email = newVal.email
                this.phone = newVal.phone
                this.address = newVal.address
                this.nameOfOrg = newVal.nameOfOrg
                this.eventTitle = newVal.eventTitle
                this.eventDescription = newVal.eventDescription
                this.specialInstructions = newVal.specialInstructions
                this.outSideOrgs = newVal.outSideOrgs
                this.expensesBenefits = newVal.expensesBenefits
              
              }
          }

        }
}
</script>

<style scoped>
.request-page {
  background-color: purple;
  color: white;
}
</style>
