<template>
    <div>
      <span class="title">Review your order</span>
      <span class="date">
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
      <hr>
      <span class="contact-info-1">
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
        <hr>
      </span>
      <span class="event-info-1">
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
        <hr>
      </span>
      <span class="cost-1">
        <table>
          <tr>
            <td>Superfrog cost x hours</td>
          </tr>
          <tr>
           
            <td>Totalcost = {{eventInfo.expensesBenefits}}</td>
          </tr>
        </table>
      </span>
    </div>
  </template>
  
  <script>
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
      
    }
  
   
}
  </script>