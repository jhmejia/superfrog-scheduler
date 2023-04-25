<template class="main">
  <div class="date">
    Pick Date:
  <VueDatePicker
    :enable-time-picker="false"
    v-model="scheduleDate"
    :format="formatDate"
    @closed="updateDate"
    
  />
</div>
Pick Starting Time:
<div class="time">
  <VueDatePicker
    v-model="startTime"
    time-picker
    :format="formatS"
    @closed="updateStart"
    :is-24="false"
    
    />
Pick Ending Time:
    <VueDatePicker
    v-model="endTime"
    time-picker
    :format='formatE'
    @closed="updateEnd"
    :is-24="false"
    />
  </div>
</template>
  
  <script>

 
  import VueDatePicker from '@vuepic/vue-datepicker';
  import '@vuepic/vue-datepicker/dist/main.css'
import { format } from 'date-fns';
  import { ref } from 'vue';

  export default {
    name: 'DateTimePage',
    data() {
      return {
        startTime: ref(new Date()),
        endTime: ref(new Date()),
        scheduleDate: ref(new Date()),
        
      }
    },
    computed:{
      
    },
    props: {
      eventInfo: {
        type: Object
      }
      
    },
    components: {
      VueDatePicker

    },
    methods: {
      updateParent() {
      this.$emit('update', {
        eventInfo: this.eventInfo,
        })},
        updateStart(){
          this.eventInfo.startTime = this.startTime;
          this.updateParent();
        },
        updateEnd(){
          this.eventInfo.endTime = this.endTime;
          this.updateParent();
        },
        updateDate(){
          this.eventInfo.scheduleDate = this.scheduleDate;
          this.updateParent();
        },
        formatS(date) {
          this.eventInfo.startTime = format(date, 'hh:mm a');
          return format(date, 'hh:mm a');
        },
        formatE(date) {
          this.eventInfo.endTime = format(date, 'hh:mm a');
          return format(date, 'hh:mm a');
          },
        formatDate(date) {
          this.eventInfo.scheduleDate = format(date, 'MM/dd/yyyy');
          return format(date, 'MM/dd/yyyy');
        },
        }
  }

    
  </script>
