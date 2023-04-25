<template>
    <div>
     
      <span class="date">
        <div class="title">Review Your Order</div>
        <table>
          <tr>
            <td>Date</td>
            <td>{{eventInfo.scheduleDate}}</td>
            
          </tr>
          <tr>
            <td>Start Time</td>
            <td>{{eventInfo.startTime}}</td>
          </tr>
          <tr>
            <td>End Time</td>
            <td>{{eventInfo.endTime}}</td>
          </tr>
        </table>
      </span>
      <span class="contact-info-1">
        <div class="title">Personal Information</div>
        <table>
          <tr>
            <td>Name</td>
            <td>{{eventInfo.contactFirstName + " " + eventInfo.contactLastName}}</td>
          </tr>

          <tr>
            <td>Email</td>
            <td>{{eventInfo.email}}</td>
          </tr>
          <tr>
            <td>Phone Number</td>
            <td>{{eventInfo.phoneNumber}}</td>
          </tr>
        </table>
      </span>
      <span class="event-info-1">
        <div class="title">Event Information</div>
        <table>
          <tr>
            <td>Address of Appearance</td>
            <td>{{eventInfo.addressOfAppearance}}</td>
          </tr>
          <tr>
            <td>Name of Organization</td>
            <td>{{eventInfo.nameOfOrganization}}</td>
          </tr>
          <tr>
            <td>Event Type</td>
            <td>{{eventInfo.eventType}}</td>
          </tr>
          <tr>
            <td>Event Description</td>
            <td>{{eventInfo.eventDescription}}</td>
          </tr>
          <tr>
            <td>Special Instructions</td>
            <td>{{eventInfo.specialInstructions}}</td>
          </tr>
          <tr>
            <td>Outside Organizations</td>
            <td>{{eventInfo.outsideOrganizations}}</td>
          </tr>
          

        </table>
      </span>
      <span class="cost-1">
        <div class="title">Total Cost</div>
        <table>
          <tr>
            <td>Event type x hours</td>
          </tr>
          <tr>
           
            <td>Totalcost = {{getTotalPrice(eventInfo.startTime,eventInfo.endTime, eventInfo.eventType)}}</td>
          </tr>
        </table>
      </span>
    </div>
  </template>
  
  <script>
import { computed } from '@vue/reactivity';

  export default {
    name: 'ReviewSubPage',
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
          expensesBenefits: this.eventInfo.expensesBenefits,
          scheduleDate: this.eventInfo.scheduleDate,
          startTime: this.eventInfo.startTime,
          endTime: this.eventInfo.endTime,
          eventType: this.eventInfo.eventType,
          totalCost: this.eventInfo.totalCost,
          
    }

    },
   
    methods: {
      getTotalPrice(startTime, endTime,type) {

          let typeCoef = 0

          if(type == "TCU") typeCoef = 100
          else if(type == "Public") typeCoef = 100
          else if(type == "Private") typeCoef = 175
          
          const start = new Date(`2000-01-01T${startTime.replace(/(\d{1,2}):(\d{2}) ([AP]M)/, function(match, hour, minute, period) {
            return `${hour % 12 + (period.toUpperCase() === 'PM' ? 12 : 0)}:${minute}:00`;
          })}`);

          const end = new Date(`2000-01-01T${endTime.replace(/(\d{1,2}):(\d{2}) ([AP]M)/, function(match, hour, minute, period) {
            return `${hour % 12 + (period.toUpperCase() === 'PM' ? 12 : 0)}:${minute}:00`;
          })}`);
          let difference = end.getTime() - start.getTime();
          console.log(difference)
         
          if (end.getTime() < start.getTime()) {
            difference += 12 * 60 * 60 * 1000;
          }
          
          const hours = difference / (60 * 60 * 1000);

          return parseFloat(hours * typeCoef);
}
    }
  
   
}
  </script>

  <style scoped>
.contact-info-1{
    border-radius: 5px;
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
}
table{
  margin-bottom: 1%;
}

td{
  border-color: lightgray;
  background-color: white;
  color: grey;
  font-size: 1rem;
  text-align: center;
  padding: 2.5px;
}
.title{
  font-size: 2rem;
  margin: 1%;
  text-align: center;

}
.event-info-1 table> tr > td:nth-child(2){
  width:20vw;
}

.contact-info-1 table> tr > td:nth-child(2){
  width:15vw;
}

.date table> tr > td:nth-child(2){
  width:15vw;
}
.event-info-1{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
}

.date{
    display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
}
.cost-1 {
  display: flex;
    flex-direction: column;
    flex-wrap: nowrap;
    align-items: center;
    padding: 1vw;
}

.cost-1 > table > tr > td{
  width: 15vw;
  height: 5vh;
  text-align: center;
}
</style>